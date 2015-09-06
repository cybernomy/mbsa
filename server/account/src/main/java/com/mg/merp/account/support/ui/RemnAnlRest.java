/*
 * RemnAnlRest.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.reference.model.Currency;


/**
 * @author leonova
 * @version $Id: RemnAnlRest.java,v 1.4 2008/02/21 10:20:28 alikaev Exp $
 */
public class RemnAnlRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("Account.RemnAnl.AnlPlan1")
  private AnlPlan anlCode1 = null;
  @DataItemName("Account.RemnAnl.AnlPlan2")
  private AnlPlan anlCode2 = null;
  @DataItemName("Account.RemnAnl.AnlPlan3")
  private AnlPlan anlCode3 = null;
  @DataItemName("Account.RemnAnl.AnlPlan4")
  private AnlPlan anlCode4 = null;
  @DataItemName("Account.RemnAnl.AnlPlan5")
  private AnlPlan anlCode5 = null;
  private AccPlan AccPlan = null;
  private Currency currencyCode = null;
  private Period period1 = null;
  private Period period2 = null;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.AccPlan = null;
    this.anlCode1 = null;
    this.anlCode2 = null;
    this.anlCode3 = null;
    this.anlCode4 = null;
    this.anlCode5 = null;
    this.currencyCode = null;
    this.period1 = null;
    this.period2 = null;
  }


  public AnlPlan getAnlCode1() {
    return anlCode1;
  }


  public AnlPlan getAnlCode2() {
    return anlCode2;
  }


  public AnlPlan getAnlCode3() {
    return anlCode3;
  }


  public AnlPlan getAnlCode4() {
    return anlCode4;
  }


  public AnlPlan getAnlCode5() {
    return anlCode5;
  }


  public AccPlan getAccCode() {
    return AccPlan;
  }


  public Currency getCurrencyCode() {
    return currencyCode;
  }


  public Period getPeriod1() {
    return period1;
  }


  public Period getPeriod2() {
    return period2;
  }

}
