/*
 * AnlPlan.java
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
 * @version $Id: AnlPlan.java,v 1.5 2006/07/03 13:02:39 leonova Exp $
 */
@DataItemName("Account.AnlPlan")
public class AnlPlan extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.account.model.AnlPlan parent;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.account.model.AccPlan AccPlan;

	private java.lang.String UpCode;

	private java.lang.String Code;

	private java.lang.String AnlName;

	private short AnlLevel;

	private boolean UseStdForm;

	private AnlStdForm StdForm;

	// Constructors

	/** default constructor */
	public AnlPlan() {
	}

	/** constructor with id */
	public AnlPlan(int Id) {
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
	@DataItemName("Account.Plan.Parent")
	public com.mg.merp.account.model.AnlPlan getParent() {
		return this.parent;
	}

	public void setParent(com.mg.merp.account.model.AnlPlan AnlPlan) {
		this.parent = AnlPlan;
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

	public com.mg.merp.account.model.AccPlan getAccPlan() {
		return this.AccPlan;
	}

	public void setAccPlan(com.mg.merp.account.model.AccPlan AccPlan) {
		this.AccPlan = AccPlan;
	}

	/**
	 * 
	 */

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String UpCode) {
		this.UpCode = UpCode;
	}

	/**
	 * 
	 */
	@DataItemName("Account.BigCode")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Name")
	public java.lang.String getAnlName() {
		return this.AnlName;
	}

	public void setAnlName(java.lang.String AnlName) {
		this.AnlName = AnlName;
	}

	/**
	 * 
	 */

	public short getAnlLevel() {
		return this.AnlLevel;
	}

	public void setAnlLevel(short AnlLevel) {
		this.AnlLevel = AnlLevel;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Plan.UseStdForm")
	public boolean getUseStdForm() {
		return this.UseStdForm;
	}

	public void setUseStdForm(boolean UseStdForm) {
		this.UseStdForm = UseStdForm;
	}

	/**
	 * 
	 */

	public AnlStdForm getStdForm() {
		return this.StdForm;
	}

	public void setStdForm(AnlStdForm Stdform) {
		this.StdForm = Stdform;
	}

}