/*
 * IncludedFee.java
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
 * @version $Id: IncludedFee.java,v 1.4 2006/09/08 07:12:28 leonova Exp $
 */
public class IncludedFee extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.salary.model.FeeRef IncludedFee;

  private com.mg.merp.salary.model.FeeRef FeeRef;

  private com.mg.merp.core.model.SysClient SysClient;

  private DoubleSumSign SumSign;

  // Constructors

  /**
   * default constructor
   */
  public IncludedFee() {
  }

  /**
   * constructor with id
   */
  public IncludedFee(java.lang.Integer Id) {
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

  public com.mg.merp.salary.model.FeeRef getIncludedFee() {
    return this.IncludedFee;
  }

  public void setIncludedFee(com.mg.merp.salary.model.FeeRef SalFeeRef) {
    this.IncludedFee = SalFeeRef;
  }

  /**
   *
   */
  @DataItemName("Salary.FeeIncluded.FeeRef")
  public com.mg.merp.salary.model.FeeRef getFeeRef() {
    return this.FeeRef;
  }

  public void setFeeRef(com.mg.merp.salary.model.FeeRef SalFeeRef_1) {
    this.FeeRef = SalFeeRef_1;
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
  @DataItemName("Salary.FeeIncluded.SumSign")
  public DoubleSumSign getSumSign() {
    return this.SumSign;
  }

  public void setSumSign(DoubleSumSign SumSign) {
    this.SumSign = SumSign;
  }

}