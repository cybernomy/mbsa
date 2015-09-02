/*
 * PersonnelLabourContract.java
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
 * @version $Id: PersonnelLabourContract.java,v 1.2 2005/06/28 10:03:41
 *          pashistova Exp $
 */
public class PersonnelLabourContract extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.OriginalDocument OriginalDocument;

	private java.lang.String Name;

	// Constructors

	/** default constructor */
	public PersonnelLabourContract() {
	}

	/** constructor with id */
	public PersonnelLabourContract(java.lang.Integer Id) {
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

	public com.mg.merp.personnelref.model.Personnel getPersonnel() {
		return this.Personnel;
	}

	public void setPersonnel(
			com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
		this.Personnel = PrefPersonnel;
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

	@DataItemName("PersonnelRef.Personnel.OrigDoc")
	public com.mg.merp.reference.model.OriginalDocument getOriginalDocument() {
		return this.OriginalDocument;
	}

	public void setOriginalDocument(
			com.mg.merp.reference.model.OriginalDocument RefOriginalDocument) {
		this.OriginalDocument = RefOriginalDocument;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.ContrName")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

}