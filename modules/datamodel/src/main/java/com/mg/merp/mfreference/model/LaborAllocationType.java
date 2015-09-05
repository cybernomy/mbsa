package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Метод начисления накладных расходов на рабочую силу
 * 
 * @author leonova
 * @version $Id: LaborAllocationType.java,v 1.1 2006/06/21 05:13:37 leonova Exp $
 */
@DataItemName("MfReference.LaborAllocationType")
public enum LaborAllocationType {

	/**
	 * <>
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.None")
	NONE,
	/**
	 * На единицу времени
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.UnitTime")
	UNITTIME,
	
	/**
	 * На единицу ГП
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.UnitProduction")
	UNITPRODUCTION,
	
	/**
	 * Процент от стоимости работ
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.Percent")
	PERCENT,
	
	/**
	 * Фиксированная стоимость на партию
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#LaborAllocationType.PriceParty")
	PRICEPARTY
	
}