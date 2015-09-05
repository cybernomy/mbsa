/*
 * TaxScale.java
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
 * @version $Id: TaxScale.java,v 1.5 2006/09/13 10:45:12 leonova Exp $
 */
public class TaxScale extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.salary.model.TaxHead TaxHead;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.Integer SNumber;

	private java.util.Date BeginDate;

	private TaxPayer TaxPayer;

	private java.lang.String SName;

	private java.util.Set SetOfSalTaxRate;

	// Constructors

	/** default constructor */
	public TaxScale() {
	}

	/** constructor with id */
	public TaxScale(Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.salary.model.TaxHead getTaxHead() {
		return this.TaxHead;
	}

	public void setTaxHead(com.mg.merp.salary.model.TaxHead SalTaxhead) {
		this.TaxHead = SalTaxhead;
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
	@DataItemName("Salary.TaxScale.SNumber")
	public java.lang.Integer getSNumber() {
		return this.SNumber;
	}

	public void setSNumber(java.lang.Integer Snumber) {
		this.SNumber = Snumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.TaxScale.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */

	public TaxPayer getTaxPayer() {
		return this.TaxPayer;
	}

	public void setTaxPayer(TaxPayer Taxpayer) {
		this.TaxPayer = Taxpayer;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.Name")
	public java.lang.String getSName() {
		return this.SName;
	}

	public void setSName(java.lang.String Sname) {
		this.SName = Sname;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalTaxRate() {
		return this.SetOfSalTaxRate;
	}

	public void setSetOfSalTaxRate(java.util.Set SetOfSalTaxRate) {
		this.SetOfSalTaxRate = SetOfSalTaxRate;
	}

}