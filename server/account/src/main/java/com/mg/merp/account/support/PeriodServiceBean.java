/*
 * PeriodServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.PeriodServiceLocal;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.Period;

/**
 * Реализация бизнес-компонента "Периоды бухгалтерского учета" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Oleg V. Safonov
 * @version $Id: PeriodServiceBean.java,v 1.11 2007/09/06 07:46:03 alikaev Exp $
 */
@Stateless(name="merp/account/PeriodService") //$NON-NLS-1$
public class PeriodServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Period, Integer> implements PeriodServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final Period entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DateFrom")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DateTo")); //$NON-NLS-1$
		context.addRule(new Rule() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#getMessage()
			 */
			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.ACC_PERIOD_IS_CROSS);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
			 */
			public void validate(ValidationContext context) {
				if(isPeriodInvalid(entity))
					context.getStatus().error(this);
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(Period entity) {
		if(!isPeriodInUse(entity))
			super.onErase(entity);
		else
			throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_PERIOD_IS_IN_USE));

	}

	/**
	 * Проверка периода на принадлежность хоз.операциям
	 * @param entity - период
	 * @return результат проверки
	 */
	private boolean isPeriodInUse(Period entity) {
		Object count = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(EconomicOper.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.between("KeepDate", entity.getDateFrom(), entity.getDateTo()))); //$NON-NLS-1$

		return (Integer) count > 0 ? true : false;
	}

	/**
	 * Проверка корректности периода бух.учета, если не корректен возвращаем <code>true</code>
	 * @param entity - период бух.учета
	 * @return результат проверки
	 */
	private boolean isPeriodInvalid(Period entity) {
		if (entity.getDateFrom() == null)
			return false;
		// проверка корректности диапазона
		if((entity.getDateFrom().compareTo(entity.getDateTo()) == 0) || (entity.getDateFrom().compareTo(entity.getDateTo()) > 0))
			return true;
		// проверка на пересечение
		Criteria criteria = OrmTemplate.createCriteria(Period.class)
				.add(Restrictions.or(
						Restrictions.or(
								Restrictions.and(Restrictions.le("DateFrom", entity.getDateFrom()), Restrictions.ge("DateTo", entity.getDateFrom())), //$NON-NLS-1$ //$NON-NLS-2$
								Restrictions.and(Restrictions.le("DateFrom", entity.getDateTo()), Restrictions.ge("DateTo", entity.getDateTo()))),					
						Restrictions.and(Restrictions.ge("DateFrom", entity.getDateFrom()), Restrictions.le("DateTo", entity.getDateTo()))));		
		// при редактировании периода исключаем изменяемый период из списка существующих периодов
		if(entity.getId() != null)
			criteria.add(Restrictions.ne("Id", entity.getId())); //$NON-NLS-1$
		List<Period> accPeriods = OrmTemplate.getInstance().findByCriteria(criteria);

		return accPeriods.size() > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.PeriodServiceLocal#openPeriod(java.io.Serializable[])
	 */
	public void openPeriod(Serializable[] periodIds) {
		if(periodIds.length != 0){
			for(int i=0; i<periodIds.length; i++){
				Period ap = load((Integer)periodIds[i]);
				internalOpenPeriod(ap);
			}
		}
	}

	/**
	 * Открыть период бух.учета
	 * @param accPeriod - период бух.учета
	 */
	private void internalOpenPeriod(Period accPeriod) {
		accPeriod.setDateClose(null);
		accPeriod.setWhoClosed(null);
		getPersistentManager().merge(accPeriod);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.PeriodServiceLocal#closePeriod(java.io.Serializable[])
	 */
	public void closePeriod(Serializable[] periodIds) {
		if(periodIds.length != 0){
			for(int i=0; i<periodIds.length; i++){
				Period ap = load((Integer)periodIds[i]);
				internalClosePeriod(ap);
			}
		}
	}

	/**
	 * Закрыть период бух.учета
	 * @param accPeriod - период бух.учета
	 */
	private void internalClosePeriod(Period accPeriod) {
		accPeriod.setDateClose(DateTimeUtils.nowDate());
		accPeriod.setWhoClosed(ServerUtils.getUserProfile().getUserName());
		getPersistentManager().merge(accPeriod);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.PeriodServiceLocal#findByDate(java.util.Date)
	 */
	@PermitAll
	public Period findByDate(Date date) {
		List<Period> result = findByCriteria(Restrictions.le("DateFrom", date), Restrictions.ge("DateTo", date)); //$NON-NLS-1$ //$NON-NLS-2$
		if (result.isEmpty())
			throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_INVALID_PERIOD));
		return result.get(0);
	}

	private void internalCheckPeriod(Period period) {
		if (period.getDateClose() != null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.ACC_PERIOD_CLOSED));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.PeriodServiceLocal#checkPeriod(java.util.Date)
	 */
	@PermitAll
	public void checkPeriod(Date date) {
		internalCheckPeriod(findByDate(date));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.PeriodServiceLocal#checkPeriod(java.lang.Integer)
	 */
	@PermitAll
	public void checkPeriod(Integer periodId) {
		internalCheckPeriod(load(periodId));
	}

}
