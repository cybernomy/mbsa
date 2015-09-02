/*
 * SettlementConfig.java
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
package com.mg.merp.settlement.model;

/**
 * @author hbm2java
 * @version $Id: SettlementConfig.java,v 1.3 2006/03/30 11:32:21 safonov Exp $
 */
public class SettlementConfig extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private com.mg.merp.reference.model.Currency Currency;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Currency NarCurrency;

	private com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority;

	private java.lang.Integer CurrencyPrec;

	// Constructors

	/** default constructor */
	public SettlementConfig() {
	}

	/** constructor with id */
	public SettlementConfig(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	public com.mg.merp.reference.model.Currency getCurrency() {
		return Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency currency) {
		Currency = currency;
	}

	public java.lang.Integer getCurrencyPrec() {
		return CurrencyPrec;
	}

	public void setCurrencyPrec(java.lang.Integer currencyPrec) {
		CurrencyPrec = currencyPrec;
	}

	public com.mg.merp.reference.model.Currency getNarCurrency() {
		return NarCurrency;
	}

	public void setNarCurrency(com.mg.merp.reference.model.Currency narCurrency) {
		NarCurrency = narCurrency;
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

	public com.mg.merp.core.model.SysClient getSysClient() {
		return SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
		SysClient = sysClient;
	}

}