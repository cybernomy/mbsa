/*
 * OrderSpecModel.java
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
 * @version $Id: OrderSpecModel.java,v 1.3 2006/04/13 10:28:32 safonov Exp $
 */
public class OrderSpecModel extends com.mg.merp.document.model.DocSpecModel
		implements java.io.Serializable {

	// Fields

	private java.math.BigDecimal Discount;

	private java.math.BigDecimal PriceWithDiscount;

	private java.math.BigDecimal SummaWithDiscount;

	private java.math.BigDecimal Cost;

	private java.math.BigDecimal DocDiscount;

	// Constructors

	/** default constructor */
	public OrderSpecModel() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpecModel.Discount")
	public java.math.BigDecimal getDiscount() {
		return this.Discount;
	}

	public void setDiscount(java.math.BigDecimal Discount) {
		this.Discount = Discount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpecModel.PriceWithDiscount")
	public java.math.BigDecimal getPriceWithDiscount() {
		return this.PriceWithDiscount;
	}

	public void setPriceWithDiscount(java.math.BigDecimal PriceWithDiscount) {
		this.PriceWithDiscount = PriceWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpecModel.SummaWithDiscount")
	public java.math.BigDecimal getSummaWithDiscount() {
		return this.SummaWithDiscount;
	}

	public void setSummaWithDiscount(java.math.BigDecimal SummaWithDiscount) {
		this.SummaWithDiscount = SummaWithDiscount;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpecModel.Cost")
	public java.math.BigDecimal getCost() {
		return this.Cost;
	}

	public void setCost(java.math.BigDecimal Cost) {
		this.Cost = Cost;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpecModel.DocDiscount")
	public java.math.BigDecimal getDocDiscount() {
		return this.DocDiscount;
	}

	public void setDocDiscount(java.math.BigDecimal DocDiscount) {
		this.DocDiscount = DocDiscount;
	}

}