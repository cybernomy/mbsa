/*
 * Okof.java
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
 * @version $Id: Okof.java,v 1.5 2006/06/08 13:03:33 leonova Exp $
 */
@DataItemName("Account.Okof")
public class Okof extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String UpCode;

	private java.lang.String Code;

	private java.lang.String OkofName;

	// Constructors

	/** default constructor */
	public Okof() {
	}

	/** constructor with id */
	public Okof(java.lang.Integer Id) {
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

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String Upcode) {
		this.UpCode = Upcode;
	}

	/**
	 * 
	 */

	@DataItemName("Account.Okof.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */

	@DataItemName("Account.Okof.OkofName")
	public java.lang.String getOkofName() {
		return this.OkofName;
	}

	public void setOkofName(java.lang.String Okofname) {
		this.OkofName = Okofname;
	}
}