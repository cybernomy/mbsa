/*
 * BinLocation.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Cекции хранения на складах"
 * 
 * @author Artem V. Sharapov
 * @version $Id: BinLocation.java,v 1.6 2008/05/30 12:55:52 sharapov Exp $
 */
@DataItemName("Warehouse.BinLocation") //$NON-NLS-1$
public class BinLocation extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.reference.model.Measure VolumeMeasure;

	private com.mg.merp.warehouse.model.WarehouseZone Zone;

	private com.mg.merp.warehouse.model.Warehouse Warehouse;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Measure WeightMeasure;

	private com.mg.merp.reference.model.Measure QuanMeasure;

	private com.mg.merp.warehouse.model.BinLocationType Type;

	private java.lang.String Code;

	private java.lang.String Description;

	private boolean InfiniteVolume = true;

	private java.math.BigDecimal MaximumVolume;

	private boolean InfiniteWeight = true;

	private java.math.BigDecimal MaximumWeight;

	private boolean InfiniteQuan = true;

	private java.math.BigDecimal MaximumQuan;

	private java.util.Set<BinLocationDetail> binLocationDetails;

	
	// Constructors

	/** default constructor */
	public BinLocation() {
	}

	/** constructor with id */
	public BinLocation(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Measure getVolumeMeasure() {
		return this.VolumeMeasure;
	}

	/**
	 * 
	 * @param VolumeMeasure
	 */
	public void setVolumeMeasure(com.mg.merp.reference.model.Measure VolumeMeasure) {
		this.VolumeMeasure = VolumeMeasure;
	}

	/**
	 * 
	 */
	public com.mg.merp.warehouse.model.WarehouseZone getZone() {
		return this.Zone;
	}

	/**
	 * 
	 * @param Zone
	 */
	public void setZone(com.mg.merp.warehouse.model.WarehouseZone Zone) {
		this.Zone = Zone;
	}

	/**
	 * 
	 */
	public com.mg.merp.warehouse.model.Warehouse getWarehouse() {
		return this.Warehouse;
	}

	/**
	 * 
	 * @param Warehouse
	 */
	public void setWarehouse(com.mg.merp.warehouse.model.Warehouse Warehouse) {
		this.Warehouse = Warehouse;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	/**
	 * 
	 * @param SysClient
	 */
	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Measure getWeightMeasure() {
		return this.WeightMeasure;
	}
	
	/**
	 * 
	 * @param WeightMeasure
	 */
	public void setWeightMeasure(com.mg.merp.reference.model.Measure WeightMeasure) {
		this.WeightMeasure = WeightMeasure;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Measure getQuanMeasure() {
		return this.QuanMeasure;
	}

	/**
	 * 
	 * @param QuanMeasure
	 */
	public void setQuanMeasure(com.mg.merp.reference.model.Measure QuanMeasure) {
		this.QuanMeasure = QuanMeasure;
	}

	/**
	 * 
	 */	
	public com.mg.merp.warehouse.model.BinLocationType getType() {
		return this.Type;
	}

	/**
	 * 
	 * @param BinLocationType
	 */
	public void setType(com.mg.merp.warehouse.model.BinLocationType BinLocationType) {
		this.Type = BinLocationType;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.Code") //$NON-NLS-1$
	public java.lang.String getCode() {
		return this.Code;
	}

	/**
	 * 
	 * @param Code
	 */
	public void setCode(java.lang.String Code) {
		this.Code = Code;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.Description") //$NON-NLS-1$
	public java.lang.String getDescription() {
		return this.Description;
	}

	/**
	 * 
	 * @param Description
	 */
	public void setDescription(java.lang.String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.InfiniteVolume") //$NON-NLS-1$
	public boolean getInfiniteVolume() {
		return this.InfiniteVolume;
	}

	/**
	 * 
	 * @param InfiniteVolume
	 */
	public void setInfiniteVolume(boolean InfiniteVolume) {
		this.InfiniteVolume = InfiniteVolume;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.MaximumVolume") //$NON-NLS-1$
	public java.math.BigDecimal getMaximumVolume() {
		return this.MaximumVolume;
	}

	/**
	 * 
	 * @param MaximumVolume
	 */
	public void setMaximumVolume(java.math.BigDecimal MaximumVolume) {
		this.MaximumVolume = MaximumVolume;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.InfiniteWeight") //$NON-NLS-1$
	public boolean getInfiniteWeight() {
		return this.InfiniteWeight;
	}

	/**
	 * 
	 * @param InfiniteWeight
	 */
	public void setInfiniteWeight(boolean InfiniteWeight) {
		this.InfiniteWeight = InfiniteWeight;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.MaximumWeight") //$NON-NLS-1$
	public java.math.BigDecimal getMaximumWeight() {
		return this.MaximumWeight;
	}

	/**
	 * 
	 * @param MaximumWeight
	 */
	public void setMaximumWeight(java.math.BigDecimal MaximumWeight) {
		this.MaximumWeight = MaximumWeight;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.InfiniteQuan") //$NON-NLS-1$
	public boolean getInfiniteQuan() {
		return this.InfiniteQuan;
	}

	/**
	 * 
	 * @param InfiniteQuan
	 */
	public void setInfiniteQuan(boolean InfiniteQuan) {
		this.InfiniteQuan = InfiniteQuan;
	}

	/**
	 * 
	 */
	@DataItemName("Warehouse.BinLocation.MaximumQuan") //$NON-NLS-1$
	public java.math.BigDecimal getMaximumQuan() {
		return this.MaximumQuan;
	}

	/**
	 * 
	 * @param MaximumQuan
	 */
	public void setMaximumQuan(java.math.BigDecimal MaximumQuan) {
		this.MaximumQuan = MaximumQuan;
	}

	/**
	 * 
	 */
	public java.util.Set<BinLocationDetail> getBinLocationDetails() {
		return this.binLocationDetails;
	}

	/**
	 * 
	 * @param binLocationDetails
	 */
	public void setBinLocationDetails(java.util.Set<BinLocationDetail> binLocationDetails) {
		this.binLocationDetails = binLocationDetails;
	}

}