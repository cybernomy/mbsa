/*
 * PrefJob.java
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

/**
 * @author hbm2java
 * @version $Id: Job.java,v 1.12 2009/03/05 12:01:36 safonov Exp $
 */
@DataItemName("Manufacture.Job")
public class Job extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.reference.model.Employees DefSrcMol;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.manufacture.model.Job ParentJob;

  private com.mg.merp.mfreference.model.CostDetail StdCostDetail;

  private com.mg.merp.reference.model.Employees DefDstMol;

  private com.mg.merp.mfreference.model.Cell Cell;

  private com.mg.merp.manufacture.model.Job RootJob;

  private com.mg.merp.mfreference.model.CostDetail ActWipCostDetail;

  private com.mg.merp.warehouse.model.Warehouse DefDstStock;

  private com.mg.merp.warehouse.model.Warehouse DefSrcStock;

  private java.lang.String JobNumber;

  private java.util.Date JobDate;

  private JobStatus JobStatus;

  private java.math.BigDecimal QtyReleased;

  private java.math.BigDecimal QtyComplete;

  private java.math.BigDecimal QtyScrapped;

  private java.util.Date RollUpDateTime;

  private java.util.Date StartDate;

  private java.util.Date EndDate;

  private long startTick;

  private long endTick;

  private long startJob;

  private long finishJob;

  private com.mg.merp.mfreference.model.ScheduleDirection SchedDirection;

  private boolean UseMoveTimes;

  private boolean UseQueueTimes;

  private boolean UseFiniteCapacity;

  private java.math.BigDecimal Priority;

  private boolean PriorityFreezeFlag;

  private java.util.Date MrpEndDate;

  private boolean ChangeJobApproved;

  private java.lang.String Comment;

  private java.util.Set<JobRouteResource> jobRoutes;

  // Constructors

  /**
   * default constructor
   */
  public Job() {
  }

  /**
   * constructor with id
   */
  public Job(int Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
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
  @DataItemName("Manufacture.Job.DefSrcMol")
  public com.mg.merp.reference.model.Employees getDefSrcMol() {
    return this.DefSrcMol;
  }

  public void setDefSrcMol(com.mg.merp.reference.model.Employees defSrcMol) {
    this.DefSrcMol = defSrcMol;
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
  @DataItemName("Manufacture.Job.ParentJob")
  public com.mg.merp.manufacture.model.Job getParentJob() {
    return this.ParentJob;
  }

  public void setParentJob(com.mg.merp.manufacture.model.Job MfJob) {
    this.ParentJob = MfJob;
  }

  /**
   *
   */

  public com.mg.merp.mfreference.model.CostDetail getStdCostDetail() {
    return this.StdCostDetail;
  }

  public void setStdCostDetail(
      com.mg.merp.mfreference.model.CostDetail MfCostDetail) {
    this.StdCostDetail = MfCostDetail;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.DefDstMol")
  public com.mg.merp.reference.model.Employees getDefDstMol() {
    return this.DefDstMol;
  }

  public void setDefDstMol(com.mg.merp.reference.model.Employees defDstMol) {
    this.DefDstMol = defDstMol;
  }

  /**
   *
   */
  public com.mg.merp.mfreference.model.Cell getCell() {
    return this.Cell;
  }

  public void setCell(com.mg.merp.mfreference.model.Cell MfCell) {
    this.Cell = MfCell;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.RootJob")
  public com.mg.merp.manufacture.model.Job getRootJob() {
    return this.RootJob;
  }

  public void setRootJob(com.mg.merp.manufacture.model.Job MfJob_1) {
    this.RootJob = MfJob_1;
  }

  /**
   *
   */

  public com.mg.merp.mfreference.model.CostDetail getActWipCostDetail() {
    return this.ActWipCostDetail;
  }

  public void setActWipCostDetail(
      com.mg.merp.mfreference.model.CostDetail MfCostDetail_1) {
    this.ActWipCostDetail = MfCostDetail_1;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.DefDstStock")
  public com.mg.merp.warehouse.model.Warehouse getDefDstStock() {
    return this.DefDstStock;
  }

  public void setDefDstStock(
      com.mg.merp.warehouse.model.Warehouse defDstStock) {
    this.DefDstStock = defDstStock;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.DefSrcStock")
  public com.mg.merp.warehouse.model.Warehouse getDefSrcStock() {
    return this.DefSrcStock;
  }

  public void setDefSrcStock(
      com.mg.merp.warehouse.model.Warehouse defSrcStock) {
    this.DefSrcStock = defSrcStock;
  }

  /**
   *
   */

  @DataItemName("Manufacture.Job.JobNumber")
  public java.lang.String getJobNumber() {
    return this.JobNumber;
  }

  public void setJobNumber(java.lang.String JobNumber) {
    this.JobNumber = JobNumber;
  }

  /**
   *
   */

  @DataItemName("Manufacture.Job.JobDate")
  public java.util.Date getJobDate() {
    return this.JobDate;
  }

  public void setJobDate(java.util.Date JobDate) {
    this.JobDate = JobDate;
  }

  /**
   *
   */

  public JobStatus getJobStatus() {
    return this.JobStatus;
  }

  public void setJobStatus(JobStatus JobStatus) {
    this.JobStatus = JobStatus;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.QtyReleased")
  public java.math.BigDecimal getQtyReleased() {
    return this.QtyReleased;
  }

  public void setQtyReleased(java.math.BigDecimal QtyReleased) {
    this.QtyReleased = QtyReleased;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.QtyComplete")
  public java.math.BigDecimal getQtyComplete() {
    return this.QtyComplete;
  }

  public void setQtyComplete(java.math.BigDecimal QtyComplete) {
    this.QtyComplete = QtyComplete;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.QtyScrapped")
  public java.math.BigDecimal getQtyScrapped() {
    return this.QtyScrapped;
  }

  public void setQtyScrapped(java.math.BigDecimal QtyScrapped) {
    this.QtyScrapped = QtyScrapped;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.RollUpDateTime")
  public java.util.Date getRollUpDateTime() {
    return this.RollUpDateTime;
  }

  public void setRollUpDateTime(java.util.Date RollupDatetime) {
    this.RollUpDateTime = RollupDatetime;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.StartDate")
  public java.util.Date getStartDate() {
    return this.StartDate;
  }

  public void setStartDate(java.util.Date StartDate) {
    this.StartDate = StartDate;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.EndDate")
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date EndDate) {
    this.EndDate = EndDate;
  }

  /**
   *
   */

  public long getStartTick() {
    return this.startTick;
  }

  public void setStartTick(long StartTick) {
    this.startTick = StartTick;
  }

  /**
   *
   */

  public long getEndTick() {
    return this.endTick;
  }

  public void setEndTick(long EndTick) {
    this.endTick = EndTick;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.StartJob")
  public long getStartJob() {
    return this.startJob;
  }

  public void setStartJob(long StartJob) {
    this.startJob = StartJob;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.FinishJob")
  public long getFinishJob() {
    return this.finishJob;
  }

  public void setFinishJob(long FinishJob) {
    this.finishJob = FinishJob;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.SchedDirection")
  public com.mg.merp.mfreference.model.ScheduleDirection getSchedDirection() {
    return this.SchedDirection;
  }

  public void setSchedDirection(
      com.mg.merp.mfreference.model.ScheduleDirection SchedDirection) {
    this.SchedDirection = SchedDirection;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.UseMoveTimes")
  public boolean isUseMoveTimes() {
    return this.UseMoveTimes;
  }

  public void setUseMoveTimes(boolean UseMoveTimes) {
    this.UseMoveTimes = UseMoveTimes;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.UseQueueTimes")
  public boolean isUseQueueTimes() {
    return this.UseQueueTimes;
  }

  public void setUseQueueTimes(boolean UseQueueTimes) {
    this.UseQueueTimes = UseQueueTimes;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.UseFiniteCapacity")
  public boolean isUseFiniteCapacity() {
    return this.UseFiniteCapacity;
  }

  public void setUseFiniteCapacity(boolean UseFiniteCapacity) {
    this.UseFiniteCapacity = UseFiniteCapacity;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.Priority")
  public java.math.BigDecimal getPriority() {
    return this.Priority;
  }

  public void setPriority(java.math.BigDecimal Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.PriorityFreezeFlag")
  public boolean isPriorityFreezeFlag() {
    return this.PriorityFreezeFlag;
  }

  public void setPriorityFreezeFlag(boolean PriorityFreezeFlag) {
    this.PriorityFreezeFlag = PriorityFreezeFlag;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.MrpEndDate")
  public java.util.Date getMrpEndDate() {
    return this.MrpEndDate;
  }

  public void setMrpEndDate(java.util.Date MrpEndDate) {
    this.MrpEndDate = MrpEndDate;
  }

  /**
   *
   */
  @DataItemName("Manufacture.Job.ChangeJobApproved")
  public boolean isChangeJobApproved() {
    return this.ChangeJobApproved;
  }

  public void setChangeJobApproved(boolean ChangeJobApproved) {
    this.ChangeJobApproved = ChangeJobApproved;
  }

  /**
   *
   */

  @DataItemName("Manufacture.Job.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */

  public java.util.Set<JobRouteResource> getJobRoutes() {
    return this.jobRoutes;
  }

  public void setJobRoutes(java.util.Set<JobRouteResource> jobRoutes) {
    this.jobRoutes = jobRoutes;
  }
}