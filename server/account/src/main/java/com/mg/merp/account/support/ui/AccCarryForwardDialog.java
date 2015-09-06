/*
 * AccCarryForwardDialog.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBox;
import com.mg.framework.api.ui.widget.Label;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.support.Messages;

/**
 * Контроллер диалога "Перенос остатков" бух.учета
 *
 * @author Artem V. Sharapov
 * @version $Id: AccCarryForwardDialog.java,v 1.2 2007/01/18 10:13:16 sharapov Exp $
 */
public class AccCarryForwardDialog extends DefaultDialog {

  //	Fields

  private final String WIGET_SELECTED_ACC = "selectedAmountLabel"; //$NON-NLS-1$
  private final String WIGET_SELECTED_ANL_ACC = "selectedAnlAmountLabel"; //$NON-NLS-1$
  private final String WIGET_SELECTED_VAL_ACC = "selectedValAmountLabel"; //$NON-NLS-1$
  private final String WIGET_SELECTED_DBKT_ACC = "selectedDbKtAmountLabel"; //$NON-NLS-1$
  private final String WIGET_ALL_ACC = "allAcc"; //$NON-NLS-1$
  private final String WIGET_ALL_ANL_ACC = "allAnlAcc"; //$NON-NLS-1$
  private final String WIGET_ALL_VAL_ACC = "allValAcc"; //$NON-NLS-1$
  private final String WIGET_ALL_DBKT_ACC = "allDbKtAcc"; //$NON-NLS-1$
  private com.mg.merp.account.model.Period accPeriodFrom;
  private com.mg.merp.account.model.Period accPeriodTill;
  private com.mg.merp.account.model.AccPlan[] accounts;
  private com.mg.merp.account.model.AccPlan[] anlAccounts;
  private com.mg.merp.account.model.AccPlan[] valAccounts;
  private com.mg.merp.account.model.AccPlan[] dbKtAccounts;
  private boolean allAcc;
  private boolean allAnlAcc;
  private boolean allValAcc;
  private boolean allDbKtAcc;


  //	Methods

  /**
   * Обработка события выбора счетов
   *
   * @param event - событие
   */
  public void onActionChooseAccounts(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccountSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject[] entitys = event.getItems();
        accounts = new AccPlan[entitys.length];
        for (int i = 0; i < entitys.length; i++)
          accounts[i] = (AccPlan) entitys[i];
        setAllAcc(false);
        view.flushModel();
        setSelectedAmount(WIGET_ALL_ACC, WIGET_SELECTED_ACC);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Обработка события выбора аналитических счетов
   *
   * @param event - событие
   */
  public void onActionChooseAnlAccounts(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccountSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject[] entitys = event.getItems();
        anlAccounts = new AccPlan[entitys.length];
        for (int i = 0; i < entitys.length; i++)
          anlAccounts[i] = (AccPlan) entitys[i];
        setAllAnlAcc(false);
        view.flushModel();
        setSelectedAmount(WIGET_ALL_ANL_ACC, WIGET_SELECTED_ANL_ACC);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Обработка события выбора счетов учета ТМЦ
   *
   * @param event - событие
   */
  public void onActionChooseValAccounts(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccountSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject[] entitys = event.getItems();
        valAccounts = new AccPlan[entitys.length];
        for (int i = 0; i < entitys.length; i++)
          valAccounts[i] = (AccPlan) entitys[i];
        setAllValAcc(false);
        view.flushModel();
        setSelectedAmount(WIGET_ALL_VAL_ACC, WIGET_SELECTED_VAL_ACC);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Обработка события выбора ведомостей дебиторов/кредиторов
   *
   * @param event - событие
   */
  public void onActionChooseDbKtAccounts(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.AccountSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        PersistentObject[] entitys = event.getItems();
        dbKtAccounts = new AccPlan[entitys.length];
        for (int i = 0; i < entitys.length; i++)
          dbKtAccounts[i] = (AccPlan) entitys[i];
        setAllDbKtAcc(false);
        view.flushModel();
        setSelectedAmount(WIGET_ALL_DBKT_ACC, WIGET_SELECTED_DBKT_ACC);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Обработка события выбора ВСЕХ счетов
   *
   * @param event - событие
   */
  public void onActionChooseAllAccounts(WidgetEvent event) throws Exception {
    setSelectedAmount(WIGET_ALL_ACC, WIGET_SELECTED_ACC);
  }

  /**
   * Обработка события выбора ВСЕХ аналитических счетов
   *
   * @param event - событие
   */
  public void onActionChooseAllAnlAccounts(WidgetEvent event) throws Exception {
    setSelectedAmount(WIGET_ALL_ANL_ACC, WIGET_SELECTED_ANL_ACC);
  }

  /**
   * Обработка события выбора ВСЕХ счетов учета ТМЦ
   *
   * @param event - событие
   */
  public void onActionChooseAllValAccounts(WidgetEvent event) throws Exception {
    setSelectedAmount(WIGET_ALL_VAL_ACC, WIGET_SELECTED_VAL_ACC);
  }

  /**
   * Обработка события выбора ВСЕХ ведомостей дебиторов/кредиторов
   *
   * @param event - событие
   */
  public void onActionChooseAllDbKtAccounts(WidgetEvent event) throws Exception {
    setSelectedAmount(WIGET_ALL_DBKT_ACC, WIGET_SELECTED_DBKT_ACC);
  }

  /**
   * Установить количество выбранных счетов
   *
   * @param wigetCheckBoxName - имя CheckBox-элемента
   * @param wigetLabelName    - имя Label-элемента
   */
  private void setSelectedAmount(String wigetCheckBoxName, String wigetLabelName) {
    if (((CheckBox) view.getWidget(wigetCheckBoxName)).getEditorValue() == Boolean.valueOf("true")) //$NON-NLS-1$
      ((Label) view.getWidget(wigetLabelName)).setText(Messages.getInstance().getMessage(Messages.ACC_SELECTED_ACCOUNTS_ALL));
    else {
      if (getSelectedAmount(wigetLabelName) != null)
        ((Label) view.getWidget(wigetLabelName)).setText(String.format(Messages.getInstance().getMessage(Messages.ACC_SELECTED_ACCOUNTS), getSelectedAmount(wigetLabelName)));
      else
        ((Label) view.getWidget(wigetLabelName)).setText(String.format(Messages.getInstance().getMessage(Messages.ACC_SELECTED_ACCOUNTS), 0));
    }
  }

  /**
   * Получить количество выбранных счетов
   *
   * @param wigetLabelName - имя Label-элемента
   * @return количество выбранных счетов
   */
  private Integer getSelectedAmount(String wigetLabelName) {
    if (WIGET_SELECTED_ACC == wigetLabelName && accounts != null)
      return accounts.length;
    if (WIGET_SELECTED_ANL_ACC == wigetLabelName && anlAccounts != null)
      return anlAccounts.length;
    if (WIGET_SELECTED_VAL_ACC == wigetLabelName && valAccounts != null)
      return valAccounts.length;
    if (WIGET_SELECTED_DBKT_ACC == wigetLabelName && dbKtAccounts != null)
      return dbKtAccounts.length;
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    setSelectedAmount(WIGET_ALL_ACC, WIGET_SELECTED_ACC);
    setSelectedAmount(WIGET_ALL_ANL_ACC, WIGET_SELECTED_ANL_ACC);
    setSelectedAmount(WIGET_ALL_VAL_ACC, WIGET_SELECTED_VAL_ACC);
    setSelectedAmount(WIGET_ALL_DBKT_ACC, WIGET_SELECTED_DBKT_ACC);
  }

  // Getter/Setter Methods

  /**
   * @return начальный период переноса остатков
   */
  public com.mg.merp.account.model.Period getAccPeriodFrom() {
    return accPeriodFrom;
  }

  public void setAccPeriodFrom(com.mg.merp.account.model.Period accPeriodFrom) {
    this.accPeriodFrom = accPeriodFrom;
  }

  /**
   * @return конечный период переноса остатков
   */
  public com.mg.merp.account.model.Period getAccPeriodTill() {
    return accPeriodTill;
  }

  public void setAccPeriodTill(com.mg.merp.account.model.Period accPeriodTill) {
    this.accPeriodTill = accPeriodTill;
  }

  /**
   * @return набор счетов
   */
  public com.mg.merp.account.model.AccPlan[] getAccounts() {
    return accounts;
  }

  /**
   * @return набор аналитических счетов
   */
  public com.mg.merp.account.model.AccPlan[] getAnlAccounts() {
    return anlAccounts;
  }

  /**
   * @return набор счетов учета ТМЦ
   */
  public com.mg.merp.account.model.AccPlan[] getValAccounts() {
    return valAccounts;
  }

  /**
   * @param valAccounts the valAccounts to set
   */
  public void setValAccounts(com.mg.merp.account.model.AccPlan[] valAccounts) {
    this.valAccounts = valAccounts;
  }

  /**
   * @return набор ведомостей дебиторов/кредиторов
   */
  public com.mg.merp.account.model.AccPlan[] getDbKtAccounts() {
    return dbKtAccounts;
  }

  /**
   * @param dbKtAccounts the dbKtAccounts to set
   */
  public void setDbKtAccounts(com.mg.merp.account.model.AccPlan[] dbKtAccounts) {
    this.dbKtAccounts = dbKtAccounts;
  }

  /**
   * @return the allAcc
   */
  public boolean isAllAcc() {
    return allAcc;
  }

  /**
   * @param allAcc the allAcc to set
   */
  public void setAllAcc(boolean allAcc) {
    this.allAcc = allAcc;
  }

  /**
   * @return the allAnlAcc
   */
  public boolean isAllAnlAcc() {
    return allAnlAcc;
  }

  /**
   * @param allAnlAcc the allAnlAcc to set
   */
  public void setAllAnlAcc(boolean allAnlAcc) {
    this.allAnlAcc = allAnlAcc;
  }

  /**
   * @return the allDbKtAcc
   */
  public boolean isAllDbKtAcc() {
    return allDbKtAcc;
  }

  /**
   * @param allDbKtAcc the allDbKtAcc to set
   */
  public void setAllDbKtAcc(boolean allDbKtAcc) {
    this.allDbKtAcc = allDbKtAcc;
  }

  /**
   * @return the allValAcc
   */
  public boolean isAllValAcc() {
    return allValAcc;
  }

  /**
   * @param allValAcc the allValAcc to set
   */
  public void setAllValAcc(boolean allValAcc) {
    this.allValAcc = allValAcc;
  }

}
