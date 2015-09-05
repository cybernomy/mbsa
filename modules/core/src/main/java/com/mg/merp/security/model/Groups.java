/*
 * Groups.java
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

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Groups.java,v 1.5 2008/03/03 13:05:31 safonov Exp $
 */
@DataItemName ("Security.Group")
public class Groups extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.util.Set<ModuleAccess> secModulesAccess;

	private java.util.Set<LinkUsersGroups> secLinkUsersGroups;

	// Constructors

	/** default constructor */
	public Groups() {
	}

	/** constructor with id */
	public Groups(int Id) {
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
	@DataItemName("Security.Group.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */

	public java.util.Set<ModuleAccess> getSecModulesAccess() {
		return this.secModulesAccess;
	}

	public void setSecModulesAccess(java.util.Set<ModuleAccess> SetOfSecModuleAccess) {
		this.secModulesAccess = SetOfSecModuleAccess;
	}

	/**
	 * 
	 */

	public java.util.Set<LinkUsersGroups> getSecLinkUsersGroups() {
		return this.secLinkUsersGroups;
	}

	public void setSecLinkUsersGroups(java.util.Set<LinkUsersGroups> SetOfSecLinkUsersGroups) {
		this.secLinkUsersGroups = SetOfSecLinkUsersGroups;
	}
}