/*
 * FamilyRelation.java
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
 * @version $Id: FamilyRelation.java,v 1.4 2006/05/30 05:38:29 leonova Exp $
 */
@DataItemName("Reference.FamilyRelation")
public class FamilyRelation extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String RCode;

  private java.lang.Integer Priority;

  private java.lang.String Okin;

  // Constructors

  /**
   * default constructor
   */
  public FamilyRelation() {
  }

  /**
   * constructor with id
   */
  public FamilyRelation(int Id) {
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
  @DataItemName("Reference.BigCode")
  public java.lang.String getRCode() {
    return this.RCode;
  }

  public void setRCode(java.lang.String RCode) {
    this.RCode = RCode;
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