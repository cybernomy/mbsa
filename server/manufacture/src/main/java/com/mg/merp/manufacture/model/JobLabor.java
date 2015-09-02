/*
 * JobLabor.java
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
package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.mfreference.model.LaborOverheadAllocationFlag;
import com.mg.merp.mfreference.model.TimeRateFlag;

/**
 * @author hbm2java
 * @version $Id: JobLabor.java,v 1.6 2007/07/30 10:27:49 safonov Exp $
 */
public class JobLabor extends com.mg.merp.manufacture.model.JobRouteResource
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Measure RunTimeLbrUm;

	private com.mg.merp.reference.model.Currency lbrOhRateCurrency;

	private com.mg.merp.mfreference.model.CostCategories LbrCostCategory;

	private com.mg.merp.mfreference.model.CostCategories LbrOhCostCategory;

	private com.mg.merp.reference.model.Currency lbrRateCurrency;

	private TimeRateFlag TimeRateFlag;

	private long RunTicksLbr;

	private java.math.BigDecimal LbrNumber;

	private java.math.BigDecimal LbrRate;

	private boolean LbrBackflushFlag;

	private LaborOverheadAllocationFlag LbrOhAllocationFlag;

	private java.math.BigDecimal LbrOhRate;

	private java.math.BigDecimal LbrOhRatio;

	private boolean LbrOhBackflushFlag;

	// Constructors

	/** default constructor */
	public JobLabor() {
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.RunTimeLbrUm")
	public com.mg.merp.reference.model.Measure getRunTimeLbrUm() {
		return this.RunTimeLbrUm;
	}

	public void setRunTimeLbrUm(com.mg.merp.reference.model.Measure Measure) {
		this.RunTimeLbrUm = Measure;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrOhRateCurCode")
	public com.mg.merp.reference.model.Currency getLbrOhRateCurrency() {
		return this.lbrOhRateCurrency;
	}

	public void setLbrOhRateCurrency(
			com.mg.merp.reference.model.Currency Currency) {
		this.lbrOhRateCurrency = Currency;
	}

	@DataItemName("Manufacture.JobLabor.LbrCostCategory")
	public com.mg.merp.mfreference.model.CostCategories getLbrCostCategory() {
		return this.LbrCostCategory;
	}

	public void setLbrCostCategory(
			com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
		this.LbrCostCategory = MfCostCategories;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrOhCostCategory")
	public com.mg.merp.mfreference.model.CostCategories getLbrOhCostCategory() {
		return this.LbrOhCostCategory;
	}

	public void setLbrOhCostCategory(
			com.mg.merp.mfreference.model.CostCategories MfCostCategories_1) {
		this.LbrOhCostCategory = MfCostCategories_1;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrRateCurCode")
	public com.mg.merp.reference.model.Currency getLbrRateCurrency() {
		return this.lbrRateCurrency;
	}

	public void setLbrRateCurrency(
			com.mg.merp.reference.model.Currency Currency_1) {
		this.lbrRateCurrency = Currency_1;
	}

	/**
	 * 
	 */

	public TimeRateFlag getTimeRateFlag() {
		return this.TimeRateFlag;
	}

	public void setTimeRateFlag(TimeRateFlag TimeRateFlag) {
		this.TimeRateFlag = TimeRateFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.RunTicksLbr")
	public long getRunTicksLbr() {
		return this.RunTicksLbr;
	}

	public void setRunTicksLbr(long RunTicksLbr) {
		this.RunTicksLbr = RunTicksLbr;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrNumber")
	public java.math.BigDecimal getLbrNumber() {
		return this.LbrNumber;
	}

	public void setLbrNumber(java.math.BigDecimal LbrNumber) {
		this.LbrNumber = LbrNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrRate")
	public java.math.BigDecimal getLbrRate() {
		return this.LbrRate;
	}

	public void setLbrRate(java.math.BigDecimal LbrRate) {
		this.LbrRate = LbrRate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrBackflushFlag")
	public boolean isLbrBackflushFlag() {
		return this.LbrBackflushFlag;
	}

	public void setLbrBackflushFlag(boolean LbrBackflushFlag) {
		this.LbrBackflushFlag = LbrBackflushFlag;
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
	@DataItemName("Manufacture.JobLabor.LbrOhRate")
	public java.math.BigDecimal getLbrOhRate() {
		return this.LbrOhRate;
	}

	public void setLbrOhRate(java.math.BigDecimal LbrOhRate) {
		this.LbrOhRate = LbrOhRate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrOhRatio")
	public java.math.BigDecimal getLbrOhRatio() {
		return this.LbrOhRatio;
	}

	public void setLbrOhRatio(java.math.BigDecimal LbrOhRatio) {
		this.LbrOhRatio = LbrOhRatio;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobLabor.LbrOhBackflushFlag")
	public boolean isLbrOhBackflushFlag() {
		return this.LbrOhBackflushFlag;
	}

	public void setLbrOhBackflushFlag(boolean LbrOhBackflushFlag) {
		this.LbrOhBackflushFlag = LbrOhBackflushFlag;
	}

}