/*
 * AccKind.java
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
 * @version $Id: AccKind.java,v 1.5 2006/06/08 13:02:50 leonova Exp $
 */
@DataItemName("Account.AccKind")
public class AccKind extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.lang.Integer Priority;

	// Constructors

	/** default constructor */
	public AccKind() {
	}

	/** constructor with id */
	public AccKind(int Id) {
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

	@DataItemName("Account.AccKind.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String KCode) {
		this.Code = KCode;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccKind.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String KName) {
		this.Name = KName;
	}

	/**
	 * 
	 */

	@DataItemName("Account.AccKind.Priority")
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}
}