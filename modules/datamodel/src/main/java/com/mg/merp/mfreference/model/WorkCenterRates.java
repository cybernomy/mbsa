/*
 * WorkCenterRates.java
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
 * @version $Id: WorkCenterRates.java,v 1.3 2006/08/24 12:16:02 leonova Exp $
 */
public class WorkCenterRates extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.mfreference.model.WorkCenter WorkCenter;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	private java.math.BigDecimal MchFohRate;

	private java.math.BigDecimal MchVohRate;

	private java.math.BigDecimal LbrRunRate;

	private java.math.BigDecimal LbrSetupRate;

	private java.math.BigDecimal LbrFohRate;

	private java.math.BigDecimal LbrVohRate;

	// Constructors

	/** default constructor */
	public WorkCenterRates() {
	}

	/** constructor with id */
	public WorkCenterRates(java.lang.Integer Id) {
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

	public com.mg.merp.mfreference.model.WorkCenter getWorkCenter() {
		return this.WorkCenter;
	}

	public void setWorkCenter(
			com.mg.merp.mfreference.model.WorkCenter WorkCenter) {
		this.WorkCenter = WorkCenter;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WorkCenterRates.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WorkCenterRates.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WorkCenterRates.MchFohRate")
	public java.math.BigDecimal getMchFohRate() {
		return this.MchFohRate;
	}

	public void setMchFohRate(java.math.BigDecimal MchFohRate) {
		this.MchFohRate = MchFohRate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WorkCenterRates.MchVohRate")
	public java.math.BigDecimal getMchVohRate() {
		return this.MchVohRate;
	}

	public void setMchVohRate(java.math.BigDecimal MchVohRate) {
		this.MchVohRate = MchVohRate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.WorkCenterRates.LbrRunRate")
	public java.math.BigDecimal getLbrRunRate() {
		return this.LbrRunRate;
	}

	public void setLbrRunRate(java.math.BigDecimal LbrRunRate) {
		this.LbrRunRate = LbrRunRate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.WorkCenterRates.LbrSetupRate")
	public java.math.BigDecimal getLbrSetupRate() {
		return this.LbrSetupRate;
	}

	public void setLbrSetupRate(java.math.BigDecimal LbrSetupRate) {
		this.LbrSetupRate = LbrSetupRate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.WorkCenterRates.LbrFohRate")
	public java.math.BigDecimal getLbrFohRate() {
		return this.LbrFohRate;
	}

	public void setLbrFohRate(java.math.BigDecimal LbrFohRate) {
		this.LbrFohRate = LbrFohRate;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.WorkCenterRates.LbrVohRate")
	public java.math.BigDecimal getLbrVohRate() {
		return this.LbrVohRate;
	}

	public void setLbrVohRate(java.math.BigDecimal LbrVohRate) {
		this.LbrVohRate = LbrVohRate;
	}

}