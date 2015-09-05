/*
 * BomMachine.java
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
 * @version $Id: BomMachine.java,v 1.5 2007/07/30 10:25:11 safonov Exp $
 */
public class BomMachine extends com.mg.merp.mfreference.model.BomRouteResource
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Currency mchRateCurrency;

	private com.mg.merp.reference.model.Currency mchOhRateCurrency;

	private com.mg.merp.mfreference.model.CostCategories mchOhCostCategory;

	private com.mg.merp.reference.model.Measure runTimeMchUm;

	private com.mg.merp.mfreference.model.CostCategories mchCostCategory;

	private com.mg.merp.mfreference.model.TimeRateFlag TimeRateFlag;

	private long RunTicksMch;

	private java.math.BigDecimal MchNumber;

	private com.mg.merp.mfreference.model.MachineRecoveryFlag MchRecoveryFlag;

	private java.math.BigDecimal MchRate;

	private boolean MchBackflushFlag;

	private com.mg.merp.mfreference.model.MachineOverheadAllocationFlag MchOhAllocationFlag;

	private java.math.BigDecimal MchOhRate;

	private java.math.BigDecimal MchOhRatio;

	private boolean MchOhBackflushFlag;

	// Constructors

	/** default constructor */
	public BomMachine() {
	}

	// Property accessors

	@DataItemName("MfReference.BomMachine.Currency")
	public com.mg.merp.reference.model.Currency getMchRateCurrency() {
		return this.mchRateCurrency;
	}

	public void setMchRateCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.mchRateCurrency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.RateCur")
	public com.mg.merp.reference.model.Currency getMchOhRateCurrency() {
		return this.mchOhRateCurrency;
	}

	public void setMchOhRateCurrency(com.mg.merp.reference.model.Currency RateCur) {
		this.mchOhRateCurrency = RateCur;
	}

	@DataItemName("MfReference.BomMachine.OhCostCategories")
	public com.mg.merp.mfreference.model.CostCategories getMchOhCostCategory() {
		return this.mchOhCostCategory;
	}

	public void setMchOhCostCategory(
			com.mg.merp.mfreference.model.CostCategories OhCostCategories) {
		this.mchOhCostCategory = OhCostCategories;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.Measure")
	public com.mg.merp.reference.model.Measure getRunTimeMchUm() {
		return this.runTimeMchUm;
	}

	public void setRunTimeMchUm(com.mg.merp.reference.model.Measure Measure) {
		this.runTimeMchUm = Measure;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.CostCategories")
	public com.mg.merp.mfreference.model.CostCategories getMchCostCategory() {
		return this.mchCostCategory;
	}

	public void setMchCostCategory(
			com.mg.merp.mfreference.model.CostCategories CostCategories) {
		this.mchCostCategory = CostCategories;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.TimeRateFlag")
	public com.mg.merp.mfreference.model.TimeRateFlag getTimeRateFlag() {
		return this.TimeRateFlag;
	}

	public void setTimeRateFlag(
			com.mg.merp.mfreference.model.TimeRateFlag TimeRateFlag) {
		this.TimeRateFlag = TimeRateFlag;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.RunTicksMch")
	public long getRunTicksMch() {
		return this.RunTicksMch;
	}

	public void setRunTicksMch(long RunTicksMch) {
		this.RunTicksMch = RunTicksMch;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchNumber")
	public java.math.BigDecimal getMchNumber() {
		return this.MchNumber;
	}

	public void setMchNumber(java.math.BigDecimal MchNumber) {
		this.MchNumber = MchNumber;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchRecoveryFlag")
	public com.mg.merp.mfreference.model.MachineRecoveryFlag getMchRecoveryFlag() {
		return this.MchRecoveryFlag;
	}

	public void setMchRecoveryFlag(
			com.mg.merp.mfreference.model.MachineRecoveryFlag MchRecoveryFlag) {
		this.MchRecoveryFlag = MchRecoveryFlag;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchRate")
	public java.math.BigDecimal getMchRate() {
		return this.MchRate;
	}

	public void setMchRate(java.math.BigDecimal MchRate) {
		this.MchRate = MchRate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchBackflushFlag")
	public boolean isMchBackflushFlag() {
		return this.MchBackflushFlag;
	}

	public void setMchBackflushFlag(boolean MchBackflushFlag) {
		this.MchBackflushFlag = MchBackflushFlag;
	}

	/**
	 * 
	 */

	public com.mg.merp.mfreference.model.MachineOverheadAllocationFlag getMchOhAllocationFlag() {
		return this.MchOhAllocationFlag;
	}

	public void setMchOhAllocationFlag(
			com.mg.merp.mfreference.model.MachineOverheadAllocationFlag MchOhAllocationFlag) {
		this.MchOhAllocationFlag = MchOhAllocationFlag;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchOhRate")
	public java.math.BigDecimal getMchOhRate() {
		return this.MchOhRate;
	}

	public void setMchOhRate(java.math.BigDecimal MchOhRate) {
		this.MchOhRate = MchOhRate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchOhRatio")
	public java.math.BigDecimal getMchOhRatio() {
		return this.MchOhRatio;
	}

	public void setMchOhRatio(java.math.BigDecimal MchOhRatio) {
		this.MchOhRatio = MchOhRatio;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMachine.MchOhBackflushFlag")
	public boolean isMchOhBackflushFlag() {
		return this.MchOhBackflushFlag;
	}

	public void setMchOhBackflushFlag(boolean MchOhBackflushFlag) {
		this.MchOhBackflushFlag = MchOhBackflushFlag;
	}

}