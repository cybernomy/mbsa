/*
 * CalcTaxesKind.java
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
 * @version $Id: CalcTaxesKind.java,v 1.5 2006/11/02 16:20:44 safonov Exp $
 */
@DataItemName("Reference.CalcTaxesKind")
public class CalcTaxesKind extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Code;

  private java.lang.String KName;

  private java.util.Set<com.mg.merp.reference.model.CalcTaxesLink> taxLinks;

  // Constructors

  /**
   * default constructor
   */
  public CalcTaxesKind() {
  }

  /**
   * constructor with id
   */
  public CalcTaxesKind(java.lang.Integer Id) {
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
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("Reference.Name")
  public java.lang.String getKName() {
    return this.KName;
  }

  public void setKName(java.lang.String KName) {
    this.KName = KName;
  }

  /**
   *
   */

  public java.util.Set<com.mg.merp.reference.model.CalcTaxesLink> getTaxLinks() {
    return this.taxLinks;
  }

  public void setTaxLinks(java.util.Set<com.mg.merp.reference.model.CalcTaxesLink> SetOfCalcTaxesLink) {
    this.taxLinks = SetOfCalcTaxesLink;
  }

}