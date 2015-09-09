/*
 * StaffCategory.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: StaffCategory.java,v 1.5 2006/06/19 08:01:13 leonova Exp $
 */
@DataItemName("PersonnelRef.StaffCategory")
public class StaffCategory extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String CCode;

  private java.lang.String CName;

  private java.lang.Integer Priority;

  // Constructors

  /**
   * default constructor
   */
  public StaffCategory() {
  }

  /**
   * constructor with id
   */
  public StaffCategory(java.lang.Integer Id) {
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

  @DataItemName("PersonnelRef.StaffCategory.CCode")
  public java.lang.String getCCode() {
    return this.CCode;
  }

  public void setCCode(java.lang.String Ccode) {
    this.CCode = Ccode;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.StaffCategory.CName")
  public java.lang.String getCName() {
    return this.CName;
  }

  public void setCName(java.lang.String Cname) {
    this.CName = Cname;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.StaffCategory.Priority")
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

}