/*
 * GenericItem.java
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
 * @version $Id: GenericItem.java,v 1.6 2007/07/30 10:37:30 safonov Exp $
 */
@DataItemName("Planning.GenericItem")
public class GenericItem extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private java.lang.Integer id;

	private com.mg.merp.core.model.SysClient sysClient;

	private com.mg.merp.reference.model.Catalog catalog;
	
	private com.mg.merp.reference.model.Measure measure;

	private java.lang.String genericItemCode;

	private java.lang.String genericItemName;

	private boolean planningItemFlag;

	private short planningShelfLife;

	private short demandFenceDays;

	private short daysStockCover;

	private short lowLevelCode;

	// Constructors

	/** default constructor */
	public GenericItem() {
	}

	/** constructor with id */
	public GenericItem(java.lang.Integer Id) {
		this.id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer Id) {
		this.id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.sysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.sysClient = SysClient;
	}

	/**
	 * @return the catalog
	 */
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(com.mg.merp.reference.model.Catalog catalog) {
		this.catalog = catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.Measure")
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.measure = Measure;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Code")
	public java.lang.String getGenericItemCode() {
		return this.genericItemCode;
	}

	public void setGenericItemCode(java.lang.String GenericItemCode) {
		this.genericItemCode = GenericItemCode;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.Name")
	public java.lang.String getGenericItemName() {
		return this.genericItemName;
	}

	public void setGenericItemName(java.lang.String GenericItemName) {
		this.genericItemName = GenericItemName;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.PlanningItemFlag")
	public boolean getPlanningItemFlag() {
		return this.planningItemFlag;
	}

	public void setPlanningItemFlag(boolean PlanningItemFlag) {
		this.planningItemFlag = PlanningItemFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.PlanningShelfLife")
	public short getPlanningShelfLife() {
		return this.planningShelfLife;
	}

	public void setPlanningShelfLife(short PlanningShelfLife) {
		this.planningShelfLife = PlanningShelfLife;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.DemandFenceDays")
	public short getDemandFenceDays() {
		return this.demandFenceDays;
	}

	public void setDemandFenceDays(short DemandFenceDays) {
		this.demandFenceDays = DemandFenceDays;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.DaysStockCover")
	public short getDaysStockCover() {
		return this.daysStockCover;
	}

	public void setDaysStockCover(short DaysStockCover) {
		this.daysStockCover = DaysStockCover;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.GenericItem.LowLevelCode")
	public short getLowLevelCode() {
		return this.lowLevelCode;
	}

	public void setLowLevelCode(short LowLevelCode) {
		this.lowLevelCode = LowLevelCode;
	}
}