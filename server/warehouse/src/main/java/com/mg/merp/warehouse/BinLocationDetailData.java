/*
 * BinLocationDetailData.java
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
package com.mg.merp.warehouse;

import com.mg.framework.utils.StringUtils;

import java.math.BigDecimal;

/**
 * Информация о секции хранения в партии
 *
 * @author Artem V. Sharapov
 * @version $Id: BinLocationDetailData.java,v 1.1 2008/05/30 12:39:54 sharapov Exp $
 */
public class BinLocationDetailData {

  private String binLocationCode;
  private BigDecimal quantity;


  /**
   * @param binLocationCode
   * @param quantity
   */
  public BinLocationDetailData(String binLocationCode, BigDecimal quantity) {
    setBinLocationCode(binLocationCode);
    setQuantity(quantity);
  }

  /**
   * @return the binLocationCode
   */
  public String getBinLocationCode() {
    return this.binLocationCode;
  }

  /**
   * @param binLocationCode the binLocationCode to set
   */
  public void setBinLocationCode(String binLocationCode) {
    if (!StringUtils.stringNullOrEmpty(binLocationCode))
      this.binLocationCode = binLocationCode.trim();
  }

  /**
   * @return the quantity
   */
  public BigDecimal getQuantity() {
    return this.quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(BigDecimal quantity) {
    if (quantity == null)
      this.quantity = BigDecimal.ZERO;
    else
      this.quantity = quantity;
  }

}
