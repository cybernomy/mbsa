/*
 * AmCode.java
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
 * @version $Id: AmCode.java,v 1.4 2006/06/08 13:03:04 leonova Exp $
 */
@DataItemName("Account.AmCode")
public class AmCode extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String CName;

	private java.util.Set setOfAccAmRate;

	// Constructors

	/** default constructor */
	public AmCode() {
	}

	/** constructor with id */
	public AmCode(int Id) {
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

	@DataItemName("Account.AmCode.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Account.AmCode.CName")
	public java.lang.String getCName() {
		return this.CName;
	}

	public void setCName(java.lang.String CName) {
		this.CName = CName;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfAccAmRate() {
		return this.setOfAccAmRate;
	}

	public void setSetOfAccAmRate(java.util.Set SetOfAccAmrate) {
		this.setOfAccAmRate = SetOfAccAmrate;
	}

}