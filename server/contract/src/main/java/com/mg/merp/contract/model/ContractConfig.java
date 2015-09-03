/*
 * ContractConfig.java
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
package com.mg.merp.contract.model;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Контракты>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: ContractConfig.java,v 1.4 2007/01/13 13:14:09 sharapov Exp $
 */
public class ContractConfig extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private int sysClientId;

	private com.mg.merp.reference.model.Currency BaseCurrency;

	private com.mg.merp.reference.model.Currency NatCurrency;

	private com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority;

	private java.lang.Integer CurrencyPrec;

	/**
	 * 
	 */
	public ContractConfig() {

	}

	public ContractConfig(int sysClientId) {
		this.sysClientId = sysClientId;

	}

	public java.lang.Integer getCurrencyPrec() {
		return CurrencyPrec;
	}

	public void setCurrencyPrec(java.lang.Integer currencyPrec) {
		CurrencyPrec = currencyPrec;
	}

	public com.mg.merp.reference.model.CurrencyRateAuthority getRefCurrencyRateAuthority() {
		return RefCurrencyRateAuthority;
	}

	public void setRefCurrencyRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority refCurrencyRateAuthority) {
		RefCurrencyRateAuthority = refCurrencyRateAuthority;
	}

	public com.mg.merp.reference.model.CurrencyRateType getRefCurrencyRateType() {
		return RefCurrencyRateType;
	}

	public void setRefCurrencyRateType(
			com.mg.merp.reference.model.CurrencyRateType refCurrencyRateType) {
		RefCurrencyRateType = refCurrencyRateType;
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

	/**
	 * @return the sysClientId
	 */
	public int getSysClientId() {
		return sysClientId;
	}

	/**
	 * @param sysClientId the sysClientId to set
	 */
	public void setSysClientId(int sysClientId) {
		this.sysClientId = sysClientId;
	}

}