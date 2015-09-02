/*
 * Currency.java
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
 * @version $Id: Currency.java,v 1.3 2006/05/29 11:21:50 leonova Exp $
 */
@DataItemName("Reference.Currency")
public class Currency extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.String UpCode;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Code;

	private java.lang.String Iso;

	private java.lang.String BankCode;

	private java.lang.String FullName;

	private CurrencyGender Gender;

	private java.lang.String AltName1;

	private java.lang.String AltName2;

	private java.lang.String AltName3;

	private java.lang.String AltUnitName1;

	private java.lang.String AltUnitName2;

	private java.lang.String AltUnitName3;

	private java.math.BigDecimal RoundPrice;

	private java.math.BigDecimal RoundSum;

	private java.lang.Integer ViewPriority;

	private java.lang.Integer Id;

	// Constructors

	/** default constructor */
	public Currency() {
	}

	/** constructor with id */
	public Currency(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */

	public java.lang.String getUpCode() {
		return this.UpCode;
	}

	public void setUpCode(java.lang.String UpCode) {
		this.UpCode = UpCode;
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
	@DataItemName("Reference.Currency.Code")
	public java.lang.String getCode() {
		return this.Code;
	}

	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.ISO")
	public java.lang.String getIso() {
		return this.Iso;
	}

	public void setIso(java.lang.String Iso) {
		this.Iso = Iso;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.BankCode")
	public java.lang.String getBankCode() {
		return this.BankCode;
	}

	public void setBankCode(java.lang.String BankCode) {
		this.BankCode = BankCode;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.FullName")
	public java.lang.String getFullName() {
		return this.FullName;
	}

	public void setFullName(java.lang.String CName) {
		this.FullName = CName;
	}

	/**
	 * 
	 */

	public CurrencyGender getGender() {
		return this.Gender;
	}

	public void setGender(CurrencyGender Men) {
		this.Gender = Men;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName1")
	public java.lang.String getAltName1() {
		return this.AltName1;
	}

	public void setAltName1(java.lang.String AltName1) {
		this.AltName1 = AltName1;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName2")
	public java.lang.String getAltName2() {
		return this.AltName2;
	}

	public void setAltName2(java.lang.String AltName2) {
		this.AltName2 = AltName2;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName3")
	public java.lang.String getAltName3() {
		return this.AltName3;
	}

	public void setAltName3(java.lang.String AltName3) {
		this.AltName3 = AltName3;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName1")
	public java.lang.String getAltUnitName1() {
		return this.AltUnitName1;
	}

	public void setAltUnitName1(java.lang.String AltunitName1) {
		this.AltUnitName1 = AltunitName1;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName2")
	public java.lang.String getAltUnitName2() {
		return this.AltUnitName2;
	}

	public void setAltUnitName2(java.lang.String AltunitName2) {
		this.AltUnitName2 = AltunitName2;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.AltName3")
	public java.lang.String getAltUnitName3() {
		return this.AltUnitName3;
	}

	public void setAltUnitName3(java.lang.String AltunitName3) {
		this.AltUnitName3 = AltunitName3;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.RoundPrice")
	public java.math.BigDecimal getRoundPrice() {
		return this.RoundPrice;
	}

	public void setRoundPrice(java.math.BigDecimal RoundPrice) {
		this.RoundPrice = RoundPrice;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Currency.RoundSum")
	public java.math.BigDecimal getRoundSum() {
		return this.RoundSum;
	}

	public void setRoundSum(java.math.BigDecimal RoundSum) {
		this.RoundSum = RoundSum;
	}

	/**
	 * 
	 */

	public java.lang.Integer getViewPriority() {
		return this.ViewPriority;
	}

	public void setViewPriority(java.lang.Integer ViewPriority) {
		this.ViewPriority = ViewPriority;
	}

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
}