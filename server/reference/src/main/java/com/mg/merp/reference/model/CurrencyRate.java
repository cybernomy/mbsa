/*
 * CurrencyRate.java
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
 * @version $Id: CurrencyRate.java,v 1.3 2006/05/29 11:15:38 leonova Exp $
 */
@DataItemName("Reference.CurrencyRate")
public class CurrencyRate extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Currency Currency1;

	private com.mg.merp.reference.model.Currency Currency2;

	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.math.BigDecimal Rate;

	private java.util.Date EffectiveDate;

	// Constructors

	/** default constructor */
	public CurrencyRate() {
	}

	/** constructor with id */
	public CurrencyRate(java.lang.Integer Id) {
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

	public com.mg.merp.reference.model.Currency getCurrency1() {
		return this.Currency1;
	}

	public void setCurrency1(com.mg.merp.reference.model.Currency Currency) {
		this.Currency1 = Currency;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Currency getCurrency2() {
		return this.Currency2;
	}

	public void setCurrency2(com.mg.merp.reference.model.Currency Currency1) {
		this.Currency2 = Currency1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
		return this.CurrencyRateType;
	}

	public void setCurrencyRateType(
			com.mg.merp.reference.model.CurrencyRateType CurrencyRateType) {
		this.CurrencyRateType = CurrencyRateType;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
		return this.CurrencyRateAuthority;
	}

	public void setCurrencyRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority) {
		this.CurrencyRateAuthority = CurrencyRateAuthority;
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
	@DataItemName("Reference.CurRate.Rate")
	public java.math.BigDecimal getRate() {
		return this.Rate;
	}

	public void setRate(java.math.BigDecimal Rate) {
		this.Rate = Rate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.CurRate.EffectiveDate")
	public java.util.Date getEffectiveDate() {
		return this.EffectiveDate;
	}

	public void setEffectiveDate(java.util.Date EffectiveDate) {
		this.EffectiveDate = EffectiveDate;
	}

}