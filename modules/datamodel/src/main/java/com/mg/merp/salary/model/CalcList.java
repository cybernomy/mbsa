/*
 * CalcList.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Расчетный листок"
 *
 * @author Artem V. Sharapov
 * @version $Id: CalcList.java,v 1.7 2007/07/09 08:21:57 sharapov Exp $
 */
public class CalcList extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.salary.model.PayRoll PayRoll;

  private com.mg.merp.personnelref.model.PositionFill PositionFill;

  private boolean NeedParams;

  private boolean IsCalculated;

  private java.math.BigDecimal TotalSumma;

  private java.math.BigDecimal PositiveSumma;

  private java.math.BigDecimal NegativeSumma;

  private java.math.BigDecimal NeutralSumma;

  private boolean IsClosed;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Set<CalcListSection> CalcListSections;


  // Constructors

  /**
   * default constructor
   */
  public CalcList() {
  }

  /**
   * constructor with id
   */
  public CalcList(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
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
  public com.mg.merp.salary.model.PayRoll getPayRoll() {
    return this.PayRoll;
  }

  public void setPayRoll(com.mg.merp.salary.model.PayRoll SalPayRoll) {
    this.PayRoll = SalPayRoll;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.PositionFill") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.PositionFill getPositionFill() {
    return this.PositionFill;
  }

  public void setPositionFill(
      com.mg.merp.personnelref.model.PositionFill SalPositionFill) {
    this.PositionFill = SalPositionFill;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.NeedParams") //$NON-NLS-1$
  public boolean getNeedParams() {
    return this.NeedParams;
  }

  public void setNeedParams(boolean NeedParams) {
    this.NeedParams = NeedParams;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.IsCalculated") //$NON-NLS-1$
  public boolean getIsCalculated() {
    return this.IsCalculated;
  }

  public void setIsCalculated(boolean IsCalculated) {
    this.IsCalculated = IsCalculated;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.TotalSumma") //$NON-NLS-1$
  public java.math.BigDecimal getTotalSumma() {
    return this.TotalSumma;
  }

  public void setTotalSumma(java.math.BigDecimal TotalSumma) {
    this.TotalSumma = TotalSumma;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.PositiveSumma") //$NON-NLS-1$
  public java.math.BigDecimal getPositiveSumma() {
    return this.PositiveSumma;
  }

  public void setPositiveSumma(java.math.BigDecimal PositiveSumma) {
    this.PositiveSumma = PositiveSumma;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.NegativeSumma") //$NON-NLS-1$
  public java.math.BigDecimal getNegativeSumma() {
    return this.NegativeSumma;
  }

  public void setNegativeSumma(java.math.BigDecimal NegativeSumma) {
    this.NegativeSumma = NegativeSumma;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.NeutralSumma") //$NON-NLS-1$
  public java.math.BigDecimal getNeutralSumma() {
    return this.NeutralSumma;
  }

  public void setNeutralSumma(java.math.BigDecimal NeutralSumma) {
    this.NeutralSumma = NeutralSumma;
  }

  /**
   *
   */
  @DataItemName("Salary.CalcListFee.IsClosed") //$NON-NLS-1$
  public boolean getIsClosed() {
    return this.IsClosed;
  }

  public void setIsClosed(boolean IsClosed) {
    this.IsClosed = IsClosed;
  }

  /**
   * @return the calcListSections
   */
  public java.util.Set<CalcListSection> getCalcListSections() {
    return this.CalcListSections;
  }

  /**
   * @param calcListSections the calcListSections to set
   */
  public void setCalcListSections(java.util.Set<CalcListSection> calcListSections) {
    this.CalcListSections = calcListSections;
  }

}