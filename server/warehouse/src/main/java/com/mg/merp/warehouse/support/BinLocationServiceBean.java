/*
 * BinLocationServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */

package com.mg.merp.warehouse.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.BinLocationDetailData;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.BinLocationDetail;
import com.mg.merp.warehouse.model.StockBatch;

/**
 * Реализация бизнес-компонента "Cекции хранения на складах"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BinLocationServiceBean.java,v 1.6 2008/05/30 12:59:21 sharapov Exp $
 */
@Stateless(name="merp/warehouse/BinLocationService") //$NON-NLS-1$
public class BinLocationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BinLocation, Integer> implements BinLocationServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, BinLocation entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.BinLocationServiceLocal#getBinLocationDetails(com.mg.merp.warehouse.model.StockBatch)
	 */
	@PermitAll
	public List<BinLocationDetailData> getBinLocationDetails(StockBatch stockBatch) {
		return doGetBinLocationDetails(stockBatch);
	}

	protected List<BinLocationDetailData> doGetBinLocationDetails(StockBatch stockBatch) {
		List<BinLocationDetailData> binLocationDetails = new ArrayList<BinLocationDetailData>();
		for (BinLocationDetailData receiptDetailData : getBinLocationDetailsByKind(stockBatch, BinLocationServiceLocal.RECEIPT_KIND)) {
			BinLocationDetailData binLocationDetailData = null;
			for (BinLocationDetailData issueDetailData : getBinLocationDetailsByKind(stockBatch, BinLocationServiceLocal.ISSUE_KIND)) {
				if(receiptDetailData.getBinLocationCode().equals(issueDetailData.getBinLocationCode())) {
					BigDecimal agregateQuantity = MathUtils.subtractNullable(receiptDetailData.getQuantity(), issueDetailData.getQuantity(), new RoundContext(6));
					if(MathUtils.compareToZero(agregateQuantity) > 0 && MathUtils.compareToZero(stockBatch.getEndQuan()) > 0) {
						binLocationDetailData = new BinLocationDetailData(receiptDetailData.getBinLocationCode(), agregateQuantity);
						break;
					}
				}
			}
			if(binLocationDetailData == null && MathUtils.compareToZero(stockBatch.getEndQuan()) > 0)
				binLocationDetailData = new BinLocationDetailData(receiptDetailData.getBinLocationCode(), receiptDetailData.getQuantity());
			if(binLocationDetailData != null)
				binLocationDetails.add(binLocationDetailData);
		}
		return binLocationDetails;
	}

	/**
	 * Получить агрегированные значения кол-ва в секции хранения партии с учетом вида движения
	 * @param stockBatch - партия
	 * @param kind - вид движения
	 * @return агрегированные значения кол-ва в секции хранения партии с учетом вида движения
	 */
	private List<BinLocationDetailData> getBinLocationDetailsByKind(StockBatch stockBatch, short kind) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BinLocationDetail.class)
				.setProjection(Projections.projectionList(
						Projections.groupProperty("binLocation.Code"), //$NON-NLS-1$
						Projections.sum("Quantity"))) //$NON-NLS-1$
						.createAlias("BinLocation", "binLocation") //$NON-NLS-1$ //$NON-NLS-2$
						.add(Restrictions.eq("StockBatch", stockBatch)) //$NON-NLS-1$
						.add(Restrictions.eq("Kind", kind)) //$NON-NLS-1$
						.setFlushMode(FlushMode.MANUAL)
						.setResultTransformer(new ResultTransformer<BinLocationDetailData>() {

							/* (non-Javadoc)
							 * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
							 */
							public BinLocationDetailData transformTuple(Object[] tuple, String[] aliases) {
								return new BinLocationDetailData((String) tuple[0], (BigDecimal) tuple[1]);
							}
						}));
	}

}
