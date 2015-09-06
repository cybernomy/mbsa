/*
 * BinLocationModelItem.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.warehouse.support.ui;

import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.StockBatch;

import java.math.BigDecimal;

/**
 * Информация о секции хранения в партии
 *
 * @author Artem V. Sharapov
 * @version $Id: BinLocationData.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class BinLocationData {

  private BinLocation binLocation;
  private StockBatch stockBatch;
  private BigDecimal quantityInSection;
  private BigDecimal quantityToPerform;


  public BinLocationData() {
  }

  public BinLocationData(BinLocation binLocation, StockBatch stockBatch, BigDecimal quantityInSection, BigDecimal quantityToPerform) {
    this.binLocation = binLocation;
    this.stockBatch = stockBatch;
    this.quantityInSection = quantityInSection;
    this.quantityToPerform = quantityToPerform;
  }

  public BinLocationData(BinLocation binLocation, StockBatch stockBatch, BigDecimal quantityInSection) {
    this(binLocation, stockBatch, quantityInSection, null);
  }

  /**
   * @return the binLocation
   */
  public BinLocation getBinLocation() {
    return this.binLocation;
  }

  /**
   * @param binLocation the binLocation to set
   */
  public void setBinLocation(BinLocation binLocation) {
    this.binLocation = binLocation;
  }

  /**
   * @return the quantity
   */
  public BigDecimal getQuantityInSection() {
    return this.quantityInSection == null ? BigDecimal.ZERO : this.quantityInSection;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantityInSection(BigDecimal quantity) {
    this.quantityInSection = quantity;
  }

  /**
   * @return the quantityToPerform
   */
  public BigDecimal getQuantityToPerform() {
    return this.quantityToPerform;
  }

  /**
   * @param quantityToPerform the quantityToPerform to set
   */
  public void setQuantityToPerform(BigDecimal quantityToPerform) {
    this.quantityToPerform = quantityToPerform;
  }

  /**
   * @return the stockBatch
   */
  public StockBatch getStockBatch() {
    return this.stockBatch;
  }

  /**
   * @param stockBatch the stockBatch to set
   */
  public void setStockBatch(StockBatch stockBatch) {
    this.stockBatch = stockBatch;
  }

}
