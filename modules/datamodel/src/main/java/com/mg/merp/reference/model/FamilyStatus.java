/*
 * FamilyStatus.java
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
 * @version $Id: FamilyStatus.java,v 1.3 2006/05/30 04:43:15 leonova Exp $
 */
@DataItemName("Reference.FamilyStatus")
public class FamilyStatus extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.FamilyStatusKind FamilyStatusKind;

	private java.util.Date BeginDate;

	// Constructors

	/** default constructor */
	public FamilyStatus() {
	}

	/** constructor with id */
	public FamilyStatus(java.lang.Integer Id) {
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

	public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
		return this.NaturalPerson;
	}

	public void setNaturalPerson(
			com.mg.merp.reference.model.NaturalPerson NaturalPerson) {
		this.NaturalPerson = NaturalPerson;
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
	public com.mg.merp.reference.model.FamilyStatusKind getFamilyStatusKind() {
		return this.FamilyStatusKind;
	}

	public void setFamilyStatusKind(
			com.mg.merp.reference.model.FamilyStatusKind FamilyStatusKind) {
		this.FamilyStatusKind = FamilyStatusKind;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.FStatus.BeginDate")
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date BeginDate) {
		this.BeginDate = BeginDate;
	}
}