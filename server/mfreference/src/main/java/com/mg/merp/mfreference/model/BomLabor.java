/*
 * BomLabor.java
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
 * @version $Id: BomLabor.java,v 1.5 2007/07/30 10:25:11 safonov Exp $
 */
public class BomLabor extends com.mg.merp.mfreference.model.BomRouteResource
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.mfreference.model.LaborClass LaborClass;

	private com.mg.merp.reference.model.Measure runTimeLbrUm;

	private long RunTicksLbr;

	private java.math.BigDecimal LbrNumber;

	private boolean LbrBackflushFlag;

	private boolean LbrOhBackflushFlag;

	// Constructors

	/** default constructor */
	public BomLabor() {
	}

	// Property accessors

	public com.mg.merp.mfreference.model.LaborClass getLaborClass() {
		return this.LaborClass;
	}

	public void setLaborClass(
			com.mg.merp.mfreference.model.LaborClass LaborClass) {
		this.LaborClass = LaborClass;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomLabor.Measure")
	public com.mg.merp.reference.model.Measure getRunTimeLbrUm() {
		return this.runTimeLbrUm;
	}

	public void setRunTimeLbrUm(com.mg.merp.reference.model.Measure Measure) {
		this.runTimeLbrUm = Measure;
	}

	@DataItemName("MfReference.BomLabor.RunTicksLbr")
	public long getRunTicksLbr() {
		return this.RunTicksLbr;
	}

	public void setRunTicksLbr(long RunTicksLbr) {
		this.RunTicksLbr = RunTicksLbr;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomLabor.LbrNumber")
	public java.math.BigDecimal getLbrNumber() {
		return this.LbrNumber;
	}

	public void setLbrNumber(java.math.BigDecimal LbrNumber) {
		this.LbrNumber = LbrNumber;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomLabor.LbrBackflushFlag")
	public boolean isLbrBackflushFlag() {
		return this.LbrBackflushFlag;
	}

	public void setLbrBackflushFlag(boolean LbrBackflushFlag) {
		this.LbrBackflushFlag = LbrBackflushFlag;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomLabor.LbrOhBackflushFlag")
	public boolean isLbrOhBackflushFlag() {
		return this.LbrOhBackflushFlag;
	}

	public void setLbrOhBackflushFlag(boolean LbrOhBackflushFlag) {
		this.LbrOhBackflushFlag = LbrOhBackflushFlag;
	}

}