/*
 * StockCard.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: StockCard.java,v 1.7 2009/01/23 13:37:57 safonov Exp $
 */
public class StockCard extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private java.util.Date sysVersion;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.warehouse.model.Warehouse Stock;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Employees Mol;

	private java.lang.String CardNumber;

	private java.math.BigDecimal SupplyNorm;

	private java.math.BigDecimal Reserve;

	private java.math.BigDecimal Quantity;

	private java.math.BigDecimal PlanIn;

	private java.math.BigDecimal PlanOut;

	private java.math.BigDecimal SupplyNorm2;

	private java.math.BigDecimal Reserve2;

	private java.math.BigDecimal Quantity2;

	private java.math.BigDecimal PlanIn2;

	private java.math.BigDecimal PlanOut2;

	// Constructors

	/** default constructor */
	public StockCard() {
	}

	/** constructor with id */
	public StockCard(java.lang.Integer Id) {
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
	 * @return the sysVersion
	 */
	public java.util.Date getSysVersion() {
		return sysVersion;
	}

	/**
	 * @param sysVersion the sysVersion to set
	 */
	public void setSysVersion(java.util.Date sysVersion) {
		this.sysVersion = sysVersion;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.Catalog")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */

	public com.mg.merp.warehouse.model.Warehouse getStock() {
		return this.Stock;
	}

	public void setStock(com.mg.merp.warehouse.model.Warehouse Stock) {
		this.Stock = Stock;
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
	@DataItemName("Warehouse.StockCard.Mol")
	public com.mg.merp.reference.model.Employees getMol() {
		return this.Mol;
	}

	public void setMol(com.mg.merp.reference.model.Employees Mol) {
		this.Mol = Mol;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.CardNumber")
	public java.lang.String getCardNumber() {
		return this.CardNumber;
	}

	public void setCardNumber(java.lang.String CardNumber) {
		this.CardNumber = CardNumber;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.SupplyNorm")
	public java.math.BigDecimal getSupplyNorm() {
		return this.SupplyNorm;
	}

	public void setSupplyNorm(java.math.BigDecimal SupplyNorm) {
		this.SupplyNorm = SupplyNorm;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.Reserve")
	public java.math.BigDecimal getReserve() {
		return this.Reserve;
	}

	public void setReserve(java.math.BigDecimal Reserve) {
		this.Reserve = Reserve;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.Quantity")
	public java.math.BigDecimal getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(java.math.BigDecimal Quantity) {
		this.Quantity = Quantity;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.PlanIn")
	public java.math.BigDecimal getPlanIn() {
		return this.PlanIn;
	}

	public void setPlanIn(java.math.BigDecimal PlanIn) {
		this.PlanIn = PlanIn;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.PlanOut")
	public java.math.BigDecimal getPlanOut() {
		return this.PlanOut;
	}

	public void setPlanOut(java.math.BigDecimal PlanOut) {
		this.PlanOut = PlanOut;
	}

	/**
	 * 
	 */

	public java.math.BigDecimal getSupplyNorm2() {
		return this.SupplyNorm2;
	}

	public void setSupplyNorm2(java.math.BigDecimal SupplyNorm2) {
		this.SupplyNorm2 = SupplyNorm2;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.Reserve2")
	public java.math.BigDecimal getReserve2() {
		return this.Reserve2;
	}

	public void setReserve2(java.math.BigDecimal Reserve2) {
		this.Reserve2 = Reserve2;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.Quantity2")
	public java.math.BigDecimal getQuantity2() {
		return this.Quantity2;
	}

	public void setQuantity2(java.math.BigDecimal Quantity2) {
		this.Quantity2 = Quantity2;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.PlanIn2")
	public java.math.BigDecimal getPlanIn2() {
		return this.PlanIn2;
	}

	public void setPlanIn2(java.math.BigDecimal PlanIn2) {
		this.PlanIn2 = PlanIn2;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.StockCard.PlanOut2")
	public java.math.BigDecimal getPlanOut2() {
		return this.PlanOut2;
	}

	public void setPlanOut2(java.math.BigDecimal PlanOut2) {
		this.PlanOut2 = PlanOut2;
	}

}