/*
 * AccGroupSearchHelp.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.account.AccGroupServiceLocal;
import com.mg.merp.account.model.AccKind;

/**
 * @author leonova
 * @version $Id: AccGroupSearchHelp.java,v 1.3 2006/10/13 12:34:52 leonova Exp $
 */
public class AccGroupSearchHelp extends AbstractSearchHelp {

  protected String accKindContextName = "AccKind";

  @Override
  protected String[] defineImportContext() {
    return new String[]{accKindContextName};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    AccGroupSearchForm form = (AccGroupSearchForm) UIProducer.produceForm("com/mg/merp/account/resources/AccGroupSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    form.setAccKind((AccKind) getImportContextValue(accKindContextName));
    form.run(UIUtils.isModalMode());
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    AccGroupServiceLocal service = (AccGroupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AccGroup");
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
