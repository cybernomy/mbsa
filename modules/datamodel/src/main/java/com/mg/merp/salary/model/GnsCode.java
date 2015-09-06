/*
 * GnsCode.java
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
 * @version $Id: GnsCode.java,v 1.4 2006/06/20 07:36:29 leonova Exp $
 */
@DataItemName("Salary.GnsCode")
public class GnsCode extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Code;

  private java.lang.String Description;

  // Constructors

  /**
   * default constructor
   */
  public GnsCode() {
  }

  /**
   * constructor with id
   */
  public GnsCode(java.lang.Integer Id) {
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
  @DataItemName("Salary.MediumCode")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("Salary.Description")
  public java.lang.String getDescription() {
    return this.Description;
  }

  public void setDescription(java.lang.String Description) {
    this.Description = Description;
  }

}