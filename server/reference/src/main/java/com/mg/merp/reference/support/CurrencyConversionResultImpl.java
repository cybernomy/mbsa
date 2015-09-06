/*
 * CurrencyConversionResultImpl.java
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
package com.mg.merp.reference.support;

import com.mg.merp.reference.CurrencyConversionResult;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Oleg V. Safonov
 * @version $Id: CurrencyConversionResultImpl.java,v 1.1 2007/04/13 11:39:04 safonov Exp $
 */
public class CurrencyConversionResultImpl implements CurrencyConversionResult, Serializable {
  private BigDecimal value;
  private BigDecimal rate;
  private boolean direct;

  public CurrencyConversionResultImpl(BigDecimal value, BigDecimal rate, boolean direct) {
    super();
    this.value = value;
    this.rate = rate;
    this.direct = direct;
  }

  /**
   * @return the direct
   */
  public boolean isDirect() {
    return direct;
  }

  /**
   * @return the rate
   */
  public BigDecimal getRate() {
    return rate;
  }

  /**
   * @return the value
   */
  public BigDecimal getAmount() {
    return value;
  }

}
