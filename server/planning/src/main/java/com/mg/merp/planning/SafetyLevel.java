/*
 * SafetyLevel.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.planning;

import com.mg.merp.planning.model.GenericItem;
import com.mg.merp.reference.model.Catalog;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Элемент страхового запаса
 *
 * @author Oleg V. Safonov
 * @version $Id: SafetyLevel.java,v 1.1 2007/07/30 10:37:51 safonov Exp $
 */
public class SafetyLevel implements Serializable {
  private BigDecimal quantity;
  private Catalog catalog;
  private GenericItem genericItem;

  public SafetyLevel(BigDecimal quantity, Catalog catalog, GenericItem genericItem) {
    super();
    this.quantity = quantity;
    this.catalog = catalog;
    this.genericItem = genericItem;
  }

  /**
   * позиция каталога
   *
   * @return the catalog
   */
  public Catalog getCatalog() {
    return catalog;
  }

  /**
   * обобщенный товар
   *
   * @return the genericItem
   */
  public GenericItem getGenericItem() {
    return genericItem;
  }

  /**
   * страховой запас
   *
   * @return the quantity
   */
  public BigDecimal getQuantity() {
    return quantity;
  }

}
