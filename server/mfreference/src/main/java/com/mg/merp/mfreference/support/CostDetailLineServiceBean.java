/*
 * CostdetaillineServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.mfreference.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.CostDetailLineItem;
import com.mg.merp.mfreference.CostDetailLineServiceLocal;
import com.mg.merp.mfreference.model.CostCategories;
import com.mg.merp.mfreference.model.CostDetail;
import com.mg.merp.mfreference.model.CostDetailLine;

/**
 * Бизнес-компонент "Строки расшифровки стоимости" 
 * 
 * @author leonova
 * @version $Id: CostDetailLineServiceBean.java,v 1.4 2007/07/30 10:24:41 safonov Exp $
 */
@Stateless(name="merp/mfreference/CostDetailLineService")
public class CostDetailLineServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CostDetailLine, Integer> implements CostDetailLineServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.CostDetailLineServiceLocal#calculateCost(com.mg.merp.mfreference.model.CostDetail, boolean)
	 */
	@PermitAll
	public List<CostDetailLineItem> calculateCost(CostDetail costDetail, boolean groupByCategories) {
		List<CostDetailLineItem> result = new ArrayList<CostDetailLineItem>();
		if (groupByCategories) {
			List<Object[]> items = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CostDetailLine.class)
					.add(Restrictions.eq("CostDetail", costDetail))
					.setProjection(Projections.projectionList(
							Projections.sum("Summa"),
							Projections.property("CostCategories"),
							Projections.groupProperty("CostCategories"))));
			for (Object[] item : items)
				result.add(new CostDetailLineItem((CostCategories) item[1], (BigDecimal) item[0]));
		} else {
			BigDecimal cost = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(CostDetailLine.class)
					.add(Restrictions.eq("CostDetail", costDetail))
					.setProjection(Projections.sum("Summa")));
			result.add(new CostDetailLineItem(null, cost));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.CostDetailLineServiceLocal#clear(com.mg.merp.mfreference.model.CostDetail)
	 */
	@PermitAll
	public void clear(CostDetail costDetail) {
		OrmTemplate.getInstance().bulkUpdateByNamedQuery("Manufacture.CostDetailLine.clear", "costDetail", costDetail);
	}

}
