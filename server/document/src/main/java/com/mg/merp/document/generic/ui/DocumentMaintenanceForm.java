/*
 * DocumentMaintenanceForm.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Базовый класс контроллера формы поддержки документов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocumentMaintenanceForm.java,v 1.8 2008/08/01 08:02:48 safonov Exp $
 */
public class DocumentMaintenanceForm extends DefaultMaintenanceForm {
  private static final String SCHEDULE_BUTTON = "Schedule"; //$NON-NLS-1$
  private static final String HISTORY_BUTTON = "History"; //$NON-NLS-1$

  private static final String CONTRACT_TYPE_WIDGET_NAME = "ContractType"; //$NON-NLS-1$
  private static final String CONTRACT_NUMBER_WIDGET_NAME = "ContractNumber"; //$NON-NLS-1$
  private static final String CONTRACT_DATE_WIDGET_NAME = "ContractDate"; //$NON-NLS-1$

  private static final String BASE_DOC_TYPE_WIDGET_NAME = "BaseDocType"; //$NON-NLS-1$
  private static final String BASE_DOC_NUMBER_WIDGET_NAME = "BaseDocNumber"; //$NON-NLS-1$
  private static final String BASE_DOC_DATE_WIDGET_NAME = "BaseDocDate"; //$NON-NLS-1$


  /**
   * контекст импорта для SearchHelp поля "от кого"
   */
  protected String[] contractorFromKinds;
  /**
   * контекст импорта для SearchHelp поля "кому"
   */
  protected String[] contractorToKinds;

  public DocumentMaintenanceForm() {
    super();
    setMasterDetail(true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    Widget widget = view.getWidget(SCHEDULE_BUTTON);
    if (widget != null)
      widget.setEnabled(!readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    MaintenanceAction mtAction = getAction();
    DocHead docHead = (DocHead) getEntity();
    //проверим доступность редактирования документа (http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4200)
    if (MaintenanceAction.EDIT == mtAction)
      DocFlowHelper.checkStatus(docHead);

    super.doOnRun();

    if (MaintenanceAction.EDIT == mtAction) {
      if (docHead.getContract() != null)
        setContractFieldsEnabled(false);
      if (docHead.getBaseDocument() != null)
        setBaseDocumentFieldsEnabled(false);
    }

    if (MaintenanceAction.ADD == mtAction) {
      Widget widget = view.getWidget(HISTORY_BUTTON);
      if (widget != null)
        widget.setEnabled(false);
    }
  }

  /**
   * Сделать доступными/не доступными для редактирования поля док-та Контракт(тип, номер, дата)
   *
   * @param isEnabled - признак доступности для редактирования
   */
  protected void setContractFieldsEnabled(boolean isEnabled) {
    view.getWidget(CONTRACT_TYPE_WIDGET_NAME).setReadOnly(!isEnabled);
    view.getWidget(CONTRACT_NUMBER_WIDGET_NAME).setEnabled(isEnabled);
    view.getWidget(CONTRACT_DATE_WIDGET_NAME).setReadOnly(!isEnabled);
  }

  /**
   * Сделать доступными/не доступными для редактирования поля док-та Документ-основание(тип, номер,
   * дата)
   *
   * @param isEnabled - признак доступности для редактирования
   */
  protected void setBaseDocumentFieldsEnabled(boolean isEnabled) {
    view.getWidget(BASE_DOC_TYPE_WIDGET_NAME).setReadOnly(!isEnabled);
    view.getWidget(BASE_DOC_NUMBER_WIDGET_NAME).setEnabled(isEnabled);
    view.getWidget(BASE_DOC_DATE_WIDGET_NAME).setReadOnly(!isEnabled);
  }

  /**
   * обработчик показа истории ДО
   */
  public void onActionShowHistory(WidgetEvent event) {
    DocFlowHelper.showDocumentHistory(((DocHead) getEntity()).getId());
  }

  /**
   * обработчик смены курса валюты
   */
  public void onActionChooseCurrencyRate(WidgetEvent event) {
    DocumentUtils.setExchangeRate(getService(), (DocHead) getEntity());
    view.flushModel();
  }

  /**
   * Обработчик просмотра/выбора контракта
   *
   * @param event - событие
   */
  public void onActionViewOrChooseContract(WidgetEvent event) {
    final DocHead docHead = (DocHead) getEntity();
    if (docHead.getContract() != null)
      DocumentUtils.viewContract(docHead);
    else
      DocumentUtils.chooseContract(new SearchHelpListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchCanceled(SearchHelpEvent event) {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchPerformed(SearchHelpEvent event) {
          DocHead contract = (DocHead) event.getItems()[0];
          docHead.setContractType(contract.getDocType());
          docHead.setContractNumber(contract.getDocNumber().trim());
          docHead.setContractDate(contract.getDocDate());
          view.flushModel();
        }
      });
  }

  /**
   * Обработчик просмотра документа-основания
   *
   * @param event - событие
   */
  public void onActionViewBaseDocument(WidgetEvent event) {
    DocumentUtils.viewBaseDocument((DocHead) getEntity());
  }

}
