/*
 * PayerBankAccountSearchHelp.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.support.ui.BankAccountSearchHelp;

/**
 * Поисковик банковского счета плательщика (для контрагента "От кого")
 *
 * @author Artem V. Sharapov
 * @version $Id: PayerBankAccountSearchHelp.java,v 1.3 2007/11/12 06:51:28 sharapov Exp $
 */
public class PayerBankAccountSearchHelp extends BankAccountSearchHelp {

  private final String CONTRACTOR_CONTEXT_NAME = "fromCode"; //$NON-NLS-1$
  private final String BANK_ACCOUNT_EXPORT = "payerBankAccount"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.BankAccountSearchHelp#getContractorContextName()
   */
  @Override
  protected String getContractorContextName() {
    return CONTRACTOR_CONTEXT_NAME;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{BANK_ACCOUNT_EXPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    BankAccount bankAccount = (BankAccount) event.getItems()[0];
    if (bankAccount != null)
      setExportContextValue(BANK_ACCOUNT_EXPORT, bankAccount.getAccount());
  }

}
