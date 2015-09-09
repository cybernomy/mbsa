/*
 * ConstantValue.java
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ConstantValue.java,v 1.1 2007/08/17 09:19:21 alikaev Exp $
 */
public class ConstantValue extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.baiengine.model.Constant Const;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Val;

  private java.util.Date StartDate;

  // Constructors

  /**
   * default constructor
   */
  public ConstantValue() {
  }

  /**
   * constructor with id
   */
  public ConstantValue(java.lang.Integer Id) {
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

  public com.mg.merp.baiengine.model.Constant getConst() {
    return this.Const;
  }

  public void setConst(com.mg.merp.baiengine.model.Constant AlgConst) {
    this.Const = AlgConst;
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
  @DataItemName("BAi.ConstValue.Value") //$NON-NLS-1$
  public java.lang.String getVal() {
    return this.Val;
  }

  public void setVal(java.lang.String Val) {
    this.Val = Val;
  }

  /**
   *
   */
  @DataItemName("BAi.ConstValue.StartDate") //$NON-NLS-1$
  public java.util.Date getStartDate() {
    return this.StartDate;
  }

  public void setStartDate(java.util.Date Startdate) {
    this.StartDate = Startdate;
  }

}