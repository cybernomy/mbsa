/*
 * MethodAccess.java
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
package com.mg.merp.security.model;

/**
 * @author hbm2java
 * @version $Id: MethodAccess.java,v 1.3 2005/07/22 07:06:22 safonov Exp $
 */
public class MethodAccess extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysMethod Method;

	private com.mg.merp.security.model.Groups Group;

	private com.mg.merp.core.model.SysClient SysClient;

	private boolean Permission;

	// Constructors

	/** default constructor */
	public MethodAccess() {
	}

	/** constructor with id */
	public MethodAccess(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysMethod getMethod() {
		return this.Method;
	}

	public void setMethod(com.mg.merp.core.model.SysMethod SysMethod) {
		this.Method = SysMethod;
	}

	/**
	 * 
	 */

	public com.mg.merp.security.model.Groups getGroup() {
		return this.Group;
	}

	public void setGroup(com.mg.merp.security.model.Groups SecGroups) {
		this.Group = SecGroups;
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

	public boolean getPermission() {
		return this.Permission;
	}

	public void setPermission(boolean Permission) {
		this.Permission = Permission;
	}

}