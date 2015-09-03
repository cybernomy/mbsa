/*
 * PayWay.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * тип вида проведения платежа
 * 
 * @author leonova
 * @version $Id: PayWayType.java,v 1.1 2006/03/30 11:22:13 safonov Exp $
 */
@DataItemName ("Account.BankOut.PayWayType")
public enum PayWayType {
	/**
	 * <>
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.None")
	NONE,
	
	/**
	 * Почта
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.Post")
	POST,
	
	/**
	 * Электронно
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.Electronic")
	ELECTRONIC,
	
	/**
	 * Телеграф
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.Telegraph")
	TELEGRAPH,
	
	/**
	 * Клиринг
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.Clearing")
	CLEARING,
	
	/**
	 * Внутри региона
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.InsideRegion")
	INSIDEREGION,
	
	/**
	 * Расч.сист.СБ
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#PayWayType.SberBank")
	SBERBANK	
}
