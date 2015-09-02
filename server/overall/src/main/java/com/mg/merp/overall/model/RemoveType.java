/*
 * RemoveType.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип выбытия из эксплуатации 
 * 
 * @author Anna V. Leonova
 * @version $Id: RemoveType.java,v 1.2 2008/06/30 04:15:16 alikaev Exp $
 */
@DataItemName("Overall.RemoveType")
public enum RemoveType {
	
	/**
	 * <>
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.None")
	NONE,
	
	/**
	 * Выдано
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.GivenOut")
	GIVENOUT,
	
	/**
	 * В эксплуатации
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.InExpl")
	INEXPL,
	
	/**
	 * Возвращено
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.Returned")
	RETURNED,
	
	/**
	 * Утеряно
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.Lost")
	LOST,
	
	/**
	 * Списано
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#RemoveType.WrittenOff")
	WRITTENOFF	
	
}