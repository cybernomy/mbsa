/*
 * Okdp.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Okdp.java,v 1.3 2006/03/29 13:06:23 safonov Exp $
 */
@DataItemName("Reference.OKDP")
public class Okdp extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.String UpCode;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String OName;

	// Constructors

	/** default constructor */
	public Okdp() {
	}

	/** constructor with id */
	public Okdp(java.lang.String UpCode) {
		this.UpCode = UpCode;
	}

	// Property accessors
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getOName() {
		return this.OName;
	}

	public void setOName(java.lang.String OName) {
		this.OName = OName;
	}

}