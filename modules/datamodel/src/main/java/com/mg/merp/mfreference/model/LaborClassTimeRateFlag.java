/*
 * LaborClassTimeRateFlag.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Метод измерения расхода
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: LaborClassTimeRateFlag.java,v 1.2 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("MfReference.LbrCl.TimeRateFlag")
public enum LaborClassTimeRateFlag {
	/**
	 * Время на единицу ГП
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#TimeRateFlag.Xxx")
	TIME,
	
	/**
	 * Единиц ГП на время
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#TimeRateFlag.Yyy")
	RATE,
	
	/**
	 * Фиксированное время на партию ГП
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#TimeRateFlag.Zzz")
	FIXED
}
