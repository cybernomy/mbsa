/*
 * PriceListHead.java
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
 */

package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PriceListHead.java,v 1.6 2006/12/27 05:53:29 sharapov Exp $
 */
@DataItemName("Reference.PriceListHead") //$NON-NLS-1$
public class PriceListHead extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

	private com.mg.merp.reference.model.PriceType BasePriceType;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor Contractor;

	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

	private com.mg.merp.reference.model.Currency Currency;

	private java.lang.String PrName;

	private java.util.Date CreateDate;

	private boolean IsCurrent;

	private java.lang.Boolean InsertSign;

	private java.lang.Integer PricePrecision;

	private java.util.Set<PriceListPriceTypeLink> priceTypeLinks;

	private java.util.Set<PriceListHeadRights> userRights;

	// Constructors

	/** default constructor */
	public PriceListHead() {
	}

	/** constructor with id */
	public PriceListHead(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.CurRateAuthority") //$NON-NLS-1$
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
	@DataItemName("Reference.PrList.BasePriceType") //$NON-NLS-1$
	public com.mg.merp.reference.model.PriceType getBasePriceType() {
		return this.BasePriceType;
	}

	public void setBasePriceType(com.mg.merp.reference.model.PriceType PriceType) {
		this.BasePriceType = PriceType;
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
	@DataItemName("Reference.PrList.Contractor") //$NON-NLS-1$
	public com.mg.merp.reference.model.Contractor getContractor() {
		return this.Contractor;
	}

	public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
		this.Contractor = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.CurRateType") //$NON-NLS-1$
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
	@DataItemName("Reference.PrList.Currency") //$NON-NLS-1$
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.Name") //$NON-NLS-1$
	public java.lang.String getPrName() {
		return this.PrName;
	}

	public void setPrName(java.lang.String PrName) {
		this.PrName = PrName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.CreateDate") //$NON-NLS-1$
	public java.util.Date getCreateDate() {
		return this.CreateDate;
	}

	public void setCreateDate(java.util.Date CreateDate) {
		this.CreateDate = CreateDate;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.IsCurrent") //$NON-NLS-1$
	public boolean getIsCurrent() {
		return this.IsCurrent;
	}

	public void setIsCurrent(boolean IsCurrent) {
		this.IsCurrent = IsCurrent;
	}

	/**
	 * 
	 */

	public java.lang.Boolean getInsertSign() {
		return this.InsertSign;
	}

	public void setInsertSign(java.lang.Boolean InsertSign) {
		this.InsertSign = InsertSign;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.PricePrecision") //$NON-NLS-1$
	public java.lang.Integer getPricePrecision() {
		return this.PricePrecision;
	}

	public void setPricePrecision(java.lang.Integer PricePrecision) {
		this.PricePrecision = PricePrecision;
	}

	/**
	 * 
	 */

	public java.util.Set<PriceListPriceTypeLink> getPriceTypeLinks() {
		return this.priceTypeLinks;
	}

	public void setPriceTypeLinks(
			java.util.Set<PriceListPriceTypeLink> SetOfPricelistPricetypeLink) {
		this.priceTypeLinks = SetOfPricelistPricetypeLink;
	}

	/**
	 * 
	 */

	public java.util.Set<PriceListHeadRights> getUserRights() {
		return this.userRights;
	}

	public void setUserRights(java.util.Set<PriceListHeadRights> SetOfPricelistheadRights) {
		this.userRights = SetOfPricelistheadRights;
	}

}