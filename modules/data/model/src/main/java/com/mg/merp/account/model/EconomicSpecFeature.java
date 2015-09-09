/*
 * EconomicSpecFeature.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: EconomicSpecFeature.java,v 1.4 2006/03/30 11:22:13 safonov Exp $
 */
public class EconomicSpecFeature extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String UpCode;

  private java.lang.String Code;

  private java.lang.String name;

  // Constructors

  /**
   * default constructor
   */
  public EconomicSpecFeature() {
  }

  /**
   * constructor with id
   */
  public EconomicSpecFeature(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */

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

  public java.lang.String getUpCode() {
    return this.UpCode;
  }

  public void setUpCode(java.lang.String Upcode) {
    this.UpCode = Upcode;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpecFeat.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpecFeat.Name")
  public java.lang.String getName() {
    return this.name;
  }

  public void setName(java.lang.String Fname) {
    this.name = Fname;
  }
}