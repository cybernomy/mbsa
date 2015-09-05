/*
 * PartnerEmpl.java
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
 * @version $Id: PartnerEmpl.java,v 1.6 2006/08/15 13:33:30 leonova Exp $
 */
public class PartnerEmpl extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Partner Partner;

	private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Name;

	private java.lang.String Patronymic;

	private java.lang.String Surname;

	private java.lang.String Address;

	private java.lang.String Militia;

	private java.lang.String MilitCity;

	private java.util.Date PasspDate;

	private java.lang.String PasspNum;

	private java.lang.String PasspSer;

	private java.lang.String Office;

	private java.lang.String Comment;

	// Constructors

	/** default constructor */
	public PartnerEmpl() {
	}

	/** constructor with id */
	public PartnerEmpl(java.lang.Integer Id) {
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

	public com.mg.merp.reference.model.Partner getPartner() {
		return this.Partner;
	}

	public void setPartner(com.mg.merp.reference.model.Partner Partner) {
		this.Partner = Partner;
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

	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String FName) {
		this.Name = FName;
	}

	/**
	 * 
	 */

	public java.lang.String getPatronymic() {
		return this.Patronymic;
	}

	public void setPatronymic(java.lang.String Patronymic) {
		this.Patronymic = Patronymic;
	}

	/**
	 * 
	 */

	public java.lang.String getSurname() {
		return this.Surname;
	}

	public void setSurname(java.lang.String Surname) {
		this.Surname = Surname;
	}

	/**
	 * 
	 */

	public java.lang.String getAddress() {
		return this.Address;
	}

	public void setAddress(java.lang.String Address) {
		this.Address = Address;
	}

	/**
	 * 
	 */

	public java.lang.String getMilitia() {
		return this.Militia;
	}

	public void setMilitia(java.lang.String Militia) {
		this.Militia = Militia;
	}

	/**
	 * 
	 */

	public java.lang.String getMilitCity() {
		return this.MilitCity;
	}

	public void setMilitCity(java.lang.String MilitCity) {
		this.MilitCity = MilitCity;
	}

	/**
	 * 
	 */

	public java.util.Date getPasspDate() {
		return this.PasspDate;
	}

	public void setPasspDate(java.util.Date PasspDate) {
		this.PasspDate = PasspDate;
	}

	/**
	 * 
	 */

	public java.lang.String getPasspNum() {
		return this.PasspNum;
	}

	public void setPasspNum(java.lang.String PasspNum) {
		this.PasspNum = PasspNum;
	}

	/**
	 * 
	 */

	public java.lang.String getPasspSer() {
		return this.PasspSer;
	}

	public void setPasspSer(java.lang.String PasspSer) {
		this.PasspSer = PasspSer;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Partner.Empl.Office")
	public java.lang.String getOffice() {
		return this.Office;
	}

	public void setOffice(java.lang.String Office) {
		this.Office = Office;
	}

	/**
	 * 
	 */

	public java.lang.String getComment() {
		return this.Comment;
	}

	public void setComment(java.lang.String Comment) {
		this.Comment = Comment;
	}

}