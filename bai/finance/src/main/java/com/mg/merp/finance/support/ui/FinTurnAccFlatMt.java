/*
 * FinTurnAccFlatMt.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

/**
 * Контроллер формы поддержки "Остататки и обороты по счетам финансового учета"
 *
 * @author Artem V. Sharapov
 * @version $Id: FinTurnAccFlatMt.java,v 1.1 2009/02/16 07:46:40 sharapov Exp $
 */
public class FinTurnAccFlatMt extends DefaultMaintenanceForm {

  protected final String ACCOUNT = "Account";

  private final String ANALYTICS_1 = "Analytics1";
  private final String ANALYTICS_2 = "Analytics2";
  private final String ANALYTICS_3 = "Analytics3";
  private final String ANALYTICS_4 = "Analytics4";
  private final String ANALYTICS_5 = "Analytics5";

  protected String anlLevel1SrcName = StringUtils.EMPTY_STRING;
  protected String anlLevel2SrcName = StringUtils.EMPTY_STRING;
  protected String anlLevel3SrcName = StringUtils.EMPTY_STRING;
  protected String anlLevel4SrcName = StringUtils.EMPTY_STRING;
  protected String anlLevel5SrcName = StringUtils.EMPTY_STRING;
  protected PersistentObject typeModel;
  protected PersistentManager persistentManager = ServerUtils.getPersistentManager();

  public PersistentObject getTypeModel() {
    return getEntity();
  }

  protected SearchHelp setSearchHelp(final SysClass classAnl) {
    return new DefaultLegacySearchHelp() {

      @Override
      protected String getServiceName() {
        return FinUtils.getBeanName(classAnl);
      }

    };
  }

  protected void runSearch(final Account account, String anlBusinessServiceName, final String anlClassName, final String anlName, final String anlLevelName, FinAnlPlanSearchHelp accSearchHelp) throws Exception {
    if ((Boolean) account.getAttribute(anlBusinessServiceName)) {
      final SysClass classAnl = ServerUtils.getPersistentManager().find(SysClass.class, ((SysClass) account.getAttribute(anlClassName)).getPrimaryKey());
      SearchHelp searchHelp = setSearchHelp(classAnl);

      searchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchCanceled(SearchHelpEvent event) {
          //do nothing
        }

        public void searchPerformed(SearchHelpEvent event) {
          PersistentObject entity = event.getItems()[0];
          (getTypeModel()).setAttribute(anlName, entity.getPrimaryKey());
          try {
            setFieldValue(anlLevelName, FinUtils.getAnlName(FinUtils.getBeanName((SysClass) account.getAttribute(anlClassName)), (Integer) (getTypeModel()).getAttribute(anlName)));
          } catch (NoSuchFieldException e) {
            getLogger().error(e);
          } catch (IllegalAccessException e) {
            getLogger().error(e);
          }
          view.flushModel();
        }

      });
      searchHelp.search();
    } else {
      accSearchHelp.setAccPlanName(account);

      accSearchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchCanceled(SearchHelpEvent event) {

        }

        public void searchPerformed(SearchHelpEvent event) {
          Analytics entity = (Analytics) event.getItems()[0];
          (getTypeModel()).setAttribute(anlName, entity.getPrimaryKey());
          try {
            setFieldValue(anlLevelName, entity.getCode());
          } catch (NoSuchFieldException e) {
            getLogger().error(e);
          } catch (IllegalAccessException e) {
            getLogger().error(e);
          }
          view.flushModel();
        }

      });
      accSearchHelp.search();
    }
  }

  protected void getFieldName(Account account, String anlId, String anlClass, String anlLevelName, String anlString) {
    if ((Integer) (getTypeModel()).getAttribute(anlId) != null) {
      try {
        Integer idAnl = (Integer) (getTypeModel()).getAttribute(anlId);
        if ((account.isAnl1Kind() && anlString == "first") || (account.isAnl2Kind() && anlString == "second") || (account.isAnl3Kind() && anlString == "third") || (account.isAnl4Kind() && anlString == "fourth") || (account.isAnl5Kind() && anlString == "fifth")) {
          setFieldValue(anlLevelName, FinUtils.getAnlName(FinUtils.getBeanName((SysClass) account.getAttribute(anlClass)), idAnl));
        } else {
          Analytics anlyticsClass = persistentManager.find(Analytics.class, idAnl);
          setFieldValue(anlLevelName, anlyticsClass.getCode());
        }
      } catch (Exception e) {
        logger.error("Error during ".concat(anlString).concat(" analytics loading"), e);
        anlLevel1SrcName = "<unknown>";
      }
    }
  }

  @Override
  protected void doOnRun() {
    final Account accountSrc = (Account) ((getTypeModel()).getAttribute(ACCOUNT));
    getFieldName(accountSrc, ANALYTICS_1, "Anl1Class", "anlLevel1SrcName", "first");
    getFieldName(accountSrc, ANALYTICS_2, "Anl2Class", "anlLevel2SrcName", "second");
    getFieldName(accountSrc, ANALYTICS_3, "Anl3Class", "anlLevel3SrcName", "third");
    getFieldName(accountSrc, ANALYTICS_4, "Anl4Class", "anlLevel4SrcName", "fourth");
    getFieldName(accountSrc, ANALYTICS_5, "Anl5Class", "anlLevel5SrcName", "fifth");
    super.doOnRun();
  }

  protected void onActionClearAnlLevel1Src(WidgetEvent event) {
    (getTypeModel()).setAttribute(ANALYTICS_1, null);
    anlLevel1SrcName = null;
  }

  protected void onActionClearAnlLevel2Src(WidgetEvent event) {
    (getTypeModel()).setAttribute(ANALYTICS_2, null);
    anlLevel2SrcName = null;
  }

  protected void onActionClearAnlLevel3Src(WidgetEvent event) {
    (getTypeModel()).setAttribute(ANALYTICS_3, null);
    anlLevel3SrcName = null;
  }

  protected void onActionClearAnlLevel4Src(WidgetEvent event) {
    (getTypeModel()).setAttribute(ANALYTICS_4, null);
    anlLevel4SrcName = null;
  }

  protected void onActionClearAnlLevel5Src(WidgetEvent event) {
    (getTypeModel()).setAttribute(ANALYTICS_5, null);
    anlLevel5SrcName = null;
  }

  protected void onActionChooseAnlLevel1Src(WidgetEvent event) throws Exception {
    final Account accountSrc = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(ACCOUNT)).getPrimaryKey());
    runSearch(accountSrc, "Anl1Kind", "Anl1Class", ANALYTICS_1, "anlLevel1SrcName", new FinAnlPlanSrcAnlLevel1SearchHelp());
  }

  protected void onActionChooseAnlLevel2Src(WidgetEvent event) throws Exception {
    final Account accountSrc = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(ACCOUNT)).getPrimaryKey());
    runSearch(accountSrc, "Anl2Kind", "Anl2Class", ANALYTICS_2, "anlLevel2SrcName", new FinAnlPlanSrcAnlLevel2SearchHelp());

  }

  protected void onActionChooseAnlLevel3Src(WidgetEvent event) throws Exception {
    final Account accountSrc = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(ACCOUNT)).getPrimaryKey());
    runSearch(accountSrc, "Anl3Kind", "Anl3Class", ANALYTICS_3, "anlLevel3SrcName", new FinAnlPlanSrcAnlLevel3SearchHelp());
  }

  protected void onActionChooseAnlLevel4Src(WidgetEvent event) throws Exception {
    final Account accountSrc = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(ACCOUNT)).getPrimaryKey());
    runSearch(accountSrc, "Anl4Kind", "Anl4Class", ANALYTICS_4, "anlLevel4SrcName", new FinAnlPlanSrcAnlLevel4SearchHelp());
  }

  protected void onActionChooseAnlLevel5Src(WidgetEvent event) throws Exception {
    final Account accountSrc = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(ACCOUNT)).getPrimaryKey());
    runSearch(accountSrc, "Anl5Kind", "Anl5Class", ANALYTICS_5, "anlLevel5SrcName", new FinAnlPlanSrcAnlLevel5SearchHelp());
  }

}
