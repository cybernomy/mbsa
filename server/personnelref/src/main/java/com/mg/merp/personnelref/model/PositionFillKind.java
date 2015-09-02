/*
 * PositionFillKind.java
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
 * @version $Id: PositionFillKind.java,v 1.5 2006/07/05 12:18:26 leonova Exp $
 */
@DataItemName("PersonnelRef.PositionFillKind")
public class PositionFillKind extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String KCode;

	private java.lang.Integer Priority;

	// Constructors

	/** default constructor */
	public PositionFillKind() {
	}

	/** constructor with id */
	public PositionFillKind(java.lang.Integer Id) {
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

	@DataItemName("PersonnelRef.PosFillKind.KCode")
	public java.lang.String getKCode() {
		return this.KCode;
	}

	public void setKCode(java.lang.String Kcode) {
		this.KCode = Kcode;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.PosFillKind.Prior")
	public java.lang.Integer getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Integer Priority) {
		this.Priority = Priority;
	}

}