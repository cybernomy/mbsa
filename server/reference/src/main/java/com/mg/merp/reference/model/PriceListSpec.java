/*
 * PriceListSpec.java
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
package com.mg.merp.reference.model;

import java.util.List;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Спецификация прайс-листов"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PriceListSpec.java,v 1.8 2007/05/21 11:25:40 sharapov Exp $
 */
@DataItemName("Reference.PriceListSpec") //$NON-NLS-1$
public class PriceListSpec extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.reference.model.PriceListFolder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.math.BigDecimal Price;

	private java.math.BigDecimal LastCost;

	private java.lang.String SName;

	private boolean Canceled;

	private java.lang.String Unid;

	private java.lang.Integer PriceListHeadId;

	private java.util.Date ActDate;

	private java.util.Date ActDateTill;

	private java.lang.String InternalCode;

	private List<PriceListSpecPrice> priceListSpecPrice;

	// Constructors

	/** default constructor */
	public PriceListSpec() {
	}

	/** constructor with id */
	public PriceListSpec(java.lang.Integer Id) {
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
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	public com.mg.merp.reference.model.PriceListFolder getFolder() {
		return this.Folder;
	}

	public void setFolder(
			com.mg.merp.reference.model.PriceListFolder PriceListFolder) {
		this.Folder = PriceListFolder;
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
	@DataItemName("Reference.PrListSpec.Price") //$NON-NLS-1$
	public java.math.BigDecimal getPrice() {
		return this.Price;
	}

	public void setPrice(java.math.BigDecimal Price) {
		this.Price = Price;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrListSpec.LastCost") //$NON-NLS-1$
	public java.math.BigDecimal getLastCost() {
		return this.LastCost;
	}

	public void setLastCost(java.math.BigDecimal LastCost) {
		this.LastCost = LastCost;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrListSpec.SName") //$NON-NLS-1$
	public java.lang.String getSName() {
		return this.SName;
	}

	public void setSName(java.lang.String SName) {
		this.SName = SName;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrListSpec.Canceled") //$NON-NLS-1$
	public boolean getCanceled() {
		return this.Canceled;
	}

	public void setCanceled(boolean Canceled) {
		this.Canceled = Canceled;
	}

	/**
	 * 
	 */
	public java.lang.String getUnid() {
		return this.Unid;
	}

	public void setUnid(java.lang.String Unid) {
		this.Unid = Unid;
	}

	/**
	 * 
	 */
	public java.lang.Integer getPriceListHeadId() {
		return this.PriceListHeadId;
	}

	public void setPriceListHeadId(java.lang.Integer PriceListHeadId) {
		this.PriceListHeadId = PriceListHeadId;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrListSpec.ActDate") //$NON-NLS-1$
	public java.util.Date getActDate() {
		return this.ActDate;
	}

	public void setActDate(java.util.Date ActDate) {
		this.ActDate = ActDate;
	}

	/**
	 * 
	 */
	public java.util.Date getActDateTill() {
		return this.ActDateTill;
	}

	public void setActDateTill(java.util.Date ActDateTill) {
		this.ActDateTill = ActDateTill;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrListSpec.InternalCode") //$NON-NLS-1$
	public java.lang.String getInternalCode() {
		return this.InternalCode;
	}

	public void setInternalCode(java.lang.String InternalCode) {
		this.InternalCode = InternalCode;
	}

	/**
	 * @return цены спецификации прайс-листа
	 */
	public List<PriceListSpecPrice> getPriceListSpecPrice() {
		return this.priceListSpecPrice;
	}

	public void setPriceListSpecPrice(List<PriceListSpecPrice> priceListSpecPrice) {
		this.priceListSpecPrice = priceListSpecPrice;
	}

}