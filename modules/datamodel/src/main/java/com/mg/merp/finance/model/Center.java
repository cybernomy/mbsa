/*
 * Center.java
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
 * @version $Id: Center.java,v 1.5 2006/10/28 07:42:00 leonova Exp $
 */
public class Center extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.Integer Parent;

  private java.lang.String Code;

  private java.lang.String UpCode;

  private java.lang.String Name;

  private CenterKind Kind;

  // Constructors

  /**
   * default constructor
   */
  public Center() {
  }

  /**
   * constructor with id
   */
  public Center(java.lang.Integer Id) {
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

  public java.lang.Integer getParent() {
    return this.Parent;
  }

  public void setParent(java.lang.Integer ParentId) {
    this.Parent = ParentId;
  }

  /**
   *
   */
  @DataItemName("Finance.Center.Code")
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
  @DataItemName("Finance.Center.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Sname) {
    this.Name = Sname;
  }

  /**
   *
   */

  public CenterKind getKind() {
    return this.Kind;
  }

  public void setKind(CenterKind Kind) {
    this.Kind = Kind;
  }

}