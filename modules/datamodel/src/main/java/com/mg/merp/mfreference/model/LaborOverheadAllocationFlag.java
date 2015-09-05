/*
 * LaborOverheadAllocationFlag.java
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
 * Метод начисления накладных расходов на рабочую силу
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: LaborOverheadAllocationFlag.java,v 1.1 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("Manufacture.LbrOhAllocationFlag")
public enum LaborOverheadAllocationFlag {
	/**
	 * На единицу времени
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#LbrOhAllocFlag.Time")
	TIME,
	
	/**
	 * На единицу готовой продукции
	*/
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#LbrOhAllocFlag.Unit")
	UNIT,
	
	/**
	 *  Процент от стоимости работы
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#LbrOhAllocFlag.Cost")
	COST,
	
	/**
	 *  Фиксированная стоимость на партию ГП
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#LbrOhAllocFlag.Fixed")
	FIXED

}