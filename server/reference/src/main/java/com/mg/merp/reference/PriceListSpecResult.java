/*
 * PriceListSpecResult.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import java.io.Serializable;

/**
 * @author pashistova
 *
 *         TODO To change the template for this generated type comment go to Window - Preferences -
 *         Java - Code Style - Code Templates
 */
public class PriceListSpecResult implements Serializable {
  public int catalogId;
  public int priceListSpecId;
  public double price;

  public PriceListSpecResult(int catalogId, int priceListSpecId, double price) {
    this.catalogId = catalogId;
    this.priceListSpecId = priceListSpecId;
    this.price = price;
  }
}
