/*
 * FeatureVal.java
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
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: FeatureVal.java,v 1.4 2007/01/18 16:11:32 safonov Exp $
 */
@DataItemName("Core.FeatureVal")
public class FeatureVal extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.Feature Feature;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Val;

  // Constructors

  /**
   * default constructor
   */
  public FeatureVal() {
  }

  /**
   * constructor with id
   */
  public FeatureVal(int Id) {
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

  public com.mg.merp.core.model.Feature getFeature() {
    return this.Feature;
  }

  public void setFeature(com.mg.merp.core.model.Feature Feature) {
    this.Feature = Feature;
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
  @DataItemName("Core.Feature.Val.Name")
  public java.lang.String getVal() {
    return this.Val;
  }

  public void setVal(java.lang.String Val) {
    this.Val = Val;
  }

}