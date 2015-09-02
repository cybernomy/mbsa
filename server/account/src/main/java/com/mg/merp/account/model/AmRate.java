/*
 * AmRate.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: AmRate.java,v 1.3 2006/09/29 07:12:40 leonova Exp $
 */
public class AmRate extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.AmCode AmCode;

	private int ActMonth;

	private java.math.BigDecimal AmRate;

	private java.math.BigDecimal AmRate1000;

	private java.lang.Short PeriodYear;

	private java.math.BigDecimal VolumeProd;

	private java.lang.Short PeriodMonth;

	// Constructors

	/** default constructor */
	public AmRate() {
	}

	/** constructor with id */
	public AmRate(java.lang.Integer Id) {
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

	public com.mg.merp.account.model.AmCode getAmCode() {
		return this.AmCode;
	}

	public void setAmCode(com.mg.merp.account.model.AmCode AmCode) {
		this.AmCode = AmCode;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.ActMonth")
	public int getActMonth() {
		return this.ActMonth;
	}

	public void setActMonth(int ActMonth) {
		this.ActMonth = ActMonth;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.AmRate")
	public java.math.BigDecimal getAmRate() {
		return this.AmRate;
	}

	public void setAmRate(java.math.BigDecimal AmRate) {
		this.AmRate = AmRate;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.AmRate1000")
	public java.math.BigDecimal getAmRate1000() {
		return this.AmRate1000;
	}

	public void setAmRate1000(java.math.BigDecimal AmRate1000) {
		this.AmRate1000 = AmRate1000;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.PeriodYear")
	public java.lang.Short getPeriodYear() {
		return this.PeriodYear;
	}

	public void setPeriodYear(java.lang.Short PeriodYear) {
		this.PeriodYear = PeriodYear;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.VolumeProd")
	public java.math.BigDecimal getVolumeProd() {
		return this.VolumeProd;
	}

	public void setVolumeProd(java.math.BigDecimal VolumeProd) {
		this.VolumeProd = VolumeProd;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmRate.PeriodMonth")
	public java.lang.Short getPeriodMonth() {
		return this.PeriodMonth;
	}

	public void setPeriodMonth(java.lang.Short PeriodMonth) {
		this.PeriodMonth = PeriodMonth;
	}

}