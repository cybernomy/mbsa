/*
 * SecUser.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: User.java,v 1.5 2006/06/14 07:51:53 leonova Exp $
 */
@DataItemName("CRM.User") 
public class User extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.NaturalPerson Person;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String ThePosition;

	private boolean Busy;

	private boolean IsAdmin;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** constructor with id */
	public User(java.lang.Integer Id) {
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

	@DataItemName("CRM.User.Person")
	public com.mg.merp.reference.model.NaturalPerson getPerson() {
		return this.Person;
	}

	public void setPerson(
			com.mg.merp.reference.model.NaturalPerson RefNaturalPerson) {
		this.Person = RefNaturalPerson;
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

	@DataItemName("CRM.User.ThePosition")
	public java.lang.String getThePosition() {
		return this.ThePosition;
	}

	public void setThePosition(java.lang.String ThePosition) {
		this.ThePosition = ThePosition;
	}

	/**
	 * 
	 */

	@DataItemName("CRM.User.Busy")
	public boolean getBusy() {
		return this.Busy;
	}

	public void setBusy(boolean Busy) {
		this.Busy = Busy;
	}

	/**
	 * 
	 */

	@DataItemName("CRM.User.IsAdmin")
	public boolean getIsAdmin() {
		return this.IsAdmin;
	}

	public void setIsAdmin(boolean IsAdmin) {
		this.IsAdmin = IsAdmin;
	}

}