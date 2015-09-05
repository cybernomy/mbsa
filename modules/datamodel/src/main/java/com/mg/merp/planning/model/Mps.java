/*
 * Mps.java
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
 * @version $Id: Mps.java,v 1.6 2007/08/06 13:32:18 safonov Exp $
 */
@DataItemName("Planning.MPS")
public class Mps extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.planning.model.InventoryForecast InventoryForecast;

	private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

	private com.mg.merp.planning.model.ForecastVersion ForecastVersion;

	private com.mg.merp.mfreference.model.WeekCalendar WeekCal;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.util.Date DemandFenceDate;

	private java.lang.String Description;

	private java.util.Date PlanningDate;

	private java.lang.Short levelProcessedTo;

	private boolean Production;

	private boolean ProfileApplied;

	private boolean PurchasesForecasts;

	private boolean PurchasesLive;

	private boolean QtyOnHand;

	private boolean SalesForecasts;

	private boolean SalesLive;

	private boolean WarehouseTransfers;

	private java.lang.Integer MpsVersion;

	private java.util.Date LastRunDateTime;

	// Constructors

	/** default constructor */
	public Mps() {
	}

	/** constructor with id */
	public Mps(java.lang.Integer Id) {
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
	@DataItemName("Planning.MPS.InventoryForecast")
	public com.mg.merp.planning.model.InventoryForecast getInventoryForecast() {
		return this.InventoryForecast;
	}

	public void setInventoryForecast(
			com.mg.merp.planning.model.InventoryForecast InventoryForecast) {
		this.InventoryForecast = InventoryForecast;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.PlanningLevel")
	public com.mg.merp.mfreference.model.PlanningLevel getPlanningLevel() {
		return this.PlanningLevel;
	}

	public void setPlanningLevel(
			com.mg.merp.mfreference.model.PlanningLevel PlanningLevel) {
		this.PlanningLevel = PlanningLevel;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.ForecastVersion")
	public com.mg.merp.planning.model.ForecastVersion getForecastVersion() {
		return this.ForecastVersion;
	}

	public void setForecastVersion(
			com.mg.merp.planning.model.ForecastVersion ForecastVersion) {
		this.ForecastVersion = ForecastVersion;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.WeekCal")
	public com.mg.merp.mfreference.model.WeekCalendar getWeekCal() {
		return this.WeekCal;
	}

	public void setWeekCal(com.mg.merp.mfreference.model.WeekCalendar WeekCal) {
		this.WeekCal = WeekCal;
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
	@DataItemName("Planning.MPS.DemandFenceDate")
	public java.util.Date getDemandFenceDate() {
		return this.DemandFenceDate;
	}

	public void setDemandFenceDate(java.util.Date DemandFenceDate) {
		this.DemandFenceDate = DemandFenceDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Description")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.PlanningDate")
	public java.util.Date getPlanningDate() {
		return this.PlanningDate;
	}

	public void setPlanningDate(java.util.Date PlanningDate) {
		this.PlanningDate = PlanningDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.PpLevelProcessedTo")
	public java.lang.Short getLevelProcessedTo() {
		return this.levelProcessedTo;
	}

	public void setLevelProcessedTo(java.lang.Short PpLevelProcessedTo) {
		this.levelProcessedTo = PpLevelProcessedTo;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.Production")
	public boolean getProduction() {
		return this.Production;
	}

	public void setProduction(boolean Production) {
		this.Production = Production;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.ProfileApplied")
	public boolean getProfileApplied() {
		return this.ProfileApplied;
	}

	public void setProfileApplied(boolean ProfileApplied) {
		this.ProfileApplied = ProfileApplied;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.PurchasesForecasts")
	public boolean getPurchasesForecasts() {
		return this.PurchasesForecasts;
	}

	public void setPurchasesForecasts(boolean PurchasesForecasts) {
		this.PurchasesForecasts = PurchasesForecasts;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.PurchasesLive")
	public boolean getPurchasesLive() {
		return this.PurchasesLive;
	}

	public void setPurchasesLive(boolean PurchasesLive) {
		this.PurchasesLive = PurchasesLive;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.QtyOnHand")
	public boolean getQtyOnHand() {
		return this.QtyOnHand;
	}

	public void setQtyOnHand(boolean QtyOnHand) {
		this.QtyOnHand = QtyOnHand;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.SalesForecasts")
	public boolean getSalesForecasts() {
		return this.SalesForecasts;
	}

	public void setSalesForecasts(boolean SalesForecasts) {
		this.SalesForecasts = SalesForecasts;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.SalesLive")
	public boolean getSalesLive() {
		return this.SalesLive;
	}

	public void setSalesLive(boolean SalesLive) {
		this.SalesLive = SalesLive;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.WarehouseTransfers")
	public boolean getWarehouseTransfers() {
		return this.WarehouseTransfers;
	}

	public void setWarehouseTransfers(boolean WarehouseTransfers) {
		this.WarehouseTransfers = WarehouseTransfers;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSVersion")
	public java.lang.Integer getMpsVersion() {
		return this.MpsVersion;
	}

	public void setMpsVersion(java.lang.Integer MpsVersion) {
		this.MpsVersion = MpsVersion;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPS.LastRunDateTime")
	public java.util.Date getLastRunDateTime() {
		return this.LastRunDateTime;
	}

	public void setLastRunDateTime(java.util.Date LastRunDateTime) {
		this.LastRunDateTime = LastRunDateTime;
	}
}