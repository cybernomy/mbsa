/*
 * PersonnelReward.java
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
 * @version $Id: PersonnelReward.java,v 1.4 2006/08/14 08:07:18 leonova Exp $
 */
public class PersonnelReward extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.OriginalDocument OriginalDocument;

	private java.lang.String RewardName;

	// Constructors

	/** default constructor */
	public PersonnelReward() {
	}

	/** constructor with id */
	public PersonnelReward(java.lang.Integer Id) {
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
	@DataItemName("PersonnelRef.Personnel.OriginalDocument")
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
	@DataItemName("PersonnelRef.Personnel.RewardName")
	public java.lang.String getRewardName() {
		return this.RewardName;
	}

	public void setRewardName(java.lang.String RewardName) {
		this.RewardName = RewardName;
	}

}