/*
 * Created on 23.12.2004
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
 */
package com.mg.merp.account;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class AccBatchSpecParam implements Serializable {
  public double quan;
  public double price;
  public double clearprice;
  public String catalogCode;
  public String catalogName;

  public AccBatchSpecParam() {
    super();
  }

  public AccBatchSpecParam(double quan, double price, double clearprice,
                           String catalogCode, String catalogName) {
    super();
    this.quan = quan;
    this.price = price;
    this.clearprice = clearprice;
    this.catalogCode = catalogCode;
    this.catalogName = catalogName;
  }
}
