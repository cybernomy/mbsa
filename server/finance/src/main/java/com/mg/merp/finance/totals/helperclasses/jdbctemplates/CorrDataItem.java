/*
 * CorrDataItem.java
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
 * @version $Id: CorrDataItem.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class CorrDataItem {

  public int accId;
  public String accCode;

  public IncomeOutcomeItem sumCur_Nat;

  public BigDecimal incomeNat;
  public BigDecimal outcomeNat;
  public BigDecimal incomeCur;
  public BigDecimal outcomeCur;

  public BigDecimal incomeNatPlan;
  public BigDecimal outcomeNatPlan;
  public BigDecimal incomeCurPlan;
  public BigDecimal outcomeCurPlan;

  CorrDataItem() {
    this.incomeNat = new BigDecimal(0);
    this.outcomeNat = new BigDecimal(0);
    this.incomeCur = new BigDecimal(0);
    this.outcomeCur = new BigDecimal(0);

    this.incomeNatPlan = new BigDecimal(0);
    this.outcomeNatPlan = new BigDecimal(0);
    this.incomeCurPlan = new BigDecimal(0);
    this.outcomeCurPlan = new BigDecimal(0);
  }

  public CorrDataItem(CorrDataItem cdi) {
    this.accId = cdi.accId;
    this.accCode = cdi.accCode;

    this.sumCur_Nat = new IncomeOutcomeItem(cdi.sumCur_Nat.getSumNat(), cdi.sumCur_Nat.getSumNat());

    this.incomeNat = new BigDecimal(cdi.incomeNat.doubleValue());
    this.outcomeNat = new BigDecimal(cdi.outcomeNat.doubleValue());
    this.incomeCur = new BigDecimal(cdi.incomeCur.doubleValue());
    this.outcomeCur = new BigDecimal(cdi.outcomeCur.doubleValue());

    this.incomeNatPlan = new BigDecimal(cdi.incomeNatPlan.doubleValue());
    this.outcomeNatPlan = new BigDecimal(cdi.outcomeNatPlan.doubleValue());
    this.incomeCurPlan = new BigDecimal(cdi.incomeCurPlan.doubleValue());
    this.outcomeCurPlan = new BigDecimal(cdi.outcomeCurPlan.doubleValue());
  }

}
