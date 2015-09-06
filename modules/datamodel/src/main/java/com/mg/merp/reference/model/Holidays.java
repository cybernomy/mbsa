/*
 * Holidays.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Holidays.java,v 1.4 2006/08/29 12:51:00 leonova Exp $
 */
@DataItemName("Reference.Holidays")
public class Holidays extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String HName;

  private java.util.Date HDate;

  private java.lang.Integer HDay;

  private java.lang.Integer HMonth;

  private java.lang.Integer HYear;

  // Constructors

  /**
   * default constructor
   */
  public Holidays() {
  }

  /**
   * constructor with id
   */
  public Holidays(java.lang.Integer Id) {
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
  @DataItemName("Reference.Name")
  public java.lang.String getHName() {
    return this.HName;
  }

  public void setHName(java.lang.String HName) {
    this.HName = HName;
  }

  /**
   *
   */
  @DataItemName("Reference.Holidays.Date")
  public java.util.Date getHDate() {
    return this.HDate;
  }

  public void setHDate(java.util.Date HDate) {
    this.HDate = HDate;
  }

  /**
   *
   */
  @DataItemName("Reference.Holidays.HDay")
  public java.lang.Integer getHDay() {
    return this.HDay;
  }

  public void setHDay(java.lang.Integer HDay) {
    this.HDay = HDay;
  }

  /**
   *
   */
  @DataItemName("Reference.Holidays.HMonth")
  public java.lang.Integer getHMonth() {
    return this.HMonth;
  }

  public void setHMonth(java.lang.Integer HMonth) {
    this.HMonth = HMonth;
  }

  /**
   *
   */
  @DataItemName("Reference.Holidays.HYear")
  public java.lang.Integer getHYear() {
    return this.HYear;
  }

  public void setHYear(java.lang.Integer HYear) {
    this.HYear = HYear;
  }

}