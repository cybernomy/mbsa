/*
 * PlanningForecast.java
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
 * @version $Id: PlanningForecast.java,v 1.5 2007/07/30 10:37:30 safonov Exp $
 */
public class PlanningForecast extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Measure Measure;

	private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.planning.model.ForecastVersion ForecastVersion;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.GenericItem planningItem;

	private com.mg.merp.reference.model.Catalog Catalog;

	private ForecastType ForecastType;

	private ForecastMethod ForecastMethod;

	private java.lang.Short BucketOffset;

	private java.util.Date RequiredDate;

	private java.math.BigDecimal ForecastQuantity;

	private java.util.Date BucketStartDate;

	private java.util.Date BucketEndDate;

	// Constructors

	/** default constructor */
	public PlanningForecast() {
	}

	/** constructor with id */
	public PlanningForecast(java.lang.Integer Id) {
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
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.Measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.Measure = Measure;
	}

	/**
	 * 
	 */
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
	@DataItemName("Planning.ForecastLine.Contractor")
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */

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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForecastLine.GenericItem")
	public com.mg.merp.planning.model.GenericItem getPlanningItem() {
		return this.planningItem;
	}

	public void setPlanningItem(
			com.mg.merp.planning.model.GenericItem GenericItem) {
		this.planningItem = GenericItem;
	}

	/**
	 * 
	 */

	public ForecastType getForecastType() {
		return this.ForecastType;
	}

	public void setForecastType(ForecastType ForecastType) {
		this.ForecastType = ForecastType;
	}

	/**
	 * 
	 */

	public ForecastMethod getForecastMethod() {
		return this.ForecastMethod;
	}

	public void setForecastMethod(ForecastMethod ForecastMethod) {
		this.ForecastMethod = ForecastMethod;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForecastLine.Bucket")
	public java.lang.Short getBucketOffset() {
		return this.BucketOffset;
	}

	public void setBucketOffset(java.lang.Short BucketOffset) {
		this.BucketOffset = BucketOffset;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForecastLine.RequiredDate")
	public java.util.Date getRequiredDate() {
		return this.RequiredDate;
	}

	public void setRequiredDate(java.util.Date RequiredDate) {
		this.RequiredDate = RequiredDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForecastLine.Quantity")
	public java.math.BigDecimal getForecastQuantity() {
		return this.ForecastQuantity;
	}

	public void setForecastQuantity(java.math.BigDecimal ForecastQuantity) {
		this.ForecastQuantity = ForecastQuantity;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Catalog")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForLine.BucketStartDate")
	public java.util.Date getBucketStartDate() {
		return this.BucketStartDate;
	}

	public void setBucketStartDate(java.util.Date BucketStartDate) {
		this.BucketStartDate = BucketStartDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.ForLine.BucketEndDate")
	public java.util.Date getBucketEndDate() {
		return this.BucketEndDate;
	}

	public void setBucketEndDate(java.util.Date BucketEndDate) {
		this.BucketEndDate = BucketEndDate;
	}
}