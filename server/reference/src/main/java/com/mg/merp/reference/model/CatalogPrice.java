/*
 * CatalogPrice.java
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
 * @version $Id: CatalogPrice.java,v 1.3 2006/05/30 10:01:27 leonova Exp $
 */
@DataItemName("Reference.CatalogPrice")
public class CatalogPrice extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

	private com.mg.merp.reference.model.Currency Currency;

	private java.util.Date InAction;

	private java.math.BigDecimal Price;

	private java.math.BigDecimal EquivalentPrice;

	// Constructors

	/** default constructor */
	public CatalogPrice() {
	}

	/** constructor with id */
	public CatalogPrice(java.lang.Integer Id) {
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

	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
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
	@DataItemName("Reference.Catalog.Price.Currency")
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Catalog.Price.InAction")
	public java.util.Date getInAction() {
		return this.InAction;
	}

	public void setInAction(java.util.Date InAction) {
		this.InAction = InAction;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Catalog.Price.Price")
	public java.math.BigDecimal getPrice() {
		return this.Price;
	}

	public void setPrice(java.math.BigDecimal Price) {
		this.Price = Price;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Catalog.Price.EqPrice")
	public java.math.BigDecimal getEquivalentPrice() {
		return this.EquivalentPrice;
	}

	public void setEquivalentPrice(java.math.BigDecimal EquivalentPrice) {
		this.EquivalentPrice = EquivalentPrice;
	}

}