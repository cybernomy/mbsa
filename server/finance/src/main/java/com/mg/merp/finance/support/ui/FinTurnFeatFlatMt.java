/*
 * FinTurnFeatFlatMt.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.merp.finance.model.Account;

/**
 * Контроллер формы поддержки "Остататки и обороты по признакам"
 *
 * @author Artem V. Sharapov
 * @version $Id: FinTurnFeatFlatMt.java,v 1.1 2009/02/16 07:46:40 sharapov Exp $
 */
public class FinTurnFeatFlatMt extends FinTurnAccFlatMt {

  protected final String FEATURE = "Feature";

  private final String FEATURE_ANALYTICS_1 = "FeatureAnalytics1";
  private final String FEATURE_ANALYTICS_2 = "FeatureAnalytics2";
  private final String FEATURE_ANALYTICS_3 = "FeatureAnalytics3";
  private final String FEATURE_ANALYTICS_4 = "FeatureAnalytics4";
  private final String FEATURE_ANALYTICS_5 = "FeatureAnalytics5";

  protected String anlLevel1FeatName = null;
  protected String anlLevel2FeatName = null;
  protected String anlLevel3FeatName = null;
  protected String anlLevel4FeatName = null;
  protected String anlLevel5FeatName = null;

  /* (non-Javadoc)
   * @see com.mg.merp.finance.support.ui.FinTurnAccFlatMt#doOnRun()
   */
  @Override
  protected void doOnRun() {
    final Account accountFeat = (Account) ((getTypeModel()).getAttribute(FEATURE));

    getFieldName(accountFeat, FEATURE_ANALYTICS_1, "Anl1Class", "anlLevel1FeatName", "first");
    getFieldName(accountFeat, FEATURE_ANALYTICS_2, "Anl2Class", "anlLevel2FeatName", "second");
    getFieldName(accountFeat, FEATURE_ANALYTICS_3, "Anl3Class", "anlLevel3FeatName", "third");
    getFieldName(accountFeat, FEATURE_ANALYTICS_4, "Anl4Class", "anlLevel4FeatName", "fourth");
    getFieldName(accountFeat, FEATURE_ANALYTICS_5, "Anl5Class", "anlLevel5FeatName", "fifth");

    super.doOnRun();
  }

  protected void onActionChooseAnlLevel1Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(FEATURE)).getPrimaryKey());
    runSearch(accountFeat, "Anl1Kind", "Anl1Class", FEATURE_ANALYTICS_1, "anlLevel1FeatName", new FinAnlPlanSrcAnlLevel1SearchHelp());
  }

  protected void onActionChooseAnlLevel2Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(FEATURE)).getPrimaryKey());
    runSearch(accountFeat, "Anl2Kind", "Anl2Class", FEATURE_ANALYTICS_2, "anlLevel2FeatName", new FinAnlPlanSrcAnlLevel2SearchHelp());

  }

  protected void onActionChooseAnlLevel3Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(FEATURE)).getPrimaryKey());
    runSearch(accountFeat, "Anl3Kind", "Anl3Class", FEATURE_ANALYTICS_3, "anlLevel3FeatName", new FinAnlPlanSrcAnlLevel3SearchHelp());
  }

  protected void onActionChooseAnlLevel4Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(FEATURE)).getPrimaryKey());
    runSearch(accountFeat, "Anl4Kind", "Anl4Class", FEATURE_ANALYTICS_4, "anlLevel4FeatName", new FinAnlPlanSrcAnlLevel4SearchHelp());
  }

  protected void onActionChooseAnlLevel5Feat(WidgetEvent event) throws Exception {
    final Account accountFeat = persistentManager.find(Account.class, ((PersistentObject) (getTypeModel()).getAttribute(FEATURE)).getPrimaryKey());
    runSearch(accountFeat, "Anl5Kind", "Anl5Class", FEATURE_ANALYTICS_5, "anlLevel5FeatName", new FinAnlPlanSrcAnlLevel5SearchHelp());
  }

  protected void onActionClearAnlLevel1Feat(WidgetEvent event) {
    getTypeModel().setAttribute(FEATURE_ANALYTICS_1, null);
    anlLevel1FeatName = null;
  }

  protected void onActionClearAnlLevel2Feat(WidgetEvent event) {
    getTypeModel().setAttribute(FEATURE_ANALYTICS_2, null);
    anlLevel2FeatName = null;
  }

  protected void onActionClearAnlLevel3Feat(WidgetEvent event) {
    getTypeModel().setAttribute(FEATURE_ANALYTICS_3, null);
    anlLevel3FeatName = null;
  }

  protected void onActionClearAnlLevel4Feat(WidgetEvent event) {
    getTypeModel().setAttribute(FEATURE_ANALYTICS_4, null);
    anlLevel4FeatName = null;
  }

  protected void onActionClearAnlLevel5Feat(WidgetEvent event) {
    getTypeModel().setAttribute(FEATURE_ANALYTICS_5, null);
    anlLevel5FeatName = null;
  }

}
