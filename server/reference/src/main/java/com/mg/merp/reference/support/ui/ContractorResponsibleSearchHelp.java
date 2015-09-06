/*
 * ContractorResponsibleSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.PartnerEmplServiceLocal;
import com.mg.merp.reference.model.Contractor;

/**
 * Базовый класс для SearchHelp сотрудников партнера
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractorResponsibleSearchHelp.java,v 1.3 2007/04/16 05:34:17 sharapov Exp $
 */
public abstract class ContractorResponsibleSearchHelp extends AbstractSearchHelp {

  protected abstract String[] getContractorContextNames();

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return getContractorContextNames();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    ContractorResponsibleSearchForm form = (ContractorResponsibleSearchForm) UIProducer.produceForm("com/mg/merp/reference/resources/ContractorResponsibleSearchForm.mfd.xml"); //$NON-NLS-1$
    form.addSearchHelpListener(this);

    Contractor contractor = null;
    for (int i = 0; i < getContractorContextNames().length; i++) {
      contractor = (Contractor) getImportContextValue(getContractorContextNames()[i]);
      if (contractor != null)
        break;
    }

    if (contractor != null)
      form.setPartner(contractor);
    else
      throw new ApplicationException(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.EXISTS_CONTRACTOR));

    form.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    PartnerEmplServiceLocal service = (PartnerEmplServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PartnerEmpl"); //$NON-NLS-1$
    MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

}
