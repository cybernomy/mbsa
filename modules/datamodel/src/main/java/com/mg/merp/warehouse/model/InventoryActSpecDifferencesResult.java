/*
 * InventoryActSpecDifferencesResult.java
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
package com.mg.merp.warehouse.model;

import java.math.BigDecimal;

/**
 * Модель даныых "Расхождения по позиции спецификации инвентаризационного акта"
 * 
 * @author Artem V. Sharapov
 * @version $Id: InventoryActSpecDifferencesResult.java,v 1.1 2007/06/18 12:50:37 sharapov Exp $
 */
public class InventoryActSpecDifferencesResult {
	
	// Fields
	
	private BigDecimal differenceQuntity;
	private BigDecimal differenceQuntity2;
	private BigDecimal differenceSum;
	
	
	// Default constructor
	public InventoryActSpecDifferencesResult() {
	}
	
	// Constructor with params
	public InventoryActSpecDifferencesResult(BigDecimal differenceQuntity, BigDecimal differenceQuntity2, BigDecimal differenceSum) {
		this.differenceQuntity = differenceQuntity;
		this.differenceQuntity2 = differenceQuntity2;
		this.differenceSum = differenceSum;
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
	
}
