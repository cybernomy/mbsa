/*
 * Route.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Route.java,v 1.5 2007/07/30 10:25:11 safonov Exp $
 */
public class Route extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Contractor Vendor;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.reference.model.Contractor Customer;

	private com.mg.merp.warehouse.model.Warehouse SrcWarehouse;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.warehouse.model.Warehouse DestWarehouse;

	private RouteSrcType SrcType;

	private java.lang.Integer SrcCycle;

	private java.lang.Short SrcRank;

	private java.math.BigDecimal SupplyPercent;

	private RouteDestType DestType;

	private java.math.BigDecimal ReorderMinQty;

	private java.math.BigDecimal ReorderLotSize;

	private java.lang.Integer DestCycle;

	private java.lang.Short QcReceivingDays;

	private java.lang.Short LeadTime;

	private java.lang.Short SafetyDays;

	// Constructors

	/** default constructor */
	public Route() {
	}

	/** constructor with id */
	public Route(java.lang.Integer Id) {
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

	@DataItemName("MfReference.Route.Vendor")
	public com.mg.merp.reference.model.Contractor getVendor() {
		return this.Vendor;
	}

	public void setVendor(com.mg.merp.reference.model.Contractor Vendor) {
		this.Vendor = Vendor;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.Catalog")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.Customer")
	public com.mg.merp.reference.model.Contractor getCustomer() {
		return this.Customer;
	}

	public void setCustomer(com.mg.merp.reference.model.Contractor Customer) {
		this.Customer = Customer;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.SrcWarehouse")
	public com.mg.merp.warehouse.model.Warehouse getSrcWarehouse() {
		return this.SrcWarehouse;
	}

	public void setSrcWarehouse(
			com.mg.merp.warehouse.model.Warehouse SrcWarehouse) {
		this.SrcWarehouse = SrcWarehouse;
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

	@DataItemName("MfReference.Route.DestWarehouse")
	public com.mg.merp.warehouse.model.Warehouse getDestWarehouse() {
		return this.DestWarehouse;
	}

	public void setDestWarehouse(
			com.mg.merp.warehouse.model.Warehouse DestWarehouse) {
		this.DestWarehouse = DestWarehouse;
	}

	/**
	 * 
	 */

	public RouteSrcType getSrcType() {
		return this.SrcType;
	}

	public void setSrcType(RouteSrcType SrcType) {
		this.SrcType = SrcType;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.SrcCycle")
	public java.lang.Integer getSrcCycle() {
		return this.SrcCycle;
	}

	public void setSrcCycle(java.lang.Integer SrcCycle) {
		this.SrcCycle = SrcCycle;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.SrcRank")
	public java.lang.Short getSrcRank() {
		return this.SrcRank;
	}

	public void setSrcRank(java.lang.Short SrcRank) {
		this.SrcRank = SrcRank;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.SupplyPercent")
	public java.math.BigDecimal getSupplyPercent() {
		return this.SupplyPercent;
	}

	public void setSupplyPercent(java.math.BigDecimal SupplyPercent) {
		this.SupplyPercent = SupplyPercent;
	}

	/**
	 * 
	 */

	public RouteDestType getDestType() {
		return this.DestType;
	}

	public void setDestType(RouteDestType DestType) {
		this.DestType = DestType;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.ReMinQty")
	public java.math.BigDecimal getReorderMinQty() {
		return this.ReorderMinQty;
	}

	public void setReorderMinQty(java.math.BigDecimal ReorderMinQty) {
		this.ReorderMinQty = ReorderMinQty;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.ReorderLotSize")
	public java.math.BigDecimal getReorderLotSize() {
		return this.ReorderLotSize;
	}

	public void setReorderLotSize(java.math.BigDecimal ReorderLotSize) {
		this.ReorderLotSize = ReorderLotSize;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.DestCycle")
	public java.lang.Integer getDestCycle() {
		return this.DestCycle;
	}

	public void setDestCycle(java.lang.Integer DestCycle) {
		this.DestCycle = DestCycle;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.Route.QcReceivingDays")
	public java.lang.Short getQcReceivingDays() {
		return this.QcReceivingDays;
	}

	public void setQcReceivingDays(java.lang.Short QcReceivingDays) {
		this.QcReceivingDays = QcReceivingDays;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.LeadTime")
	public java.lang.Short getLeadTime() {
		return this.LeadTime;
	}

	public void setLeadTime(java.lang.Short LeadTime) {
		this.LeadTime = LeadTime;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.Route.SafetyDays")
	public java.lang.Short getSafetyDays() {
		return this.SafetyDays;
	}

	public void setSafetyDays(java.lang.Short SafetyDays) {
		this.SafetyDays = SafetyDays;
	}

}