/*
 * MpsLine.java
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
 *
 */
package com.mg.merp.planning.model;

import java.math.BigDecimal;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.utils.MathUtils;

/**
 * Модель бизнес-компонента "Строки производственного плана"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: MpsLine.java,v 1.6 2007/07/30 10:37:30 safonov Exp $
 */
public class MpsLine extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.planning.model.InventoryForecast InventoryForecast;

	private com.mg.merp.planning.model.Mps Mps;

	private com.mg.merp.planning.model.ForecastVersion ForecastVersion;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.planning.model.GenericItem planningItem;

	private com.mg.merp.reference.model.Measure Measure;

	private java.math.BigDecimal AdjustmentQty;

	private java.lang.Short BucketOffset;

	private java.util.Date BucketOffsetDate;

	private java.util.Date DemandFenceDate;

	private java.math.BigDecimal DemandQty;

	private java.lang.Integer MpsSequence;

	private java.lang.Integer OutputMpsSequence;

	private java.math.BigDecimal PlannedQty;

	private java.math.BigDecimal dependantDemand;

	private java.lang.Short levelCode;

	private java.math.BigDecimal ProductionDemandQty;

	private java.math.BigDecimal ProductionProfileQty;

	private java.math.BigDecimal ProductionQty;

	private java.math.BigDecimal PurchaseForecastQty;

	private java.math.BigDecimal PurchaseOrderQty;

	private java.math.BigDecimal PurchaseQty;

	private java.math.BigDecimal QtyAvailable;

	private java.math.BigDecimal SalesForecastQty;

	private java.math.BigDecimal SalesOrderQty;

	private java.math.BigDecimal SalesQty;

	private java.math.BigDecimal TransfersInQty;

	private java.math.BigDecimal TransfersOutQty;

	private java.math.BigDecimal LiveProductionDemand;

	private java.math.BigDecimal SafetyLevelQty;

	private java.util.Date BucketEndDate;

	private boolean FirmPlanSuggested;

	private boolean MpsOrdered;

	// Constructors

	/** default constructor */
	public MpsLine() {
	}

	/** constructor with id */
	public MpsLine(java.lang.Integer Id) {
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

	public com.mg.merp.planning.model.Mps getMps() {
		return this.Mps;
	}

	public void setMps(com.mg.merp.planning.model.Mps Mps) {
		this.Mps = Mps;
	}

	/**
	 * 
	 */

	public com.mg.merp.planning.model.ForecastVersion getForecastVersion() {
		return this.ForecastVersion;
	}

	public void setForecastVersion(
			com.mg.merp.planning.model.ForecastVersion ForecastVersion) {
		this.ForecastVersion = ForecastVersion;
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
	@DataItemName("Planning.MPSLine.GenericItem")
	public com.mg.merp.planning.model.GenericItem getPlanningItem() {
		return this.planningItem;
	}

	public void setPlanningItem(
			com.mg.merp.planning.model.GenericItem GenericItem) {
		this.planningItem = GenericItem;
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
	@DataItemName("Planning.MPSLine.AdjustmentQty")
	public java.math.BigDecimal getAdjustmentQty() {
		return this.AdjustmentQty;
	}

	public void setAdjustmentQty(java.math.BigDecimal AdjustmentQty) {
		this.AdjustmentQty = AdjustmentQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.BucketOffset")
	public java.lang.Short getBucketOffset() {
		return this.BucketOffset;
	}

	public void setBucketOffset(java.lang.Short BucketOffset) {
		this.BucketOffset = BucketOffset;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.BucketOffsetDate")
	public java.util.Date getBucketOffsetDate() {
		return this.BucketOffsetDate;
	}

	public void setBucketOffsetDate(java.util.Date BucketOffsetDate) {
		this.BucketOffsetDate = BucketOffsetDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.DemandFenceDate")
	public java.util.Date getDemandFenceDate() {
		return this.DemandFenceDate;
	}

	public void setDemandFenceDate(java.util.Date DemandFenceDate) {
		this.DemandFenceDate = DemandFenceDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.DemandQty")
	public java.math.BigDecimal getDemandQty() {
		return this.DemandQty;
	}

	public void setDemandQty(java.math.BigDecimal DemandQty) {
		this.DemandQty = DemandQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.MpsSequence")
	public java.lang.Integer getMpsSequence() {
		return this.MpsSequence;
	}

	public void setMpsSequence(java.lang.Integer MpsSequence) {
		this.MpsSequence = MpsSequence;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.OutputMpsSequence")
	public java.lang.Integer getOutputMpsSequence() {
		return this.OutputMpsSequence;
	}

	public void setOutputMpsSequence(java.lang.Integer OutputMpsSequence) {
		this.OutputMpsSequence = OutputMpsSequence;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.PlannedQty")
	public java.math.BigDecimal getPlannedQty() {
		return this.PlannedQty;
	}

	public void setPlannedQty(java.math.BigDecimal PlannedQty) {
		this.PlannedQty = PlannedQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.DependantDemand")
	public java.math.BigDecimal getDependantDemand() {
		return this.dependantDemand;
	}

	public void setDependantDemand(java.math.BigDecimal PpDependantDemand) {
		this.dependantDemand = PpDependantDemand;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.LevelCode")
	public java.lang.Short getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(java.lang.Short PpLevelCode) {
		this.levelCode = PpLevelCode;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.ProductionDemandQty")
	public java.math.BigDecimal getProductionDemandQty() {
		return this.ProductionDemandQty;
	}

	public void setProductionDemandQty(java.math.BigDecimal ProductionDemandQty) {
		this.ProductionDemandQty = ProductionDemandQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.ProductionProfileQty")
	public java.math.BigDecimal getProductionProfileQty() {
		return this.ProductionProfileQty;
	}

	public void setProductionProfileQty(
			java.math.BigDecimal ProductionProfileQty) {
		this.ProductionProfileQty = ProductionProfileQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.ProductionQty")
	public java.math.BigDecimal getProductionQty() {
		return this.ProductionQty;
	}

	public void setProductionQty(java.math.BigDecimal ProductionQty) {
		this.ProductionQty = ProductionQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.PurchaseForecastQty")
	public java.math.BigDecimal getPurchaseForecastQty() {
		return this.PurchaseForecastQty;
	}

	public void setPurchaseForecastQty(java.math.BigDecimal PurchaseForecastQty) {
		this.PurchaseForecastQty = PurchaseForecastQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.PurchaseOrderQty")
	public java.math.BigDecimal getPurchaseOrderQty() {
		return this.PurchaseOrderQty;
	}

	public void setPurchaseOrderQty(java.math.BigDecimal PurchaseOrderQty) {
		this.PurchaseOrderQty = PurchaseOrderQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.PurchaseQty")
	public java.math.BigDecimal getPurchaseQty() {
		return this.PurchaseQty;
	}

	public void setPurchaseQty(java.math.BigDecimal PurchaseQty) {
		this.PurchaseQty = PurchaseQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.QtyAvailable")
	public java.math.BigDecimal getQtyAvailable() {
		return this.QtyAvailable;
	}

	public void setQtyAvailable(java.math.BigDecimal QtyAvailable) {
		this.QtyAvailable = QtyAvailable;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.SalesForecastQty")
	public java.math.BigDecimal getSalesForecastQty() {
		return this.SalesForecastQty;
	}

	public void setSalesForecastQty(java.math.BigDecimal SalesForecastQty) {
		this.SalesForecastQty = SalesForecastQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.SalesOrderQty")
	public java.math.BigDecimal getSalesOrderQty() {
		return this.SalesOrderQty;
	}

	public void setSalesOrderQty(java.math.BigDecimal SalesOrderQty) {
		this.SalesOrderQty = SalesOrderQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.SalesQty")
	public java.math.BigDecimal getSalesQty() {
		return this.SalesQty;
	}

	public void setSalesQty(java.math.BigDecimal SalesQty) {
		this.SalesQty = SalesQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.TransfersInQty")
	public java.math.BigDecimal getTransfersInQty() {
		return this.TransfersInQty;
	}

	public void setTransfersInQty(java.math.BigDecimal TransfersInQty) {
		this.TransfersInQty = TransfersInQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.TransfersOutQty")
	public java.math.BigDecimal getTransfersOutQty() {
		return this.TransfersOutQty;
	}

	public void setTransfersOutQty(java.math.BigDecimal TransfersOutQty) {
		this.TransfersOutQty = TransfersOutQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.LiveProductionDemand")
	public java.math.BigDecimal getLiveProductionDemand() {
		return this.LiveProductionDemand;
	}

	public void setLiveProductionDemand(
			java.math.BigDecimal LiveProductionDemand) {
		this.LiveProductionDemand = LiveProductionDemand;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.SafetyLevelQty")
	public java.math.BigDecimal getSafetyLevelQty() {
		return this.SafetyLevelQty;
	}

	public void setSafetyLevelQty(java.math.BigDecimal SafetyLevelQty) {
		this.SafetyLevelQty = SafetyLevelQty;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.BucketEndDate")
	public java.util.Date getBucketEndDate() {
		return this.BucketEndDate;
	}

	public void setBucketEndDate(java.util.Date BucketEndDate) {
		this.BucketEndDate = BucketEndDate;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.FirmPlanSuggested")
	public boolean isFirmPlanSuggested() {
		return this.FirmPlanSuggested;
	}

	public void setFirmPlanSuggested(boolean FirmPlanSuggested) {
		this.FirmPlanSuggested = FirmPlanSuggested;
	}

	/**
	 * 
	 */
	@DataItemName("Planning.MPSLine.MpsOrdered")
	public boolean isMpsOrdered() {
		return this.MpsOrdered;
	}

	public void setMpsOrdered(boolean MpsOrdered) {
		this.MpsOrdered = MpsOrdered;
	}

	public void updateDemandQuantities() {
		setSalesQty(getSalesForecastQty().max(getSalesOrderQty()));
		setPurchaseQty(getPurchaseForecastQty().max(getPurchaseQty()));
		setDemandQty(getSalesQty().add(getLiveProductionDemand()).add(getTransfersOutQty()));
	}

	/**
	 * 
	 * @param availableOnBeginBucket Остаток на начало бакета
	 * @return
	 */
	public BigDecimal calculatePlannedQuantity(BigDecimal availableOnBeginBucket) {
		BigDecimal result = getDemandQty().add(getDependantDemand()) //потребность
				.subtract(getProductionQty()) //кол-во, которое будет произведено по запущенным в производство ЗНП
				.subtract(getPurchaseQty()) //поступления от поставщиков
				.subtract(getQtyAvailable()) //остаток в наличии
				.subtract(availableOnBeginBucket) //остаток на начало бакета
				.add(getSafetyLevelQty()); //страховой запас по товару
		if (MathUtils.compareToZero(result) < 0)
			return BigDecimal.ZERO;
		else
			return result;
	}
	
	public BigDecimal calculateAvailableQuantityOnEndBucket(BigDecimal plannedQty, BigDecimal availableOnBeginBucket) {
		return plannedQty.subtract(getDemandQty()).add(getDependantDemand())
				.subtract(getProductionQty())
				.subtract(getPurchaseQty())
				.subtract(getQtyAvailable())
				.subtract(availableOnBeginBucket);
	}
	
	public boolean isEmpty() {
		return MathUtils.compareToZero(getSalesOrderQty()) == 0
				&& MathUtils.compareToZero(getSalesForecastQty()) == 0
				&& MathUtils.compareToZero(getPurchaseOrderQty()) == 0
				&& MathUtils.compareToZero(getPurchaseForecastQty()) == 0
				&& MathUtils.compareToZero(getProductionQty()) == 0
				&& MathUtils.compareToZero(getAdjustmentQty()) == 0
				&& MathUtils.compareToZero(getQtyAvailable()) == 0
				&& MathUtils.compareToZero(getSalesQty()) == 0
				&& MathUtils.compareToZero(getPurchaseQty()) == 0
				&& MathUtils.compareToZero(getProductionProfileQty()) == 0
				&& MathUtils.compareToZero(getDemandQty()) == 0
				&& MathUtils.compareToZero(getProductionDemandQty()) == 0
				&& MathUtils.compareToZero(getPlannedQty()) == 0
				&& MathUtils.compareToZero(getDependantDemand()) == 0
				&& MathUtils.compareToZero(getTransfersInQty()) == 0
				&& MathUtils.compareToZero(getTransfersOutQty()) == 0;
	}
	
}