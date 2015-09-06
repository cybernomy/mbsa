/*
 * TariffingCategory.java
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
 * @version $Id: TariffingCategory.java,v 1.5 2006/07/03 13:00:37 leonova Exp $
 */
@DataItemName("PersonnelRef.TariffingCategory")
public class TariffingCategory extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String CCode;

  private java.lang.String CName;

  private TarifCategType CType;

  private java.lang.Integer Priority;

  // Constructors

  /**
   * default constructor
   */
  public TariffingCategory() {
  }

  /**
   * constructor with id
   */
  public TariffingCategory(java.lang.Integer Id) {
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

  @DataItemName("PersonnelRef.TariffingCategory.Code")
  public java.lang.String getCCode() {
    return this.CCode;
  }

  public void setCCode(java.lang.String Ccode) {
    this.CCode = Ccode;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.TariffingCategory.Name")
  public java.lang.String getCName() {
    return this.CName;
  }

  public void setCName(java.lang.String Cname) {
    this.CName = Cname;
  }

  /**
   *
   */

  public TarifCategType getCType() {
    return this.CType;
  }

  public void setCType(TarifCategType Ctype) {
    this.CType = Ctype;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.TariffingCategory.Priority")
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

}