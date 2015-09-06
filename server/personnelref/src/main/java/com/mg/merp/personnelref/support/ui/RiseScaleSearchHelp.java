/*
 * RiseScaleSearchHelp.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.personnelref.RiseScaleServiceLocal;
import com.mg.merp.personnelref.model.RiseScale;

/**
 * Поисковик сущностей "Шкала надбавки"
 *
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: RiseScaleSearchHelp.java,v 1.3 2007/07/09 08:07:46 sharapov Exp $
 */
public class RiseScaleSearchHelp extends AbstractSearchHelp {

  private final String FORM_NAME = "com/mg/merp/personnelref/resources/RiseScaleSearchForm.mfd.xml"; //$NON-NLS-1$

  private final String RISE_EXPORT = "Rise"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    RiseScaleSearchForm searchForm = (RiseScaleSearchForm) UIProducer.produceForm(FORM_NAME);
    searchForm.addSearchHelpListener(this);
    searchForm.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{RISE_EXPORT};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    RiseScale riseScale = (RiseScale) event.getItems()[0];
    if (riseScale != null)
      setExportContextValue(RISE_EXPORT, riseScale.getRise());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    MaintenanceHelper.view((RiseScaleServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(RiseScaleServiceLocal.LOCAL_SERVICE_NAME), (Integer) entity.getPrimaryKey(), null, null);
  }

}

