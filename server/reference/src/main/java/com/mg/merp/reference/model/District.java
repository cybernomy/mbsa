/*
 * District.java
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
 * @version $Id: District.java,v 1.4 2006/05/29 11:28:29 leonova Exp $
 */
@DataItemName("Reference.District")
public class District extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Region Region;

	private java.lang.String Prefix;

	private java.lang.String Name;

	// Constructors

	/** default constructor */
	public District() {
	}

	/** constructor with id */
	public District(java.lang.Integer Id) {
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
	
	public com.mg.merp.reference.model.Region getRegion() {
		return this.Region;
	}

	public void setRegion(com.mg.merp.reference.model.Region Region) {
		this.Region = Region;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Address.Prefix")
	public java.lang.String getPrefix() {
		return this.Prefix;
	}

	public void setPrefix(java.lang.String Prefix) {
		this.Prefix = Prefix;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Address.District")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

}