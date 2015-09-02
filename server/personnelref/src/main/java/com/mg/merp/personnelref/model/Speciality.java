/*
 * Speciality.java
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
 * @version $Id: Speciality.java,v 1.4 2006/09/04 13:01:16 leonova Exp $
 */
public class Speciality extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.lang.String Okso;

	private java.lang.String Msko;

	// Constructors

	/** default constructor */
	public Speciality() {
	}

	/** constructor with id */
	public Speciality(java.lang.Integer Id) {
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

	@DataItemName("PersonnelRef.Speciality.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Speciality.Okso")
	public java.lang.String getOkso() {
		return this.Okso;
	}

	public void setOkso(java.lang.String Okso) {
		this.Okso = Okso;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Speciality.Msko")
	public java.lang.String getMsko() {
		return this.Msko;
	}

	public void setMsko(java.lang.String Msko) {
		this.Msko = Msko;
	}

}