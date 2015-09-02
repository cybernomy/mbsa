/*
 * Metal.java
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
 * @version $Id: Metal.java,v 1.6 2006/06/08 13:03:33 leonova Exp $
 */
@DataItemName("Account.Metal")
public class Metal extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.String UpCode;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	// Constructors

	/** default constructor */
	public Metal() {
	}

	/** constructor with id */
	public Metal(java.lang.String Upcode) {
		this.UpCode = Upcode;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String Upcode) {
		this.UpCode = Upcode;
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
	@DataItemName("Account.Metal.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Account.Metal.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Mname) {
		this.Name = Mname;
	}
}