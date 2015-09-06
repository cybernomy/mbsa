/*
 * JobRouteSearchHelp.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.merp.manufacture.JobRouteServiceLocal;
import com.mg.merp.manufacture.model.Job;

/**
 * Механизм поиска операций ЗНП
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: JobRouteSearchHelp.java,v 1.3 2007/07/31 06:31:06 safonov Exp $
 */
public class JobRouteSearchHelp extends AbstractSearchHelp {

  /**
   * получить наименование атрибута хранящего ссылку на ЗНП
   *
   * @return наименование атрибута
   */
  protected String getJobPropertyName() {
    return "Job";
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{getJobPropertyName()};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    Job job = (Job) getImportContextValue(getJobPropertyName());
    if (job == null)
      return;
    JobRouteSearchForm form = (JobRouteSearchForm) UIProducer.produceForm("com/mg/merp/manufacture/resources/JobRouteSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    form.execute(job);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    JobRouteServiceLocal service = (JobRouteServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(JobRouteServiceLocal.SERVICE_NAME);
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
