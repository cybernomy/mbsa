/*
 * DefaultLegacySearchHelp.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacySearchHelp.java,v 1.6 2009/02/09 14:33:08 safonov Exp $
 */
public abstract class DefaultLegacySearchHelp extends AbstractSearchHelp {

  protected abstract String getServiceName();

  protected String getBrowseFormName() {
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch(PersistentObject entity) throws Exception {
    DataBusinessObjectService<?, ?> service = (DataBusinessObjectService<?, ?>) ApplicationDictionaryLocator.locate().getBusinessService(getServiceName());
    SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(service, getBrowseFormName());
    form.addSearchHelpListener(this);
    form.setTargetEntity(entity);
    form.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  protected void doView(PersistentObject entity) {
    if (entity == null)
      throw new IllegalArgumentException("Entity is null");

    DataBusinessObjectService<PersistentObject, Serializable> service = (DataBusinessObjectService<PersistentObject, Serializable>) ApplicationDictionaryLocator.locate().getBusinessService(getServiceName());
    MaintenanceHelper.view(service, (Serializable) entity.getPrimaryKey(), null, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

}
