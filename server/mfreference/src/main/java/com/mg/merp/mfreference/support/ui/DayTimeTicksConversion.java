/*
 * DayTimeTicksConversion.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.support.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.generic.ui.AbstractConversionRoutine;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.mfreference.support.Messages;

/**
 * ѕреобразование тиков в часы. ƒанный конвертер преобразует количество тиков в количество часов и минут,
 * а не во врем€, поэтому используетс€ календарь GMT, чтобы не было вли€ни€ временных зон, DateFormat
 * используетс€ исключительно дл€ удобства преобразовани€ строк, чтобы в UI часы и минуты выгл€дели
 * в локали пользовател€
 * 
 * @author leonova
 * @version $Id: DayTimeTicksConversion.java,v 1.3 2008/03/04 10:20:55 alikaev Exp $
 */
public class DayTimeTicksConversion extends AbstractConversionRoutine<Long, String> {

	private Calendar getCalendar() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT"));//гринвич, исключаем вли€ние временных зон
	}
	
	@Override
	protected Long doInputConverse(String value) {
		Date time = null;
		try {
			time = DateFormat.getTimeInstance(DateFormat.SHORT, ServerUtils.getUserLocale()).parse(value);
		} catch (ParseException e) {
			throw new ApplicationException(Messages.getInstance().getMessage(Messages.INVALID_TIME_FORMAT));
		}
		Calendar calend = getCalendar();
		calend.clear();
		calend.set(1970, 0, 1, DateTimeUtils.getHour(time), DateTimeUtils.getMinute(time), DateTimeUtils.getSecond(time));
		return calend.getTimeInMillis();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractConversionRoutine#doOutputConverse(S)
	 */
	@Override
	protected String doOutputConverse(Long value) {
		Calendar calend = getCalendar();
		if (value == null)
			value = 0l;
		calend.setTimeInMillis(value);
		DateFormat sDF = DateFormat.getTimeInstance(DateFormat.SHORT, ServerUtils.getUserLocale());
		sDF.setCalendar(calend);
		return sDF.format(calend.getTime());
	}
}