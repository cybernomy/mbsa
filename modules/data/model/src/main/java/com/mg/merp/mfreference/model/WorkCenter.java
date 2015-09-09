/*
 * WorkCenter.java
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
 * @version $Id: WorkCenter.java,v 1.7 2007/07/30 11:00:23 safonov Exp $
 */
@DataItemName("MfReference.WorkCenter")
public class WorkCenter extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.CostDetail ActCostDetail;

  private com.mg.merp.mfreference.model.WeekCalendar WeekCal;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.mfreference.model.CostDetail StdCostDetail;

  private com.mg.merp.mfreference.model.Cell Cell;

  private com.mg.merp.reference.model.Contractor Contractor;

  private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;

  private java.lang.String WcCode;

  private java.lang.String WcName;

  private java.math.BigDecimal Efficiency;

  private java.math.BigDecimal Utilization;

  private boolean CapacityPlan;

  private boolean CapacitySchedule;

  private boolean MchFlag;

  private java.lang.Integer MchNumber;

  private boolean LbrFlag;

  private java.math.BigDecimal LbrNumber;

  private java.math.BigDecimal SchTolerance;

  private java.math.BigDecimal queueTicks;

  private java.math.BigDecimal MchRunTotalHrs;

  private java.math.BigDecimal LbrSetupTotalHrs;

  private java.math.BigDecimal LbrRunTotalHrs;

  private java.math.BigDecimal QueueTotalQty;

  private java.math.BigDecimal QueueAvgHrs;

  private boolean OutsideFlag;

  private boolean MchBackflushFlag;

  private boolean LbrBackflushFlag;

  private boolean MchBaseOhFlag;

  private boolean LbrBaseOhFlag;

  private boolean MtlBaseOhFlag;

  private boolean ControlPointFlag;

  private java.math.BigDecimal WipLbrTotal;

  private java.math.BigDecimal WipMtlTotal;

  private java.math.BigDecimal WipFohTotal;

  private java.math.BigDecimal WipVohTotal;

  private java.math.BigDecimal WipOutTotal;

  private java.math.BigDecimal LbrSetupAvgRate;

  private java.math.BigDecimal LbrRunAvgRate;

  private java.lang.String Comment;

  // Constructors

  /**
   * default constructor
   */
  public WorkCenter() {
  }

  /**
   * constructor with id
   */
  public WorkCenter(java.lang.Integer Id) {
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

  public com.mg.merp.mfreference.model.CostDetail getActCostDetail() {
    return this.ActCostDetail;
  }

  public void setActCostDetail(
      com.mg.merp.mfreference.model.CostDetail ActCostDetail) {
    this.ActCostDetail = ActCostDetail;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.WeekCal")
  public com.mg.merp.mfreference.model.WeekCalendar getWeekCal() {
    return this.WeekCal;
  }

  public void setWeekCal(com.mg.merp.mfreference.model.WeekCalendar WeekCal) {
    this.WeekCal = WeekCal;
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

  public com.mg.merp.mfreference.model.CostDetail getStdCostDetail() {
    return this.StdCostDetail;
  }

  public void setStdCostDetail(
      com.mg.merp.mfreference.model.CostDetail StdCostDetail) {
    this.StdCostDetail = StdCostDetail;
  }

  /**
   *
   */

  @DataItemName("MfReference.WorkCenter.Cell")
  public com.mg.merp.mfreference.model.Cell getCell() {
    return this.Cell;
  }

  public void setCell(com.mg.merp.mfreference.model.Cell Cell) {
    this.Cell = Cell;
  }

  /**
   *
   */

  @DataItemName("MfReference.WorkCenter.Contractor")
  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.WcCode")
  public java.lang.String getWcCode() {
    return this.WcCode;
  }

  public void setWcCode(java.lang.String WcCode) {
    this.WcCode = WcCode;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.WcName")
  public java.lang.String getWcName() {
    return this.WcName;
  }

  public void setWcName(java.lang.String WcName) {
    this.WcName = WcName;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.Efficiency")
  public java.math.BigDecimal getEfficiency() {
    return this.Efficiency;
  }

  public void setEfficiency(java.math.BigDecimal Efficiency) {
    this.Efficiency = Efficiency;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.Utilization")
  public java.math.BigDecimal getUtilization() {
    return this.Utilization;
  }

  public void setUtilization(java.math.BigDecimal Utilization) {
    this.Utilization = Utilization;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.CapacityPlan")
  public boolean getCapacityPlan() {
    return this.CapacityPlan;
  }

  public void setCapacityPlan(boolean CapacityPlan) {
    this.CapacityPlan = CapacityPlan;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.CapacitySchedule")
  public boolean getCapacitySchedule() {
    return this.CapacitySchedule;
  }

  public void setCapacitySchedule(boolean CapacitySchedule) {
    this.CapacitySchedule = CapacitySchedule;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MchFlag")
  public boolean getMchFlag() {
    return this.MchFlag;
  }

  public void setMchFlag(boolean MchFlag) {
    this.MchFlag = MchFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MchNumber")
  public java.lang.Integer getMchNumber() {
    return this.MchNumber;
  }

  public void setMchNumber(java.lang.Integer MchNumber) {
    this.MchNumber = MchNumber;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrFlag")
  public boolean getLbrFlag() {
    return this.LbrFlag;
  }

  public void setLbrFlag(boolean LbrFlag) {
    this.LbrFlag = LbrFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrNumber")
  public java.math.BigDecimal getLbrNumber() {
    return this.LbrNumber;
  }

  public void setLbrNumber(java.math.BigDecimal LbrNumber) {
    this.LbrNumber = LbrNumber;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.SchTolerance")
  public java.math.BigDecimal getSchTolerance() {
    return this.SchTolerance;
  }

  public void setSchTolerance(java.math.BigDecimal SchTolerance) {
    this.SchTolerance = SchTolerance;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.QueueTicks")
  public java.math.BigDecimal getQueueTicks() {
    return this.queueTicks;
  }

  public void setQueueTicks(java.math.BigDecimal QueueTicks) {
    this.queueTicks = QueueTicks;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MchRunTotalHrs")
  public java.math.BigDecimal getMchRunTotalHrs() {
    return this.MchRunTotalHrs;
  }

  public void setMchRunTotalHrs(java.math.BigDecimal MchRunTotalHrs) {
    this.MchRunTotalHrs = MchRunTotalHrs;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrSetupTotalHrs")
  public java.math.BigDecimal getLbrSetupTotalHrs() {
    return this.LbrSetupTotalHrs;
  }

  public void setLbrSetupTotalHrs(java.math.BigDecimal LbrSetupTotalHrs) {
    this.LbrSetupTotalHrs = LbrSetupTotalHrs;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrRunTotalHrs")
  public java.math.BigDecimal getLbrRunTotalHrs() {
    return this.LbrRunTotalHrs;
  }

  public void setLbrRunTotalHrs(java.math.BigDecimal LbrRunTotalHrs) {
    this.LbrRunTotalHrs = LbrRunTotalHrs;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.QueueTotalQty")
  public java.math.BigDecimal getQueueTotalQty() {
    return this.QueueTotalQty;
  }

  public void setQueueTotalQty(java.math.BigDecimal QueueTotalQty) {
    this.QueueTotalQty = QueueTotalQty;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.QueueAvgHrs")
  public java.math.BigDecimal getQueueAvgHrs() {
    return this.QueueAvgHrs;
  }

  public void setQueueAvgHrs(java.math.BigDecimal QueueAvgHrs) {
    this.QueueAvgHrs = QueueAvgHrs;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.OutsideFlag")
  public boolean getOutsideFlag() {
    return this.OutsideFlag;
  }

  public void setOutsideFlag(boolean OutsideFlag) {
    this.OutsideFlag = OutsideFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MchBackflushFlag")
  public boolean getMchBackflushFlag() {
    return this.MchBackflushFlag;
  }

  public void setMchBackflushFlag(boolean MchBackflushFlag) {
    this.MchBackflushFlag = MchBackflushFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrBackflushFlag")
  public boolean getLbrBackflushFlag() {
    return this.LbrBackflushFlag;
  }

  public void setLbrBackflushFlag(boolean LbrBackflushFlag) {
    this.LbrBackflushFlag = LbrBackflushFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MchBaseOhFlag")
  public boolean getMchBaseOhFlag() {
    return this.MchBaseOhFlag;
  }

  public void setMchBaseOhFlag(boolean MchBaseOhFlag) {
    this.MchBaseOhFlag = MchBaseOhFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrBaseOhFlag")
  public boolean getLbrBaseOhFlag() {
    return this.LbrBaseOhFlag;
  }

  public void setLbrBaseOhFlag(boolean LbrBaseOhFlag) {
    this.LbrBaseOhFlag = LbrBaseOhFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.MtlBaseOhFlag")
  public boolean getMtlBaseOhFlag() {
    return this.MtlBaseOhFlag;
  }

  public void setMtlBaseOhFlag(boolean MtlBaseOhFlag) {
    this.MtlBaseOhFlag = MtlBaseOhFlag;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.ControlPointFlag")
  public boolean getControlPointFlag() {
    return this.ControlPointFlag;
  }

  public void setControlPointFlag(boolean ControlPointFlag) {
    this.ControlPointFlag = ControlPointFlag;
  }

  /**
   *
   */

  public java.math.BigDecimal getWipLbrTotal() {
    return this.WipLbrTotal;
  }

  public void setWipLbrTotal(java.math.BigDecimal WipLbrTotal) {
    this.WipLbrTotal = WipLbrTotal;
  }

  /**
   *
   */

  public java.math.BigDecimal getWipMtlTotal() {
    return this.WipMtlTotal;
  }

  public void setWipMtlTotal(java.math.BigDecimal WipMtlTotal) {
    this.WipMtlTotal = WipMtlTotal;
  }

  /**
   *
   */

  public java.math.BigDecimal getWipFohTotal() {
    return this.WipFohTotal;
  }

  public void setWipFohTotal(java.math.BigDecimal WipFohTotal) {
    this.WipFohTotal = WipFohTotal;
  }

  /**
   *
   */

  public java.math.BigDecimal getWipVohTotal() {
    return this.WipVohTotal;
  }

  public void setWipVohTotal(java.math.BigDecimal WipVohTotal) {
    this.WipVohTotal = WipVohTotal;
  }

  /**
   *
   */

  public java.math.BigDecimal getWipOutTotal() {
    return this.WipOutTotal;
  }

  public void setWipOutTotal(java.math.BigDecimal WipOutTotal) {
    this.WipOutTotal = WipOutTotal;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrSetupAvgRate")
  public java.math.BigDecimal getLbrSetupAvgRate() {
    return this.LbrSetupAvgRate;
  }

  public void setLbrSetupAvgRate(java.math.BigDecimal LbrSetupAvgRate) {
    this.LbrSetupAvgRate = LbrSetupAvgRate;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.LbrRunAvgRate")
  public java.math.BigDecimal getLbrRunAvgRate() {
    return this.LbrRunAvgRate;
  }

  public void setLbrRunAvgRate(java.math.BigDecimal LbrRunAvgRate) {
    this.LbrRunAvgRate = LbrRunAvgRate;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("MfReference.WorkCenter.ResourceGroup")
  public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup() {
    return this.ResourceGroup;
  }

  public void setResourceGroup(
      com.mg.merp.mfreference.model.ResourceGroup ResourceGroup) {
    this.ResourceGroup = ResourceGroup;
  }

}