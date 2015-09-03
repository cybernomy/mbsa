/*
 * StlConfig.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.settlement.model;

import com.mg.framework.service.PersistentObjectHibernate;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Расчеты с партнерами>"
 * 
 * @author Artem V. Sharapov
 * @version $Id: StlConfig.java,v 1.1 2007/03/19 14:55:34 sharapov Exp $
 */
public class StlConfig extends PersistentObjectHibernate implements
java.io.Serializable {

	// Fields
	private int sysClientId;
	private com.mg.merp.reference.model.Currency BaseCurrency;
	private com.mg.merp.reference.model.Currency NatCurrency;
	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;
	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;
	private java.lang.Integer CurrencyPrec;

	public StlConfig() {
	}

	/** constructor with id */
	public StlConfig(int sysClientId) {
		this.sysClientId = sysClientId;
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
	 * @return the currencyPrec
	 */
	public java.lang.Integer getCurrencyPrec() {
		return CurrencyPrec;
	}

	/**
	 * @param currencyPrec the currencyPrec to set
	 */
	public void setCurrencyPrec(java.lang.Integer currencyPrec) {
		CurrencyPrec = currencyPrec;
	}

	/**
	 * @return the currencyRateAuthority
	 */
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
		return CurrencyRateAuthority;
	}

	/**
	 * @param currencyRateAuthority the currencyRateAuthority to set
	 */
	public void setCurrencyRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority currencyRateAuthority) {
		CurrencyRateAuthority = currencyRateAuthority;
	}

	/**
	 * @return the currencyRateType
	 */
	public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
		return CurrencyRateType;
	}

	/**
	 * @param currencyRateType the currencyRateType to set
	 */
	public void setCurrencyRateType(
			com.mg.merp.reference.model.CurrencyRateType currencyRateType) {
		CurrencyRateType = currencyRateType;
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
