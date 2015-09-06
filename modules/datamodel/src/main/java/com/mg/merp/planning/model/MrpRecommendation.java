/*
 * MrpRecommendation.java
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
 * @version $Id: MrpRecommendation.java,v 1.6 2007/07/30 10:37:30 safonov Exp $
 */
public class MrpRecommendation extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Contractor vendor;

  private com.mg.merp.reference.model.Measure Measure;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.warehouse.model.Warehouse Warehouse;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.planning.model.MrpVersionControl MrpVersionControl;

  private com.mg.merp.warehouse.model.Warehouse SourceWarehouse;

  private java.util.Date RequiredDate;

  private java.math.BigDecimal OrderQty;

  private java.math.BigDecimal MrpQuantity;

  private java.lang.Short PurchaseLeadTime;

  private boolean FirmPlanSuggestedOrder;

  private java.util.Date OrderDate;

  private boolean MrpArrearsFlag;

  private boolean MrpOrdered;

  private RecommendType PurchaseOrTransfer;

  private java.lang.String PpReference;

  private boolean ManualEntry;

  private RescheduleFlag MrpRescheduleFlag;

  private MRPSource MrpSource;

  private java.util.Date OriginalDate;

  private java.util.Date BatchDate;

  private java.math.BigDecimal OriginalQuantity;

  // Constructors

  /**
   * default constructor
   */
  public MrpRecommendation() {
  }

  /**
   * constructor with id
   */
  public MrpRecommendation(java.lang.Integer Id) {
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
  @DataItemName("Planning.MRPRecommendation.Vendor")
  public com.mg.merp.reference.model.Contractor getVendor() {
    return this.vendor;
  }

  public void setVendor(com.mg.merp.reference.model.Contractor Contractor) {
    this.vendor = Contractor;
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
  @DataItemName("Planning.Catalog")
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.Warehouse")
  public com.mg.merp.warehouse.model.Warehouse getWarehouse() {
    return this.Warehouse;
  }

  public void setWarehouse(com.mg.merp.warehouse.model.Warehouse Warehouse) {
    this.Warehouse = Warehouse;
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
  @DataItemName("Planning.MRPRecommendation.MRPVersionControl")
  public com.mg.merp.planning.model.MrpVersionControl getMrpVersionControl() {
    return this.MrpVersionControl;
  }

  public void setMrpVersionControl(
      com.mg.merp.planning.model.MrpVersionControl MrpVersionControl) {
    this.MrpVersionControl = MrpVersionControl;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.SourceWarehouse")
  public com.mg.merp.warehouse.model.Warehouse getSourceWarehouse() {
    return this.SourceWarehouse;
  }

  public void setSourceWarehouse(
      com.mg.merp.warehouse.model.Warehouse SourceWarehouse) {
    this.SourceWarehouse = SourceWarehouse;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.RequiredDate")
  public java.util.Date getRequiredDate() {
    return this.RequiredDate;
  }

  public void setRequiredDate(java.util.Date RequiredDate) {
    this.RequiredDate = RequiredDate;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.OrderQty")
  public java.math.BigDecimal getOrderQty() {
    return this.OrderQty;
  }

  public void setOrderQty(java.math.BigDecimal OrderQty) {
    this.OrderQty = OrderQty;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.MRPQuantity")
  public java.math.BigDecimal getMrpQuantity() {
    return this.MrpQuantity;
  }

  public void setMrpQuantity(java.math.BigDecimal MrpQuantity) {
    this.MrpQuantity = MrpQuantity;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.PurchaseLeadTime")
  public java.lang.Short getPurchaseLeadTime() {
    return this.PurchaseLeadTime;
  }

  public void setPurchaseLeadTime(java.lang.Short PurchaseLeadTime) {
    this.PurchaseLeadTime = PurchaseLeadTime;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.FirmPlanSuggestedOrder")
  public boolean getFirmPlanSuggestedOrder() {
    return this.FirmPlanSuggestedOrder;
  }

  public void setFirmPlanSuggestedOrder(boolean FirmPlanSuggestedOrder) {
    this.FirmPlanSuggestedOrder = FirmPlanSuggestedOrder;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.OrderDate")
  public java.util.Date getOrderDate() {
    return this.OrderDate;
  }

  public void setOrderDate(java.util.Date OrderDate) {
    this.OrderDate = OrderDate;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.MRPArrearsFlag")
  public boolean getMrpArrearsFlag() {
    return this.MrpArrearsFlag;
  }

  public void setMrpArrearsFlag(boolean MrpArrearsFlag) {
    this.MrpArrearsFlag = MrpArrearsFlag;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.MRPOrdered")
  public boolean getMrpOrdered() {
    return this.MrpOrdered;
  }

  public void setMrpOrdered(boolean MrpOrdered) {
    this.MrpOrdered = MrpOrdered;
  }

  /**
   *
   */
  public RecommendType getPurchaseOrTransfer() {
    return this.PurchaseOrTransfer;
  }

  public void setPurchaseOrTransfer(RecommendType PurchaseOrTransfer) {
    this.PurchaseOrTransfer = PurchaseOrTransfer;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.Reference")
  public java.lang.String getPpReference() {
    return this.PpReference;
  }

  public void setPpReference(java.lang.String PpReference) {
    this.PpReference = PpReference;
  }

  /**
   *
   */

  public boolean getManualEntry() {
    return this.ManualEntry;
  }

  public void setManualEntry(boolean ManualEntry) {
    this.ManualEntry = ManualEntry;
  }

  /**
   *
   */

  public RescheduleFlag getMrpRescheduleFlag() {
    return this.MrpRescheduleFlag;
  }

  public void setMrpRescheduleFlag(RescheduleFlag MrpRescheduleFlag) {
    this.MrpRescheduleFlag = MrpRescheduleFlag;
  }

  /**
   *
   */

  public MRPSource getMrpSource() {
    return this.MrpSource;
  }

  public void setMrpSource(MRPSource MrpSource) {
    this.MrpSource = MrpSource;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.OriginalDate")
  public java.util.Date getOriginalDate() {
    return this.OriginalDate;
  }

  public void setOriginalDate(java.util.Date OriginalDate) {
    this.OriginalDate = OriginalDate;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.BatchDate")
  public java.util.Date getBatchDate() {
    return this.BatchDate;
  }

  public void setBatchDate(java.util.Date BatchDate) {
    this.BatchDate = BatchDate;
  }

  /**
   *
   */
  @DataItemName("Planning.MRPRecommendation.OriginalQuantity")
  public java.math.BigDecimal getOriginalQuantity() {
    return this.OriginalQuantity;
  }

  public void setOriginalQuantity(java.math.BigDecimal OriginalQuantity) {
    this.OriginalQuantity = OriginalQuantity;
  }
}