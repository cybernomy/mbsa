/*
 * CashDocumentRest.java
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
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.document.generic.ui.DocumentRest;
import com.mg.merp.reference.model.Contractor;

/**
 * @author leonova
 * @version $Id: CashDocumentRest.java,v 1.3 2006/10/18 06:16:04 leonova Exp $
 */
public class CashDocumentRest extends DocumentRest {

  @DataItemName("Account.CashIn.Company")
  private Contractor companyCode = null;
  /*	@DataItemName("Account.CashIn.Cashier")
      private Contractor companyCode = null;*/
  @DataItemName("Account.CashIn.Acc")
  private AccPlan account = null;


  public AccPlan getAccount() {
    return account;
  }


  public Contractor getCompanyCode() {
    return companyCode;
  }


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.companyCode = null;
    this.account = null;
  }

}
