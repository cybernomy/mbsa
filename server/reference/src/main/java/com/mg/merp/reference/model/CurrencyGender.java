/*
 * CurrencyGender.java.
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
 * Род наименования валюты
 * 
 * @author leonova
 * @version $Id: CurrencyGender.java,v 1.1 2006/03/29 13:06:23 safonov Exp $
 */
@DataItemName ("Reference.Currency.CurrencyGender")
public enum CurrencyGender {
	/**
	 * Женский
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#CurrencyGender.Female")
	FEMALE,
	
	/**
	 * Мужской
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#CurrencyGender.Male")
	MALE,
	
	/**
	 * Средний
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#CurrencyGender.Neuter")
	NEUTER

}

