/*
 * FamilyStatusKind.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FamilyStatusKind.java,v 1.5 2006/08/08 05:47:48 leonova Exp $
 */
@DataItemName("Reference.FamilyStatusKind")
public class FamilyStatusKind extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String KCode;

  private java.lang.Integer Priority;

  private java.lang.String Okin;

  // Constructors

  /**
   * default constructor
   */
  public FamilyStatusKind() {
  }

  /**
   * constructor with id
   */
  public FamilyStatusKind(java.lang.Integer Id) {
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
  @DataItemName("Reference.Code")
  public java.lang.String getKCode() {
    return this.KCode;
  }

  public void setKCode(java.lang.String KCode) {
    this.KCode = KCode;
  }

  /**
   *
   */
  @DataItemName("Reference.Priority")
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("Reference.Okin")
  public java.lang.String getOkin() {
    return this.Okin;
  }

  public void setOkin(java.lang.String Okin) {
    this.Okin = Okin;
  }

}