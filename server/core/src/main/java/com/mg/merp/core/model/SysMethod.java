/*
 * SysMethod.java
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
package com.mg.merp.core.model;

import com.mg.merp.security.model.MethodAccess;

/**
 * @author hbm2java
 * @version $Id: SysMethod.java,v 1.2 2007/02/24 14:11:49 safonov Exp $
 */
public class SysMethod extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClass SysClass;

	private java.lang.String CorbaName;

	private java.lang.String Description;

	private java.util.Set<MethodAccess> permissions;

	// Constructors

	/** default constructor */
	public SysMethod() {
	}

	/** constructor with id */
	public SysMethod(java.lang.Integer Id) {
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

	public com.mg.merp.core.model.SysClass getSysClass() {
		return this.SysClass;
	}

	public void setSysClass(com.mg.merp.core.model.SysClass SysClass) {
		this.SysClass = SysClass;
	}

	/**
	 * 
	 */

	public java.lang.String getCorbaName() {
		return this.CorbaName;
	}

	public void setCorbaName(java.lang.String CorbaName) {
		this.CorbaName = CorbaName;
	}

	/**
	 * 
	 */

	public java.lang.String getDescription() {
		return this.Description;
	}

	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */

	public java.util.Set<MethodAccess> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(java.util.Set<MethodAccess> SetOfSecMethodAccess) {
		this.permissions = SetOfSecMethodAccess;
	}

}