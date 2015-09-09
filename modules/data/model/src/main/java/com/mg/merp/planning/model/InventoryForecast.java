/*
 * InventoryForecast.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InventoryForecast.java,v 1.4 2006/06/20 05:11:51 leonova Exp $
 */
@DataItemName("Planning.InventoryForecast")
public class InventoryForecast extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String InventoryForecastCode;

  private java.lang.String Description;

  // Constructors

  /**
   * default constructor
   */
  public InventoryForecast() {
  }

  /**
   * constructor with id
   */
  public InventoryForecast(java.lang.Integer Id) {
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
  @DataItemName("Planning.Code")
  public java.lang.String getInventoryForecastCode() {
    return this.InventoryForecastCode;
  }

  public void setInventoryForecastCode(java.lang.String InventoryForecastCode) {
    this.InventoryForecastCode = InventoryForecastCode;
  }

  /**
   *
   */
  @DataItemName("Planning.Description")
  public java.lang.String getDescription() {
    return this.Description;
  }

  public void setDescription(java.lang.String Description) {
    this.Description = Description;
  }
}