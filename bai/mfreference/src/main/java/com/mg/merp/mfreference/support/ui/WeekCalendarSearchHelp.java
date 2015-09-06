/*
 * WeekCalendarSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.mfreference.WeekCalendarServiceLocal;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WeekCalendarSearchHelp.java,v 1.3 2006/10/06 13:29:21 leonova Exp $
 */
public class WeekCalendarSearchHelp extends AbstractSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    SearchHelpForm form = (SearchHelpForm) UIProducer.produceForm("com/mg/merp/mfreference/resources/WeekCalendarSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    form.run(UIUtils.isModalMode());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doView(PersistentObject entity) {
    WeekCalendarServiceLocal service = (WeekCalendarServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/WeekCalendar");
    MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }


}