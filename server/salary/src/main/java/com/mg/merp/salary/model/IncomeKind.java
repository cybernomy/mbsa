/*
 * IncomeKind.java
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
 * @version $Id: IncomeKind.java,v 1.5 2006/06/20 08:15:52 leonova Exp $
 */
@DataItemName("Salary.IncomeKind")
public class IncomeKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String ICode;

	private java.lang.String IName;

	private java.util.Date BeginDate;

	// Constructors

	/** default constructor */
	public IncomeKind() {
	}

	/** constructor with id */
	public IncomeKind(int Id) {
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
	@DataItemName("Salary.BigCode")
	public java.lang.String getICode() {
		return this.ICode;
	}

	public void setICode(java.lang.String Icode) {
		this.ICode = Icode;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.Name")
	public java.lang.String getIName() {
		return this.IName;
	}

	public void setIName(java.lang.String Iname) {
		this.IName = Iname;
	}

	/**
	 * 
	 */
	@DataItemName("Salary.IncomeKind.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

}