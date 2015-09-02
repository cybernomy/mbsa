/*
 * PersonnelAttestation.java
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
 * @version $Id: PersonnelAttestation.java,v 1.2 2005/06/28 10:03:40 pashistova
 *          Exp $
 */
public class PersonnelAttestation extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.humanresources.model.Order Order;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.OriginalDocument ResolutionDocument;

	private java.util.Date AttestationDate;

	private java.lang.String Resolution;

	// Constructors

	/** default constructor */
	public PersonnelAttestation() {
	}

	/** constructor with id */
	public PersonnelAttestation(java.lang.Integer Id) {
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

	public com.mg.merp.humanresources.model.Order getOrder() {
		return this.Order;
	}

	public void setOrder(com.mg.merp.humanresources.model.Order HrOrder) {
		this.Order = HrOrder;
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
	@DataItemName("PersonnelRef.Personnel.ResolutionDocument")
	public com.mg.merp.reference.model.OriginalDocument getResolutionDocument() {
		return this.ResolutionDocument;
	}

	public void setResolutionDocument(
			com.mg.merp.reference.model.OriginalDocument RefOriginalDocument) {
		this.ResolutionDocument = RefOriginalDocument;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.AttestationDate")
	public java.util.Date getAttestationDate() {
		return this.AttestationDate;
	}

	public void setAttestationDate(java.util.Date AttestationDate) {
		this.AttestationDate = AttestationDate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Resolution")
	public java.lang.String getResolution() {
		return this.Resolution;
	}

	public void setResolution(java.lang.String Resolution) {
		this.Resolution = Resolution;
	}

}