/*
 * OrderSpec.java
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
 * @version $Id: OrderSpec.java,v 1.6 2007/09/27 15:38:46 safonov Exp $
 */
public class OrderSpec extends BaseStockDocumentSpec implements
		java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Contractor Warehouse;

	private java.util.Date RequiredDate;

	private java.util.Date PromisedDate;

	private java.lang.String VendorItemCode;

	private java.math.BigDecimal QtyAccepted;

	private java.math.BigDecimal QtyInvoiced;

	private java.math.BigDecimal QtyReturned;

	private java.math.BigDecimal QtyShipped;

	private java.math.BigDecimal QtyPicked;

	private java.math.BigDecimal QtyOutstanding;

	private boolean ClosedForPlanning;

	private OrderStatus Status;

	// Constructors

	/** default constructor */
	public OrderSpec() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.Warehouse")
	public com.mg.merp.reference.model.Contractor getWarehouse() {
		return this.Warehouse;
	}

	public void setWarehouse(com.mg.merp.reference.model.Contractor Contractor) {
		this.Warehouse = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.RequiredDate")
	public java.util.Date getRequiredDate() {
		return this.RequiredDate;
	}

	public void setRequiredDate(java.util.Date RequiredDate) {
		this.RequiredDate = RequiredDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.PromisedDate")
	public java.util.Date getPromisedDate() {
		return this.PromisedDate;
	}

	public void setPromisedDate(java.util.Date PromisedDate) {
		this.PromisedDate = PromisedDate;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.VendorItemCode")
	public java.lang.String getVendorItemCode() {
		return this.VendorItemCode;
	}

	public void setVendorItemCode(java.lang.String VendorItemCode) {
		this.VendorItemCode = VendorItemCode;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getQtyAccepted() {
		return this.QtyAccepted;
	}

	public void setQtyAccepted(java.math.BigDecimal QtyAccepted) {
		this.QtyAccepted = QtyAccepted;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getQtyInvoiced() {
		return this.QtyInvoiced;
	}

	public void setQtyInvoiced(java.math.BigDecimal QtyInvoiced) {
		this.QtyInvoiced = QtyInvoiced;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.QtyReturned")
	public java.math.BigDecimal getQtyReturned() {
		return this.QtyReturned;
	}

	public void setQtyReturned(java.math.BigDecimal QtyReturned) {
		this.QtyReturned = QtyReturned;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.QtyShipped")
	public java.math.BigDecimal getQtyShipped() {
		return this.QtyShipped;
	}

	public void setQtyShipped(java.math.BigDecimal QtyShipped) {
		this.QtyShipped = QtyShipped;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.QtyPicked")
	public java.math.BigDecimal getQtyPicked() {
		return this.QtyPicked;
	}

	public void setQtyPicked(java.math.BigDecimal QtyPicked) {
		this.QtyPicked = QtyPicked;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.QtyOutstanding")
	public java.math.BigDecimal getQtyOutstanding() {
		return this.QtyOutstanding;
	}

	public void setQtyOutstanding(java.math.BigDecimal QtyOutstanding) {
		this.QtyOutstanding = QtyOutstanding;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.OrderSpec.ClosedForPlanning")
	public boolean getClosedForPlanning() {
		return this.ClosedForPlanning;
	}

	public void setClosedForPlanning(boolean ClosedForPlanning) {
		this.ClosedForPlanning = ClosedForPlanning;
	}

	/**
	 * 
	 */
	public OrderStatus getStatus() {
		return this.Status;
	}

	public void setStatus(OrderStatus Status) {
		this.Status = Status;
	}

}