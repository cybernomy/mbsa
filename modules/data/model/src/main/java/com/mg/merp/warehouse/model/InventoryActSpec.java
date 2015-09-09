/*
 * InventoryActSpec.java
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
 * @version $Id: InventoryActSpec.java,v 1.4 2006/04/13 10:28:32 safonov Exp $
 */
public class InventoryActSpec extends com.mg.merp.document.model.DocSpec
    implements java.io.Serializable {

  // Fields

  private java.math.BigDecimal RealQuantity;

  private java.math.BigDecimal RealSumma;

  private java.math.BigDecimal RealQuantity2;

  // Constructors

  /**
   * default constructor
   */
  public InventoryActSpec() {
  }

  // Property accessors
  @DataItemName("Warehouse.InventoryActSpec.RealQuantity")
  public java.math.BigDecimal getRealQuantity() {
    return this.RealQuantity;
  }

  public void setRealQuantity(java.math.BigDecimal RealQuantity) {
    this.RealQuantity = RealQuantity;
  }

  /**
   *
   */

  @DataItemName("Warehouse.InventoryActSpec.RealSumma")
  public java.math.BigDecimal getRealSumma() {
    return this.RealSumma;
  }

  public void setRealSumma(java.math.BigDecimal RealSumma) {
    this.RealSumma = RealSumma;
  }

  /**
   *
   */

  @DataItemName("Warehouse.InventoryActSpec.RealQuantity2")
  public java.math.BigDecimal getRealQuantity2() {
    return this.RealQuantity2;
  }

  public void setRealQuantity2(java.math.BigDecimal RealQuantity2) {
    this.RealQuantity2 = RealQuantity2;
  }

}