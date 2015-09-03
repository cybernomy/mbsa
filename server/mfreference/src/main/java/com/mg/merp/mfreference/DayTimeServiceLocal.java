/*
 * DayTimeServiceLocal.java
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
package com.mg.merp.mfreference;

import com.mg.merp.mfreference.model.DayTime;
import com.mg.merp.mfreference.model.ScheduleDirection;

/**
 * Бизнес-компонент "Сменный календарь"
 * 
 * @author leonova
 * @version $Id: DayTimeServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface DayTimeServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<DayTime, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/mfreference/DayTime";
	
	/**
	 * получение диапазона времени
	 * 
	 * @param weekCalId			недельный календарь
	 * @param baseDateTime		дата
	 * @param runTime			время в тиках
	 * @param schedDirection	направление планирования
	 * @return	диапазон времени
	 */
	TimeRange getTimes(int weekCalId, long baseDateTime, long runTime, ScheduleDirection schedDirection);

}
