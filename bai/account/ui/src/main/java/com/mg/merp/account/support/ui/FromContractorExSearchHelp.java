/*
 * FromContractorSearchHelpEx.java
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
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.document.support.ui.FromContractorSearchHelp;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.model.Partner;

/**
 * Поисковик контрагента для поля документа "От кого" и установка банковского счета по умолчанию
 *
 * @author Artem V. Sharapov
 * @version $Id: FromContractorExSearchHelp.java,v 1.1 2007/11/08 14:52:39 sharapov Exp $
 */
public class FromContractorExSearchHelp extends FromContractorSearchHelp {

  private final String PAYER_BANK_EXPORT = "PayerBankReq"; //$NON-NLS-1$
  private PartnerServiceLocal partnerService = (PartnerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PartnerServiceLocal.SERVICE_NAME);


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{PAYER_BANK_EXPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    Partner partner = (Partner) event.getItems()[0];
    ApplicationDictionaryLocator.locate().getBusinessService(PartnerServiceLocal.SERVICE_NAME);
    if (partner != null)
      setExportContextValue(PAYER_BANK_EXPORT, partnerService.getDefaultBankAccount(partner));
  }

}
