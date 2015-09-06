/*
 * Created on 23.11.2004
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
package com.mg.merp.lbschedule;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class RecomputeTaxesResult implements Serializable {
  public double taxPrice;
  public double taxSum;

  /**
   * @param taxPrice
   * @param taxSum
   */
  public RecomputeTaxesResult(double taxPrice, double taxSum) {
    this.taxPrice = taxPrice;
    this.taxSum = taxSum;
  }
}
