/*
 * FinSpecFeatureMt.java
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

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.model.Account;

/**
 * @author leonova
 * @version $Id: FinSpecFeatureMt.java,v 1.3 2008/05/22 13:33:50 alikaev Exp $
 */
public class FinSpecFeatureMt extends FinAnalytics {

  protected String accFeatName = StringUtils.EMPTY_STRING;

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.support.ui.FinAnalytics#doOnRun()
   */
  @Override
  protected void doOnRun() {
    if ((getTypeModel().getAttribute("SrcAcc")) != null) {
      Integer idAnl = ((Account) (getTypeModel().getAttribute("SrcAcc"))).getId();
      Account feat = ServerUtils.getPersistentManager().find(Account.class, idAnl);
      try {
        setFieldValue("accFeatName", feat.getCode());
      } catch (Exception e) {
      }
    }
    super.doOnRun();
  }

  /**
   * Обработчик кнопки "Удаление признака"
   */
  protected void onActionClearAccFeat(WidgetEvent event) {
    (getTypeModel()).setAttribute("SrcAcc", null);
    accFeatName = null;
    clearFeatAnalytics();
  }

  /**
   * Обработчик кнопки "Выбор принака"
   */
  protected void onActionChooseAccFeat(WidgetEvent event) throws Exception {
    AbstractSearchHelp accSearchHelp = new FeatureSearchHelp();
    accSearchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
      }

      public void searchPerformed(SearchHelpEvent event) {
        Account entity = (Account) event.getItems()[0];
        (getTypeModel()).setAttribute("SrcAcc", entity);
        clearFeatAnalytics();
        try {
          setFieldValue("accFeatName", entity.getCode());
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

  /**
   * Удаляем установленные аналитики признака
   */
  private void clearFeatAnalytics() {
    (getTypeModel()).setAttribute("SrcAnl1", null);
    (getTypeModel()).setAttribute("SrcAnl2", null);
    (getTypeModel()).setAttribute("SrcAnl3", null);
    (getTypeModel()).setAttribute("SrcAnl4", null);
    (getTypeModel()).setAttribute("SrcAnl5", null);

    anlLevel1FeatName = null;
    anlLevel2FeatName = null;
    anlLevel3FeatName = null;
    anlLevel4FeatName = null;
    anlLevel5FeatName = null;
  }

}
