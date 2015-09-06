/*
 * FeeRef.java
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
 * Модель бизнес-компонента "Справочник начислений/удержаний"
 *
 * @author Artem V. Sharapov
 * @version $Id: FeeRef.java,v 1.9 2007/08/21 05:27:57 sharapov Exp $
 */
@DataItemName("Salary.FeeRef") //$NON-NLS-1$
public class FeeRef extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.salary.model.GnsCode GnsCode;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;

  private com.mg.merp.salary.model.IncomeKind IncomeKind;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;

  private com.mg.merp.baiengine.model.Repository CalcAlg;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

  private com.mg.merp.salary.model.CalcListSectionRef CalcListSectionRef;

  private com.mg.merp.salary.model.RollKind RollKind;

  private FeeType FeeType;

  private java.lang.String FCode;

  private java.lang.String FName;

  private java.lang.Integer Priority;

  private TripleSumSign SumSign;

  private java.util.Date BeginDate;

  private java.util.Date EndDate;

  private FeePerioicity PeriodiCity;

  private boolean IsZeroIncluded;

  private java.util.Set<FeeRefParam> FeeRefParams;

  private java.util.Set SetOfSalTariffingInFee;

  private java.util.Set SetOfSalIncludedFee;

  private java.util.Set SetOfSalReplacedFee;

  private java.util.Set SetOfSalReplacedFee1;

  private java.util.Set SetOfSalIncludedFee1;

  // Constructors

  /**
   * default constructor
   */
  public FeeRef() {
  }

  /**
   * constructor with id
   */
  public FeeRef(java.lang.Integer Id) {
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
  public com.mg.merp.salary.model.GnsCode getGnsCode() {
    return this.GnsCode;
  }

  public void setGnsCode(com.mg.merp.salary.model.GnsCode SalGnsCode) {
    this.GnsCode = SalGnsCode;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.CostsAnl1") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
    return this.CostsAnl1;
  }

  public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
    this.CostsAnl1 = PrefCostsAnl;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.CostsAnl3") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
    return this.CostsAnl3;
  }

  public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
    this.CostsAnl3 = PrefCostsAnl_1;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.CostsAnl2") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
    return this.CostsAnl2;
  }

  public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
    this.CostsAnl2 = PrefCostsAnl_2;
  }

  /**
   *
   */
  public com.mg.merp.salary.model.IncomeKind getIncomeKind() {
    return this.IncomeKind;
  }

  public void setIncomeKind(com.mg.merp.salary.model.IncomeKind SalIncomekind) {
    this.IncomeKind = SalIncomekind;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.CostsAnl4") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
    return this.CostsAnl4;
  }

  public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
    this.CostsAnl4 = PrefCostsAnl_3;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.CalcAlg") //$NON-NLS-1$
  public com.mg.merp.baiengine.model.Repository getCalcAlg() {
    return this.CalcAlg;
  }

  public void setCalcAlg(com.mg.merp.baiengine.model.Repository AlgRepository) {
    this.CalcAlg = AlgRepository;
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
  @DataItemName("Salary.FeeRef.CostsAnl5") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
    return this.CostsAnl5;
  }

  public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
    this.CostsAnl5 = PrefCostsAnl_4;
  }

  /**
   *
   */
  public com.mg.merp.salary.model.CalcListSectionRef getCalcListSectionRef() {
    return this.CalcListSectionRef;
  }

  public void setCalcListSectionRef(com.mg.merp.salary.model.CalcListSectionRef SalCalcListSectionRef) {
    this.CalcListSectionRef = SalCalcListSectionRef;
  }

  /**
   *
   */
  public com.mg.merp.salary.model.RollKind getRollKind() {
    return this.RollKind;
  }

  public void setRollKind(com.mg.merp.salary.model.RollKind SalRollKind) {
    this.RollKind = SalRollKind;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.FeeType") //$NON-NLS-1$
  public FeeType getFeeType() {
    return this.FeeType;
  }

  public void setFeeType(FeeType FeeType) {
    this.FeeType = FeeType;
  }

  /**
   *
   */
  @DataItemName("Salary.BigCode") //$NON-NLS-1$
  public java.lang.String getFCode() {
    return this.FCode;
  }

  public void setFCode(java.lang.String Fcode) {
    this.FCode = Fcode;
  }

  /**
   *
   */
  @DataItemName("Salary.Name") //$NON-NLS-1$
  public java.lang.String getFName() {
    return this.FName;
  }

  public void setFName(java.lang.String Fname) {
    this.FName = Fname;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.Priority") //$NON-NLS-1$
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.SumSign") //$NON-NLS-1$
  public TripleSumSign getSumSign() {
    return this.SumSign;
  }

  public void setSumSign(TripleSumSign SumSign) {
    this.SumSign = SumSign;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.BeginDate") //$NON-NLS-1$
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.EndDate") //$NON-NLS-1$
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date Enddate) {
    this.EndDate = Enddate;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.PeriodiCity") //$NON-NLS-1$
  public FeePerioicity getPeriodiCity() {
    return this.PeriodiCity;
  }

  public void setPeriodiCity(FeePerioicity Periodicity) {
    this.PeriodiCity = Periodicity;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeRef.IsZeroIncluded") //$NON-NLS-1$
  public boolean getIsZeroIncluded() {
    return this.IsZeroIncluded;
  }

  public void setIsZeroIncluded(boolean IsZeroIncluded) {
    this.IsZeroIncluded = IsZeroIncluded;
  }


  /**
   *
   */
  public java.util.Set getSetOfSalTariffingInFee() {
    return this.SetOfSalTariffingInFee;
  }

  public void setSetOfSalTariffingInFee(java.util.Set SetOfSalTariffingInFee) {
    this.SetOfSalTariffingInFee = SetOfSalTariffingInFee;
  }

  /**
   *
   */
  public java.util.Set getSetOfSalIncludedFee() {
    return this.SetOfSalIncludedFee;
  }

  public void setSetOfSalIncludedFee(java.util.Set SetOfSalIncludedFee) {
    this.SetOfSalIncludedFee = SetOfSalIncludedFee;
  }

  /**
   *
   */
  public java.util.Set getSetOfSalReplacedFee() {
    return this.SetOfSalReplacedFee;
  }

  public void setSetOfSalReplacedFee(java.util.Set SetOfSalReplacedFee) {
    this.SetOfSalReplacedFee = SetOfSalReplacedFee;
  }

  /**
   *
   */
  public java.util.Set getSetOfSalReplacedFee1() {
    return this.SetOfSalReplacedFee1;
  }

  public void setSetOfSalReplacedFee1(java.util.Set SetOfSalReplacedFee1) {
    this.SetOfSalReplacedFee1 = SetOfSalReplacedFee1;
  }

  /**
   *
   */
  public java.util.Set getSetOfSalIncludedFee1() {
    return this.SetOfSalIncludedFee1;
  }

  public void setSetOfSalIncludedFee1(java.util.Set SetOfSalIncludedFee1) {
    this.SetOfSalIncludedFee1 = SetOfSalIncludedFee1;
  }

  /**
   * @return the feeRefParams
   */
  public java.util.Set<FeeRefParam> getFeeRefParams() {
    return this.FeeRefParams;
  }

  /**
   * @param feeRefParams the feeRefParams to set
   */
  public void setFeeRefParams(java.util.Set<FeeRefParam> feeRefParams) {
    this.FeeRefParams = feeRefParams;
  }

}