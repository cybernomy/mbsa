/*
 * SysClient.java
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

import com.mg.framework.api.SystemTenant;

/**
 * @author hbm2java
 * @version $Id: SysClient.java,v 1.4 2009/02/18 12:18:41 safonov Exp $
 */
public class SysClient extends
		com.mg.framework.service.PersistentObjectHibernate implements
		SystemTenant, java.io.Serializable {

	// Fields    

	private java.lang.Integer id;

	private java.lang.String code;

	private java.lang.String name;

	private java.lang.String description;

	private boolean isActive;

	private java.lang.String language;

	// Constructors

	/** default constructor */
	public SysClient() {
	}

	/** constructor with id */
	public SysClient(java.lang.Integer Id) {
		this.id = Id;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SystemTenant#getIdentifier()
	 */
	public int getIdentifier() {
		return getId();
	}

	// Property accessors
	/**
	 
	 */

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 
	 */

	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String Code) {
		this.code = Code;
	}

	/**
	 
	 */

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String Name) {
		this.name = Name;
	}

	/**
	 
	 */

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String Description) {
		this.description = Description;
	}

	/**
	 
	 */

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean IsActive) {
		this.isActive = IsActive;
	}

	/**
	 
	 */

	public java.lang.String getLanguage() {
		return this.language;
	}

	public void setLanguage(java.lang.String Language) {
		this.language = Language;
	}

}