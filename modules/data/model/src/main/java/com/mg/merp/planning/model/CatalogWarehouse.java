/*
 * CatalogWarehouse.java
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
 * @version $Id: CatalogWarehouse.java,v 1.4 2007/07/30 10:37:30 safonov Exp $
 */
public class CatalogWarehouse extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.warehouse.model.Warehouse warehouse;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.math.BigDecimal SafetyLevel;

  private java.lang.Short MrpDampingDays;

  private java.lang.Short DemandFenceDays;

  private java.lang.Short OrderIntervalDays;

  // Constructors

  /**
   * default constructor
   */
  public CatalogWarehouse() {
  }

  /**
   * constructor with id
   */
  public CatalogWarehouse(java.lang.Integer Id) {
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
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  @DataItemName("Planning.CatWarehouse.Contractor")
  public com.mg.merp.warehouse.model.Warehouse getWarehouse() {
    return this.warehouse;
  }

  public void setWarehouse(com.mg.merp.warehouse.model.Warehouse Contractor) {
    this.warehouse = Contractor;
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
  @DataItemName("Planning.CatWarehouse.SafetyLevel")
  public java.math.BigDecimal getSafetyLevel() {
    return this.SafetyLevel;
  }

  public void setSafetyLevel(java.math.BigDecimal SafetyLevel) {
    this.SafetyLevel = SafetyLevel;
  }

  /**
   *
   */
  @DataItemName("Planning.CatWarehouse.MrpDampingDays")
  public java.lang.Short getMrpDampingDays() {
    return this.MrpDampingDays;
  }

  public void setMrpDampingDays(java.lang.Short MrpDampingDays) {
    this.MrpDampingDays = MrpDampingDays;
  }

  /**
   *
   */
  @DataItemName("Planning.CatWarehouse.DemandFenceDays")
  public java.lang.Short getDemandFenceDays() {
    return this.DemandFenceDays;
  }

  public void setDemandFenceDays(java.lang.Short DemandFenceDays) {
    this.DemandFenceDays = DemandFenceDays;
  }

  /**
   *
   */
  @DataItemName("Planning.CatWarehouse.OrderIntervalDays")
  public java.lang.Short getOrderIntervalDays() {
    return this.OrderIntervalDays;
  }

  public void setOrderIntervalDays(java.lang.Short OrderIntervalDays) {
    this.OrderIntervalDays = OrderIntervalDays;
  }

}