/*
 * PersonnelLanguage.java
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
 * @version $Id: PersonnelLanguage.java,v 1.4 2006/06/19 07:30:04 leonova Exp $
 */
public class PersonnelLanguage extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.LanguageKnowledge LanguageKnowledge;

	private com.mg.merp.personnelref.model.ForeignLanguage ForeignLanguage;

	// Constructors

	/** default constructor */
	public PersonnelLanguage() {
	}

	/** constructor with id */
	public PersonnelLanguage(java.lang.Integer Id) {
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
	@DataItemName("PersonnelRef.Personnel.LanguageKnowledge")
	public com.mg.merp.personnelref.model.LanguageKnowledge getLanguageKnowledge() {
		return this.LanguageKnowledge;
	}

	public void setLanguageKnowledge(
			com.mg.merp.personnelref.model.LanguageKnowledge PrefLanguageKnowledge) {
		this.LanguageKnowledge = PrefLanguageKnowledge;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.ForeignLanguage getForeignLanguage() {
		return this.ForeignLanguage;
	}

	public void setForeignLanguage(
			com.mg.merp.personnelref.model.ForeignLanguage PrefForeignLanguage) {
		this.ForeignLanguage = PrefForeignLanguage;
	}

}