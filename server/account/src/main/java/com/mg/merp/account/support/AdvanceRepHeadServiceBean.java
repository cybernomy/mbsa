/*
 * AdvanceRepHeadServiceBean.java
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

package com.mg.merp.account.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.AdvanceRepHeadModelServiceLocal;
import com.mg.merp.account.AdvanceRepHeadResult;
import com.mg.merp.account.AdvanceRepHeadServiceLocal;
import com.mg.merp.account.AdvanceRepSpecServiceLocal;
import com.mg.merp.account.model.AccConfig;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AdvanceRepHead;
import com.mg.merp.account.model.AnlForm;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;


/**
 * Бизнес-компонент "Авансовые отчеты" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: AdvanceRepHeadServiceBean.java,v 1.10 2008/03/17 14:41:42 alikaev Exp $
 */
@Stateless(name="merp/account/AdvanceRepHeadService") //$NON-NLS-1$
public class AdvanceRepHeadServiceBean extends  GoodsDocumentServiceBean<AdvanceRepHead, Integer, AdvanceRepHeadModelServiceLocal, AdvanceRepSpecServiceLocal> implements AdvanceRepHeadServiceLocal {

	private AccConfig config = ConfigurationHelper.getConfiguration();

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final AdvanceRepHead entity) {
		super.onValidate(context, entity);

		context.addRule(new MandatoryAttribute(entity, "From") { //$NON-NLS-1$
			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.validator.EntityBeanRule#getMessage()
			 */
			@Override
			public String getMessage() {
				return com.mg.merp.account.support.Messages.getInstance().getMessage(com.mg.merp.account.support.Messages.MANDATORY_VALIDATOR_FROM);
			}
		}
		);

		context.addRule(new MandatoryAttribute(entity, "To") { //$NON-NLS-1$
			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.validator.EntityBeanRule#getMessage()
			 */
			@Override
			public String getMessage() {
				return com.mg.merp.account.support.Messages.getInstance().getMessage(com.mg.merp.account.support.Messages.MANDATORY_VALIDATOR_TO);
			}
		}
		);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
	 */
	@Override
	protected Configuration doGetConfiguration() {
		return ConfigurationHelper.getDocumentConfiguration();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return AdvanceRepHeadServiceLocal.DOCSECTION;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.AdvanceRepHeadServiceLocal#getPrevAdvanceSum(com.mg.merp.account.model.AccPlan, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Currency)
	 */
	@PermitAll
	public BigDecimal getPrevAdvanceSum(AccPlan accPlan, Contractor contractor,	Currency currency) throws ApplicationException {
		boolean isCurDocEqualsNatCur = true;
		Currency natCurrency = ServerUtils.getPersistentManager().find(Currency.class, config.getNatCurrency().getId());
		Date lastDate = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(EconomicSpec.class)
				.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
				.createAlias("AccKt", "akt") //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("eo.To", contractor)) //$NON-NLS-1$
				.add(Restrictions.eq("AccDb", accPlan)) //$NON-NLS-1$
				.add(Restrictions.eq("akt.AnlForm", AnlForm.MONEYMEANS)) //$NON-NLS-1$
				.setProjection(Projections.max("eo.KeepDate"))); //$NON-NLS-1$
		//в двойке написано так last_date := FieldByName('max_date').AsDateTime - 1, предположил что вычитается день
		BigDecimal prevAdvanceSum = BigDecimal.ZERO;
		if (lastDate != null) {
			lastDate = DateTimeUtils.incDay(lastDate, -1);
			Criteria criteria = OrmTemplate.createCriteria(RemnDbKt.class)
			.createAlias("AccPlan", "acp") //$NON-NLS-1$ //$NON-NLS-2$
			.createAlias("Period", "p") //$NON-NLS-1$ //$NON-NLS-2$
			.add(Restrictions.eq("AccPlan", accPlan)) //$NON-NLS-1$
			.add(Restrictions.eq("Contractor", contractor)) //$NON-NLS-1$
			.add(Restrictions.le("p.DateFrom", lastDate)) //$NON-NLS-1$
			.add(Restrictions.ge("p.DateTo", lastDate)) //$NON-NLS-1$
			.addOrder(Order.desc("p.DateFrom")) //$NON-NLS-1$
			.setProjection(Projections.projectionList(Projections.property("RemnBeginNatDb"), Projections.property("RemnBeginNatKt"), Projections.property("p.DateFrom"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.setResultTransformer(new ResultTransformer<RemnDbKtItem> () {

				public RemnDbKtItem transformTuple(Object[] tuple, String[] aliases) {
					BigDecimal remnBegNatDb = tuple[0] != null ? (BigDecimal) tuple[0] : BigDecimal.ZERO;
					BigDecimal remnBegNatKt = tuple[1] != null ? (BigDecimal) tuple[1] : BigDecimal.ZERO;
					return new RemnDbKtItem(remnBegNatDb, remnBegNatKt, (Date) tuple[2]);
				}

			});
			if (currency.getCode().trim().compareTo(natCurrency.getCode().trim()) == 0)
				criteria.add(Restrictions.isNull("acp.Currency")); //$NON-NLS-1$
			else {
				criteria.add(Restrictions.eq("acp.Currency", currency)); //$NON-NLS-1$
				isCurDocEqualsNatCur = false;
			}
			List<RemnDbKtItem> remnAccList = OrmTemplate.getInstance().findByCriteria(criteria);
			for (RemnDbKtItem remnDbKtItem : remnAccList) 
				prevAdvanceSum = prevAdvanceSum.add(remnDbKtItem.remnBegNatDb.subtract(remnDbKtItem.remnBegNatKt));

			if (!remnAccList.isEmpty()) {
				Date beginDate = remnAccList.get(0).dateFrom;
				criteria = OrmTemplate.createCriteria(EconomicSpec.class)
				.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
				.createAlias("AccKt", "akt") //$NON-NLS-1$ //$NON-NLS-2$
				.setProjection(Projections.sum("SummaNat")) //$NON-NLS-1$
				.add(Restrictions.eq("eo.To", contractor)) //$NON-NLS-1$
				.add(Restrictions.eq("AccDb", accPlan)) //$NON-NLS-1$
				.add(Restrictions.eq("akt.AnlForm", AnlForm.MONEYMEANS)) //$NON-NLS-1$
				.add(Restrictions.between("eo.KeepDate", beginDate, lastDate)); //$NON-NLS-1$
				if (isCurDocEqualsNatCur)
					criteria.add(Restrictions.isNull("akt.Currency")); //$NON-NLS-1$
				else
					criteria.add(Restrictions.eq("akt.Currency", currency)); //$NON-NLS-1$

				BigDecimal sumDb = OrmTemplate.getInstance().findUniqueByCriteria(criteria);
				if (sumDb != null)
					prevAdvanceSum = prevAdvanceSum.add(sumDb);
				criteria = OrmTemplate.createCriteria(EconomicSpec.class)
				.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
				.createAlias("AccDb", "adb") //$NON-NLS-1$ //$NON-NLS-2$
				.setProjection(Projections.sum("SummaNat")) //$NON-NLS-1$
				.add(Restrictions.eq("eo.To", contractor)) //$NON-NLS-1$
				.add(Restrictions.eq("AccKt", accPlan)) //$NON-NLS-1$
				.add(Restrictions.eq("adb.AnlForm", AnlForm.MONEYMEANS)) //$NON-NLS-1$
				.add(Restrictions.between("eo.KeepDate", beginDate, lastDate)); //$NON-NLS-1$
				if (isCurDocEqualsNatCur)
					criteria.add(Restrictions.isNull("adb.Currency")); //$NON-NLS-1$
				else
					criteria.add(Restrictions.eq("adb.Currency", currency)); //$NON-NLS-1$

				BigDecimal sumKt = OrmTemplate.getInstance().findUniqueByCriteria(criteria);
				if (sumKt != null)
					prevAdvanceSum = prevAdvanceSum.subtract(sumKt);
				return prevAdvanceSum;
			} 
		}
		return prevAdvanceSum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.AdvanceRepHeadServiceLocal#getReceivedSum(com.mg.merp.account.model.AccPlan, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Currency, java.util.Date)
	 */
	@PermitAll
	public AdvanceRepHeadResult getReceivedSum(AccPlan accPlan,	Contractor contractor, Currency currency, Date curDate)	throws ApplicationException {
		Currency natCurrency = ServerUtils.getPersistentManager().find(Currency.class, config.getNatCurrency().getId());
		Date lastDate = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(EconomicSpec.class)
				.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
				.createAlias("AccKt", "akt") //$NON-NLS-1$ //$NON-NLS-2$
				.add(Restrictions.eq("eo.To", contractor)) //$NON-NLS-1$
				.add(Restrictions.eq("AccDb", accPlan)) //$NON-NLS-1$
				.add(Restrictions.eq("akt.AnlForm", AnlForm.MONEYMEANS)) //$NON-NLS-1$
				.add(Restrictions.le("eo.KeepDate", curDate)) //$NON-NLS-1$
				.setProjection(Projections.max("eo.KeepDate"))); //$NON-NLS-1$
		if (lastDate != null) {
			Criteria criteria = OrmTemplate.createCriteria(EconomicSpec.class)
			.createAlias("EconomicOper", "eo") //$NON-NLS-1$ //$NON-NLS-2$
			.createAlias("AccKt", "akt") //$NON-NLS-1$ //$NON-NLS-2$
			.add(Restrictions.eq("eo.To", contractor)) //$NON-NLS-1$
			.add(Restrictions.eq("AccDb", accPlan)) //$NON-NLS-1$
			.add(Restrictions.eq("akt.AnlForm", AnlForm.MONEYMEANS)) //$NON-NLS-1$
			.add(Restrictions.between("eo.KeepDate", lastDate, lastDate)) //$NON-NLS-1$
			.setProjection(Projections.sum("SummaNat")); //$NON-NLS-1$
			if (currency.getCode().trim().compareTo(natCurrency.getCode().trim()) == 0)
				criteria.add(Restrictions.isNull("akt.Currency")); //$NON-NLS-1$
			else
				criteria.add(Restrictions.eq("akt.Currency", currency)); //$NON-NLS-1$
			return new AdvanceRepHeadResult((BigDecimal) OrmTemplate.getInstance().findUniqueByCriteria(criteria), lastDate);
		} else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(AdvanceRepHead entity) {
		super.doAdjust(entity);
		BigDecimal received1Sum = entity.getReceived1Sum() != null ? entity.getReceived1Sum() : BigDecimal.ZERO;
		BigDecimal received2Sum = entity.getReceived2Sum() != null ? entity.getReceived2Sum() : BigDecimal.ZERO;
		BigDecimal received3Sum = entity.getReceived3Sum() != null ? entity.getReceived3Sum() : BigDecimal.ZERO;
		//сумма получено
		entity.setReceivedSum(received1Sum.add(received2Sum.add(received3Sum)));
		//сумма остаток перерасход
		BigDecimal restSum = entity.getBalanceSum();
		if (entity.getPrevAdvanceSum() != null) {
			restSum = entity.getRestDocKind() ? restSum.add(entity.getPrevAdvanceSum()) : 
				restSum.subtract(entity.getPrevAdvanceSum());
		}
		if (MathUtils.compareToZero(restSum) > -1) {
			entity.setRestSum(restSum);
			entity.setBalanceOrOverRun(true);
		} else {
			entity.setRestSum(restSum.negate());
			entity.setBalanceOrOverRun(false);
		}		
	}

	// написал класс т.к. у RemnDbKt загрузка идет через хранимую процедуру, а для решения задачи нужны только три поля
	private class RemnDbKtItem {
		BigDecimal remnBegNatDb;
		BigDecimal remnBegNatKt;
		Date dateFrom;

		public RemnDbKtItem(BigDecimal remnBegNatDb, BigDecimal remnBegNatKt, Date dateFrom) {
			this.remnBegNatDb = remnBegNatDb;
			this.remnBegNatKt = remnBegNatKt;
			this.dateFrom = dateFrom;
		}				
	}

}
