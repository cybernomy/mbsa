/*
 * MonthOfYear.java
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

package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Месяцы года
 * 
 * @author leonova
 * @version $Id: MonthOfYear.java,v 1.1 2006/10/10 06:29:36 leonova Exp $
 */
@DataItemName("Reference.QMonth")
public enum MonthOfYear {
	/**
	 * Январь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.January")
	JANUARY,
	
	/**
	 * Февраль
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.February")
	FEBRUARY,
	
	/**
	 * Март
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.March")
	MARCH,
	
	/**
	 * Апрель
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.April")
	APRIL,
	
	/**
	 * Май
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.May")
	MAY,
	
	/**
	 * Июнь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.June")
	JUNE,
	
	/**
	 * Июль
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.July")
	JULY,
	
	/**
	 * Август
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.August")
	AUGUST,
	
	/**
	 * Сентябрь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.September")
	SEPTEMBER,
	
	/**
	 * Октябрь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.October")
	OCTORER,
	
	/**
	 * Ноябрь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.November")
	NOVEMBER,
	
	/**
	 * Декабрь
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.December")
	DECEMBER
	
}
