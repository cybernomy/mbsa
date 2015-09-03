/*
 * PersonnelConfig.java
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

/**
 * Модель бизнес-компонента "Конфигурация модуля <Управление персоналом>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PersonnelConfig.java,v 1.3 2007/01/13 13:31:16 sharapov Exp $
 */
public class PersonnelConfig extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    

	private java.lang.Integer Id;
	private com.mg.merp.core.model.SysClient SysClient;
	private com.mg.merp.reference.model.Catalog CostsAnl;
	private com.mg.merp.reference.model.CurrencyRateType CurRateType;
	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;
//	private java.lang.String BaseCurrency;
//	private java.lang.String NatCurrency;
	private com.mg.merp.reference.model.Currency BaseCurrency;
	private com.mg.merp.reference.model.Currency NatCurrency;
	private java.lang.Short CurrencyPrec;


	// Constructors

	/** default constructor */
	public PersonnelConfig() {
	}

	/** constructor with id */
	public PersonnelConfig(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**

	 */
	public java.lang.Integer getId () {
		return this.Id;
	}

	public void setId (java.lang.Integer Id) {
		this.Id = Id;
	}
	/**

	 */
	public com.mg.merp.core.model.SysClient getSysClient () {
		return this.SysClient;
	}

	public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**

	 */
	public com.mg.merp.reference.model.Catalog getCostsAnl () {
		return this.CostsAnl;
	}

	public void setCostsAnl (com.mg.merp.reference.model.Catalog Catalog) {
		this.CostsAnl = Catalog;
	}

	/**

	 */
	public com.mg.merp.reference.model.CurrencyRateType getCurRateType () {
		return this.CurRateType;
	}

	public void setCurRateType (com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
		this.CurRateType = RefCurrencyRateType;
	}

	/**

	 */
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority () {
		return this.CurRateAuthority;
	}

	public void setCurRateAuthority (com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
		this.CurRateAuthority = RefCurrencyRateAuthority;
	}

	/**

	 */
//	public java.lang.String getBaseCurrency () {
//	return this.BaseCurrency;
//	}

//	public void setBaseCurrency (java.lang.String BaseCurrency) {
//	this.BaseCurrency = BaseCurrency;
//	}
//	/**

//	*/
//	public java.lang.String getNatCurrency () {
//	return this.NatCurrency;
//	}

//	public void setNatCurrency (java.lang.String NatCurrency) {
//	this.NatCurrency = NatCurrency;
//	}

	/**

	 */
	public java.lang.Short getCurrencyPrec () {
		return this.CurrencyPrec;
	}

	public void setCurrencyPrec (java.lang.Short CurrencyPrec) {
		this.CurrencyPrec = CurrencyPrec;
	}

	/**
	 * @return the baseCurrency
	 */
	public com.mg.merp.reference.model.Currency getBaseCurrency() {
		return BaseCurrency;
	}

	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public void setBaseCurrency(com.mg.merp.reference.model.Currency baseCurrency) {
		BaseCurrency = baseCurrency;
	}

	/**
	 * @return the natCurrency
	 */
	public com.mg.merp.reference.model.Currency getNatCurrency() {
		return NatCurrency;
	}

	/**
	 * @param natCurrency the natCurrency to set
	 */
	public void setNatCurrency(com.mg.merp.reference.model.Currency natCurrency) {
		NatCurrency = natCurrency;
	}

}