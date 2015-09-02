/*
 * AllocationParamsDlg.java
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
package com.mg.merp.paymentalloc.support.ui;

import java.math.BigDecimal;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;

/**
 * Контроллер диалога "Параметры распределения"
 * 
 * @author Artem V. Sharapov
 * @version $Id: AllocationParamsDlg.java,v 1.1 2007/05/31 14:13:30 sharapov Exp $
 */
public class AllocationParamsDlg extends DefaultDialog {

	private BigDecimal allocationSum;
	private BigDecimal allocationQty;
	private BigDecimal price;
	
	public AllocationParamsDlg() {
		
	}
	
	/**
	 * Обработчик кнопки "Рассчитать количество"
	 * @param event - событие
	 */
	public void onActionComputeQty(WidgetEvent event) {
		if(allocationSum != null && price != null)
			if(BigDecimal.ZERO.compareTo(price) != 0)
				allocationQty = allocationSum.divide(price);
	}

	/**
	 * Обработчик кнопки "Рассчитать сумму"
	 * @param event - событие
	 */
	public void onActionComputeSum(WidgetEvent event) {
		if(allocationQty != null && price != null)
			allocationSum = allocationQty.multiply(price);
	}

	public BigDecimal getAllocationQty() {
		return this.allocationQty;
	}

	public void setAllocationQty(BigDecimal allocationQty) {
		this.allocationQty = allocationQty;
	}

	public BigDecimal getAllocationSum() {
		return this.allocationSum;
	}

	public void setAllocationSum(BigDecimal allocationSum) {
		this.allocationSum = allocationSum;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
