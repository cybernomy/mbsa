/*
 * TaxRate.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: TaxRate.java,v 1.5 2006/09/13 10:45:12 leonova Exp $
 */
public class TaxRate extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.salary.model.TaxScale TaxScale;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Integer RNumber;

	private java.math.BigDecimal MinIncome;

	private java.math.BigDecimal MaxIncome;

	private java.math.BigDecimal TaxPercent;

	private java.math.BigDecimal ConstValue;

	private java.math.BigDecimal Privilegeratio;

	// Constructors

	/** default constructor */
	public TaxRate() {
	}

	/** constructor with id */
	public TaxRate(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.salary.model.TaxScale getTaxScale() {
		return this.TaxScale;
	}

	public void setTaxScale(com.mg.merp.salary.model.TaxScale SalTaxscale) {
		this.TaxScale = SalTaxscale;
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
	@DataItemName("Salary.TaxRate.RNumber")
	public java.lang.Integer getRNumber() {
		return this.RNumber;
	}

	public void setRNumber(java.lang.Integer RNumber) {
		this.RNumber = RNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxRate.MinIncome")
	public java.math.BigDecimal getMinIncome() {
		return this.MinIncome;
	}

	public void setMinIncome(java.math.BigDecimal Minincome) {
		this.MinIncome = Minincome;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxRate.MaxIncome")
	public java.math.BigDecimal getMaxIncome() {
		return this.MaxIncome;
	}

	public void setMaxIncome(java.math.BigDecimal Maxincome) {
		this.MaxIncome = Maxincome;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxRate.TaxPercent")
	public java.math.BigDecimal getTaxPercent() {
		return this.TaxPercent;
	}

	public void setTaxPercent(java.math.BigDecimal TaxPercent) {
		this.TaxPercent = TaxPercent;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxRate.ConstValue")
	public java.math.BigDecimal getConstValue() {
		return this.ConstValue;
	}

	public void setConstValue(java.math.BigDecimal Constvalue) {
		this.ConstValue = Constvalue;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxRate.Privilegeratio")
	public java.math.BigDecimal getPrivilegeratio() {
		return this.Privilegeratio;
	}

	public void setPrivilegeratio(java.math.BigDecimal Privilegeratio) {
		this.Privilegeratio = Privilegeratio;
	}

}