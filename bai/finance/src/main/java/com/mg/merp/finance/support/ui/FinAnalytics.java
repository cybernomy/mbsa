/*
 * FinAnalytics.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

/**
 * @author leonova
 * @version $Id: FinAnalytics.java,v 1.3 2008/02/08 09:23:46 alikaev Exp $
 */
public abstract class FinAnalytics extends DefaultMaintenanceForm {
  protected String anlLevel1SrcName = "";
  protected String anlLevel2SrcName = "";
  protected String anlLevel3SrcName = "";
  protected String anlLevel4SrcName = "";
  protected String anlLevel5SrcName = "";
  protected String anlLevel1DstName = "";
  protected String anlLevel2DstName = "";
  protected String anlLevel3DstName = "";
  protected String anlLevel4DstName = "";
  protected String anlLevel5DstName = "";
  protected String anlLevel1FeatName = "";
  protected String anlLevel2FeatName = "";
  protected String anlLevel3FeatName = "";
  protected String anlLevel4FeatName = "";
  protected String anlLevel5FeatName = "";
  PersistentObject typeModel;

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

  @SuppressWarnings("unused")
  private void runSearch(final Account account, String anlBusinessServiceName, final String anlClassName, final String anlName, final String anlLevelName, FinAnlPlanSearchHelp accSearchHelp) throws Exception {
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
          Analytics anlyticsClass = ServerUtils.getPersistentManager().find(Analytics.class, idAnl);
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
    final Account accountSrc = (Account) ((getTypeModel()).getAttribute("SrcAcc"));
    final Account accountDst = (Account) ((getTypeModel()).getAttribute("DstAcc"));
    final Account accountFeat = (Account) ((getTypeModel()).getAttribute("SrcAcc"));

    getFieldName(accountSrc, "SrcAnl1", "Anl1Class", "anlLevel1SrcName", "first");
    getFieldName(accountSrc, "SrcAnl2", "Anl2Class", "anlLevel2SrcName", "second");
    getFieldName(accountSrc, "SrcAnl3", "Anl3Class", "anlLevel3SrcName", "third");
    getFieldName(accountSrc, "SrcAnl4", "Anl4Class", "anlLevel4SrcName", "fourth");
    getFieldName(accountSrc, "SrcAnl5", "Anl5Class", "anlLevel5SrcName", "fifth");
    getFieldName(accountDst, "DstAnl1", "Anl1Class", "anlLevel1DstName", "first");
    getFieldName(accountDst, "DstAnl2", "Anl2Class", "anlLevel2DstName", "second");
    getFieldName(accountDst, "DstAnl3", "Anl3Class", "anlLevel3DstName", "third");
    getFieldName(accountDst, "DstAnl4", "Anl4Class", "anlLevel4DstName", "fourth");
    getFieldName(accountDst, "DstAnl5", "Anl5Class", "anlLevel5DstName", "fifth");
    getFieldName(accountFeat, "SrcAnl1", "Anl1Class", "anlLevel1FeatName", "first");
    getFieldName(accountFeat, "SrcAnl2", "Anl2Class", "anlLevel2FeatName", "second");
    getFieldName(accountFeat, "SrcAnl3", "Anl3Class", "anlLevel3FeatName", "third");
    getFieldName(accountFeat, "SrcAnl4", "Anl4Class", "anlLevel4FeatName", "fourth");
    getFieldName(accountFeat, "SrcAnl5", "Anl5Class", "anlLevel5FeatName", "fifth");

    super.doOnRun();
  }

  protected void onActionClearAnlLevel1Src(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl1", null);
    anlLevel1SrcName = null;
  }

  protected void onActionClearAnlLevel2Src(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl2", null);
    anlLevel2SrcName = null;
  }

  protected void onActionClearAnlLevel3Src(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl3", null);
    anlLevel3SrcName = null;
  }

  protected void onActionClearAnlLevel4Src(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl4", null);
    anlLevel4SrcName = null;
  }

  protected void onActionClearAnlLevel5Src(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl5", null);
    anlLevel5SrcName = null;
  }

  protected void onActionClearAnlLevel1Dst(WidgetEvent event) {
    (getTypeModel()).setAttribute("DstAnl1", null);
    anlLevel1DstName = null;
  }

  protected void onActionClearAnlLevel2Dst(WidgetEvent event) {
    (getTypeModel()).setAttribute("DstAnl2", null);
    anlLevel2DstName = null;
  }

  protected void onActionClearAnlLevel3Dst(WidgetEvent event) {
    (getTypeModel()).setAttribute("DstAnl3", null);
    anlLevel3DstName = null;
  }

  protected void onActionClearAnlLevel4Dst(WidgetEvent event) {
    (getTypeModel()).setAttribute("DstAnl4", null);
    anlLevel4DstName = null;
  }

  protected void onActionClearAnlLevel5Dst(WidgetEvent event) {
    (getTypeModel()).setAttribute("DstAnl5", null);
    anlLevel5DstName = null;
  }

  protected void onActionClearAnlLevel1Feat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl1", null);
    anlLevel1FeatName = null;
  }

  protected void onActionClearAnlLevel2Feat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl2", null);
    anlLevel2FeatName = null;
  }

  protected void onActionClearAnlLevel3Feat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl3", null);
    anlLevel3FeatName = null;
  }

  protected void onActionClearAnlLevel4Feat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl4", null);
    anlLevel4FeatName = null;
  }

  protected void onActionClearAnlLevel5Feat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAnl5", null);
    anlLevel5FeatName = null;
  }

  protected void onActionChooseAnlLevel1Src(WidgetEvent event) throws Exception {
    final Account accountSrc = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountSrc, "Anl1Kind", "Anl1Class", "SrcAnl1", "anlLevel1SrcName", new FinAnlPlanSrcAnlLevel1SearchHelp());
  }

  protected void onActionChooseAnlLevel2Src(WidgetEvent event) throws Exception {
    final Account accountSrc = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountSrc, "Anl2Kind", "Anl2Class", "SrcAnl2", "anlLevel2SrcName", new FinAnlPlanSrcAnlLevel2SearchHelp());

  }

  protected void onActionChooseAnlLevel3Src(WidgetEvent event) throws Exception {
    final Account accountSrc = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountSrc, "Anl3Kind", "Anl3Class", "SrcAnl3", "anlLevel3SrcName", new FinAnlPlanSrcAnlLevel3SearchHelp());
  }

  protected void onActionChooseAnlLevel4Src(WidgetEvent event) throws Exception {
    final Account accountSrc = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountSrc, "Anl4Kind", "Anl4Class", "SrcAnl4", "anlLevel4SrcName", new FinAnlPlanSrcAnlLevel4SearchHelp());
  }

  protected void onActionChooseAnlLevel5Src(WidgetEvent event) throws Exception {
    final Account accountSrc = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountSrc, "Anl5Kind", "Anl5Class", "SrcAnl5", "anlLevel5SrcName", new FinAnlPlanSrcAnlLevel5SearchHelp());
  }

  protected void onActionChooseAnlLevel1Dst(WidgetEvent event) throws Exception {
    final Account accountDst = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("DstAcc")).getPrimaryKey());
    runSearch(accountDst, "Anl1Kind", "Anl1Class", "DstAnl1", "anlLevel1DstName", new FinAnlPlanDstAnlLevel1SearchHelp());
  }

  protected void onActionChooseAnlLevel2Dst(WidgetEvent event) throws Exception {
    final Account accountDst = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("DstAcc")).getPrimaryKey());
    runSearch(accountDst, "Anl2Kind", "Anl2Class", "DstAnl2", "anlLevel2DstName", new FinAnlPlanDstAnlLevel2SearchHelp());
  }

  protected void onActionChooseAnlLevel3Dst(WidgetEvent event) throws Exception {
    final Account accountDst = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("DstAcc")).getPrimaryKey());
    runSearch(accountDst, "Anl3Kind", "Anl3Class", "DstAnl3", "anlLevel3DstName", new FinAnlPlanDstAnlLevel3SearchHelp());
  }

  protected void onActionChooseAnlLevel4Dst(WidgetEvent event) throws Exception {
    final Account accountDst = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("DstAcc")).getPrimaryKey());
    runSearch(accountDst, "Anl4Kind", "Anl4Class", "DstAnl4", "anlLevel4DstName", new FinAnlPlanDstAnlLevel4SearchHelp());
  }

  protected void onActionChooseAnlLevel5Dst(WidgetEvent event) throws Exception {
    final Account accountDst = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("DstAcc")).getPrimaryKey());
    runSearch(accountDst, "Anl5Kind", "Anl5Class", "DstAnl5", "anlLevel5DstName", new FinAnlPlanDstAnlLevel5SearchHelp());

  }

  protected void onActionChooseAnlLevel1Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountFeat, "Anl1Kind", "Anl1Class", "SrcAnl1", "anlLevel1FeatName", new FinAnlPlanSrcAnlLevel1SearchHelp());
  }

  protected void onActionChooseAnlLevel2Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountFeat, "Anl2Kind", "Anl2Class", "SrcAnl2", "anlLevel2FeatName", new FinAnlPlanSrcAnlLevel2SearchHelp());

  }

  protected void onActionChooseAnlLevel3Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountFeat, "Anl3Kind", "Anl3Class", "SrcAnl3", "anlLevel3FeatName", new FinAnlPlanSrcAnlLevel3SearchHelp());
  }

  protected void onActionChooseAnlLevel4Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountFeat, "Anl4Kind", "Anl4Class", "SrcAnl4", "anlLevel4FeatName", new FinAnlPlanSrcAnlLevel4SearchHelp());
  }

  protected void onActionChooseAnlLevel5Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = ServerUtils.getPersistentManager().find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute("SrcAcc")).getPrimaryKey());
    runSearch(accountFeat, "Anl5Kind", "Anl5Class", "SrcAnl5", "anlLevel5FeatName", new FinAnlPlanSrcAnlLevel5SearchHelp());
  }


}
