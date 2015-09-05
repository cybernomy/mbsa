/*
 * LaborClass.java
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
 * @version $Id: LaborClass.java,v 1.6 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("MfReference.LaborClass")
public class LaborClass extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Measure LbrOhTimeUm;

	private com.mg.merp.reference.model.Currency lbrOhRateCurrency;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.CostCategories lbrCostCategory;

	private com.mg.merp.mfreference.model.CostCategories lbrOhCostCategory;

	private com.mg.merp.reference.model.Measure LbrTimeUm;

	private com.mg.merp.reference.model.Currency lbrRateCurrency;

	private java.lang.String Description;

	private TimeRateFlag timeRateFlag;

	private java.math.BigDecimal LbrRate;

	private LaborOverheadAllocationFlag LbrOhAllocationFlag;

	private java.math.BigDecimal LbrOhRate;

	private java.math.BigDecimal LbrOhRatio;

	// Constructors

	/** default constructor */
	public LaborClass() {
	}

	/** constructor with id */
	public LaborClass(java.lang.Integer Id) {
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

	@DataItemName("MfReference.LbrCl.LbrOhTimeUm")
	public com.mg.merp.reference.model.Measure getLbrOhTimeUm() {
		return this.LbrOhTimeUm;
	}

	public void setLbrOhTimeUm(com.mg.merp.reference.model.Measure LbrOhTimeUm) {
		this.LbrOhTimeUm = LbrOhTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrOhRatCurCod")
	public com.mg.merp.reference.model.Currency getLbrOhRateCurrency() {
		return this.lbrOhRateCurrency;
	}

	public void setLbrOhRateCurrency(
			com.mg.merp.reference.model.Currency LbrOhRateCurCode) {
		this.lbrOhRateCurrency = LbrOhRateCurCode;
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

	@DataItemName("MfReference.LbrCl.LbrCostCategId")
	public com.mg.merp.mfreference.model.CostCategories getLbrCostCategory() {
		return this.lbrCostCategory;
	}

	public void setLbrCostCategory(
			com.mg.merp.mfreference.model.CostCategories LbrCostCategoryId) {
		this.lbrCostCategory = LbrCostCategoryId;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrOhCostCatId")
	public com.mg.merp.mfreference.model.CostCategories getLbrOhCostCategory() {
		return this.lbrOhCostCategory;
	}

	public void setLbrOhCostCategory(
			com.mg.merp.mfreference.model.CostCategories LbrOhCostCategoryId) {
		this.lbrOhCostCategory = LbrOhCostCategoryId;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrTimeUm")
	public com.mg.merp.reference.model.Measure getLbrTimeUm() {
		return this.LbrTimeUm;
	}

	public void setLbrTimeUm(com.mg.merp.reference.model.Measure LbrTimeUm) {
		this.LbrTimeUm = LbrTimeUm;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrRateCurCode")
	public com.mg.merp.reference.model.Currency getLbrRateCurrency() {
		return this.lbrRateCurrency;
	}

	public void setLbrRateCurrency(
			com.mg.merp.reference.model.Currency LbrRateCurCode) {
		this.lbrRateCurrency = LbrRateCurCode;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.Descript")
	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	
	public TimeRateFlag getTimeRateFlag() {
		return this.timeRateFlag;
	}

	public void setTimeRateFlag(TimeRateFlag timeRateFlag) {
		this.timeRateFlag = timeRateFlag;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrRate")
	public java.math.BigDecimal getLbrRate() {
		return this.LbrRate;
	}

	public void setLbrRate(java.math.BigDecimal LbrRate) {
		this.LbrRate = LbrRate;
	}

	/**
	 * 
	 */
	public LaborOverheadAllocationFlag getLbrOhAllocationFlag() {
		return this.LbrOhAllocationFlag;
	}

	public void setLbrOhAllocationFlag(LaborOverheadAllocationFlag LbrOhAllocationFlag) {
		this.LbrOhAllocationFlag = LbrOhAllocationFlag;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrOhRate")
	public java.math.BigDecimal getLbrOhRate() {
		return this.LbrOhRate;
	}

	public void setLbrOhRate(java.math.BigDecimal LbrOhRate) {
		this.LbrOhRate = LbrOhRate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.LbrCl.LbrOhRatio")
	public java.math.BigDecimal getLbrOhRatio() {
		return this.LbrOhRatio;
	}

	public void setLbrOhRatio(java.math.BigDecimal LbrOhRatio) {
		this.LbrOhRatio = LbrOhRatio;
	}
}