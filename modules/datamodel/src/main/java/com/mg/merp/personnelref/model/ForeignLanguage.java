/*
 * ForeignLanguage.java
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
 * @version $Id: ForeignLanguage.java,v 1.4 2006/06/19 07:29:16 leonova Exp $
 */
@DataItemName("PersonnelRef.ForeignLanguage")
public class ForeignLanguage extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Name;

  private java.lang.String Okin;

  // Constructors

  /**
   * default constructor
   */
  public ForeignLanguage() {
  }

  /**
   * constructor with id
   */
  public ForeignLanguage(java.lang.Integer Id) {
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

  @DataItemName("PersonnelRef.ForeignLang.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.ForeignLang.Okin")
  public java.lang.String getOkin() {
    return this.Okin;
  }

  public void setOkin(java.lang.String Okin) {
    this.Okin = Okin;
  }

}