/**
 * Window.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.service.PersistentObjectHibernate;

/**
 * Окно пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: Window.java,v 1.1 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName ("Core.Window")
public class Window extends PersistentObjectHibernate {
	private Integer id;
	private ApplicationLayer applicationLayer;
	private com.mg.merp.security.model.Groups role;
	private com.mg.merp.security.model.SecUser user;
	private String name;
	private String description;
	private String implementation;
	
	public Window() {
	}

	public Window(Integer id) {
		this.id = id;
	}

	//Property accessors

	/**
	 * @return the id
	 */
	@DataItemName("ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the name
	 */
	@DataItemName ("Core.Window.Name")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@DataItemName ("Core.Description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the implementation
	 */
	@DataItemName ("Core.Window.Implementation")
	public String getImplementation() {
		return implementation;
	}

	/**
	 * @param implementation the implementation to set
	 */
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	
}
