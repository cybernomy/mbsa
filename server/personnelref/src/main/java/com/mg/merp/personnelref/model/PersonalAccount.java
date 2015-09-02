/*
 * PersonalAccount.java
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
 * @version $Id: PersonalAccount.java,v 1.6 2006/11/02 16:13:49 safonov Exp $
 */
@DataItemName("Salary.PersonalAccount")
public class PersonalAccount extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String ANumber;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.util.Set SetOfSalFeeModel;

	private java.util.Set SetOfSalPositionFill;

	// Constructors

	/** default constructor */
	public PersonalAccount() {
	}

	/** constructor with id */
	public PersonalAccount(java.lang.Integer Id) {
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
	@DataItemName("Salary.PersonAcc.Personnel")
	public com.mg.merp.personnelref.model.Personnel getPersonnel() {
		return this.Personnel;
	}

	public void setPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.Personnel = PrefPersonnel;
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
	@DataItemName("Salary.PersonAcc.ANumber")
	public java.lang.String getANumber() {
		return this.ANumber;
	}

	public void setANumber(java.lang.String Anumber) {
		this.ANumber = Anumber;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PersonAcc.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.PersonAcc.EndDate")
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalFeeModel() {
		return this.SetOfSalFeeModel;
	}

	public void setSetOfSalFeeModel(java.util.Set SetOfSalFeeModel) {
		this.SetOfSalFeeModel = SetOfSalFeeModel;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfSalPositionFill() {
		return this.SetOfSalPositionFill;
	}

	public void setSetOfSalPositionFill(java.util.Set SetOfSalPositionFill) {
		this.SetOfSalPositionFill = SetOfSalPositionFill;
	}

}