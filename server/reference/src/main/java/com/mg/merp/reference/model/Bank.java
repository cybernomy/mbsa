/*
 * Bank.java
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
 * @version $Id: Bank.java,v 1.5 2006/05/29 10:12:16 leonova Exp $
 */
@DataItemName("Reference.Bank")
public class Bank extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Currency Currency;

	private com.mg.merp.reference.model.Country Country;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String BIK;

	private boolean BikUnique;

	private java.lang.String Name;

	private java.lang.String Branch;

	private java.lang.String CityType;

	private java.lang.String City;

	private java.lang.String Address;

	private java.lang.String Zip;

	private java.lang.String CorrAcc;

	private java.lang.String CorrName;

	private java.lang.String CorrAddress;

	private java.lang.String Swift;

	private java.lang.String Iban;

	private java.lang.String Bsc;

	private java.lang.String Phone;

	private java.lang.String Fax;

	private java.lang.String Email;

	private java.lang.String Www;

	private java.lang.String Comments;

	// Constructors

	/** default constructor */
	public Bank() {
	}

	/** constructor with id */
	public Bank(java.lang.Integer Id) {
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
	@DataItemName("Reference.Bank.Currency")
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Country")
	public com.mg.merp.reference.model.Country getCountry() {
		return this.Country;
	}

	public void setCountry(com.mg.merp.reference.model.Country Country) {
		this.Country = Country;
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
	@DataItemName("Reference.Bank.BIK.Name")
	public java.lang.String getBIK() {
		return this.BIK;
	}

	public void setBIK(java.lang.String Bik) {
		this.BIK = Bik;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.BikUnique")
	public boolean getBikUnique() {
		return this.BikUnique;
	}

	public void setBikUnique(boolean BikUnique) {
		this.BikUnique = BikUnique;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Name")
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Branch")
	public java.lang.String getBranch() {
		return this.Branch;
	}

	public void setBranch(java.lang.String Branch) {
		this.Branch = Branch;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.CityType")
	public java.lang.String getCityType() {
		return this.CityType;
	}

	public void setCityType(java.lang.String CityType) {
		this.CityType = CityType;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.City")
	public java.lang.String getCity() {
		return this.City;
	}

	public void setCity(java.lang.String City) {
		this.City = City;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Address")
	public java.lang.String getAddress() {
		return this.Address;
	}

	public void setAddress(java.lang.String Address) {
		this.Address = Address;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Zip")
	public java.lang.String getZip() {
		return this.Zip;
	}

	public void setZip(java.lang.String Zip) {
		this.Zip = Zip;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.CorrAcc")
	public java.lang.String getCorrAcc() {
		return this.CorrAcc;
	}

	public void setCorrAcc(java.lang.String CorrAcc) {
		this.CorrAcc = CorrAcc;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.CorrName")
	public java.lang.String getCorrName() {
		return this.CorrName;
	}

	public void setCorrName(java.lang.String CorrName) {
		this.CorrName = CorrName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.CorrAddress")
	public java.lang.String getCorrAddress() {
		return this.CorrAddress;
	}

	public void setCorrAddress(java.lang.String CorrAddress) {
		this.CorrAddress = CorrAddress;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Swift")
	public java.lang.String getSwift() {
		return this.Swift;
	}

	public void setSwift(java.lang.String Swift) {
		this.Swift = Swift;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Iban")
	public java.lang.String getIban() {
		return this.Iban;
	}

	public void setIban(java.lang.String Iban) {
		this.Iban = Iban;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Bsc")
	public java.lang.String getBsc() {
		return this.Bsc;
	}

	public void setBsc(java.lang.String Bsc) {
		this.Bsc = Bsc;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Phone")
	public java.lang.String getPhone() {
		return this.Phone;
	}

	public void setPhone(java.lang.String Phone) {
		this.Phone = Phone;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.Fax")
	public java.lang.String getFax() {
		return this.Fax;
	}

	public void setFax(java.lang.String Fax) {
		this.Fax = Fax;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.EMail")
	public java.lang.String getEmail() {
		return this.Email;
	}

	public void setEmail(java.lang.String Email) {
		this.Email = Email;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Bank.WWW")
	public java.lang.String getWww() {
		return this.Www;
	}

	public void setWww(java.lang.String Www) {
		this.Www = Www;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Comments")
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

}