/*
 * PersonnelEducation.java
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
 * Модель бизнес-компонента "Образование"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PersonnelEducation.java,v 1.2 2005/06/28 10:03:40 pashistova
 */
public class PersonnelEducation extends
com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.personnelref.model.AdditionalEducationKind AdditionalEducationKind;

	private com.mg.merp.personnelref.model.Speciality Speciality;

	private com.mg.merp.personnelref.model.Personnel Personnel;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String InstitutionName;

	private java.lang.String DiplomaQualification;

	private java.lang.String DiplomaNumber;

	private java.util.Date DiplomaDate;

	private java.lang.Integer GraduationYear;

	private PrefIsAdditionalEducation IsAdditional;

	// Constructors

	/** default constructor */
	public PersonnelEducation() {
	}

	/** constructor with id */
	public PersonnelEducation(java.lang.Integer Id) {
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
	public com.mg.merp.personnelref.model.AdditionalEducationKind getAdditionalEducationKind() {
		return this.AdditionalEducationKind;
	}

	public void setAdditionalEducationKind(
			com.mg.merp.personnelref.model.AdditionalEducationKind PrefAdditionalEducationKind) {
		this.AdditionalEducationKind = PrefAdditionalEducationKind;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Speciality") //$NON-NLS-1$
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
	@DataItemName("PersonnelRef.Personnel.InstitutionName") //$NON-NLS-1$
	public java.lang.String getInstitutionName() {
		return this.InstitutionName;
	}

	public void setInstitutionName(java.lang.String InstitutionName) {
		this.InstitutionName = InstitutionName;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.DiplomaQualification") //$NON-NLS-1$
	public java.lang.String getDiplomaQualification() {
		return this.DiplomaQualification;
	}

	public void setDiplomaQualification(java.lang.String DiplomaQualification) {
		this.DiplomaQualification = DiplomaQualification;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.DiplomaNumber") //$NON-NLS-1$
	public java.lang.String getDiplomaNumber() {
		return this.DiplomaNumber;
	}

	public void setDiplomaNumber(java.lang.String DiplomaNumber) {
		this.DiplomaNumber = DiplomaNumber;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.DiplomaDate") //$NON-NLS-1$
	public java.util.Date getDiplomaDate() {
		return this.DiplomaDate;
	}

	public void setDiplomaDate(java.util.Date DiplomaDate) {
		this.DiplomaDate = DiplomaDate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.GraduationYear") //$NON-NLS-1$
	public java.lang.Integer getGraduationYear() {
		return this.GraduationYear;
	}

	public void setGraduationYear(java.lang.Integer GraduationYear) {
		this.GraduationYear = GraduationYear;
	}

	/**
	 * 
	 */

	public PrefIsAdditionalEducation getIsAdditional() {
		return this.IsAdditional;
	}

	public void setIsAdditional(PrefIsAdditionalEducation IsAdditional) {
		this.IsAdditional = IsAdditional;
	}

}