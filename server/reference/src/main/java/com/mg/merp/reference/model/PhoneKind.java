/*
 * PhoneKind.java
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
 * @version $Id: PhoneKind.java,v 1.4 2006/05/29 12:17:50 leonova Exp $
 */
@DataItemName("Reference.PhoneKind")
public class PhoneKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String KName;

	// Constructors

	/** default constructor */
	public PhoneKind() {
	}

	/** constructor with id */
	public PhoneKind(int Id) {
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
	@DataItemName("Reference.Name")
	public java.lang.String getKName() {
		return this.KName;
	}

	public void setKName(java.lang.String KName) {
		this.KName = KName;
	}
}