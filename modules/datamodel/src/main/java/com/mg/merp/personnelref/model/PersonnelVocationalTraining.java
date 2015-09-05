/*
 * PersonnelVocationalTraining.java
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
 * @version $Id: PersonnelVocationalTraining.java,v 1.2 2005/06/28 10:03:44
 *          pashistova Exp $
 */
public class PersonnelVocationalTraining extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.Speciality Speciality;

	private com.mg.merp.humanresources.model.Order Order;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.OriginalDocument CertificateDocument;

	private java.util.Date TrainingBeginDate;

	private java.util.Date TrainingEndDate;

	// Constructors

	/** default constructor */
	public PersonnelVocationalTraining() {
	}

	/** constructor with id */
	public PersonnelVocationalTraining(java.lang.Integer Id) {
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
	@DataItemName("PersonnelRef.Vocat.Speciality")
	public com.mg.merp.personnelref.model.Speciality getSpeciality() {
		return this.Speciality;
	}

	public void setSpeciality(
			com.mg.merp.personnelref.model.Speciality PrefSpeciality) {
		this.Speciality = PrefSpeciality;
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
	@DataItemName("PersonnelRef.Personnel.OriginalDocument")
	public com.mg.merp.reference.model.OriginalDocument getCertificateDocument() {
		return this.CertificateDocument;
	}

	public void setCertificateDocument(
			com.mg.merp.reference.model.OriginalDocument RefOriginalDocument) {
		this.CertificateDocument = RefOriginalDocument;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.TrainingBeginDate")
	public java.util.Date getTrainingBeginDate() {
		return this.TrainingBeginDate;
	}

	public void setTrainingBeginDate(java.util.Date TrainingBegindate) {
		this.TrainingBeginDate = TrainingBegindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.TrainingEndDate")
	public java.util.Date getTrainingEndDate() {
		return this.TrainingEndDate;
	}

	public void setTrainingEndDate(java.util.Date TrainingEnddate) {
		this.TrainingEndDate = TrainingEnddate;
	}

}