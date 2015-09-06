/*
 * Analytics.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Analytics.java,v 1.5 2006/06/22 12:53:01 leonova Exp $
 */
@DataItemName("Finance.Analytics")
public class Analytics extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.finance.model.Account FinAcc;

  private com.mg.merp.finance.model.Analytics Parent;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Code;

  private java.lang.String UpCode;

  private java.lang.String AnlName;

  private short AnlLevel;

  // Constructors

  /**
   * default constructor
   */
  public Analytics() {
  }

  /**
   * constructor with id
   */
  public Analytics(java.lang.Integer Id) {
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

  public com.mg.merp.finance.model.Account getFinAcc() {
    return this.FinAcc;
  }

  public void setFinAcc(com.mg.merp.finance.model.Account Finaccount) {
    this.FinAcc = Finaccount;
  }

  /**
   *
   */
  @DataItemName("Finance.AccAnl.Parent")
  public com.mg.merp.finance.model.Analytics getParent() {
    return this.Parent;
  }

  public void setParent(com.mg.merp.finance.model.Analytics Finanl) {
    this.Parent = Finanl;
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
  @DataItemName("Finance.AccAnl.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */

  public java.lang.String getUpCode() {
    return this.UpCode;
  }

  public void setUpCode(java.lang.String Upcode) {
    this.UpCode = Upcode;
  }

  /**
   *
   */
  @DataItemName("Finance.AccAnl.AnlName")
  public java.lang.String getAnlName() {
    return this.AnlName;
  }

  public void setAnlName(java.lang.String Anlname) {
    this.AnlName = Anlname;
  }

  /**
   *
   */

  public short getAnlLevel() {
    return this.AnlLevel;
  }

  public void setAnlLevel(short Anllevel) {
    this.AnlLevel = Anllevel;
  }
}