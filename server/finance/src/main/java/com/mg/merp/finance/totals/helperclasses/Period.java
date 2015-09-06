/*
 * Period.java
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
package com.mg.merp.finance.totals.helperclasses;


import com.mg.merp.finance.totals.FinanceTotals;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: Period.java,v 1.3 2006/07/05 09:03:17 poroxnenko Exp $
 */
public class Period {
  private int id;
  private FinanceTotals.DatePositionInPeriod datePosition;

  public Period() {
  }

  public Period(int id, FinanceTotals.DatePositionInPeriod datePosition) {
    this.id = id;
    this.datePosition = datePosition;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public FinanceTotals.DatePositionInPeriod getDatePosition() {
    return datePosition;
  }

  public void setDatePosition(FinanceTotals.DatePositionInPeriod datePosition) {
    this.datePosition = datePosition;
  }
}
