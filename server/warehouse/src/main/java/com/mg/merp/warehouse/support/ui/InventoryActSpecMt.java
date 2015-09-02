/*
 * InventoryActSpecMt.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.ui;

import java.math.BigDecimal;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.document.generic.ui.GoodsDocumentSpecMaintenanceForm;
import com.mg.merp.warehouse.InventoryActHeadServiceLocal;
import com.mg.merp.warehouse.model.InventoryActSpec;
import com.mg.merp.warehouse.model.InventoryActSpecDifferencesResult;

/**
 * Контроллер формы поддержки спецификации актов инвентаризации
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: InventoryActSpecMt.java,v 1.4 2007/06/18 13:26:55 sharapov Exp $
 */
public class InventoryActSpecMt extends GoodsDocumentSpecMaintenanceForm {

	private BigDecimal differenceQuntity = BigDecimal.ZERO;
	private BigDecimal differenceQuntity2 = BigDecimal.ZERO;
	private BigDecimal differenceSum = BigDecimal.ZERO;


	public InventoryActSpecMt() throws Exception {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		initializeDifferences();
		super.doOnRun();
	}

	/**
	 * Обработчик кнопки "Рассчитать сумму относительно количества"
	 * @param event - событие
	 */
	public void onActionComputeDifferenceQuntities(WidgetEvent event) {
		InventoryActSpecDifferencesResult inventoryActSpecDifferencesResult = getInventoryActHeadService().computeDifferenceByQuantity((InventoryActSpec) getEntity()); 
		differenceQuntity = inventoryActSpecDifferencesResult.getDifferenceQuntity();
		differenceQuntity2 = inventoryActSpecDifferencesResult.getDifferenceQuntity2();
		differenceSum = inventoryActSpecDifferencesResult.getDifferenceSum();
	}

	/**
	 * Обработчик кнопки "Рассчитать количество относительно суммы"
	 * @param event - событие
	 */
	public void onActionComputeDifferenceSum(WidgetEvent event) {
		InventoryActSpecDifferencesResult inventoryActSpecDifferencesResult = getInventoryActHeadService().computeDifferenceBySum((InventoryActSpec) getEntity()); 
		differenceQuntity = inventoryActSpecDifferencesResult.getDifferenceQuntity();
		differenceQuntity2 = inventoryActSpecDifferencesResult.getDifferenceQuntity2();
		differenceSum = inventoryActSpecDifferencesResult.getDifferenceSum();
	}

	private void initializeDifferences() {
		InventoryActSpec inventoryActSpec = (InventoryActSpec) getEntity();
		if(inventoryActSpec != null) {
			BigDecimal realQuantity = BigDecimal.ZERO;
			BigDecimal realQuantity2 = BigDecimal.ZERO;
			BigDecimal realSumma = BigDecimal.ZERO;

			if(inventoryActSpec.getRealQuantity() != null)
				realQuantity = inventoryActSpec.getRealQuantity();
			if(inventoryActSpec.getRealQuantity2() != null)
				realQuantity2 = inventoryActSpec.getRealQuantity2();
			if(inventoryActSpec.getRealSumma() != null)
				realSumma = inventoryActSpec.getRealSumma();

			differenceQuntity = realQuantity.subtract(inventoryActSpec.getQuantity() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity());
			differenceQuntity2 = realQuantity2.subtract(inventoryActSpec.getQuantity2() == null ? BigDecimal.ZERO : inventoryActSpec.getQuantity2());
			differenceSum = realSumma.subtract(inventoryActSpec.getSumma() == null ? BigDecimal.ZERO : inventoryActSpec.getSumma());
		}
	}


	// Property accessors

	public BigDecimal getDifferenceQuntity() {
		return this.differenceQuntity;
	}

	public void setDifferenceQuntity(BigDecimal differenceQuntity) {
		this.differenceQuntity = differenceQuntity;
	}

	public BigDecimal getDifferenceQuntity2() {
		return this.differenceQuntity2;
	}

	public void setDifferenceQuntity2(BigDecimal differenceQuntity2) {
		this.differenceQuntity2 = differenceQuntity2;
	}

	public BigDecimal getDifferenceSum() {
		return this.differenceSum;
	}

	public void setDifferenceSum(BigDecimal differenceSum) {
		this.differenceSum = differenceSum;
	}

	private InventoryActHeadServiceLocal getInventoryActHeadService() {
		return (InventoryActHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/InventoryActHead"); //$NON-NLS-1$
	}

}
