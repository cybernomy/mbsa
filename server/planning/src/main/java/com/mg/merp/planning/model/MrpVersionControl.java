/*
 * MrpVersionControl.java
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
 * @version $Id: MrpVersionControl.java,v 1.4 2006/09/13 12:14:41 leonova Exp $
 */
public class MrpVersionControl extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Description;

	private java.lang.Integer MrpVersion;

	private boolean MrpSoFlag;

	private boolean MrpSfFlag;

	private boolean MrpPoFlag;

	private boolean MrpPfFlag;

	private boolean MrpQohFlag;

	private boolean MrpFirmPlannedOrdersFlag;

	private boolean MrpJobFlag;

	private java.lang.Short DampingDays;

	private java.lang.Short QcReceivingDays;

	private java.util.Date RunDate;

	private java.util.Date MrpStartDate;

	private java.util.Date MrpEndDate;

	private java.util.Date LastRunDatetime;

	private boolean MrpSuggestedOrdersFirmed;

	// Constructors

	/** default constructor */
	public MrpVersionControl() {
	}

	/** constructor with id */
	public MrpVersionControl(java.lang.Integer Id) {
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
	@DataItemName("Planning.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Name")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpVersion")
	public java.lang.Integer getMrpVersion() {
		return this.MrpVersion;
	}

	public void setMrpVersion(java.lang.Integer MrpVersion) {
		this.MrpVersion = MrpVersion;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpSoFlag")
	public boolean getMrpSoFlag() {
		return this.MrpSoFlag;
	}

	public void setMrpSoFlag(boolean MrpSoFlag) {
		this.MrpSoFlag = MrpSoFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpSfFlag")
	public boolean getMrpSfFlag() {
		return this.MrpSfFlag;
	}

	public void setMrpSfFlag(boolean MrpSfFlag) {
		this.MrpSfFlag = MrpSfFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpPoFlag")
	public boolean getMrpPoFlag() {
		return this.MrpPoFlag;
	}

	public void setMrpPoFlag(boolean MrpPoFlag) {
		this.MrpPoFlag = MrpPoFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpPfFlag")
	public boolean getMrpPfFlag() {
		return this.MrpPfFlag;
	}

	public void setMrpPfFlag(boolean MrpPfFlag) {
		this.MrpPfFlag = MrpPfFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpQohFlag")
	public boolean getMrpQohFlag() {
		return this.MrpQohFlag;
	}

	public void setMrpQohFlag(boolean MrpQohFlag) {
		this.MrpQohFlag = MrpQohFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpFirmPlannedOrdersFlag")
	public boolean getMrpFirmPlannedOrdersFlag() {
		return this.MrpFirmPlannedOrdersFlag;
	}

	public void setMrpFirmPlannedOrdersFlag(boolean MrpFirmPlannedOrdersFlag) {
		this.MrpFirmPlannedOrdersFlag = MrpFirmPlannedOrdersFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpJobFlag")
	public boolean getMrpJobFlag() {
		return this.MrpJobFlag;
	}

	public void setMrpJobFlag(boolean MrpJobFlag) {
		this.MrpJobFlag = MrpJobFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.DampingDays")
	public java.lang.Short getDampingDays() {
		return this.DampingDays;
	}

	public void setDampingDays(java.lang.Short DampingDays) {
		this.DampingDays = DampingDays;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.QcReceivingDays")
	public java.lang.Short getQcReceivingDays() {
		return this.QcReceivingDays;
	}

	public void setQcReceivingDays(java.lang.Short QcReceivingDays) {
		this.QcReceivingDays = QcReceivingDays;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.RunDate")
	public java.util.Date getRunDate() {
		return this.RunDate;
	}

	public void setRunDate(java.util.Date RunDate) {
		this.RunDate = RunDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpStartDate")
	public java.util.Date getMrpStartDate() {
		return this.MrpStartDate;
	}

	public void setMrpStartDate(java.util.Date MrpStartDate) {
		this.MrpStartDate = MrpStartDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpEndDate")
	public java.util.Date getMrpEndDate() {
		return this.MrpEndDate;
	}

	public void setMrpEndDate(java.util.Date MrpEndDate) {
		this.MrpEndDate = MrpEndDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.LastRunDatetime")
	public java.util.Date getLastRunDatetime() {
		return this.LastRunDatetime;
	}

	public void setLastRunDatetime(java.util.Date LastRunDatetime) {
		this.LastRunDatetime = LastRunDatetime;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MRP.MrpSuggestedOrdersFirmed")
	public boolean getMrpSuggestedOrdersFirmed() {
		return this.MrpSuggestedOrdersFirmed;
	}

	public void setMrpSuggestedOrdersFirmed(boolean MrpSuggestedOrdersFirmed) {
		this.MrpSuggestedOrdersFirmed = MrpSuggestedOrdersFirmed;
	}
}