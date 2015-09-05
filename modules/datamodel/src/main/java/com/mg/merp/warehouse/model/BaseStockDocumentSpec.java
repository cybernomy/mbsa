/**
 * BaseStockDocumentSpec.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.model.DocSpec;

/**
 * Базовый класс спецификаций складских документов
 * 
 * @author Oleg V. Safonov
 * @version $Id: BaseStockDocumentSpec.java,v 1.1 2007/09/27 15:38:46 safonov Exp $
 */
public class BaseStockDocumentSpec extends DocSpec {
	// Fields

	private java.math.BigDecimal Cost;

	private java.math.BigDecimal Discount;

	private java.math.BigDecimal PriceWithDiscount;

	private java.math.BigDecimal SummaWithDiscount;

	private java.math.BigDecimal DocDiscount;
	
	private java.math.BigDecimal externalDiscountValue;

	// Constructors

	/** default constructor */
	public BaseStockDocumentSpec() {
	}

	// Property accessors

	@DataItemName("Warehouse.WareDocSpec.Cost")
	public java.math.BigDecimal getCost() {
		return this.Cost;
	}

	public void setCost(java.math.BigDecimal Cost) {
		this.Cost = Cost;
	}

	/**
	 * 
	 */

	@DataItemName("Warehouse.WareDocSpec.Discount")
	public java.math.BigDecimal getDiscount() {
		return this.Discount;
	}

	public void setDiscount(java.math.BigDecimal Discount) {
		this.Discount = Discount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.WareDocSpec.PriceWithDiscount")
	public java.math.BigDecimal getPriceWithDiscount() {
		return this.PriceWithDiscount;
	}

	public void setPriceWithDiscount(java.math.BigDecimal PriceWithDiscount) {
		this.PriceWithDiscount = PriceWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.WareDocSpec.SummaWithDiscount")
	public java.math.BigDecimal getSummaWithDiscount() {
		return this.SummaWithDiscount;
	}

	public void setSummaWithDiscount(java.math.BigDecimal SummaWithDiscount) {
		this.SummaWithDiscount = SummaWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.WareDocSpec.DocDiscount")
	public java.math.BigDecimal getDocDiscount() {
		return this.DocDiscount;
	}

	public void setDocDiscount(java.math.BigDecimal DocDiscount) {
		this.DocDiscount = DocDiscount;
	}

	public java.math.BigDecimal getExternalDiscountValue() {
		return externalDiscountValue;
	}

	public void setExternalDiscountValue(java.math.BigDecimal discountValue) {
		this.externalDiscountValue = discountValue;
	}

}
