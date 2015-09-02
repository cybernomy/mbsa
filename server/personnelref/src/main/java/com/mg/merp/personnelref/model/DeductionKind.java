/*
 * DeductionKind.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DeductionKind.java,v 1.5 2006/07/05 12:16:48 leonova Exp $
 */
@DataItemName("PersonnelRef.DeductionKind")
public class DeductionKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private DeductionClass DeductionClass;

	private java.lang.String DCode;

	private java.lang.String DName;

	private java.math.BigDecimal MinSalaryNumber;

	private java.math.BigDecimal FixedSum;

	private java.util.Date BeginDate;

	private java.math.BigDecimal MaxIncome;

	// Constructors

	/** default constructor */
	public DeductionKind() {
	}

	/** constructor with id */
	public DeductionKind(int Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.Class")
	public DeductionClass getDeductionClass() {
		return this.DeductionClass;
	}

	public void setDeductionClass(DeductionClass Deductionclass) {
		this.DeductionClass = Deductionclass;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.Code")
	public java.lang.String getDCode() {
		return this.DCode;
	}

	public void setDCode(java.lang.String Dcode) {
		this.DCode = Dcode;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.Name")
	public java.lang.String getDName() {
		return this.DName;
	}

	public void setDName(java.lang.String Dname) {
		this.DName = Dname;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.MinSalaryNumber")
	public java.math.BigDecimal getMinSalaryNumber() {
		return this.MinSalaryNumber;
	}

	public void setMinSalaryNumber(java.math.BigDecimal Minsalarynumber) {
		this.MinSalaryNumber = Minsalarynumber;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.FixedSum")
	public java.math.BigDecimal getFixedSum() {
		return this.FixedSum;
	}

	public void setFixedSum(java.math.BigDecimal Fixedsum) {
		this.FixedSum = Fixedsum;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.DeductionKind.MaxIncome")
	public java.math.BigDecimal getMaxIncome() {
		return this.MaxIncome;
	}

	public void setMaxIncome(java.math.BigDecimal MaxIncome) {
		this.MaxIncome = MaxIncome;
	}

}