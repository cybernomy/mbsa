/*
 * JobMaterial.java
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
package com.mg.merp.manufacture.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.mfreference.model.MaterialOverheadAllocationFlag;
import com.mg.merp.mfreference.model.QuantityRateFlag;

/**
 * @author hbm2java
 * @version $Id: JobMaterial.java,v 1.6 2006/11/02 16:10:13 safonov Exp $
 */
public class JobMaterial extends com.mg.merp.manufacture.model.JobRouteResource
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.mfreference.model.CostCategories MtlCostCategory;

	private com.mg.merp.reference.model.Measure Measure;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.warehouse.model.WarehouseZone BackflushZone;

	private com.mg.merp.mfreference.model.CostCategories MtlOhCostCategory;

	private com.mg.merp.reference.model.Currency Currency;

	private java.lang.Integer Revision;

	private int ViewSequence;

	private java.lang.Integer ReportSequence;

	private QuantityRateFlag QuantityRateFlag;

	private java.math.BigDecimal MtlQty;

	private java.math.BigDecimal ScrapFactor;

	private boolean MtlBackflushFlag;

	private MaterialOverheadAllocationFlag MtlOhAllocationFlag;

	private java.math.BigDecimal MtlOhRate;

	private java.math.BigDecimal MtlOhRatio;

	private boolean MtlOhBackflushFlag;

	// Constructors

	/** default constructor */
	public JobMaterial() {
	}

	// Property accessors
	/**
	 * 
	 */

	@DataItemName("Manufacture.JobMaterial.MtlCostCategory")
	public com.mg.merp.mfreference.model.CostCategories getMtlCostCategory() {
		return this.MtlCostCategory;
	}

	public void setMtlCostCategory(
			com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
		this.MtlCostCategory = MfCostCategories;
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
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.BackflushZone")
	public com.mg.merp.warehouse.model.WarehouseZone getBackflushZone() {
		return this.BackflushZone;
	}

	public void setBackflushZone(
			com.mg.merp.warehouse.model.WarehouseZone WhZone) {
		this.BackflushZone = WhZone;
	}

	@DataItemName("MfReference.JobMaterial.MtlOhCostCategory")
	public com.mg.merp.mfreference.model.CostCategories getMtlOhCostCategory() {
		return this.MtlOhCostCategory;
	}

	public void setMtlOhCostCategory(
			com.mg.merp.mfreference.model.CostCategories MfCostCategories_1) {
		this.MtlOhCostCategory = MfCostCategories_1;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.Revision")
	public java.lang.Integer getRevision() {
		return this.Revision;
	}

	public void setRevision(java.lang.Integer Revision) {
		this.Revision = Revision;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.ViewSequence")
	public int getViewSequence() {
		return this.ViewSequence;
	}

	public void setViewSequence(int ViewSequence) {
		this.ViewSequence = ViewSequence;
	}

	/**
	 * 
	 */

	@DataItemName("Manufacture.JobMaterial.ReportSequence")
	public java.lang.Integer getReportSequence() {
		return this.ReportSequence;
	}

	public void setReportSequence(java.lang.Integer ReportSequence) {
		this.ReportSequence = ReportSequence;
	}

	/**
	 * 
	 */

	public QuantityRateFlag getQuantityRateFlag() {
		return this.QuantityRateFlag;
	}

	public void setQuantityRateFlag(QuantityRateFlag QuantityRateFlag) {
		this.QuantityRateFlag = QuantityRateFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.MtlQty")
	public java.math.BigDecimal getMtlQty() {
		return this.MtlQty;
	}

	public void setMtlQty(java.math.BigDecimal MtlQty) {
		this.MtlQty = MtlQty;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.ScrapFactor")
	public java.math.BigDecimal getScrapFactor() {
		return this.ScrapFactor;
	}

	public void setScrapFactor(java.math.BigDecimal ScrapFactor) {
		this.ScrapFactor = ScrapFactor;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.MtlBackflushFlag")
	public boolean getMtlBackflushFlag() {
		return this.MtlBackflushFlag;
	}

	public void setMtlBackflushFlag(boolean MtlBackflushFlag) {
		this.MtlBackflushFlag = MtlBackflushFlag;
	}

	/**
	 * 
	 */

	public MaterialOverheadAllocationFlag getMtlOhAllocationFlag() {
		return this.MtlOhAllocationFlag;
	}

	public void setMtlOhAllocationFlag(MaterialOverheadAllocationFlag MtlOhAllocationFlag) {
		this.MtlOhAllocationFlag = MtlOhAllocationFlag;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.MtlOhRate")
	public java.math.BigDecimal getMtlOhRate() {
		return this.MtlOhRate;
	}

	public void setMtlOhRate(java.math.BigDecimal MtlOhRate) {
		this.MtlOhRate = MtlOhRate;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.MtlOhRatio")
	public java.math.BigDecimal getMtlOhRatio() {
		return this.MtlOhRatio;
	}

	public void setMtlOhRatio(java.math.BigDecimal MtlOhRatio) {
		this.MtlOhRatio = MtlOhRatio;
	}

	/**
	 * 
	 */
	@DataItemName("Manufacture.JobMaterial.MtlOhBackflushFlag")
	public boolean getMtlOhBackflushFlag() {
		return this.MtlOhBackflushFlag;
	}

	public void setMtlOhBackflushFlag(boolean MtlOhBackflushFlag) {
		this.MtlOhBackflushFlag = MtlOhBackflushFlag;
	}

}