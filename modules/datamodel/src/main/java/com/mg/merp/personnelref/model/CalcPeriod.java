/*
 * CalcPeriod.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CalcPeriod.java,v 1.5 2006/06/19 05:19:37 leonova Exp $
 */
@DataItemName("PersonnelRef.CalcPeriod")
public class CalcPeriod extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.StaffList StaffList;

  private java.util.Date BeginDate;

  private java.util.Date EndDate;

  private java.lang.String PName;

  // Constructors

  /**
   * default constructor
   */
  public CalcPeriod() {
  }

  /**
   * constructor with id
   */
  public CalcPeriod(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.CalcPeriod.StaffList")
  public com.mg.merp.personnelref.model.StaffList getStaffList() {
    return this.StaffList;
  }

  public void setStaffList(
      com.mg.merp.personnelref.model.StaffList PrefStaffList) {
    this.StaffList = PrefStaffList;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.CalcPeriod.BeginDate")
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.CalcPeriod.EndDate")
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date Enddate) {
    this.EndDate = Enddate;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.CalcPeriod.Name")
  public java.lang.String getPName() {
    return this.PName;
  }

  public void setPName(java.lang.String Pname) {
    this.PName = Pname;
  }

}