/*
 * JobMachine.java
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
import com.mg.merp.mfreference.model.MachineOverheadAllocationFlag;
import com.mg.merp.mfreference.model.MachineRecoveryFlag;
import com.mg.merp.mfreference.model.TimeRateFlag;

/**
 * @author hbm2java
 * @version $Id: JobMachine.java,v 1.9 2007/07/30 10:27:49 safonov Exp $
 */
public class JobMachine extends com.mg.merp.manufacture.model.JobRouteResource
    implements java.io.Serializable {

  // Fields

  private com.mg.merp.reference.model.Currency mchOhRateCurrency;

  private com.mg.merp.reference.model.Currency mchRateCurrency;

  private com.mg.merp.mfreference.model.CostCategories MchOhCostCategory;

  private com.mg.merp.reference.model.Measure RunTimeMchUm;

  private com.mg.merp.mfreference.model.CostCategories MchCostCategory;

  private TimeRateFlag TimeRateFlag;

  private long RunTicksMch;

  private java.math.BigDecimal MchNumber;

  private MachineRecoveryFlag MchRecoveryFlag;

  private java.math.BigDecimal MchRate;

  private boolean MchBackflushFlag;

  private MachineOverheadAllocationFlag MchOhAllocationFlag;

  private java.math.BigDecimal MchOhRate;

  private java.math.BigDecimal MchOhRatio;

  private boolean MchOhBackflushFlag;

  // Constructors

  /**
   * default constructor
   */
  public JobMachine() {
  }

  // Property accessors
  @DataItemName("Manufacture.JobMachine.MchOhRateCurCode")
  public com.mg.merp.reference.model.Currency getMchOhRateCurrency() {
    return this.mchOhRateCurrency;
  }

  public void setMchOhRateCurrency(
      com.mg.merp.reference.model.Currency Currency) {
    this.mchOhRateCurrency = Currency;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchRateCurCode")
  public com.mg.merp.reference.model.Currency getMchRateCurrency() {
    return this.mchRateCurrency;
  }

  public void setMchRateCurrency(
      com.mg.merp.reference.model.Currency Currency_1) {
    this.mchRateCurrency = Currency_1;
  }

  @DataItemName("Manufacture.JobMachine.MchOhCostCategory")
  public com.mg.merp.mfreference.model.CostCategories getMchOhCostCategory() {
    return this.MchOhCostCategory;
  }

  public void setMchOhCostCategory(
      com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
    this.MchOhCostCategory = MfCostCategories;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.RunTimeMchUm")
  public com.mg.merp.reference.model.Measure getRunTimeMchUm() {
    return this.RunTimeMchUm;
  }

  public void setRunTimeMchUm(com.mg.merp.reference.model.Measure Measure) {
    this.RunTimeMchUm = Measure;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchCostCategory")
  public com.mg.merp.mfreference.model.CostCategories getMchCostCategory() {
    return this.MchCostCategory;
  }

  public void setMchCostCategory(
      com.mg.merp.mfreference.model.CostCategories MfCostCategories_1) {
    this.MchCostCategory = MfCostCategories_1;
  }

  /**
   *
   */

  public TimeRateFlag getTimeRateFlag() {
    return this.TimeRateFlag;
  }

  public void setTimeRateFlag(TimeRateFlag TimeRateFlag) {
    this.TimeRateFlag = TimeRateFlag;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.RunTicksMch")
  public long getRunTicksMch() {
    return this.RunTicksMch;
  }

  public void setRunTicksMch(long RunTicksMch) {
    this.RunTicksMch = RunTicksMch;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchNumber")
  public java.math.BigDecimal getMchNumber() {
    return this.MchNumber;
  }

  public void setMchNumber(java.math.BigDecimal MchNumber) {
    this.MchNumber = MchNumber;
  }

  /**
   *
   */

  public MachineRecoveryFlag getMchRecoveryFlag() {
    return this.MchRecoveryFlag;
  }

  public void setMchRecoveryFlag(MachineRecoveryFlag MchRecoveryFlag) {
    this.MchRecoveryFlag = MchRecoveryFlag;
  }

  /**
   *
   */

  @DataItemName("Manufacture.JobMachine.MchRate")
  public java.math.BigDecimal getMchRate() {
    return this.MchRate;
  }

  public void setMchRate(java.math.BigDecimal MchRate) {
    this.MchRate = MchRate;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchBackflushFlag")
  public boolean getMchBackflushFlag() {
    return this.MchBackflushFlag;
  }

  public void setMchBackflushFlag(boolean MchBackflushFlag) {
    this.MchBackflushFlag = MchBackflushFlag;
  }

  /**
   *
   */

  public MachineOverheadAllocationFlag getMchOhAllocationFlag() {
    return this.MchOhAllocationFlag;
  }

  public void setMchOhAllocationFlag(MachineOverheadAllocationFlag MchOhAllocationFlag) {
    this.MchOhAllocationFlag = MchOhAllocationFlag;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchOhRate")
  public java.math.BigDecimal getMchOhRate() {
    return this.MchOhRate;
  }

  public void setMchOhRate(java.math.BigDecimal MchOhRate) {
    this.MchOhRate = MchOhRate;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchOhRatio")
  public java.math.BigDecimal getMchOhRatio() {
    return this.MchOhRatio;
  }

  public void setMchOhRatio(java.math.BigDecimal MchOhRatio) {
    this.MchOhRatio = MchOhRatio;
  }

  /**
   *
   */
  @DataItemName("Manufacture.JobMachine.MchOhBackflushFlag")
  public boolean getMchOhBackflushFlag() {
    return this.MchOhBackflushFlag;
  }

  public void setMchOhBackflushFlag(boolean MchOhBackflushFlag) {
    this.MchOhBackflushFlag = MchOhBackflushFlag;
  }

}