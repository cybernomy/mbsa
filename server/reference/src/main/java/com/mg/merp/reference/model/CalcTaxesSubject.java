/*
 * CalcTaxesSubject.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Вид начисдение налога
 * 
 * @author leonova
 * @version $Id: CalcTaxesSubject.java,v 1.3 2007/06/20 12:00:47 safonov Exp $
 */
@DataItemName("Reference.CalcTaxesSubject")
public enum CalcTaxesSubject {
	
	/**
	 * цену
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#CalcTaxesSubject.Price")
	PRICE,
	
	/**
	 * сумму
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#CalcTaxesSubject.Summa")
	SUMMA
}
