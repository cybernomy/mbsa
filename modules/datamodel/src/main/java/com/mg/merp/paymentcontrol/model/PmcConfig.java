/*
 * PmcConfig.java
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
package com.mg.merp.paymentcontrol.model;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Платежный календарь>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PmcConfig.java,v 1.4 2007/01/13 13:28:40 sharapov Exp $
 */
public class PmcConfig extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private int sysClientId;

	private com.mg.merp.reference.model.Currency Currency;

	private com.mg.merp.reference.model.CurrencyRateType CurRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

	private java.lang.Short CurrencyPrec;

	// Constructors

	/** default constructor */
	public PmcConfig() {
	}

	/** constructor with id */
	public PmcConfig(int sysClientId) {
		this.sysClientId = sysClientId;
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

	public com.mg.merp.reference.model.Currency getCurrency() {
		return Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency currency) {
		Currency = currency;
	}

	public java.lang.Short getCurrencyPrec() {
		return CurrencyPrec;
	}

	public void setCurrencyPrec(java.lang.Short currencyPrec) {
		CurrencyPrec = currencyPrec;
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