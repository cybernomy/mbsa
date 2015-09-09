/*
 * SetOfGood.java
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
 * @version $Id: SetOfGood.java,v 1.3 2006/05/31 05:47:02 leonova Exp $
 */
@DataItemName("Reference.SetOfGood")
public class SetOfGood extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.Catalog CatalogGood;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Catalog CatalogComponent;

  private java.math.BigDecimal Quantity;

  private java.math.BigDecimal PriceRelate;

  // Constructors

  /**
   * default constructor
   */
  public SetOfGood() {
  }

  /**
   * constructor with id
   */
  public SetOfGood(int Id) {
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
  public com.mg.merp.reference.model.Catalog getCatalogGood() {
    return this.CatalogGood;
  }

  public void setCatalogGood(com.mg.merp.reference.model.Catalog CatalogGood) {
    this.CatalogGood = CatalogGood;
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

  public com.mg.merp.reference.model.Catalog getCatalogComponent() {
    return this.CatalogComponent;
  }

  public void setCatalogComponent(
      com.mg.merp.reference.model.Catalog CatalogComponent) {
    this.CatalogComponent = CatalogComponent;
  }

  /**
   *
   */
  @DataItemName("Reference.SetOfGood.Quantity")
  public java.math.BigDecimal getQuantity() {
    return this.Quantity;
  }

  public void setQuantity(java.math.BigDecimal Quantity) {
    this.Quantity = Quantity;
  }

  /**
   *
   */
  @DataItemName("Reference.SetOfGood.Percent")
  public java.math.BigDecimal getPriceRelate() {
    return this.PriceRelate;
  }

  public void setPriceRelate(java.math.BigDecimal PriceRelate) {
    this.PriceRelate = PriceRelate;
  }

}