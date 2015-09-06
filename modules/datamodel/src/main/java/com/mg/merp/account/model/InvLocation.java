/*
 * InvLocation.java
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
 * @version $Id: InvLocation.java,v 1.5 2006/06/08 13:03:33 leonova Exp $
 */
@DataItemName("Account.InvLocation")
public class InvLocation extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String UpCode;

  private java.lang.String Code;

  private java.lang.String IlName;

  // Constructors

  /**
   * default constructor
   */
  public InvLocation() {
  }

  /**
   * constructor with id
   */
  public InvLocation(java.lang.Integer Id) {
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

  public java.lang.String getUpCode() {
    return this.UpCode;
  }

  public void setUpCode(java.lang.String Upcode) {
    this.UpCode = Upcode;
  }

  /**
   *
   */
  @DataItemName("Account.InvLocation.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("Account.InvLocation.Name")
  public java.lang.String getIlName() {
    return this.IlName;
  }

  public void setIlName(java.lang.String Ilname) {
    this.IlName = Ilname;
  }
}