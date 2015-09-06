/*
 * DiscountKind.java
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
 * @version $Id: DiscountKind.java,v 1.4 2006/08/28 12:47:15 leonova Exp $
 */
public class DiscountKind extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String DCode;

  private java.lang.String DName;

  private java.math.BigDecimal MinSalaryNumber;

  private java.math.BigDecimal FixedSum;

  private java.util.Date BeginDate;

  private java.math.BigDecimal ActionPeriod;

  private boolean IsAccumulating;

  // Constructors

  /**
   * default constructor
   */
  public DiscountKind() {
  }

  /**
   * constructor with id
   */
  public DiscountKind(int Id) {
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
  @DataItemName("Salary.BigCode")
  public java.lang.String getDCode() {
    return this.DCode;
  }

  public void setDCode(java.lang.String Dcode) {
    this.DCode = Dcode;
  }

  /**
   *
   */
  @DataItemName("Salary.Name")
  public java.lang.String getDName() {
    return this.DName;
  }

  public void setDName(java.lang.String Dname) {
    this.DName = Dname;
  }

  /**
   *
   */
  @DataItemName("Salary.DiscountKind.MinSalaryNumber")
  public java.math.BigDecimal getMinSalaryNumber() {
    return this.MinSalaryNumber;
  }

  public void setMinSalaryNumber(java.math.BigDecimal Minsalarynumber) {
    this.MinSalaryNumber = Minsalarynumber;
  }

  /**
   *
   */
  @DataItemName("Salary.DiscountKind.FixedSum")
  public java.math.BigDecimal getFixedSum() {
    return this.FixedSum;
  }

  public void setFixedSum(java.math.BigDecimal Fixedsum) {
    this.FixedSum = Fixedsum;
  }

  /**
   *
   */
  @DataItemName("Salary.DiscountKind.BeginDate")
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */
  @DataItemName("Salary.DiscountKind.ActionPeriod")
  public java.math.BigDecimal getActionPeriod() {
    return this.ActionPeriod;
  }

  public void setActionPeriod(java.math.BigDecimal Actionperiod) {
    this.ActionPeriod = Actionperiod;
  }

  /**
   *
   */
  @DataItemName("Salary.DiscountKind.IsAccumulating")
  public boolean getIsAccumulating() {
    return this.IsAccumulating;
  }

  public void setIsAccumulating(boolean IsAccumulating) {
    this.IsAccumulating = IsAccumulating;
  }

}