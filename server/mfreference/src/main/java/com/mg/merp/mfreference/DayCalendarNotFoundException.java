/*
 * DayCalendarNotFoundException.java
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
package com.mg.merp.mfreference;

import java.util.Date;

import javax.ejb.ApplicationException;

import com.mg.framework.api.BusinessException;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.mfreference.support.Messages;

/**
 * ИС генерируется если не найден дневной календарь
 * 
 * @author Oleg V. Safonov
 * @version $Id: DayCalendarNotFoundException.java,v 1.1 2007/07/30 10:25:31 safonov Exp $
 */
@ApplicationException
public class DayCalendarNotFoundException extends BusinessException {
	private String code;
	private Date date;

	public DayCalendarNotFoundException(WeekCalendar weekCal, Date date) {
		super("Day calendar not found");
		this.code = weekCal.getCode();
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.DAY_CALENDAR_NOT_FOUND, new Object[] {code, date});
	}

}
