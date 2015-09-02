/*
 * FinConfig.java
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
package com.mg.merp.finance.model;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Фин.учет>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: FinConfig.java,v 1.4 2007/01/13 13:17:18 sharapov Exp $
 */
public class FinConfig extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Currency BaseCurrency;

	private int sysClientId;

	private com.mg.merp.reference.model.Currency NatCurrency;

	private com.mg.merp.reference.model.CurrencyRateType CurRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

	private java.lang.Integer CurrencyPrec;

	// Constructors

	/** default constructor */
	public FinConfig() {
	}

	/** constructor with id */
	public FinConfig(int sysClientId) {
		this.sysClientId = sysClientId;
	}

	public com.mg.merp.reference.model.Currency getBaseCurrency() {
		return BaseCurrency;
	}

	public void setBaseCurrency(
			com.mg.merp.reference.model.Currency baseCurrency) {
		BaseCurrency = baseCurrency;
	}

	public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
		return CurRateAuthority;
	}

	public void setCurRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority curRateAuthority) {
		CurRateAuthority = curRateAuthority;
	}

	public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
		return CurRateType;
	}

	public void setCurRateType(
			com.mg.merp.reference.model.CurrencyRateType curRateType) {
		CurRateType = curRateType;
	}

	public java.lang.Integer getCurrencyPrec() {
		return CurrencyPrec;
	}

	public void setCurrencyPrec(java.lang.Integer currencyPrec) {
		CurrencyPrec = currencyPrec;
	}

	public com.mg.merp.reference.model.Currency getNatCurrency() {
		return NatCurrency;
	}

	public void setNatCurrency(com.mg.merp.reference.model.Currency natCurrency) {
		NatCurrency = natCurrency;
	}

	public int getSysClientId() {
		return sysClientId;
	}

	public void setSysClientId(int sysClientId) {
		this.sysClientId = sysClientId;
	}

}