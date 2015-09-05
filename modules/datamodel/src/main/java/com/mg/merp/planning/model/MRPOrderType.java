/*
 * MRPOrderType.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип заказа ППМ
 * 
 * @author Oleg V. Safonov
 * @version $Id: MRPOrderType.java,v 1.1 2007/07/30 10:37:30 safonov Exp $
 */
@DataItemName ("Planning.MRPOrderType")
public enum MRPOrderType {
	/**
	 * Действительный
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#MRPOrderType.Actual")
	ACTUAL,
	
	/**
	 * Закупка
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#MRPOrderType.Purchase")
	PURCHASE,
	
	/**
	 * Прогноз
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#MRPOrderType.Forecast")
	FORECAST,
	
	/**
	 * Запланировано
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#MRPOrderType.FirmPlanned")
	FIRM_PLANNED,
	
	/**
	 * Предлагаемый
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#MRPOrderType.RescheduleSuggested")
	RESCHEDULE_SUGGESTED
}
