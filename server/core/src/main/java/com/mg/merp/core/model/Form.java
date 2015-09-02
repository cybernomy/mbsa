/*
 * Form.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Oleg V. Safonov
 * @version $Id: Form.java,v 1.3 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName ("Core.Form")
public class Form extends PersistentObjectHibernate {
	private Integer id;
	private ApplicationLayer applicationLayer;
	private com.mg.merp.security.model.Groups role;
	private com.mg.merp.security.model.SecUser user;
	private String name;
	private String description;
	private SysClass sysClass;
	private String implementation;
	
	public Form() {
	}
	
	public Form(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the description.
	 */
	@DataItemName ("Core.Description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the id.
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Returns the name.
	 */
	@DataItemName ("Core.Form.Name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the sysClass.
	 */
	public SysClass getSysClass() {
		return sysClass;
	}
	/**
	 * @param sysClass The sysClass to set.
	 */
	public void setSysClass(SysClass sysClass) {
		this.sysClass = sysClass;
	}

	/**
	 * @return Returns the implementation.
	 */
	@DataItemName ("Core.Window.Implementation")
	public String getImplementation() {
		return implementation;
	}

	/**
	 * @param implementation The implementation to set.
	 */
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	/**
	 * @return the applicationLayer
	 */
	public ApplicationLayer getApplicationLayer() {
		return applicationLayer;
	}

	/**
	 * @param applicationLayer the applicationLayer to set
	 */
	public void setApplicationLayer(ApplicationLayer applicationLayer) {
		this.applicationLayer = applicationLayer;
	}

	/**
	 * @return the role
	 */
	public com.mg.merp.security.model.Groups getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(com.mg.merp.security.model.Groups role) {
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public com.mg.merp.security.model.SecUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(com.mg.merp.security.model.SecUser user) {
		this.user = user;
	}

}
