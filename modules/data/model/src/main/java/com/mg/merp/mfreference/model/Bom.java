/*
 * Bom.java
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
 * @version $Id: Bom.java,v 1.9 2009/03/05 11:21:54 safonov Exp $
 */
public class Bom extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.reference.model.Measure setupTimeUM;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.mfreference.model.CostDetail standartCostDetail;

  private com.mg.merp.mfreference.model.Cell Cell;

  private com.mg.merp.reference.model.Measure runTimeUM;

  private BomType BomType;

  private BomStatus BomStatus;

  private com.mg.merp.warehouse.model.Warehouse DefSrcStock;

  private com.mg.merp.warehouse.model.Warehouse DefDstStock;

  private com.mg.merp.reference.model.Employees DefSrcMol;

  private com.mg.merp.reference.model.Employees DefDstMol;

  private java.lang.Integer Revision;

  private java.util.Date RevisionDateTime;

  private java.util.Date RollUpDateTime;

  private java.math.BigDecimal setupTicks;

  private java.math.BigDecimal runTicks;

  private ScheduleDirection scheduleDirection;

  private boolean UseMoveTimes;

  private boolean UseQueueTimes;

  private boolean UseFiniteCapacity;

  private java.math.BigDecimal Priority;

  private boolean PriorityFreezeFlag;

  private java.math.BigDecimal PcsPerHrCell;

  private boolean ApprovedFlag;

  private boolean BomVersionRequired;

  private boolean CreateJobApproved;

  private java.lang.String Comment;

  private java.math.BigDecimal MinLotQty;

  private java.math.BigDecimal MaxLotQty;

  private java.math.BigDecimal LotIncrementQty;

  private java.math.BigDecimal PlanningLotQty;

  private java.util.Set<BomRoute> bomRoutes;

  // Constructors

  /**
   * default constructor
   */
  public Bom() {
  }

  /**
   * constructor with id
   */
  public Bom(java.lang.Integer Id) {
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
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.SetupTimeUM")
  public com.mg.merp.reference.model.Measure getSetupTimeUM() {
    return this.setupTimeUM;
  }

  public void setSetupTimeUM(com.mg.merp.reference.model.Measure setupTimeUM) {
    this.setupTimeUM = setupTimeUM;
  }

  /**
   *
   */

  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  public void setFolder(com.mg.merp.core.model.Folder Folder) {
    this.Folder = Folder;
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

  public com.mg.merp.mfreference.model.CostDetail getStandartCostDetail() {
    return this.standartCostDetail;
  }

  public void setStandartCostDetail(
      com.mg.merp.mfreference.model.CostDetail CostDetail) {
    this.standartCostDetail = CostDetail;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.Cell")
  public com.mg.merp.mfreference.model.Cell getCell() {
    return this.Cell;
  }

  public void setCell(com.mg.merp.mfreference.model.Cell Cell) {
    this.Cell = Cell;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.RunTimeUm")
  public com.mg.merp.reference.model.Measure getRunTimeUM() {
    return this.runTimeUM;
  }

  public void setRunTimeUM(com.mg.merp.reference.model.Measure RunTimeUM) {
    this.runTimeUM = RunTimeUM;
  }

  /**
   *
   */

  public BomType getBomType() {
    return this.BomType;
  }

  public void setBomType(BomType BomType) {
    this.BomType = BomType;
  }

  /**
   *
   */

  public BomStatus getBomStatus() {
    return this.BomStatus;
  }

  public void setBomStatus(BomStatus BomStatus) {
    this.BomStatus = BomStatus;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.DefSrcStock")
  public com.mg.merp.warehouse.model.Warehouse getDefSrcStock() {
    return this.DefSrcStock;
  }

  public void setDefSrcStock(
      com.mg.merp.warehouse.model.Warehouse DefSrcStockId) {
    this.DefSrcStock = DefSrcStockId;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.DefDstStock")
  public com.mg.merp.warehouse.model.Warehouse getDefDstStock() {
    return this.DefDstStock;
  }

  public void setDefDstStock(
      com.mg.merp.warehouse.model.Warehouse DefDstStockId) {
    this.DefDstStock = DefDstStockId;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.DefSrcMol")
  public com.mg.merp.reference.model.Employees getDefSrcMol() {
    return this.DefSrcMol;
  }

  public void setDefSrcMol(com.mg.merp.reference.model.Employees DefSrcMolId) {
    this.DefSrcMol = DefSrcMolId;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.DefDstMol")
  public com.mg.merp.reference.model.Employees getDefDstMol() {
    return this.DefDstMol;
  }

  public void setDefDstMol(com.mg.merp.reference.model.Employees DefDstMolId) {
    this.DefDstMol = DefDstMolId;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.Revision")
  public java.lang.Integer getRevision() {
    return this.Revision;
  }

  public void setRevision(java.lang.Integer Revision) {
    this.Revision = Revision;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.RevisionDate")
  public java.util.Date getRevisionDateTime() {
    return this.RevisionDateTime;
  }

  public void setRevisionDateTime(java.util.Date RevisionDateTime) {
    this.RevisionDateTime = RevisionDateTime;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.RollUpDateTime")
  public java.util.Date getRollUpDateTime() {
    return this.RollUpDateTime;
  }

  public void setRollUpDateTime(java.util.Date RollUpDateTime) {
    this.RollUpDateTime = RollUpDateTime;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.SetupTicks")
  public java.math.BigDecimal getSetupTicks() {
    return this.setupTicks;
  }

  public void setSetupTicks(java.math.BigDecimal SetupTicks) {
    this.setupTicks = SetupTicks;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.RunTicks")
  public java.math.BigDecimal getRunTicks() {
    return this.runTicks;
  }

  public void setRunTicks(java.math.BigDecimal RunTicks) {
    this.runTicks = RunTicks;
  }

  /**
   *
   */

  public ScheduleDirection getScheduleDirection() {
    return this.scheduleDirection;
  }

  public void setScheduleDirection(ScheduleDirection SchedDirection) {
    this.scheduleDirection = SchedDirection;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.UseMoveTimes")
  public boolean getUseMoveTimes() {
    return this.UseMoveTimes;
  }

  public void setUseMoveTimes(boolean UseMoveTimes) {
    this.UseMoveTimes = UseMoveTimes;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.UseQueueTimes")
  public boolean getUseQueueTimes() {
    return this.UseQueueTimes;
  }

  public void setUseQueueTimes(boolean UseQueueTimes) {
    this.UseQueueTimes = UseQueueTimes;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.UseFiniteCapacity")
  public boolean getUseFiniteCapacity() {
    return this.UseFiniteCapacity;
  }

  public void setUseFiniteCapacity(boolean UseFiniteCapacity) {
    this.UseFiniteCapacity = UseFiniteCapacity;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.Priority")
  public java.math.BigDecimal getPriority() {
    return this.Priority;
  }

  public void setPriority(java.math.BigDecimal Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.PriorityFreezeFlag")
  public boolean getPriorityFreezeFlag() {
    return this.PriorityFreezeFlag;
  }

  public void setPriorityFreezeFlag(boolean PriorityFreezeFlag) {
    this.PriorityFreezeFlag = PriorityFreezeFlag;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.PcsPerHrCell")
  public java.math.BigDecimal getPcsPerHrCell() {
    return this.PcsPerHrCell;
  }

  public void setPcsPerHrCell(java.math.BigDecimal PcsPerHrCell) {
    this.PcsPerHrCell = PcsPerHrCell;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.ApprovedFlag")
  public boolean getApprovedFlag() {
    return this.ApprovedFlag;
  }

  public void setApprovedFlag(boolean ApprovedFlag) {
    this.ApprovedFlag = ApprovedFlag;
  }

  /**
   *
   */

  @DataItemName("MfReference.BomVersionRequired")
  public boolean getBomVersionRequired() {
    return this.BomVersionRequired;
  }

  public void setBomVersionRequired(boolean BomVersionRequired) {
    this.BomVersionRequired = BomVersionRequired;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.CreateJobApproved")
  public boolean getCreateJobApproved() {
    return this.CreateJobApproved;
  }

  public void setCreateJobApproved(boolean CreateJobApproved) {
    this.CreateJobApproved = CreateJobApproved;
  }

  /**
   *
   */
  @DataItemName("MfReference.BOM.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.MinLotQty")
  public java.math.BigDecimal getMinLotQty() {
    return this.MinLotQty;
  }

  public void setMinLotQty(java.math.BigDecimal MinLotQty) {
    this.MinLotQty = MinLotQty;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.MaxLotQty")
  public java.math.BigDecimal getMaxLotQty() {
    return this.MaxLotQty;
  }

  public void setMaxLotQty(java.math.BigDecimal MaxLotQty) {
    this.MaxLotQty = MaxLotQty;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.LotIncrementQty")
  public java.math.BigDecimal getLotIncrementQty() {
    return this.LotIncrementQty;
  }

  public void setLotIncrementQty(java.math.BigDecimal LotIncrementQty) {
    this.LotIncrementQty = LotIncrementQty;
  }

  /**
   *
   */

  @DataItemName("MfReference.BOM.PlanningLotQty")
  public java.math.BigDecimal getPlanningLotQty() {
    return this.PlanningLotQty;
  }

  public void setPlanningLotQty(java.math.BigDecimal PlanningLotQty) {
    this.PlanningLotQty = PlanningLotQty;
  }

  /**
   *
   */

  public java.util.Set<BomRoute> getBomRoutes() {
    return this.bomRoutes;
  }

  public void setBomRoutes(java.util.Set<BomRoute> SetOfMfBomRoute) {
    this.bomRoutes = SetOfMfBomRoute;
  }

}