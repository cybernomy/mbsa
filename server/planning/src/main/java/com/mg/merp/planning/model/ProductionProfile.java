/*
 * ProductionProfile.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Профили производства"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: ProductionProfile.java,v 1.6 2007/01/15 08:12:37 sharapov Exp $
 */
public class ProductionProfile extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.GenericItem GenericItem;

	private java.lang.Short StartBucketOffset;

	private java.lang.Short EndBucketOffset;

	private java.math.BigDecimal ProductionRatio;

	private java.math.BigDecimal BucketProductionRatio;

	private java.util.Date StartBucketStartDate;

	private java.util.Date EndBucketStartDate;

	private java.util.Date StartBucketEndDate;

	private java.util.Date EndBucketEndDate;

	// Constructors

	/** default constructor */
	public ProductionProfile() {
	}

	/** constructor with id */
	public ProductionProfile(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.Level") //$NON-NLS-1$
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.GenericItem") //$NON-NLS-1$
	public com.mg.merp.planning.model.GenericItem getGenericItem() {
		return this.GenericItem;
	}

	public void setGenericItem(
			com.mg.merp.planning.model.GenericItem GenericItem) {
		this.GenericItem = GenericItem;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.StartBucketOffset") //$NON-NLS-1$
	public java.lang.Short getStartBucketOffset() {
		return this.StartBucketOffset;
	}

	public void setStartBucketOffset(java.lang.Short StartBucketOffset) {
		this.StartBucketOffset = StartBucketOffset;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.EndBucketOffset") //$NON-NLS-1$
	public java.lang.Short getEndBucketOffset() {
		return this.EndBucketOffset;
	}

	public void setEndBucketOffset(java.lang.Short EndBucketOffset) {
		this.EndBucketOffset = EndBucketOffset;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.ProductionRatio") //$NON-NLS-1$
	public java.math.BigDecimal getProductionRatio() {
		return this.ProductionRatio;
	}

	public void setProductionRatio(java.math.BigDecimal ProductionRatio) {
		this.ProductionRatio = ProductionRatio;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.BucketProductionRatio") //$NON-NLS-1$
	public java.math.BigDecimal getBucketProductionRatio() {
		return this.BucketProductionRatio;
	}

	public void setBucketProductionRatio(
			java.math.BigDecimal BucketProductionRatio) {
		this.BucketProductionRatio = BucketProductionRatio;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.StartBucketStartDate") //$NON-NLS-1$
	public java.util.Date getStartBucketStartDate() {
		return this.StartBucketStartDate;
	}

	public void setStartBucketStartDate(java.util.Date StartBucketStartDate) {
		this.StartBucketStartDate = StartBucketStartDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.EndBucketStartDate") //$NON-NLS-1$
	public java.util.Date getEndBucketStartDate() {
		return this.EndBucketStartDate;
	}

	public void setEndBucketStartDate(java.util.Date EndBucketStartDate) {
		this.EndBucketStartDate = EndBucketStartDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.StartBucketEndDate") //$NON-NLS-1$
	public java.util.Date getStartBucketEndDate() {
		return this.StartBucketEndDate;
	}

	public void setStartBucketEndDate(java.util.Date StartBucketEndDate) {
		this.StartBucketEndDate = StartBucketEndDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Profile.EndBucketEndDate") //$NON-NLS-1$
	public java.util.Date getEndBucketEndDate() {
		return this.EndBucketEndDate;
	}

	public void setEndBucketEndDate(java.util.Date EndBucketEndDate) {
		this.EndBucketEndDate = EndBucketEndDate;
	}

}