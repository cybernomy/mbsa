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
 */

package com.mg.merp.paymentcontrol.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.PeriodServiceLocal;
import com.mg.merp.paymentcontrol.model.PmcPeriod;

/**
 * ���������� ������-���������� "������� ������������" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodServiceBean.java,v 1.8 2007/07/12 10:30:30 safonov Exp $
 */
@Stateless(name="merp/paymentcontrol/PeriodService") //$NON-NLS-1$
public class PeriodServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PmcPeriod, Integer> implements PeriodServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final PmcPeriod entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(PmcPeriod entity) {
		checkForErasePossibility(entity);
	}

	/**
	 * �������� ����������� �������� ������� ������������
	 * @param entity - ������ ������������
	 */
	private void checkForErasePossibility(PmcPeriod entity) {
		Messages msg = Messages.getInstance();
		if(entity.getParent() == null)
			throw new BusinessException(msg.getMessage(Messages.PMCPERIOD_CANT_DELETE_ROOT));
		if(entity.getParent() != getRoot())
			throw new BusinessException(msg.getMessage(Messages.PMCPERIOD_CANT_DELETE_NESTED));
	}

	/**
	 * �������� �������� ������ (�.�. "������� ������������")
	 * @return �������� ������
	 */
	private PmcPeriod getRoot() {
		return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(PmcPeriod.class)
				.add(Restrictions.isNull("Parent"))); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PeriodServiceLocal#createPeriods(boolean, boolean, boolean, boolean, boolean, boolean, boolean, java.util.Date, int, com.mg.merp.paymentcontrol.model.PmcPeriod)
	 */
	public void createPeriods(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, java.lang.Integer upLevelQuantity, com.mg.merp.paymentcontrol.model.PmcPeriod Parent) {
		internalCreatePeriods(isPmcYear, isPmcHalfYear, isPmcQuarter, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay,  beginDate, upLevelQuantity, Parent);
	}

	/**
	 * ��������������� ����� ��� �������� �������� ������������ (� �������� ����������)
	 * @param isPmcYear - ������� ��������
	 * @param isPmcHalfYear - ����-������� ��������
	 * @param isPmcQuarter - ����������� ��������
	 * @param isPmcMonth - �������� ��������
	 * @param isPmcTenDays - �������� ������
	 * @param isPmcWeek - ��������� ��������
	 * @param isPmcDay - ������� ��������
	 * @param beginDate - ���� ������ ������������
	 * @param upLevelQuantity - ���������� �������� (�������� ������)
	 * @param Parent - ������������ ����
	 */
	private void internalCreatePeriods(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, java.lang.Integer upLevelQuantity, com.mg.merp.paymentcontrol.model.PmcPeriod Parent) {
		Locale serverLocale = ServerUtils.getUserLocale();
		List<PmcPeriod> periodList = new ArrayList<PmcPeriod>();

		if((upLevelQuantity == null) || (upLevelQuantity <= 0)) 
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PMCPERIOD_UPLEVELQUANTITY_INVALID));

		if(beginDate == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PMCPERIOD_DATE_INVALID));

		if(isPmcTenDays && isPmcWeek)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.PMCPERIOD_EXCLUSION_INVALID));

		isPmcDay = true; 

		beginDate = startDateOfPmcPeriod(isPmcYear, isPmcHalfYear, isPmcQuarter, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay,  beginDate, serverLocale);

		recurseCreate(isPmcYear, isPmcHalfYear, isPmcQuarter, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay, beginDate, null, upLevelQuantity, Parent, periodList, serverLocale);
		if(!periodList.isEmpty())
			storePmcPeriods(periodList);

		getPersistentManager().flush(); // ��� �������� ������� IDX_PMC_PERIOD_UN_DATE
	}

	/**
	 * ���������� ���� ������ ������������ ������� 
	 * @param isPmcHalfYear - ����-������� ��������
	 * @param isPmcQuarter - ����������� ��������
	 * @param isPmcMonth - �������� ��������
	 * @param isPmcTenDays - �������� ������
	 * @param isPmcWeek - ��������� ��������
	 * @param isPmcDay - ������� ��������
	 * @param beginDate - ��������� ����
	 * @param locale - ������ ���������(����, �������) ���������� 
	 * @return ���� ������ ������������ �������
	 */
	protected java.util.Date startDateOfPmcPeriod(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, Locale locale) {
		if(isPmcYear)
			return  PmcPeriodUtils.startOfTheYear(beginDate, locale);
		else if(isPmcHalfYear)
			return  PmcPeriodUtils.beginOfTheHalfYear(beginDate, locale);
		else if(isPmcQuarter)
			return PmcPeriodUtils.beginOfTheQuarter(beginDate, locale);
		else if(isPmcMonth)
			return PmcPeriodUtils.startOfTheMonth(beginDate, locale);
		else if(isPmcTenDays)
			return beginDate = PmcPeriodUtils.startOfTheMonth(beginDate, locale);
		else if(isPmcWeek) 
			return PmcPeriodUtils.startOfTheWeek(beginDate, locale);
		else 
			return beginDate;
	}

	/**
	 * ����������� �������� �������� ������������ (� �������� ����������)
	 * @param isPmcYear - ������� ��������
	 * @param isPmcHalfYear - ����-������� ��������
	 * @param isPmcQuarter - ����������� ��������
	 * @param isPmcMonth - �������� ��������
	 * @param isPmcTenDays - �������� ������
	 * @param isPmcWeek - ��������� ��������
	 * @param isPmcDay - ������� ��������
	 * @param beginDate - ���� ������ ������������
	 * @param upLevelQuantity - ���������� �������� (�������� ������)
	 * @param Parent - ������������ ����
	 * @param periodList - ������ ��� ������������ ��������� �����
	 * @param locale ������ ���������(����, �������) ����������
	 */	
	protected void recurseCreate(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, java.util.Date endDate, int upLevelQuantity, com.mg.merp.paymentcontrol.model.PmcPeriod Parent, List<PmcPeriod> periodList, Locale locale) {
		com.mg.merp.paymentcontrol.model.PmcPeriod currentEntity;
		Messages nameMessage = Messages.getInstance();
		java.util.Date d2; 
		java.util.Date begin_d;
		java.util.Date end_d;
		int tenDaysCounter;

		if(isPmcYear) {
			begin_d = beginDate;
			if(endDate == null) 
				d2 = PmcPeriodUtils.incYear(PmcPeriodUtils.startOfTheYear(beginDate, locale), upLevelQuantity, locale);
			else
				d2 = endDate;
			while(begin_d.compareTo(d2) < 0) {
				end_d = PmcPeriodUtils.endOfTheYear(begin_d, locale);
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_YEAR), begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(false, isPmcHalfYear, isPmcQuarter, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.incYear(begin_d, 1, locale);
			}
		}
		else if(isPmcHalfYear) {
			begin_d = beginDate;
			if(endDate == null) 
				d2 = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incMonth(beginDate, upLevelQuantity * 6 - 1, locale), locale);
			else
				d2 = endDate;
			while (begin_d.compareTo(d2) <= 0) {
				end_d = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incMonth(begin_d, 5, locale), locale);
				end_d = end_d.compareTo(d2) < 0 ? end_d : d2;
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_HALFYEAR), PmcPeriodUtils.halfYearOf(begin_d, locale), begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(false, false, isPmcQuarter, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.incMonth(begin_d, 6, locale);
			}
		}
		else if(isPmcQuarter) {
			begin_d = beginDate;
			if(endDate == null) 
				d2 = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incMonth(beginDate, upLevelQuantity * 3 - 1, locale), locale);
			else
				d2 = endDate;
			while (begin_d.compareTo(d2) <= 0) {
				end_d = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incMonth(begin_d, 2, locale), locale);
				end_d = end_d.compareTo(d2) < 0 ? end_d : d2;
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_QUARTER), PmcPeriodUtils.qurterOf(begin_d, locale), begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(false, false, false, isPmcMonth, isPmcTenDays, isPmcWeek, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.incMonth(begin_d, 3, locale);
			}
		}
		else if(isPmcMonth) {
			begin_d = beginDate;
			if(endDate == null )
				d2 = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incMonth(beginDate, upLevelQuantity - 1, locale), locale);
			else
				d2 = endDate;
			while (begin_d.compareTo(d2) <= 0) {
				end_d = PmcPeriodUtils.endOfTheMonth(begin_d, locale);
				end_d = end_d.compareTo(d2) < 0 ? end_d : d2;
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_MONTH), begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(isPmcYear, isPmcHalfYear, isPmcQuarter, false, isPmcTenDays, isPmcWeek, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.incMonth(begin_d, 1, locale);
			}
		}
		else if(isPmcTenDays) {
			begin_d = beginDate;
			if(endDate == null) {
				if((upLevelQuantity / 3) > 1) {
					d2 = PmcPeriodUtils.startOfTheMonth(PmcPeriodUtils.incDay(beginDate, upLevelQuantity * 10, locale), locale);
					d2 = PmcPeriodUtils.incDay(d2, (upLevelQuantity / 3) * 10 - 1, locale);
				}
				else 
					d2 = PmcPeriodUtils.endOfTheMonth(PmcPeriodUtils.incDay(beginDate, upLevelQuantity * 10, locale), locale);
			}
			else
				d2 = endDate;
			tenDaysCounter = 1;
			while (begin_d.compareTo(d2) <= 0) {
				if(tenDaysCounter < 3) 
					end_d = PmcPeriodUtils.incDay(beginDate, tenDaysCounter * 10 - 1, locale);
				else 
					end_d = PmcPeriodUtils.endOfTheMonth(begin_d, locale);
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_DEKADA), tenDaysCounter, begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(isPmcYear, isPmcHalfYear, isPmcQuarter, isPmcMonth, false, isPmcWeek, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.incDay(end_d, 1, locale);
				tenDaysCounter++;
				if(tenDaysCounter > 3) {
					tenDaysCounter = 1;
					beginDate = begin_d;
				}
			}
		}
		else if(isPmcWeek) {
			begin_d = beginDate;
			if (endDate == null) 
				d2 = PmcPeriodUtils.endOfTheWeek(PmcPeriodUtils.incWeek(beginDate, upLevelQuantity - 1, locale), locale);
			else
				d2 = endDate;
			while(begin_d.compareTo(d2) <= 0) {
				end_d = PmcPeriodUtils.endOfTheWeek(begin_d, locale);
				end_d = end_d.compareTo(d2) < 0 ? end_d : d2;
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_WEEK), PmcPeriodUtils.weekOfYear(begin_d, locale), begin_d), begin_d, end_d, (short)1, Parent);
				periodList.add(currentEntity);
				recurseCreate(isPmcYear, isPmcHalfYear, isPmcQuarter, isPmcMonth, isPmcTenDays, false, isPmcDay,  begin_d, end_d, 0, currentEntity, periodList, locale);
				begin_d = PmcPeriodUtils.startOfTheWeek(PmcPeriodUtils.incWeek(begin_d, 1, locale), locale);
			}
		}
		else if(isPmcDay) {
			begin_d = beginDate;
			if(endDate == null) 
				d2 = PmcPeriodUtils.incDay(beginDate, upLevelQuantity - 1, locale);
			else
				d2 = endDate;
			while (begin_d.compareTo(d2) <= 0) {
				currentEntity = intializePmcPeriod(String.format(nameMessage.getMessage(Messages.PMCPERIOD_DAY), begin_d), begin_d, begin_d, (short)1, Parent);
				periodList.add(currentEntity);
				begin_d = PmcPeriodUtils.incDay(begin_d, 1, locale);
			}
		}
	}

	/**
	 * ������������� ������� ��������� �����������
	 * @param name - ������������ �������
	 * @param dateFrom - ������ �������
	 * @param dateTill - ��������� �������
	 * @param kind - ��� �������
	 * @param parent - ������������ ���� 
	 * @return ������ � �������������� �����������
	 */
	protected PmcPeriod intializePmcPeriod(String name, java.util.Date dateFrom, java.util.Date dateTill, short kind, PmcPeriod parent) {
		PmcPeriod periodEntity = initialize();
		periodEntity.setParent(parent);
		periodEntity.setDateFrom(dateFrom);
		periodEntity.setDateTill(dateTill);
		periodEntity.setName(name);
		periodEntity.setKind((short) kind);
		return periodEntity;
	}

	/**
	 * ������������ ������ �������� � ���������� ���������
	 * @param periodList - ������ �������� ��� ������ 
	 */
	protected void storePmcPeriods(List<PmcPeriod> periodList) {
		for(PmcPeriod periodEntity : periodList) 
			ServerUtils.getPersistentManager().persist(periodEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.paymentcontrol.PeriodServiceLocal#getNestedPeriods(com.mg.merp.paymentcontrol.model.PmcPeriod)
	 */
	@PermitAll
	public List<PmcPeriod> getNestedPeriods(PmcPeriod parentPeriod) {
		return doGetNestedPeriods(parentPeriod);
	}

	private List<PmcPeriod> doGetNestedPeriods(PmcPeriod parentPeriod) {
		List<PmcPeriod> periods = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PmcPeriod.class)
				.add(Restrictions.eq("Parent", parentPeriod))); //$NON-NLS-1$
		return periods;
	}

}
