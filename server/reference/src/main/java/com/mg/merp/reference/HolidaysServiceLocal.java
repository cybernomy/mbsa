/*
 * HolidaysServiceLocal.java
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
package com.mg.merp.reference;

import java.io.Serializable;
import java.util.Date;

import com.mg.merp.reference.model.Holidays;

/**
 * Бизнес-компонент "Праздничные дни"
 * 
 * @author leonova
 * @version $Id: HolidaysServiceLocal.java,v 1.2 2007/07/30 06:27:42 safonov Exp $
 */
public interface HolidaysServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<Holidays,Integer>
{

	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/reference/Holidays";
	
	/**
	 * является ли дата праздничным днем
	 * 
	 * @param date	дата
	 * @return	<code>true</code> если дата праздничный день
	 */
	boolean isDayHoliday(Date date);
	
	/**
	 * копирование праздничных дней на следующий год
	 * 
	 * @param keys
	 */
	void copyHolidays(Serializable[] keys);

}
