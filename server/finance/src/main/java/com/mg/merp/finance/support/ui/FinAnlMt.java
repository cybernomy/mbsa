/*
 * FinAnlMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

/**
 * Контроллер формы поддержки бизнес - компонента "Аналитика фин. операции"
 *
 * @author leonova
 * @version $Id: FinAnlMt.java,v 1.2 2007/11/21 10:52:41 alikaev Exp $
 */
public class FinAnlMt extends DefaultMaintenanceForm {

  protected static String anlParentName = StringUtils.EMPTY_STRING;
  private static Boolean anlKind = null;
  private static FinAnlPlanSearchHelp accSearchHelp = null;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {

    final Account account = ((Analytics) getEntity()).getFinAcc();
    try {
      short level = ((Analytics) getEntity()).getAnlLevel();
      level--;
      switch (level) {
        case 1:
          anlKind = account.isAnl1Kind();
          accSearchHelp = new FinAnlPlanDstAnlLevel1SearchHelp();
          break;
        case 2:
          anlKind = account.isAnl2Kind();
          accSearchHelp = new FinAnlPlanDstAnlLevel2SearchHelp();
          break;
        case 3:
          anlKind = account.isAnl3Kind();
          accSearchHelp = new FinAnlPlanDstAnlLevel3SearchHelp();
          break;
        case 4:
          anlKind = account.isAnl4Kind();
          accSearchHelp = new FinAnlPlanDstAnlLevel4SearchHelp();
          break;
        default:
          break;
      }
      if (!anlKind) {
        Analytics parent = ((Analytics) getEntity()).getParent();
        if (parent != null) {
          Analytics anlClass = ServerUtils.getPersistentManager().find(Analytics.class, parent.getPrimaryKey());
          anlParentName = anlClass.getCode();
        } else {
          anlParentName = ""; //$NON-NLS-1$
        }
      }
    } catch (Exception e) {
      logger.error("Error during analytics loading", e); //$NON-NLS-1$
      anlParentName = "<unknown>";     //$NON-NLS-1$
    }
    super.doOnRun();
  }

  protected void onActionClearAnlParent(WidgetEvent event) {
    ((Analytics) getEntity()).setParent(null);
    anlParentName = null;
  }

  protected void onActionChooseParent(WidgetEvent event) throws Exception {
    Analytics finAnl = (Analytics) getEntity();
    if (finAnl.getAnlLevel() != 1) {
      final Account account = ServerUtils.getPersistentManager().find(Account.class, finAnl.getFinAcc().getPrimaryKey());

      if (!anlKind) {
        accSearchHelp.setAccPlanName(account);

        accSearchHelp.addSearchHelpListener(new SearchHelpListener() {

          public void searchCanceled(SearchHelpEvent event) {

          }

          public void searchPerformed(SearchHelpEvent event) {
            Analytics entity = (Analytics) event.getItems()[0];
            ((Analytics) getEntity()).setParent(entity);
            anlParentName = entity.getCode();
            view.flushModel();
          }

        });
        accSearchHelp.search();
      }
    }
  }

}
