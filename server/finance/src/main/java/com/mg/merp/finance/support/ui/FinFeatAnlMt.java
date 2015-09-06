/*
 * FinFeatAnlMt.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

/**
 * @author leonova
 * @version $Id: FinFeatAnlMt.java,v 1.2 2007/09/17 12:04:24 alikaev Exp $
 */
public class FinFeatAnlMt extends DefaultMaintenanceForm {
  protected static String featParentName = "";
  private static Boolean anlKind = false;
  private static FinAnlPlanSearchHelp accSearchHelp = null;

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
          featParentName = anlClass.getCode();
        } else {
          featParentName = "";
        }
      }
    } catch (Exception e) {
      logger.error("Error during analytics loading", e);
      featParentName = "<unknown>";
    }
    super.doOnRun();
  }

  protected void onActionClearParent(WidgetEvent event) {
    ((Analytics) getEntity()).setParent(null);
    featParentName = null;
  }

  protected void onActionChooseParent(WidgetEvent event) throws Exception {
    if (((Analytics) getEntity()).getFinAcc() != null) {
      final Account account = ServerUtils.getPersistentManager().find(Account.class, ((Analytics) getEntity()).getFinAcc().getPrimaryKey());

      if (!anlKind) {
        accSearchHelp.setAccPlanName(account);

        accSearchHelp.addSearchHelpListener(new SearchHelpListener() {

          public void searchCanceled(SearchHelpEvent event) {

          }

          public void searchPerformed(SearchHelpEvent event) {
            Analytics entity = (Analytics) event.getItems()[0];
            ((Analytics) getEntity()).setParent(entity);
            featParentName = entity.getCode();
            view.flushModel();
          }

        });
        accSearchHelp.search();
      }
    }
  }

}
