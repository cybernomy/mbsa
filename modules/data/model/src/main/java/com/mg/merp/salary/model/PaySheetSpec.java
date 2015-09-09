/*
 * PaySheetSpec.java
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
 * @version $Id: PaySheetSpec.java,v 1.5 2006/11/02 16:24:16 safonov Exp $
 */
public class PaySheetSpec extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.PositionFill PositionFill;

  private com.mg.merp.salary.model.PaySheet PaySheet;

  private java.math.BigDecimal SummaFull;

  private java.math.BigDecimal SummaPaidOut;

  private java.math.BigDecimal SummaDeposited;

  private java.math.BigDecimal SummaRest;

  // Constructors

  /**
   * default constructor
   */
  public PaySheetSpec() {
  }

  /**
   * constructor with id
   */
  public PaySheetSpec(java.lang.Integer Id) {
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
    /* @DataItemName ("Salary.PaySheet.Position") */
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

  public com.mg.merp.salary.model.PaySheet getPaySheet() {
    return this.PaySheet;
  }

  public void setPaySheet(com.mg.merp.salary.model.PaySheet SalPaySheet) {
    this.PaySheet = SalPaySheet;
  }

  /**
   *
   */
  @DataItemName("Salary.PaySheetSpec.SummaFull")
  public java.math.BigDecimal getSummaFull() {
    return this.SummaFull;
  }

  public void setSummaFull(java.math.BigDecimal SummaFull) {
    this.SummaFull = SummaFull;
  }

  /**
   *
   */
  @DataItemName("Salary.PaySheetSpec.SummaPaidOut")
  public java.math.BigDecimal getSummaPaidOut() {
    return this.SummaPaidOut;
  }

  public void setSummaPaidOut(java.math.BigDecimal SummaPaidOut) {
    this.SummaPaidOut = SummaPaidOut;
  }

  /**
   *
   */
  @DataItemName("Salary.PaySheetSpec.SummaDeposited")
  public java.math.BigDecimal getSummaDeposited() {
    return this.SummaDeposited;
  }

  public void setSummaDeposited(java.math.BigDecimal SummaDeposited) {
    this.SummaDeposited = SummaDeposited;
  }

  /**
   *
   */
  @DataItemName("Salary.PaySheetSpec.SummaRest")
  public java.math.BigDecimal getSummaRest() {
    return this.SummaRest;
  }

  public void setSummaRest(java.math.BigDecimal SummaRest) {
    this.SummaRest = SummaRest;
  }

}