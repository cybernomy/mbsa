/*
 * PersonnelSocialBenefit.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Социальные льготы"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PersonnelSocialBenefit.java,v 1.2 2005/06/28 10:03:44
 */
public class PersonnelSocialBenefit extends
com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.OriginalDocument OriginalDocument;

	private java.lang.String BenefitName;

	private java.lang.String BenefitReason;

	// Constructors

	/** default constructor */
	public PersonnelSocialBenefit() {
	}

	/** constructor with id */
	public PersonnelSocialBenefit(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	@DataItemName("ID") //$NON-NLS-1$
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
	@DataItemName("PersonnelRef.Personnel.OriginalDocument") //$NON-NLS-1$
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
	@DataItemName("PersonnelRef.Personnel.BenefitName") //$NON-NLS-1$
	public java.lang.String getBenefitName() {
		return this.BenefitName;
	}

	public void setBenefitName(java.lang.String BenefitName) {
		this.BenefitName = BenefitName;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.BenefitReason") //$NON-NLS-1$
	public java.lang.String getBenefitReason() {
		return this.BenefitReason;
	}

	public void setBenefitReason(java.lang.String BenefitReason) {
		this.BenefitReason = BenefitReason;
	}

}