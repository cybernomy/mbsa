/*
 * Country.java
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
 * @version $Id: Country.java,v 1.6 2006/05/29 11:07:46 leonova Exp $
 */
@DataItemName("Reference.Country")
public class Country extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String CCode;

	private java.lang.String CName;

	private java.lang.String UniversalCode;

	private java.lang.String UniversalAbbr;

	private java.lang.String UniversalNumber;

	private java.lang.String Capital;

	private java.lang.String PhoneCode;

	private java.lang.String FullName;

	private java.lang.String AddressRule;

	private java.lang.String AddressDlm;

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** constructor with id */
	public Country(int Id) {
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.SmallCode")
	public java.lang.String getCCode() {
		return this.CCode;
	}

	public void setCCode(java.lang.String CCode) {
		this.CCode = CCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Name")
	public java.lang.String getCName() {
		return this.CName;
	}

	public void setCName(java.lang.String CName) {
		this.CName = CName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.AlfaTwo")
	public java.lang.String getUniversalCode() {
		return this.UniversalCode;
	}

	public void setUniversalCode(java.lang.String UniversalCode) {
		this.UniversalCode = UniversalCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.AlfaThree")
	public java.lang.String getUniversalAbbr() {
		return this.UniversalAbbr;
	}

	public void setUniversalAbbr(java.lang.String UniversalAbbr) {
		this.UniversalAbbr = UniversalAbbr;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.UNumber")
	public java.lang.String getUniversalNumber() {
		return this.UniversalNumber;
	}

	public void setUniversalNumber(java.lang.String UniversalNumber) {
		this.UniversalNumber = UniversalNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.Capital")
	public java.lang.String getCapital() {
		return this.Capital;
	}

	public void setCapital(java.lang.String Capital) {
		this.Capital = Capital;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.PhoneCode")
	public java.lang.String getPhoneCode() {
		return this.PhoneCode;
	}

	public void setPhoneCode(java.lang.String PhoneCode) {
		this.PhoneCode = PhoneCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.FullName")
	public java.lang.String getFullName() {
		return this.FullName;
	}

	public void setFullName(java.lang.String FullName) {
		this.FullName = FullName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.AddrRule")
	public java.lang.String getAddressRule() {
		return this.AddressRule;
	}

	public void setAddressRule(java.lang.String AddressRule) {
		this.AddressRule = AddressRule;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Country.AddrDml")
	public java.lang.String getAddressDlm() {
		return this.AddressDlm;
	}

	public void setAddressDlm(java.lang.String AddressDlm) {
		this.AddressDlm = AddressDlm;
	}

}