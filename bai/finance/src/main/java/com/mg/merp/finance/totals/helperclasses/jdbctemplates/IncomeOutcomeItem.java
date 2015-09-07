/*
 * IncomeOutcomeItem.java
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
package com.mg.merp.finance.totals.helperclasses.jdbctemplates;

import java.math.BigDecimal;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: IncomeOutcomeItem.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class IncomeOutcomeItem {
  private BigDecimal sumNat;
  private BigDecimal sumCur;

  public IncomeOutcomeItem() {
  }

  public IncomeOutcomeItem(BigDecimal sumNat, BigDecimal sumCur) {
    this.sumNat = sumNat;
    this.sumCur = sumCur;
  }

  public BigDecimal getSumCur() {
    if (sumCur == null)
      return new BigDecimal(0);
    else
      return sumCur;
  }

  public void setSumCur(BigDecimal sumCur) {
    this.sumCur = sumCur;
  }

  public BigDecimal getSumNat() {
    if (sumNat == null)
      return new BigDecimal(0);
    else
      return sumNat;
  }

  public void setSumNat(BigDecimal sumNat) {
    this.sumNat = sumNat;
  }
}
