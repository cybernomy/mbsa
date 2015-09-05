/*
 * NaturalPerson.java
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
 * @version $Id: NaturalPerson.java,v 1.7 2006/11/02 16:20:44 safonov Exp $
 */
@DataItemName("Reference.NaturalPerson")
public class NaturalPerson extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.security.model.SecUser Users;

	private java.util.Date ActDate;

	private java.lang.String Surname;

	private java.lang.String Name;

	private java.lang.String Patronymic;

	private java.util.Date BornDate;

	private GenderType Sex;

	// private byte[] Photo;
	private java.lang.String Inn;

	private java.lang.String AdditionalInfo;

	private java.lang.String Code;

	private java.util.Set SetOfRefIdentDoc;

	private java.util.Set SetOfRefFamilyStatus;

	private java.util.Set SetOfRefFamilyMember;

	private java.util.Set SetOfRefPersonPhone;

	private java.util.Set SetOfRefNaturalPersonHist;

	private java.util.Set SetOfRefPersonAddress;

	private java.util.Set SetOfRefPersonEAddress;

	// Constructors

	/** default constructor */
	public NaturalPerson() {
	}

	/** constructor with id */
	public NaturalPerson(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("Reference.Person.User")
	public com.mg.merp.security.model.SecUser getUsers() {
		return this.Users;
	}

	public void setUsers(com.mg.merp.security.model.SecUser Users) {
		this.Users = Users;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.ActDate")
	public java.util.Date getActDate() {
		return this.ActDate;
	}

	public void setActDate(java.util.Date ActDate) {
		this.ActDate = ActDate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.Surname")
	public java.lang.String getSurname() {
		return this.Surname;
	}

	public void setSurname(java.lang.String Surname) {
		this.Surname = Surname;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.Patronymic")
	public java.lang.String getPatronymic() {
		return this.Patronymic;
	}

	public void setPatronymic(java.lang.String Patronymic) {
		this.Patronymic = Patronymic;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.NaturalPerson.BornDate")
	public java.util.Date getBornDate() {
		return this.BornDate;
	}

	public void setBornDate(java.util.Date BornDate) {
		this.BornDate = BornDate;
	}

	/**
	 * 
	 */

	public GenderType getSex() {
		return this.Sex;
	}

	public void setSex(GenderType Sex) {
		this.Sex = Sex;
	}

	/**
	 * 
	 */

	/*
	 * public byte[] getPhoto () { return this.Photo; }
	 * 
	 * public void setPhoto (byte[] Photo) { this.Photo = Photo; }
	 */
	/**
	 * 
	 */
	@DataItemName("Reference.Person.INN")
	public java.lang.String getInn() {
		return this.Inn;
	}

	public void setInn(java.lang.String Inn) {
		this.Inn = Inn;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Person.Info")
	public java.lang.String getAdditionalInfo() {
		return this.AdditionalInfo;
	}

	public void setAdditionalInfo(java.lang.String AdditionalInfo) {
		this.AdditionalInfo = AdditionalInfo;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefIdentDoc() {
		return this.SetOfRefIdentDoc;
	}

	public void setSetOfRefIdentDoc(java.util.Set SetOfRefIdentDoc) {
		this.SetOfRefIdentDoc = SetOfRefIdentDoc;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefFamilyStatus() {
		return this.SetOfRefFamilyStatus;
	}

	public void setSetOfRefFamilyStatus(java.util.Set SetOfRefFamilyStatus) {
		this.SetOfRefFamilyStatus = SetOfRefFamilyStatus;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefFamilyMember() {
		return this.SetOfRefFamilyMember;
	}

	public void setSetOfRefFamilyMember(java.util.Set SetOfRefFamilyMember) {
		this.SetOfRefFamilyMember = SetOfRefFamilyMember;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefPersonPhone() {
		return this.SetOfRefPersonPhone;
	}

	public void setSetOfRefPersonPhone(java.util.Set SetOfRefPersonPhone) {
		this.SetOfRefPersonPhone = SetOfRefPersonPhone;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefNaturalPersonHist() {
		return this.SetOfRefNaturalPersonHist;
	}

	public void setSetOfRefNaturalPersonHist(
			java.util.Set SetOfRefNaturalPersonHist) {
		this.SetOfRefNaturalPersonHist = SetOfRefNaturalPersonHist;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefPersonAddress() {
		return this.SetOfRefPersonAddress;
	}

	public void setSetOfRefPersonAddress(java.util.Set SetOfRefPersonAddress) {
		this.SetOfRefPersonAddress = SetOfRefPersonAddress;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfRefPersonEAddress() {
		return this.SetOfRefPersonEAddress;
	}

	public void setSetOfRefPersonEAddress(java.util.Set SetOfRefPersonEaddress) {
		this.SetOfRefPersonEAddress = SetOfRefPersonEaddress;
	}

}