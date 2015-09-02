/*
 * RemnServiceBean.java
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
package com.mg.merp.account.generic;

import java.io.Serializable;
import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.Remn;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.support.Messages;

/**
 * Ѕазовый класс реализации бизнес-компонента "ќстатки и обороты бух. учета"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnServiceBean.java,v 1.8 2008/03/21 10:47:19 alikaev Exp $
 */
public abstract class RemnServiceBean<T extends PersistentObject, ID extends Serializable> extends AbstractPOJODataBusinessObjectServiceBean<T, ID> implements Remn {

	/**
	 * ¬озвращает им€ запроса дл€ удалени€ пустых записей
	 * @return
	 */
	abstract protected String getQueryNameRemoveEmptyRecords();
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, T entity) {
		context.addRule(new MandatoryAttribute(entity, "AccPlan"));	 //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Period")); //$NON-NLS-1$
	}

	/**
	 * ѕеренос остатков бух.учета
	 * @param periodFrom - начальный период
	 * @param periodTo - конечный период
	 * @param allAcc - все счета
	 * @param accList - список выбранных счетов
	 */
	public void carryForward(Period periodFrom, Period periodTo, boolean allAcc, AccPlan[] accList) {
		internalCarryForward(periodFrom, periodTo, allAcc, accList);
	}

	/**
	 * ѕеренос остатков бух.учета
	 * @param accPeriod - период
	 * @param account - счет
	 */
	abstract protected void carryForwardBalance(Period accPeriod,  AccPlan account);

	/**
	 * ќпредел€ет список всех счетов
	 * @return список всех счетов
	 */
	abstract protected AccPlan[] getAllAccountsList();

	/**
	 * ѕеренос остатков бух.учета
	 * @param periodFrom - начальный период
	 * @param periodTo - конечный период
	 * @param allAcc - все счета
	 * @param accList - список выбранных счетов
	 */
	protected void internalCarryForward (Period periodFrom, Period periodTo, boolean allAcc, AccPlan[] accList) {
		Messages errorMsg = Messages.getInstance();

		if(periodFrom == null || periodTo == null)
			throw new BusinessException(errorMsg.getMessage(Messages.ACC_INVALID_PERIOD_RANGE));

		List<Period> accPeriods = getAccPeriodsRange(periodFrom, periodTo);

		if(accPeriods == null || accPeriods.isEmpty())
			throw new BusinessException(errorMsg.getMessage(Messages.ACC_INVALID_PERIOD_RANGE));

		if(allAcc) 
			accList = getAllAccountsList();

		if(accList == null)	
			throw new BusinessException(errorMsg.getMessage(Messages.ACC_INVALID_ACCOUNTS_LIST));

		for (AccPlan account : accList) {
			for (Period period : accPeriods) {
				if(period.getDateClose() != null)
					throw new BusinessException(errorMsg.getMessage(Messages.ACC_PERIOD_CLOSED));
				else
					carryForwardBalance(period, account);
			}
		}

	}

	/**
	 * ќпредел€ет диапазон периодов, подлежащий переносу остатков
	 * @param periodFrom - начальный период
	 * @param periodTo - конечный период
	 * @return список периодов, подлежащий переносу остатков
	 */
	protected List<Period> getAccPeriodsRange(Period periodFrom, Period periodTo) {
		List<Period> accPeriods = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Period.class)
				.add(Restrictions.conjunction(Restrictions.gt("DateFrom", periodFrom.getDateFrom()), Restrictions.le("DateFrom", periodTo.getDateFrom()))) //$NON-NLS-1$ //$NON-NLS-2$
				.addOrder(Order.asc("DateFrom")));  //$NON-NLS-1$
		return accPeriods;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.Remn#deleteEmptyString(com.mg.merp.account.model.Period, com.mg.merp.account.model.Period)
	 */
	public void removeEmptyRecords(Period periodFrom, Period periodTo) {
		doRemoveEmptyRecords(periodFrom, periodTo);
	}

	/**
	 * ”даление "пустых" обороток
	 * 
	 * @param periodFrom
	 * 				- начальный период
	 * @param periodTo
	 * 				- конечный период
	 */
	protected void doRemoveEmptyRecords(Period periodFrom, Period periodTo) {
		Criteria criteria = OrmTemplate.createCriteria(Period.class);
		if (periodFrom != null) 
			criteria.add(Restrictions.ge("DateFrom", periodFrom.getDateFrom())); //$NON-NLS-1$
		if (periodTo != null) 
			criteria.add(Restrictions.le("DateFrom", periodTo.getDateFrom())); //$NON-NLS-1$
		List<Period> periods = OrmTemplate.getInstance().findByCriteria(criteria);
		OrmTemplate.getInstance().bulkUpdateByNamedQuery(getQueryNameRemoveEmptyRecords(), "periods", periods.toArray(new Period[periods.size()]));		 //$NON-NLS-1$
	}
	
	/**
	 * Ќе реализована
	 * @param remnId
	 * @return
	 * @throws ApplicationException
	 */
	public byte[] loadEconomicOperDbBrowse(int remnId) throws ApplicationException {
		//TODO
		//return ((RemnDomainImpl) getDomain()).loadEconomicOperDbBrowse(remnId);
		return null;
	}
	
	/**
	 * Ќе реализована
	 * @param remnId
	 * @return
	 * @throws ApplicationException
	 */
	public byte[] loadEconomicOperKtBrowse(int remnId) throws ApplicationException {
		//TODO
		//return ((RemnDomainImpl) getDomain()).loadEconomicOperKtBrowse(remnId);
		return null;
	}
	
	/**
	 * Ќе реализована
	 * @param remnId
	 * @return
	 * @throws ApplicationException
	 */
	public byte[] loadEconomicOperDbKtBrowse(int remnId) throws ApplicationException {
		//TODO
		//return ((RemnDomainImpl) getDomain()).loadEconomicOperDbKtBrowse(remnId);
		return null;
	}

}
