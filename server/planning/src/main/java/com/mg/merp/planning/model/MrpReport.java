/*
 * MrpReport.java
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
 * @version $Id: MrpReport.java,v 1.4 2007/07/30 10:37:30 safonov Exp $
 */
public class MrpReport extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

	private com.mg.merp.warehouse.model.Warehouse Warehouse;

	private com.mg.merp.reference.model.Catalog Catalog;

	private java.util.Date RequiredDate;

	private com.mg.merp.planning.model.MRPOrderType MrpOrderType;

	private java.lang.String PpReference;

	private java.math.BigDecimal QtyAvailable;

	private java.math.BigDecimal MrpQuantity;

	private com.mg.merp.planning.model.MRPSource MrpSource;

	private com.mg.merp.planning.model.InputOutputFlag MrpInputOutputFlag;

	private boolean FixedInput;

	private java.util.Date OrderDate;

	private boolean MrpArrearsFlag;

	private java.util.Date OriginalDate;

	private java.math.BigDecimal OriginalQuantity;

	private java.lang.Integer Sequence;

	private com.mg.merp.planning.model.RescheduleFlag MrpRescheduleFlag;

	// Constructors

	/** default constructor */
	public MrpReport() {
	}

	/** constructor with id */
	public MrpReport(java.lang.Integer Id) {
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
	@DataItemName("Planning.MRPRecommendation.RequiredDate")
	public java.util.Date getRequiredDate() {
		return this.RequiredDate;
	}

	public void setRequiredDate(java.util.Date RequiredDate) {
		this.RequiredDate = RequiredDate;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.MRPOrderType getMrpOrderType() {
		return this.MrpOrderType;
	}

	public void setMrpOrderType(
			com.mg.merp.planning.model.MRPOrderType MrpOrderType) {
		this.MrpOrderType = MrpOrderType;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPRecommendation.Reference")
	public java.lang.String getPpReference() {
		return this.PpReference;
	}

	public void setPpReference(java.lang.String PpReference) {
		this.PpReference = PpReference;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPReport.QtyAvailable")
	public java.math.BigDecimal getQtyAvailable() {
		return this.QtyAvailable;
	}

	public void setQtyAvailable(java.math.BigDecimal QtyAvailable) {
		this.QtyAvailable = QtyAvailable;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPReport.MrpQuantity")
	public java.math.BigDecimal getMrpQuantity() {
		return this.MrpQuantity;
	}

	public void setMrpQuantity(java.math.BigDecimal MrpQuantity) {
		this.MrpQuantity = MrpQuantity;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.MRPSource getMrpSource() {
		return this.MrpSource;
	}

	public void setMrpSource(com.mg.merp.planning.model.MRPSource MrpSource) {
		this.MrpSource = MrpSource;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.InputOutputFlag getMrpInputOutputFlag() {
		return this.MrpInputOutputFlag;
	}

	public void setMrpInputOutputFlag(
			com.mg.merp.planning.model.InputOutputFlag MrpInputOutputFlag) {
		this.MrpInputOutputFlag = MrpInputOutputFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPReport.FixedInput")
	public boolean getFixedInput() {
		return this.FixedInput;
	}

	public void setFixedInput(boolean FixedInput) {
		this.FixedInput = FixedInput;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPRecommendation.OrderDate")
	public java.util.Date getOrderDate() {
		return this.OrderDate;
	}

	public void setOrderDate(java.util.Date OrderDate) {
		this.OrderDate = OrderDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPRecommendation.MRPArrearsFlag")
	public boolean getMrpArrearsFlag() {
		return this.MrpArrearsFlag;
	}

	public void setMrpArrearsFlag(boolean MrpArrearsFlag) {
		this.MrpArrearsFlag = MrpArrearsFlag;
	}

	/**
	 * 
	 */

	public com.mg.merp.warehouse.model.Warehouse getWarehouse() {
		return this.Warehouse;
	}

	public void setWarehouse(com.mg.merp.warehouse.model.Warehouse Warehouse) {
		this.Warehouse = Warehouse;
	}

	/**
	 * 
	 */

	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPRecommendation.OriginalDate")
	public java.util.Date getOriginalDate() {
		return this.OriginalDate;
	}

	public void setOriginalDate(java.util.Date OriginalDate) {
		this.OriginalDate = OriginalDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPRecommendation.OriginalQuantity")
	public java.math.BigDecimal getOriginalQuantity() {
		return this.OriginalQuantity;
	}

	public void setOriginalQuantity(java.math.BigDecimal OriginalQuantity) {
		this.OriginalQuantity = OriginalQuantity;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRPSequence")
	public java.lang.Integer getSequence() {
		return this.Sequence;
	}

	public void setSequence(java.lang.Integer Sequence) {
		this.Sequence = Sequence;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.RescheduleFlag getMrpRescheduleFlag() {
		return this.MrpRescheduleFlag;
	}

	public void setMrpRescheduleFlag(com.mg.merp.planning.model.RescheduleFlag MrpRescheduleFlag) {
		this.MrpRescheduleFlag = MrpRescheduleFlag;
	}

}