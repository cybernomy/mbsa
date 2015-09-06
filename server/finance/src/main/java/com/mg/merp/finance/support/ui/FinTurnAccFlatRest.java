/*
 * FinTurnAccFlatRest.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;


/**
 * @author leonova
 * @version $Id: FinTurnAccFlatRest.java,v 1.3 2007/08/17 08:33:11 alikaev Exp $
 */
public class FinTurnAccFlatRest extends DefaultHierarhyRestrictionForm {

  protected Account srcAcc;
  @DataItemName("Finance.Acc.CurrencyCode")
  protected com.mg.merp.reference.model.Currency currencyCode;
  @DataItemName("Finance.TurnAccount.Analytics1")
  protected String anlLevel1SrcName = "";
  @DataItemName("Finance.TurnAccount.Analytics2")
  protected String anlLevel2SrcName = "";
  @DataItemName("Finance.TurnAccount.Analytics3")
  protected String anlLevel3SrcName = "";
  @DataItemName("Finance.TurnAccount.Analytics4")
  protected String anlLevel4SrcName = "";
  @DataItemName("Finance.TurnAccount.Analytics5")
  protected String anlLevel5SrcName = "";
  @DataItemName("Finance.Cond.TurnFlat.Period1")
  private com.mg.merp.finance.model.FinPeriod periodBegin;
  @DataItemName("Finance.Cond.TurnFlat.Period2")
  private com.mg.merp.finance.model.FinPeriod periodEnd;
  private java.lang.Integer anlLevel1SrcId;
  private java.lang.Integer anlLevel2SrcId;
  private java.lang.Integer anlLevel3SrcId;
  private java.lang.Integer anlLevel4SrcId;
  private java.lang.Integer anlLevel5SrcId;


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.srcAcc = null;
    this.currencyCode = null;
    this.periodBegin = null;
    this.periodEnd = null;
    this.anlLevel1SrcId = null;
    this.anlLevel2SrcId = null;
    this.anlLevel3SrcId = null;
    this.anlLevel4SrcId = null;
    this.anlLevel5SrcId = null;
    this.anlLevel1SrcName = "";
    this.anlLevel2SrcName = "";
    this.anlLevel3SrcName = "";
    this.anlLevel4SrcName = "";
    this.anlLevel5SrcName = "";


  }

  private SearchHelp setSearchHelp(final SysClass classAnl) {
    return new DefaultLegacySearchHelp() {

      @Override
      protected String getServiceName() {
        return FinUtils.getBeanName(classAnl);
      }

    };
  }

  private void setIdAnalit(String fieldName, Integer value) {
    if (fieldName.equals("anlLevel1SrcName")) {
      setAnlLevel1SrcId(value);
    }
    if (fieldName.equals("anlLevel2SrcName")) {
      setAnlLevel2SrcId(value);
    }
    if (fieldName.equals("anlLevel3SrcName")) {
      setAnlLevel3SrcId(value);
    }
    if (fieldName.equals("anlLevel4SrcName")) {
      setAnlLevel4SrcId(value);
    }
    if (fieldName.equals("anlLevel5SrcName")) {
      setAnlLevel5SrcId(value);
    }
  }

  @SuppressWarnings("unused")
  private void runSearch(final Account account, String anlBusinessServiceName, final String anlClassName, final String anlLevelName, FinAnlPlanSearchHelp accSearchHelp) throws Exception {
    if ((Boolean) account.getAttribute(anlBusinessServiceName)) {
      final SysClass classAnl = ServerUtils.getPersistentManager().find(SysClass.class, ((SysClass) account.getAttribute(anlClassName)).getPrimaryKey());
      SearchHelp searchHelp = setSearchHelp(classAnl);

      searchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchCanceled(SearchHelpEvent event) {

        }

        public void searchPerformed(SearchHelpEvent event) {
          PersistentObject entity = event.getItems()[0];
          try {
            setFieldValue(anlLevelName, FinUtils.getAnlName(FinUtils.getBeanName((SysClass) account.getAttribute(anlClassName)), (Integer) (entity.getAttribute("Id"))));
            setIdAnalit(anlLevelName, (Integer) entity.getPrimaryKey());
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
          try {
            setFieldValue(anlLevelName, entity.getCode());
            setIdAnalit(anlLevelName, (Integer) entity.getPrimaryKey());
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

  private Account getCurrentSrc() {
    Account srcAccount = ServerUtils.getPersistentManager().find(Account.class, getSrcAcc().getId());
    return srcAccount;
  }

  public com.mg.merp.reference.model.Currency getCurrencyCode() {
    return currencyCode;
  }

  public com.mg.merp.finance.model.FinPeriod getPeriodBegin() {
    return periodBegin;
  }

  public com.mg.merp.finance.model.FinPeriod getPeriodEnd() {
    return periodEnd;
  }

  public Account getSrcAcc() {
    return srcAcc;
  }

  public java.lang.Integer getAnlLevel1SrcId() {
    return anlLevel1SrcId;
  }

  public void setAnlLevel1SrcId(java.lang.Integer anlLevel1SrcId) {
    this.anlLevel1SrcId = anlLevel1SrcId;
  }

  public java.lang.Integer getAnlLevel2SrcId() {
    return anlLevel2SrcId;
  }

  public void setAnlLevel2SrcId(java.lang.Integer anlLevel2SrcId) {
    this.anlLevel2SrcId = anlLevel2SrcId;
  }

  public java.lang.Integer getAnlLevel3SrcId() {
    return anlLevel3SrcId;
  }

  public void setAnlLevel3SrcId(java.lang.Integer anlLevel3SrcId) {
    this.anlLevel3SrcId = anlLevel3SrcId;
  }

  public java.lang.Integer getAnlLevel4SrcId() {
    return anlLevel4SrcId;
  }

  public void setAnlLevel4SrcId(java.lang.Integer anlLevel4SrcId) {
    this.anlLevel4SrcId = anlLevel4SrcId;
  }

  public java.lang.Integer getAnlLevel5SrcId() {
    return anlLevel5SrcId;
  }

  public void setAnlLevel5SrcId(java.lang.Integer anlLevel5SrcId) {
    this.anlLevel5SrcId = anlLevel5SrcId;
  }

  protected void onActionClearAnlLevel1Src(WidgetEvent event) {
    anlLevel1SrcName = null;
  }

  protected void onActionClearAnlLevel2Src(WidgetEvent event) {
    anlLevel2SrcName = null;
  }

  protected void onActionClearAnlLevel3Src(WidgetEvent event) {
    anlLevel3SrcName = null;
  }

  protected void onActionClearAnlLevel4Src(WidgetEvent event) {
    anlLevel4SrcName = null;
  }

  protected void onActionClearAnlLevel5Src(WidgetEvent event) {
    anlLevel5SrcName = null;
  }

  protected void onActionChooseAnlLevel1Src(WidgetEvent event) throws Exception {
    if (getSrcAcc() != null) {
      runSearch(getCurrentSrc(), "Anl1Kind", "Anl1Class", "anlLevel1SrcName", new FinAnlPlanSrcAnlLevel1SearchHelp());
    }
  }

  protected void onActionChooseAnlLevel2Src(WidgetEvent event) throws Exception {
    if (getSrcAcc() != null) {
      runSearch(getCurrentSrc(), "Anl2Kind", "Anl2Class", "anlLevel2SrcName", new FinAnlPlanSrcAnlLevel2SearchHelp());
    }
  }

  protected void onActionChooseAnlLevel3Src(WidgetEvent event) throws Exception {
    if (getSrcAcc() != null) {
      runSearch(getCurrentSrc(), "Anl3Kind", "Anl3Class", "anlLevel3SrcName", new FinAnlPlanSrcAnlLevel3SearchHelp());
    }
  }

  protected void onActionChooseAnlLevel4Src(WidgetEvent event) throws Exception {
    if (getSrcAcc() != null) {
      runSearch(getCurrentSrc(), "Anl4Kind", "Anl4Class", "anlLevel4SrcName", new FinAnlPlanSrcAnlLevel4SearchHelp());
    }
  }

  protected void onActionChooseAnlLevel5Src(WidgetEvent event) throws Exception {
    if (getSrcAcc() != null) {
      runSearch(getCurrentSrc(), "Anl5Kind", "Anl5Class", "anlLevel5SrcName", new FinAnlPlanSrcAnlLevel5SearchHelp());
    }
  }

}
