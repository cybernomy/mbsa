/*
 * MachineOverheadAllocationFlag.java
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
 * Метод начисления накладных расходов на оборудование
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: MachineOverheadAllocationFlag.java,v 1.2 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("Manufacture.MchOhAllocationFlag")
public enum MachineOverheadAllocationFlag {
	/**
	 * На 1 час операции
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchOhAllocationFlag.Time")
	TIME,
	
	/**
	 * На единицу готовой продукции
	*/
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchOhAllocationFlag.Unit")
	UNIT,
	
	/**
	 *  Процент от стоимости работы
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchOhAllocationFlag.Cost")
	COST,
	
	/**
	 *  Фиксированная стоимость на партию ГП
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchOhAllocationFlag.Fixed")
	FIXED

}
