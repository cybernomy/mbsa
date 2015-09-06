/*
 * MinSalary.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: MinSalary.java,v 1.4 2006/08/28 12:47:15 leonova Exp $
 */
public class MinSalary extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Date BeginDate;

  private java.math.BigDecimal MinSalary;

  // Constructors

  /**
   * default constructor
   */
  public MinSalary() {
  }

  /**
   * constructor with id
   */
  public MinSalary(int Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("Salary.MinSal.BeginDate")
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */
  @DataItemName("Salary.MinSal.MinSalary")
  public java.math.BigDecimal getMinSalary() {
    return this.MinSalary;
  }

  public void setMinSalary(java.math.BigDecimal Minsalary) {
    this.MinSalary = Minsalary;
  }

}