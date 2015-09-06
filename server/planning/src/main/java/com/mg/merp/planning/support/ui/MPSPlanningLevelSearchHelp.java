/*
 * MPSPlanningLevelSearchHelp.java
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
package com.mg.merp.planning.support.ui;

import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.mfreference.PlanningLevelBucketServiceLocal;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.support.ui.PlanningLevelSearchHelp;
import com.mg.merp.planning.support.Messages;

import java.util.Date;

/**
 * @author Oleg V. Safonov
 * @version $Id: MPSPlanningLevelSearchHelp.java,v 1.1 2007/07/30 10:36:31 safonov Exp $
 */
public class MPSPlanningLevelSearchHelp extends PlanningLevelSearchHelp {

  private static final String PLANNING_DATE = "PlanningDate";

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{PLANNING_DATE};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{PLANNING_DATE};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    Date planningDate = (Date) getImportContextValue(PLANNING_DATE);
    //не устанавливаем дату если планируемая пустая
    if (planningDate == null)
      return;

    Date newPlanningDate = ((PlanningLevelBucketServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PlanningLevelBucketServiceLocal.SERVICE_NAME))
        .nearestBucketStartDate(((PlanningLevel) event.getItems()[0]).getId(), planningDate);
    if (newPlanningDate.compareTo(planningDate) != 0) {
      setExportContextValue(PLANNING_DATE, newPlanningDate);
      UIUtils.showAlert(Alert.MessageType.INFORMATION_MESSAGE, null, Messages.getInstance().getMessage(Messages.CHANGE_PLANNING_DATE)
          , com.mg.framework.support.Messages.getInstance().getMessage(com.mg.framework.support.Messages.OK_BUTTON_TEXT), null);
    }
  }

}
