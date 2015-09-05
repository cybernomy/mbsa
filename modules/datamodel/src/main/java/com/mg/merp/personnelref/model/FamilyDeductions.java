/*
 * FamilyDeductions.java
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
 * Модель бизнес-компонента "Вычеты на членов семьи" 
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: FamilyDeductions.java,v 1.6 2007/01/24 11:28:59 sharapov Exp $
 */
public class FamilyDeductions extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.DeductionKind DeductionKind;

	private com.mg.merp.reference.model.FamilyMember FamilyMember;

	private java.util.Date BeginDate;

	private java.util.Date EndDate;

	private java.math.BigDecimal Ratio;
	
	/**
	 * person - объект-сущность "Сотрудник"
	 * Служит для реализации механизма поиска членов семьи сотрудника
	 */
	private com.mg.merp.reference.model.NaturalPerson person;

	// Constructors

	/** default constructor */
	public FamilyDeductions() {
	}

	/** constructor with id */
	public FamilyDeductions(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
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
	@DataItemName("PersonnelRef.FamilyDed.DeductionKind") //$NON-NLS-1$
	public com.mg.merp.personnelref.model.DeductionKind getDeductionKind() {
		return this.DeductionKind;
	}

	public void setDeductionKind(
			com.mg.merp.personnelref.model.DeductionKind PrefDeductionKind) {
		this.DeductionKind = PrefDeductionKind;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.FamilyDed.FamilyMembers") //$NON-NLS-1$
	public com.mg.merp.reference.model.FamilyMember getFamilyMember() {
		return this.FamilyMember;
	}

	public void setFamilyMember(
			com.mg.merp.reference.model.FamilyMember RefFamilyMember) {
		this.FamilyMember = RefFamilyMember;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.FamilyDed.BeginDate") //$NON-NLS-1$
	public java.util.Date getBeginDate() {
		return this.BeginDate;
	}

	public void setBeginDate(java.util.Date Begindate) {
		this.BeginDate = Begindate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.FamilyDed.EndDate") //$NON-NLS-1$
	public java.util.Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(java.util.Date Enddate) {
		this.EndDate = Enddate;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.FamilyDed.Ratio") //$NON-NLS-1$
	public java.math.BigDecimal getRatio() {
		return this.Ratio;
	}

	public void setRatio(java.math.BigDecimal Ratio) {
		this.Ratio = Ratio;
	}

	/**
	 * @return the person
	 */
	public com.mg.merp.reference.model.NaturalPerson getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(com.mg.merp.reference.model.NaturalPerson person) {
		this.person = person;
	}

}