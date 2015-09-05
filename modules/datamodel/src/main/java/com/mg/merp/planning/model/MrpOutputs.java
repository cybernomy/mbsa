/*
 * MrpOutputs.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

/**
 * @author hbm2java
 * @version $Id: MrpOutputs.java,v 1.2 2007/07/30 10:37:30 safonov Exp $
 */
public class MrpOutputs extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

	private java.util.Date RequiredDate;

	private java.lang.String PpReference;

	private java.lang.Integer ReferenceId;

	private java.math.BigDecimal MrpQuantity;

	private MRPOrderType MrpOrderType;

	private MRPSource MrpSource;

	private java.lang.Integer WarehouseId;

	private java.lang.Integer CatalogId;

	// Constructors

	/** default constructor */
	public MrpOutputs() {
	}

	/** constructor with id */
	public MrpOutputs(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
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

	public java.util.Date getRequiredDate() {
		return this.RequiredDate;
	}

	public void setRequiredDate(java.util.Date RequiredDate) {
		this.RequiredDate = RequiredDate;
	}

	/**
	 * 
	 */

	public java.lang.String getPpReference() {
		return this.PpReference;
	}

	public void setPpReference(java.lang.String PpReference) {
		this.PpReference = PpReference;
	}

	/**
	 * 
	 */

	public java.lang.Integer getReferenceId() {
		return this.ReferenceId;
	}

	public void setReferenceId(java.lang.Integer ReferenceId) {
		this.ReferenceId = ReferenceId;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getMrpQuantity() {
		return this.MrpQuantity;
	}

	public void setMrpQuantity(java.math.BigDecimal MrpQuantity) {
		this.MrpQuantity = MrpQuantity;
	}

	/**
	 * 
	 */

	public MRPOrderType getMrpOrderType() {
		return this.MrpOrderType;
	}

	public void setMrpOrderType(MRPOrderType MrpOrderType) {
		this.MrpOrderType = MrpOrderType;
	}

	/**
	 * 
	 */

	public MRPSource getMrpSource() {
		return this.MrpSource;
	}

	public void setMrpSource(MRPSource MrpSource) {
		this.MrpSource = MrpSource;
	}

	/**
	 * 
	 */

	public java.lang.Integer getWarehouseId() {
		return this.WarehouseId;
	}

	public void setWarehouseId(java.lang.Integer WarehouseId) {
		this.WarehouseId = WarehouseId;
	}

	/**
	 * 
	 */

	public java.lang.Integer getCatalogId() {
		return this.CatalogId;
	}

	public void setCatalogId(java.lang.Integer CatalogId) {
		this.CatalogId = CatalogId;
	}

}