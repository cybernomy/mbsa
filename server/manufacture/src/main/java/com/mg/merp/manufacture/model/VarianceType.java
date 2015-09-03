package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип отклонения
 * 
 * @author leonova
 * @version $Id: VarianceType.java,v 1.2 2007/08/06 12:45:35 safonov Exp $
 */
@DataItemName("Manufacture.VarianceType")
public enum VarianceType {
	/**
	 * Стоимость материала
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.MaterialCostVariance")
	MATERIAL_COST_VARIANCE,
	
	/**
	 * Количество материала
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.MaterialUsageVariance")
	MATERIAL_USAGE_VARIANCE,
	
	/**
	 * Оплата труда РС
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.LaborRateVariance")
	LABOR_RATE_VARIANCE,
	
	/**
	 * Время по РС
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.LaborUsageVariance")
	LABOR_USAGE_VARIANCE,
	
	/**
	 * Возмещение оборудования
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.MachineRateVariance")
	MACHINE_RATE_VARIANCE,
	
	/**
	 * Время по оборудованию
	 */
	@EnumConstantText ("resource://com.mg.merp.manufacture.resources.dataitemlabels#VarianceType.MachineUsageVariance")
	MACHINE_USAGE_VARIANCE
}

