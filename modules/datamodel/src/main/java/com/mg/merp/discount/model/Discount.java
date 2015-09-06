/*
 * Discount.java
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
package com.mg.merp.discount.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Discount.java,v 1.6 2006/11/02 15:46:17 safonov Exp $
 */
@DataItemName("Discount.Discount")
public class Discount extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.baiengine.model.Repository Alg;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String DName;

  private java.lang.String Formula;

  // Constructors

  /**
   * default constructor
   */
  public Discount() {
  }

  /**
   * constructor with id
   */
  public Discount(java.lang.Integer Id) {
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
  @DataItemName("Discount.Disc.Alg")
  public com.mg.merp.baiengine.model.Repository getAlg() {
    return this.Alg;
  }

  public void setAlg(
      com.mg.merp.baiengine.model.Repository AlgRepository) {
    this.Alg = AlgRepository;
  }

  /**
   *
   */

  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  public void setFolder(com.mg.merp.core.model.Folder Folder) {
    this.Folder = Folder;
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
  @DataItemName("Discount.Name")
  public java.lang.String getDName() {
    return this.DName;
  }

  public void setDName(java.lang.String Dname) {
    this.DName = Dname;
  }

  /**
   *
   */
  @DataItemName("Discount.Disc.Formula")
  public java.lang.String getFormula() {
    return this.Formula;
  }

  public void setFormula(java.lang.String Formula) {
    this.Formula = Formula;
  }

}