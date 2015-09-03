/*
 * HolidaysServiceBeann.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.HolidaysServiceLocal;
import com.mg.merp.reference.model.Holidays;

/**
 * Бизнес-компонент "Праздничные дни"
 * 
 * @author leonova
 * @version $Id: HolidaysServiceBean.java,v 1.7 2007/08/06 14:50:05 safonov Exp $
 */
@Stateless(name="merp/reference/HolidaysService")
public class HolidaysServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Holidays,Integer> implements HolidaysServiceLocal {

	private void distributeHolidays(Holidays entity) {
 		Calendar calend = Calendar.getInstance(ServerUtils.getUserLocale());
 		calend.setTime(entity.getHDate());
		entity.setHYear(calend.get(Calendar.YEAR));
		entity.setHMonth(calend.get(Calendar.MONTH) + 1);
		entity.setHDay(calend.get(Calendar.DAY_OF_MONTH));
	}
	
	@Override
	protected void onCreate(Holidays entity) {
		distributeHolidays(entity);
	}

	@Override
	protected void onStore(Holidays entity) {
		distributeHolidays(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Holidays entity) {
		context.addRule(new MandatoryAttribute(entity, "HDate"));
	}

	
	/* (non-Javadoc)
	 * @see com.mg.merp.reference.HolidaysServiceLocal#isDayHoliday(java.util.Date)
	 */
	@PermitAll
	public boolean isDayHoliday(Date date) {
		List<Holidays> holidays = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Holidays.class)
				.add(Restrictions.eq("HDate", date)));
		return !holidays.isEmpty();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.HolidaysServiceLocal#copyHolidays(int[])
	 */
 	public void copyHolidays(Serializable[] keys) {
 		for (Serializable key : keys) {
 			Holidays holiday = load((Integer) key);
 			holiday.setHDate(DateTimeUtils.incYear(holiday.getHDate(), 1));
 			holiday.setHYear(holiday.getHYear() + 1); //added by RVG. Reason: SQA 1395
 		}
 	}
}
