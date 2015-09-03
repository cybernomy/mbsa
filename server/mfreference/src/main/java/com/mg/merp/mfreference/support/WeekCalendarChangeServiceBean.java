/*
 * WeekCalendarChangeServiceBean.java
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

package com.mg.merp.mfreference.support;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.mfreference.WeekCalendarChangeServiceLocal;
import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.mfreference.model.WeekCalendarChange;

/**
 * Бизнес-компонент "Недельный календарь" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: WeekCalendarChangeServiceBean.java,v 1.6 2007/08/28 07:52:48 alikaev Exp $
 */
@Stateless(name="merp/mfreference/WeekCalendarChangeService") //$NON-NLS-1$
public class WeekCalendarChangeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WeekCalendarChange, Integer> implements WeekCalendarChangeServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, WeekCalendarChange entity) {
		context.addRule(new MandatoryStringAttribute(entity, "DayCalMon")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalTue")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalWed")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalThu")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalFri")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalSat")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "DayCalSun")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "WeekCal"));  //$NON-NLS-1$
	}

	/**
	 * 
	 * @param weekCal
	 * @param searchDate
	 * @return
	 */
	private DayCalendar internalGetDayCalendar(WeekCalendar weekCal, Date searchDate) {
		WeekCalendarChange weekCalendarChange = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(WeekCalendarChange.class)
				.add(Restrictions.eq("WeekCal", weekCal)) //$NON-NLS-1$
				.add(Restrictions.le("EffOnDate", searchDate)) //$NON-NLS-1$
				.add(Restrictions.ge("EffOffDate", searchDate))); //$NON-NLS-1$
		switch (DateTimeUtils.getDayOfWeek(searchDate)) {
		case Calendar.SUNDAY:
			return weekCalendarChange.getDayCalSun();
		case Calendar.MONDAY:
			return weekCalendarChange.getDayCalMon();
		case Calendar.TUESDAY:
			return weekCalendarChange.getDayCalTue();
		case Calendar.WEDNESDAY:
			return weekCalendarChange.getDayCalWed();
		case Calendar.THURSDAY:
			return weekCalendarChange.getDayCalThu();
		case Calendar.FRIDAY:
			return weekCalendarChange.getDayCalFri();
		case Calendar.SATURDAY:
			return weekCalendarChange.getDayCalSat();
		default:
			throw new IllegalStateException("Invalid day of week"); //$NON-NLS-1$
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.WeekCalendarServiceLocal#getDayCalendar(int, java.util.Date)
	 */
	@PermitAll
	public DayCalendar getDayCalendar(int weekCalId, Date searchDate) {
		return internalGetDayCalendar(getPersistentManager().find(WeekCalendar.class, weekCalId), searchDate);
	}

}
