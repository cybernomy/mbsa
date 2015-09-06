/*
 * ReadyDocSpec.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support;

import java.math.BigDecimal;

/**
 * @author Oleg V. Safonov
 * @version $Id: ReadyDocSpec.java,v 1.1 2006/03/01 15:03:50 safonov Exp $
 */
public class ReadyDocSpec {
  public Integer docSpecId;
  public BigDecimal readySum;
  public BigDecimal readyQuantity1;
  public BigDecimal readyQuantity2;

  public ReadyDocSpec(Integer docSpecId, BigDecimal readySum, BigDecimal readyQuantity1, BigDecimal readyQuantity2) {
    this.docSpecId = docSpecId;
    this.readySum = readySum;
    this.readyQuantity1 = readyQuantity1;
    this.readyQuantity2 = readyQuantity2;
  }
}
