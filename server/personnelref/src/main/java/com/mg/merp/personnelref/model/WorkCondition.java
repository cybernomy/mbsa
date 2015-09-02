/*
 * WorkCondition.java
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
 * @version $Id: WorkCondition.java,v 1.5 2006/06/19 07:30:48 leonova Exp $
 */
@DataItemName("PersonnelRef.WorkCondition")
public class WorkCondition extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String CCode;

	private java.lang.String CName;

	// Constructors

	/** default constructor */
	public WorkCondition() {
	}

	/** constructor with id */
	public WorkCondition(java.lang.Integer Id) {
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

	@DataItemName("PersonnelRef.WorkCondition.Ccode")
	public java.lang.String getCCode() {
		return this.CCode;
	}

	public void setCCode(java.lang.String Ccode) {
		this.CCode = Ccode;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.WorkCondition.Cname")
	public java.lang.String getCName() {
		return this.CName;
	}

	public void setCName(java.lang.String Cname) {
		this.CName = Cname;
	}

}