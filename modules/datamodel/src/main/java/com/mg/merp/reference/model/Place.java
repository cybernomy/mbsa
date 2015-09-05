/*
 * Place.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Place.java,v 1.5 2006/08/04 10:59:26 leonova Exp $
 */
@DataItemName("Reference.Place")
public class Place extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.District District;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Prefix;

	private java.lang.String Name;

	// Constructors

	/** default constructor */
	public Place() {
	}

	/** constructor with id */
	public Place(java.lang.Integer Id) {
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
	public com.mg.merp.reference.model.District getDistrict() {
		return this.District;
	}

	public void setDistrict(com.mg.merp.reference.model.District District) {
		this.District = District;
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
	@DataItemName("Reference.Address.Place")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

}