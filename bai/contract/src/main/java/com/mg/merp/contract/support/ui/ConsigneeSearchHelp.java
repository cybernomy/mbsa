/*
 * ConsigneeSearchHelp.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.support.ui.PartnerSearchHelp;

/**
 * Механизм поиска "Грузополучателя" с установкой "Банковского счета грузополучателя"
 *
 * @author Artem V. Sharapov
 * @version $Id: ConsigneeSearchHelp.java,v 1.1 2008/09/22 10:09:34 sharapov Exp $
 */
public class ConsigneeSearchHelp extends PartnerSearchHelp {

  public static final String CONSIGNEE_BANK_EXPORT = "ConsigneeBankReq"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{CONSIGNEE_BANK_EXPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    Partner partner = (Partner) event.getItems()[0];
    if (partner != null)
      setExportContextValue(CONSIGNEE_BANK_EXPORT, ((PartnerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PartnerServiceLocal.SERVICE_NAME)).getDefaultBankAccount(partner));
  }
}