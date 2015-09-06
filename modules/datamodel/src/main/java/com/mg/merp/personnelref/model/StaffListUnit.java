/*
 * StaffListUnit.java
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
 * Модель бизнес-компонента "Подразделения в штатном расписании"
 *
 * @author Artem V. Sharapov
 * @version $Id: StaffListUnit.java,v 1.7 2007/07/09 07:47:11 sharapov Exp $
 */
public class StaffListUnit extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.CostsAnl CostsAnl1;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl2;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl3;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl4;
  private com.mg.merp.personnelref.model.CostsAnl CostsAnl5;

  private com.mg.merp.personnelref.model.WorkSchedule WorkSchedule;

  private com.mg.merp.personnelref.model.TaxCalcKind TaxCalcKind;

  private com.mg.merp.personnelref.model.StaffList StaffList;

  private com.mg.merp.personnelref.model.StaffListUnit Parent;
  private java.lang.String FolderTag;
  private java.lang.String UCode;
  private java.lang.String UName;

  private java.lang.String Comments;

  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public StaffListUnit() {
  }

  /**
   * constructor with id
   */
  public StaffListUnit(java.lang.Integer Id) {
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
  public com.mg.merp.personnelref.model.StaffListUnit getParent() {
    return this.Parent;
  }

  public void setParent(com.mg.merp.personnelref.model.StaffListUnit PrefStaffListUnit) {
    this.Parent = PrefStaffListUnit;
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
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  public com.mg.merp.personnelref.model.TaxCalcKind getTaxCalcKind() {
    return this.TaxCalcKind;
  }

  public void setTaxCalcKind(com.mg.merp.personnelref.model.TaxCalcKind PrefTaxCalcKind) {
    this.TaxCalcKind = PrefTaxCalcKind;
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
  public com.mg.merp.personnelref.model.WorkSchedule getWorkSchedule() {
    return this.WorkSchedule;
  }

  public void setWorkSchedule(com.mg.merp.personnelref.model.WorkSchedule PrefWorkSchedule) {
    this.WorkSchedule = PrefWorkSchedule;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.StaffListUnit.Code") //$NON-NLS-1$
  public java.lang.String getUCode() {
    return this.UCode;
  }

  public void setUCode(java.lang.String Ucode) {
    this.UCode = Ucode;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.StaffListUnit.Name") //$NON-NLS-1$
  public java.lang.String getUName() {
    return this.UName;
  }

  public void setUName(java.lang.String Uname) {
    this.UName = Uname;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.StaffListUnit.Comments") //$NON-NLS-1$
  public java.lang.String getComments() {
    return this.Comments;
  }

  public void setComments(java.lang.String Comments) {
    this.Comments = Comments;
  }

  /**
   *
   */
  @DataItemName("Reference.FolderTag") //$NON-NLS-1$
  public java.lang.String getFolderTag() {
    return this.FolderTag;
  }

  public void setFolderTag(java.lang.String FolderTag) {
    this.FolderTag = FolderTag;
  }

}