/*
 * BinTypeCapacityByProduct.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: BinTypeCapacityByProduct.java,v 1.2 2005/07/13 04:55:14 pashistova Exp $
 */
@DataItemName("Warehouse.BinTypeCapacityByProduct")
public class BinTypeCapacityByProduct extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.Measure Measure;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.warehouse.model.BinLocationType Type;

  private java.math.BigDecimal MaxQuantity;

  // Constructors

  /**
   * default constructor
   */
  public BinTypeCapacityByProduct() {
  }

  /**
   * constructor with id
   */
  public BinTypeCapacityByProduct(int Id) {
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
  public com.mg.merp.reference.model.Measure getMeasure() {
    return this.Measure;
  }

  public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
    this.Measure = Measure;
  }

  /**
   *
   */

  @DataItemName("Warehouse.BinTypeCapacByProd.Catalog")
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
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

  public com.mg.merp.warehouse.model.BinLocationType getType() {
    return this.Type;
  }

  public void setType(
      com.mg.merp.warehouse.model.BinLocationType BinLocationType) {
    this.Type = BinLocationType;
  }

  /**
   *
   */

  @DataItemName("Warehouse.BinTypeCapacByProd.MaxQuantity")
  public java.math.BigDecimal getMaxQuantity() {
    return this.MaxQuantity;
  }

  public void setMaxQuantity(java.math.BigDecimal MaxQuantity) {
    this.MaxQuantity = MaxQuantity;
  }

}