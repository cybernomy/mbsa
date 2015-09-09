/*
 * FeeModel.java
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
 * Модель бизнес-компонента "Образцы начислений / удержаний"
 *
 * @author Artem V. Sharapov
 * @version $Id: FeeModel.java,v 1.7 2007/07/09 08:21:57 sharapov Exp $
 */
public class FeeModel extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

  private com.mg.merp.personnelref.model.PersonalAccount PersonalAccount;
  private com.mg.merp.personnelref.model.CalcPeriod CalcPeriod;
  private com.mg.merp.salary.model.FeeRef FeeRef;
  private com.mg.merp.personnelref.model.PositionFill PositionFill;
  private com.mg.merp.salary.model.RollKind RollKind;

  private java.util.Date BeginDate;
  private java.util.Date EndDate;

  private java.math.BigDecimal Summa;

  private boolean UseBasicPosition;

  private java.util.Set<FeeModelParam> FeeModelParams;

  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public FeeModel() {
  }

  /**
   * constructor with id
   */
  public FeeModel(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.CostsAnl1") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
    return this.CostsAnl1;
  }

  public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
    this.CostsAnl1 = PrefCostsAnl;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.CostsAnl3") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
    return this.CostsAnl3;
  }

  public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
    this.CostsAnl3 = PrefCostsAnl_1;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.CostsAnl2") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
    return this.CostsAnl2;
  }

  public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
    this.CostsAnl2 = PrefCostsAnl_2;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.CostsAnl4") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
    return this.CostsAnl4;
  }

  public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
    this.CostsAnl4 = PrefCostsAnl_3;
  }

  /**

   */
  public com.mg.merp.personnelref.model.PersonalAccount getPersonalAccount() {
    return this.PersonalAccount;
  }

  public void setPersonalAccount(com.mg.merp.personnelref.model.PersonalAccount SalPersonalAccount) {
    this.PersonalAccount = SalPersonalAccount;
  }

  /**

   */
  public com.mg.merp.salary.model.FeeRef getFeeRef() {
    return this.FeeRef;
  }

  public void setFeeRef(com.mg.merp.salary.model.FeeRef SalFeeRef) {
    this.FeeRef = SalFeeRef;
  }

  /**

   */
  public com.mg.merp.personnelref.model.PositionFill getPositionFill() {
    return this.PositionFill;
  }

  public void setPositionFill(com.mg.merp.personnelref.model.PositionFill SalPositionFill) {
    this.PositionFill = SalPositionFill;
  }

  /**

   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.CostsAnl5") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
    return this.CostsAnl5;
  }

  public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
    this.CostsAnl5 = PrefCostsAnl_4;
  }

  /**

   */
  public com.mg.merp.salary.model.RollKind getRollKind() {
    return this.RollKind;
  }

  public void setRollKind(com.mg.merp.salary.model.RollKind SalRollKind) {
    this.RollKind = SalRollKind;
  }

  /**

   */
  public com.mg.merp.personnelref.model.CalcPeriod getCalcPeriod() {
    return this.CalcPeriod;
  }

  public void setCalcPeriod(com.mg.merp.personnelref.model.CalcPeriod CalcPeriod) {
    this.CalcPeriod = CalcPeriod;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.BeginDate") //$NON-NLS-1$
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.EndDate") //$NON-NLS-1$
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date Enddate) {
    this.EndDate = Enddate;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.Summa") //$NON-NLS-1$
  public java.math.BigDecimal getSumma() {
    return this.Summa;
  }

  public void setSumma(java.math.BigDecimal Summa) {
    this.Summa = Summa;
  }

  /**

   */
  @DataItemName("Salary.FeeModel.UseBasicPosition") //$NON-NLS-1$
  public boolean getUseBasicPosition() {
    return this.UseBasicPosition;
  }

  public void setUseBasicPosition(boolean UseBasicPosition) {
    this.UseBasicPosition = UseBasicPosition;
  }

  /**
   * @return the feeModelParams
   */
  public java.util.Set<FeeModelParam> getFeeModelParams() {
    return this.FeeModelParams;
  }

  /**
   * @param feeModelParams the feeModelParams to set
   */
  public void setFeeModelParams(java.util.Set<FeeModelParam> feeModelParams) {
    this.FeeModelParams = feeModelParams;
  }

}