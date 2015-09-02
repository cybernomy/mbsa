/*
 * InventoryForecastLine.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InventoryForecastLine.java,v 1.2 2005/07/29 12:47:27 pashistova
 *          Exp $
 */
public class InventoryForecastLine extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.planning.model.InventoryForecast InventoryForecast;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.GenericItem GenericItem;

	private com.mg.merp.reference.model.Contractor Warehouse;

	private com.mg.merp.reference.model.Measure Measure;

	private java.util.Date EffOnDate;

	private java.util.Date EffOffDate;

	private java.math.BigDecimal QtyOnHand;

	// Constructors

	/** default constructor */
	public InventoryForecastLine() {
	}

	/** constructor with id */
	public InventoryForecastLine(java.lang.Integer Id) {
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

	public com.mg.merp.planning.model.InventoryForecast getInventoryForecast() {
		return this.InventoryForecast;
	}

	public void setInventoryForecast(
			com.mg.merp.planning.model.InventoryForecast InventoryForecast) {
		this.InventoryForecast = InventoryForecast;
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
	public com.mg.merp.planning.model.GenericItem getGenericItem() {
		return this.GenericItem;
	}

	public void setGenericItem(
			com.mg.merp.planning.model.GenericItem GenericItem) {
		this.GenericItem = GenericItem;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.InvForecast.Warehouse")
	public com.mg.merp.reference.model.Contractor getWarehouse() {
		return this.Warehouse;
	}

	public void setWarehouse(com.mg.merp.reference.model.Contractor Warehouse) {
		this.Warehouse = Warehouse;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.Measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.Measure = Measure;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.InvForecast.EffOnDate")
	public java.util.Date getEffOnDate() {
		return this.EffOnDate;
	}

	public void setEffOnDate(java.util.Date EffOnDate) {
		this.EffOnDate = EffOnDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.InvForecast.EffOffDate")
	public java.util.Date getEffOffDate() {
		return this.EffOffDate;
	}

	public void setEffOffDate(java.util.Date EffOffDate) {
		this.EffOffDate = EffOffDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.InvForecast.QtyOnHand")
	public java.math.BigDecimal getQtyOnHand() {
		return this.QtyOnHand;
	}

	public void setQtyOnHand(java.math.BigDecimal QtyOnHand) {
		this.QtyOnHand = QtyOnHand;
	}
	/**
	 * 
	 */

}