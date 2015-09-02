/*
 * TaxResult.java
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
package com.mg.merp.lbschedule.model;

import java.math.BigDecimal;

/**
 * Модель данных результата расчета налогов
 * 
 * @author Artem V. Sharapov
 * @version $Id: TaxResult.java,v 1.1 2007/04/17 12:49:25 sharapov Exp $
 */
public class TaxResult {

	private BigDecimal taxPrice;
	private BigDecimal taxSum;
	
	
	public TaxResult(BigDecimal taxPrice, BigDecimal taxSum) {
		this.taxPrice = taxPrice;
		this.taxSum = taxSum;
	}

	/**
	 * @return the taxPrice
	 */
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	
	/**
	 * @param taxPrice the taxPrice to set
	 */
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	
	/**
	 * @return the taxSum
	 */
	public BigDecimal getTaxSum() {
		return taxSum;
	}
	
	/**
	 * @param taxSum the taxSum to set
	 */
	public void setTaxSum(BigDecimal taxSum) {
		this.taxSum = taxSum;
	}
	
}
