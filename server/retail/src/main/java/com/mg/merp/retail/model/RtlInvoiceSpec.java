/*
 * RtlInvoiceSpec.java
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
package com.mg.merp.retail.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Спецификация документов на отпуск"
 * 
 * @author Artem V. Sharapov
 * @version $Id: RtlInvoiceSpec.java,v 1.5 2007/10/30 14:43:19 sharapov Exp $
 */
public class RtlInvoiceSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

	// Fields

	private java.math.BigDecimal Discount;

	private java.math.BigDecimal PriceWithDiscount;

	private java.math.BigDecimal SummaWithDiscount;

	private java.math.BigDecimal DocDiscount;

	private java.math.BigDecimal externalDiscountValue;


	// Constructors

	/** default constructor */
	public RtlInvoiceSpec() {
	}

	// Property accessors

	/**
	 * 
	 */
	@DataItemName("Retail.Discount") //$NON-NLS-1$
	public java.math.BigDecimal getDiscount() {
		return this.Discount;
	}

	public void setDiscount(java.math.BigDecimal Discount) {
		this.Discount = Discount;
	}

	/**
	 * 
	 */
	@DataItemName("Retail.DocDiscount") //$NON-NLS-1$
	public java.math.BigDecimal getDocDiscount() {
		return this.DocDiscount;
	}

	public void setDocDiscount(java.math.BigDecimal DocDiscount) {
		this.DocDiscount = DocDiscount;
	}

	/**
	 * @return the externalDiscountValue
	 */
	public java.math.BigDecimal getExternalDiscountValue() {
		return this.externalDiscountValue;
	}

	/**
	 * @param externalDiscountValue the externalDiscountValue to set
	 */
	public void setExternalDiscountValue(java.math.BigDecimal externalDiscountValue) {
		this.externalDiscountValue = externalDiscountValue;
	}

	/**
	 * @return the priceWithDiscount
	 */
	@DataItemName("Retail.PriceDiscount") //$NON-NLS-1$
	public java.math.BigDecimal getPriceWithDiscount() {
		return this.PriceWithDiscount;
	}

	/**
	 * @param priceWithDiscount the priceWithDiscount to set
	 */
	public void setPriceWithDiscount(java.math.BigDecimal priceWithDiscount) {
		this.PriceWithDiscount = priceWithDiscount;
	}

	/**
	 * @return the summaWithDiscount
	 */
	public java.math.BigDecimal getSummaWithDiscount() {
		return this.SummaWithDiscount;
	}

	/**
	 * @param summaWithDiscount the summaWithDiscount to set
	 */
	public void setSummaWithDiscount(java.math.BigDecimal summaWithDiscount) {
		this.SummaWithDiscount = summaWithDiscount;
	}

}