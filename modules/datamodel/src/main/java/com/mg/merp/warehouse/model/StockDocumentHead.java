/*
 * StockDocumentHead.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: StockDocumentHead.java,v 1.6 2008/02/29 12:35:16 safonov Exp $
 */
public class StockDocumentHead extends com.mg.merp.document.model.DocHead
		implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

	// Fields

	private java.math.BigDecimal SummaCurWithDiscount;

	private java.math.BigDecimal SummaNatWithDiscount;

	private java.math.BigDecimal AddExpenses;

	private java.math.BigDecimal DiscountOnDoc;

	private java.math.BigDecimal DiscountOnLine;

	// Constructors

	/** default constructor */
	public StockDocumentHead() {
	}

	// Property accessors

	@DataItemName("Warehouse.BillHead.SummaCurWithDiscount")
	public java.math.BigDecimal getSummaCurWithDiscount() {
		return this.SummaCurWithDiscount;
	}

	public void setSummaCurWithDiscount(
			java.math.BigDecimal SummaCurWithDiscount) {
		this.SummaCurWithDiscount = SummaCurWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.SummaNatWithDiscount")
	public java.math.BigDecimal getSummaNatWithDiscount() {
		return this.SummaNatWithDiscount;
	}

	public void setSummaNatWithDiscount(
			java.math.BigDecimal SummaNatWithDiscount) {
		this.SummaNatWithDiscount = SummaNatWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.WareDocHead.AddExpenses")
	public java.math.BigDecimal getAddExpenses() {
		return this.AddExpenses;
	}

	public void setAddExpenses(java.math.BigDecimal AddExpenses) {
		this.AddExpenses = AddExpenses;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.DiscountOnDoc")
	public java.math.BigDecimal getDiscountOnDoc() {
		return this.DiscountOnDoc;
	}

	public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
		this.DiscountOnDoc = DiscountOnDoc;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BillHead.DiscountOnLine")
	public java.math.BigDecimal getDiscountOnLine() {
		return this.DiscountOnLine;
	}

	public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
		this.DiscountOnLine = DiscountOnLine;
	}

}