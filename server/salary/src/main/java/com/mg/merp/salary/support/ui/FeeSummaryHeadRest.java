/*
 * FeeSummaryHeadRest.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.generic.ui.GoodsDocumentRest;
import com.mg.merp.personnelref.model.CalcPeriod;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.salary.model.PayRoll;

/**
 * @author leonova
 * @version $Id: FeeSummaryHeadRest.java,v 1.4 2008/06/07 12:30:46 alikaev Exp $
 */
public class FeeSummaryHeadRest extends GoodsDocumentRest {

  @DataItemName("Salary.FeeSummaryHead.PayRoll")
  private PayRoll payRollNumber = null;
  private CalcPeriod calcPeriod = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode1 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode2 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode3 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode4 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode5 = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.payRollNumber = null;
    this.calcPeriod = null;
    this.anlCode1 = null;
    this.anlCode2 = null;
    this.anlCode3 = null;
    this.anlCode4 = null;
    this.anlCode5 = null;
  }

  public CostsAnl getAnlCode1() {
    return anlCode1;
  }

  public CostsAnl getAnlCode2() {
    return anlCode2;
  }

  public CostsAnl getAnlCode3() {
    return anlCode3;
  }

  public CostsAnl getAnlCode4() {
    return anlCode4;
  }

  public CostsAnl getAnlCode5() {
    return anlCode5;
  }

  public CalcPeriod getCalcPeriod() {
    return calcPeriod;
  }

  public PayRoll getPayRollNumber() {
    return payRollNumber;
  }
}
