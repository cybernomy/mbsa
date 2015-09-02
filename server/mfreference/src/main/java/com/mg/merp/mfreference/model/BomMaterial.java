/*
 * BomMaterial.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: BomMaterial.java,v 1.5 2007/07/30 10:25:11 safonov Exp $
 */
public class BomMaterial extends com.mg.merp.mfreference.model.BomRouteResource
		implements java.io.Serializable {

	// Fields

	private com.mg.merp.mfreference.model.CostCategories mtlCostCategory;

	private com.mg.merp.reference.model.Measure Measure;

	private com.mg.merp.reference.model.Catalog Catalog;

	private com.mg.merp.warehouse.model.WarehouseZone backflushZone;

	private com.mg.merp.mfreference.model.CostCategories mtlOhCostCategory;

	private com.mg.merp.reference.model.Currency Currency;

	private java.lang.Integer Revision;

	private int ViewSequence;

	private java.lang.Integer ReportSequence;

	private java.math.BigDecimal Probable;

	private com.mg.merp.mfreference.model.QuantityRateFlag QuantityRateFlag;

	private java.math.BigDecimal MtlQty;

	private java.math.BigDecimal ScrapFactor;

	private boolean MtlBackflushFlag;

	private com.mg.merp.mfreference.model.MaterialOverheadAllocationFlag MtlOhAllocationFlag;

	private java.math.BigDecimal MtlOhRate;

	private java.math.BigDecimal MtlOhRatio;

	private boolean MtlOhBackflushFlag;

	// Constructors

	/** default constructor */
	public BomMaterial() {
	}

	// Property accessors

	@DataItemName("MfReference.BomMaterial.CostCategories")
	public com.mg.merp.mfreference.model.CostCategories getMtlCostCategory() {
		return this.mtlCostCategory;
	}

	public void setMtlCostCategory(
			com.mg.merp.mfreference.model.CostCategories CostCategories) {
		this.mtlCostCategory = CostCategories;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.Measure")
	public com.mg.merp.reference.model.Measure getMeasure() {
		return this.Measure;
	}

	public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
		this.Measure = Measure;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.Catalog")
	public com.mg.merp.reference.model.Catalog getCatalog() {
		return this.Catalog;
	}

	public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
		this.Catalog = Catalog;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.Zone")
	public com.mg.merp.warehouse.model.WarehouseZone getBackflushZone() {
		return this.backflushZone;
	}

	public void setBackflushZone(com.mg.merp.warehouse.model.WarehouseZone Zone) {
		this.backflushZone = Zone;
	}

	@DataItemName("MfReference.BomMaterial.OhCostCategories")
	public com.mg.merp.mfreference.model.CostCategories getMtlOhCostCategory() {
		return this.mtlOhCostCategory;
	}

	public void setMtlOhCostCategory(
			com.mg.merp.mfreference.model.CostCategories OhCostCategories) {
		this.mtlOhCostCategory = OhCostCategories;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.Currency")
	public com.mg.merp.reference.model.Currency getCurrency() {
		return this.Currency;
	}

	public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
		this.Currency = Currency;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.Revision")
	public java.lang.Integer getRevision() {
		return this.Revision;
	}

	public void setRevision(java.lang.Integer Revision) {
		this.Revision = Revision;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.ViewSequence")
	public int getViewSequence() {
		return this.ViewSequence;
	}

	public void setViewSequence(int ViewSequence) {
		this.ViewSequence = ViewSequence;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.ReportSequence")
	public java.lang.Integer getReportSequence() {
		return this.ReportSequence;
	}

	public void setReportSequence(java.lang.Integer ReportSequence) {
		this.ReportSequence = ReportSequence;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.Probable")
	public java.math.BigDecimal getProbable() {
		return this.Probable;
	}

	public void setProbable(java.math.BigDecimal Probable) {
		this.Probable = Probable;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.QuantityRateFlag")
	public com.mg.merp.mfreference.model.QuantityRateFlag getQuantityRateFlag() {
		return this.QuantityRateFlag;
	}

	public void setQuantityRateFlag(
			com.mg.merp.mfreference.model.QuantityRateFlag QuantityRateFlag) {
		this.QuantityRateFlag = QuantityRateFlag;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.MtlQty")
	public java.math.BigDecimal getMtlQty() {
		return this.MtlQty;
	}

	public void setMtlQty(java.math.BigDecimal MtlQty) {
		this.MtlQty = MtlQty;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.ScrapFactor")
	public java.math.BigDecimal getScrapFactor() {
		return this.ScrapFactor;
	}

	public void setScrapFactor(java.math.BigDecimal ScrapFactor) {
		this.ScrapFactor = ScrapFactor;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.MtlBackflushFlag")
	public boolean isMtlBackflushFlag() {
		return this.MtlBackflushFlag;
	}

	public void setMtlBackflushFlag(boolean MtlBackflushFlag) {
		this.MtlBackflushFlag = MtlBackflushFlag;
	}

	/**
	 * 
	 */

	// @DataItemName ("MfReference.MtlOhAllocationFlag")
	public com.mg.merp.mfreference.model.MaterialOverheadAllocationFlag getMtlOhAllocationFlag() {
		return this.MtlOhAllocationFlag;
	}

	public void setMtlOhAllocationFlag(
			com.mg.merp.mfreference.model.MaterialOverheadAllocationFlag MtlOhAllocationFlag) {
		this.MtlOhAllocationFlag = MtlOhAllocationFlag;
	}

	/**
	 * 
	 */

	@DataItemName("MfReference.BomMaterial.MtlOhRate")
	public java.math.BigDecimal getMtlOhRate() {
		return this.MtlOhRate;
	}

	public void setMtlOhRate(java.math.BigDecimal MtlOhRate) {
		this.MtlOhRate = MtlOhRate;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.MtlOhRatio")
	public java.math.BigDecimal getMtlOhRatio() {
		return this.MtlOhRatio;
	}

	public void setMtlOhRatio(java.math.BigDecimal MtlOhRatio) {
		this.MtlOhRatio = MtlOhRatio;
	}

	/**
	 * 
	 */
	@DataItemName("MfReference.BomMaterial.MtlOhBackflushFlag")
	public boolean isMtlOhBackflushFlag() {
		return this.MtlOhBackflushFlag;
	}

	public void setMtlOhBackflushFlag(boolean MtlOhBackflushFlag) {
		this.MtlOhBackflushFlag = MtlOhBackflushFlag;
	}

}