/*
 * Tariffing.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Тарификация должности"
 *
 * @author Artem V. Sharapov
 * @version $Id: Tariffing.java,v 1.8 2007/07/09 07:47:11 sharapov Exp $
 */
public class Tariffing extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

  private com.mg.merp.personnelref.model.TariffingCategory Category;

  private com.mg.merp.personnelref.model.PositionFill PositionFill;
  private java.lang.String SlPositionUniqueId;

  private com.mg.merp.personnelref.model.StaffList StaffList;

  private boolean UseRiseReference;
  private com.mg.merp.personnelref.model.Rise Rise;
  private com.mg.merp.personnelref.model.RiseScale RiseScale;
  private java.math.BigDecimal RiseValue;

  private java.util.Date BeginDate;
  private java.util.Date EndDate;

  private java.math.BigDecimal RateOfSalary;
  private java.math.BigDecimal MinSalaryNumber;

  private java.lang.String TariffScaleCode;
  private java.lang.Integer TariffClass;

  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public Tariffing() {
  }

  /**
   * constructor with id
   */
  public Tariffing(java.lang.Integer Id) {
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
  //@DataItemName("PersonnelRef.Tariffing.CostsAnl")
  @DataItemName("Personnelref.CostsAnl1") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl1() {
    return this.CostsAnl1;
  }

  public void setCostsAnl1(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl) {
    this.CostsAnl1 = PrefCostsAnl;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.Category") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.TariffingCategory getCategory() {
    return this.Category;
  }

  public void setCategory(com.mg.merp.personnelref.model.TariffingCategory PrefTariffingCategory) {
    this.Category = PrefTariffingCategory;
  }

  /**
   *
   */
  @DataItemName("Personnelref.CostsAnl2") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl2() {
    return this.CostsAnl2;
  }

  public void setCostsAnl2(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_1) {
    this.CostsAnl2 = PrefCostsAnl_1;
  }

  /**
   *
   */
  @DataItemName("Personnelref.CostsAnl3") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl3() {
    return this.CostsAnl3;
  }

  public void setCostsAnl3(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_2) {
    this.CostsAnl3 = PrefCostsAnl_2;
  }

  /**
   *
   */
  @DataItemName("Personnelref.CostsAnl4") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl4() {
    return this.CostsAnl4;
  }

  public void setCostsAnl4(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_3) {
    this.CostsAnl4 = PrefCostsAnl_3;
  }

  /**
   *
   */
  public com.mg.merp.personnelref.model.PositionFill getPositionFill() {
    return this.PositionFill;
  }

  public void setPositionFill(com.mg.merp.personnelref.model.PositionFill SalPositionFill) {
    this.PositionFill = SalPositionFill;
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
  public com.mg.merp.personnelref.model.StaffList getStaffList() {
    return this.StaffList;
  }

  public void setStaffList(com.mg.merp.personnelref.model.StaffList PrefStaffList) {
    this.StaffList = PrefStaffList;
  }

  /**
   *
   */
  @DataItemName("Personnelref.CostsAnl5") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.CostsAnl getCostsAnl5() {
    return this.CostsAnl5;
  }

  public void setCostsAnl5(com.mg.merp.personnelref.model.CostsAnl PrefCostsAnl_4) {
    this.CostsAnl5 = PrefCostsAnl_4;
  }

  /**
   *
   */
  public java.lang.String getSlPositionUniqueId() {
    return this.SlPositionUniqueId;
  }

  public void setSlPositionUniqueId(java.lang.String SlPositionUniqueId) {
    this.SlPositionUniqueId = SlPositionUniqueId;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.BeginDate") //$NON-NLS-1$
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.EndDate") //$NON-NLS-1$
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date Enddate) {
    this.EndDate = Enddate;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.RateOfSalary") //$NON-NLS-1$
  public java.math.BigDecimal getRateOfSalary() {
    return this.RateOfSalary;
  }

  public void setRateOfSalary(java.math.BigDecimal RateOfSalary) {
    this.RateOfSalary = RateOfSalary;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.TariffScaleCode") //$NON-NLS-1$
  public java.lang.String getTariffScaleCode() {
    return this.TariffScaleCode;
  }

  public void setTariffScaleCode(java.lang.String TariffScaleCode) {
    this.TariffScaleCode = TariffScaleCode;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.TariffClass") //$NON-NLS-1$
  public java.lang.Integer getTariffClass() {
    return this.TariffClass;
  }

  public void setTariffClass(java.lang.Integer Tariffclass) {
    this.TariffClass = Tariffclass;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.RiseValue") //$NON-NLS-1$
  public java.math.BigDecimal getRiseValue() {
    return this.RiseValue;
  }

  public void setRiseValue(java.math.BigDecimal RiseValue) {
    this.RiseValue = RiseValue;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.UseRiseReference") //$NON-NLS-1$
  public boolean getUseRiseReference() {
    return this.UseRiseReference;
  }

  public void setUseRiseReference(boolean UseRiseReference) {
    this.UseRiseReference = UseRiseReference;
  }

  /**
   *
   */
  public com.mg.merp.personnelref.model.Rise getRise() {
    return this.Rise;
  }

  public void setRise(com.mg.merp.personnelref.model.Rise Rise) {
    this.Rise = Rise;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.RiseScale") //$NON-NLS-1$
  public com.mg.merp.personnelref.model.RiseScale getRiseScale() {
    return this.RiseScale;
  }

  public void setRiseScale(com.mg.merp.personnelref.model.RiseScale RiseScale) {
    this.RiseScale = RiseScale;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Tariffing.MinSalaryNumber") //$NON-NLS-1$
  public java.math.BigDecimal getMinSalaryNumber() {
    return this.MinSalaryNumber;
  }

  public void setMinSalaryNumber(java.math.BigDecimal MinsalaryNumber) {
    this.MinSalaryNumber = MinsalaryNumber;
  }

}