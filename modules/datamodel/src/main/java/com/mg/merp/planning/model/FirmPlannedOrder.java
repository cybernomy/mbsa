/*
 * FirmPlannedOrder.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FirmPlannedOrder.java,v 1.4 2007/07/30 10:37:30 safonov Exp $
 */
public class FirmPlannedOrder extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor Vendor;

	private com.mg.merp.reference.model.Measure Measure;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.reference.model.Contractor Warehouse;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

	private com.mg.merp.reference.model.Contractor SourceWarehouse;

	private com.mg.merp.planning.model.MrpRecommendation MrpRecommendation;

	private java.util.Date RequiredDate;

	private java.math.BigDecimal OrderQty;

	private java.util.Date OrderDate;

	private boolean FixedInput;

	private boolean RequisitionFlag;

	private RecommendType PurchaseOrTransfer;

	private boolean ManualEntry;

	// Constructors

	/** default constructor */
	public FirmPlannedOrder() {
	}

	/** constructor with id */
	public FirmPlannedOrder(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.Vendor")
	public com.mg.merp.reference.model.Contractor getVendor() {
		return this.Vendor;
	}

	public void setVendor(com.mg.merp.reference.model.Contractor Vendor) {
		this.Vendor = Vendor;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.Measure")
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.Measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.Measure = Measure;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.Catalog")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.Warehouse")
	public com.mg.merp.reference.model.Contractor getWarehouse() {
		return this.Warehouse;
	}

	public void setWarehouse(com.mg.merp.reference.model.Contractor Warehouse) {
		this.Warehouse = Warehouse;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.MrpVersionControl")
	public com.mg.merp.planning.model.MrpVersionControl getMrpVersionControl() {
		return this.MrpVersionControl;
	}

	public void setMrpVersionControl(
			com.mg.merp.planning.model.MrpVersionControl MrpVersionControl) {
		this.MrpVersionControl = MrpVersionControl;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.SourceWarehouse")
	public com.mg.merp.reference.model.Contractor getSourceWarehouse() {
		return this.SourceWarehouse;
	}

	public void setSourceWarehouse(
			com.mg.merp.reference.model.Contractor SourceWarehouse) {
		this.SourceWarehouse = SourceWarehouse;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.MrpRecommendation getMrpRecommendation() {
		return this.MrpRecommendation;
	}

	public void setMrpRecommendation(
			com.mg.merp.planning.model.MrpRecommendation MrpRecommendation) {
		this.MrpRecommendation = MrpRecommendation;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.RequiredDate")
	public java.util.Date getRequiredDate() {
		return this.RequiredDate;
	}

	public void setRequiredDate(java.util.Date RequiredDate) {
		this.RequiredDate = RequiredDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.OrderQty")
	public java.math.BigDecimal getOrderQty() {
		return this.OrderQty;
	}

	public void setOrderQty(java.math.BigDecimal OrderQty) {
		this.OrderQty = OrderQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.OrderDate")
	public java.util.Date getOrderDate() {
		return this.OrderDate;
	}

	public void setOrderDate(java.util.Date OrderDate) {
		this.OrderDate = OrderDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.FixedInput")
	public boolean getFixedInput() {
		return this.FixedInput;
	}

	public void setFixedInput(boolean FixedInput) {
		this.FixedInput = FixedInput;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.RequisitionFlag")
	public boolean getRequisitionFlag() {
		return this.RequisitionFlag;
	}

	public void setRequisitionFlag(boolean RequisitionFlag) {
		this.RequisitionFlag = RequisitionFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.FirmPlanOrder.AllocOrderType")
	public RecommendType getPurchaseOrTransfer() {
		return this.PurchaseOrTransfer;
	}

	public void setPurchaseOrTransfer(RecommendType PurchaseOrTransfer) {
		this.PurchaseOrTransfer = PurchaseOrTransfer;
	}

	/**
	 * 
	 */

	public boolean getManualEntry() {
		return this.ManualEntry;
	}

	public void setManualEntry(boolean ManualEntry) {
		this.ManualEntry = ManualEntry;
	}

}