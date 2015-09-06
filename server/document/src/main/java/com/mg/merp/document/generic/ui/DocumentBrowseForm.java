/*
 * DocumentBrowseForm.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.Form;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.Document;
import com.mg.merp.document.LBScheduleManager;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.document.support.LBScheduleManagerLocator;
import com.mg.merp.reference.AttachmentHandler;
import com.mg.merp.reference.support.ui.AttachmentHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Базовый класс контроллера формы списка документов
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: DocumentBrowseForm.java,v 1.19 2008/08/15 06:24:54 sharapov Exp $
 */
public class DocumentBrowseForm extends DefaultHierarchyBrowseForm {
  protected String INIT_QUERY_TEXT = "select distinct %s from DocHead d %s %s"; //$NON-NLS-1$
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected StringBuilder whereText;
  protected String fieldsList;
  protected String fromList;
  protected Set<TableEJBQLFieldDef> fieldDefs;
  protected DocumentRest restDocument;

  public DocumentBrowseForm() {
    super();
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#initializeMaster(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master); //$NON-NLS-1$
  }

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    restDocument = (DocumentRest) getRestrictionForm();
    paramsName.clear();
    paramsValue.clear();

    whereText = new StringBuilder(" where ") //$NON-NLS-1$
        .append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restDocument).isUseHierarchy(), "d.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocSection", getRestDocSection(), "docSection", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("d.DocDate", restDocument.getDocDateFrom(), restDocument.getDocDateTill(), "actDateFrom", "actDateTill", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.SysCompany", restDocument.getSysCompany(), "sysCompany", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLStringRestriction("d.DocNumber", restDocument.getDocNumber(), "docNumber", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocType", restDocument.getDocType(), "docType", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.BaseDocDate", restDocument.getBaseDocDate(), "baseDocDate", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLStringRestriction("d.BaseDocNumber", restDocument.getBaseDocNumber(), "baseDocNumber", paramsName, paramsValue, false))         //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.BaseDocType", restDocument.getBaseDocType(), "baseDocType", paramsName, paramsValue, false))         //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.From", restDocument.getFromCode(), "fromCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.To", restDocument.getToCode(), "toCode", paramsName, paramsValue, false))         //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("d.SumCur", restDocument.getSumCurMin(), restDocument.getSumCurMax(), "sumCurMin", "sumCurMax", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("d.SumNat", restDocument.getSumNatMin(), restDocument.getSumNatMax(), "sumNatMin", "sumNatMax", paramsName, paramsValue, false))         //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.Currency", restDocument.getСurCode(), "сurCode", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "d.Id", restDocument.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
    if (restDocument.getDocProcessStage() != null) {
      paramsName.add("docProcessStage"); //$NON-NLS-1$
      paramsValue.add(restDocument.getDocProcessStage());
      boolean isDpsCompletedPartly = restDocument.getIsDpsCompletedPartly();
      if (!restDocument.getIsDpsCompleted() && !isDpsCompletedPartly)
        whereText.append(" and (not exists (select da from com.mg.merp.docprocess.model.DocAction da where (da.DocHead = d) and (da.Stage = :docProcessStage)))"); //$NON-NLS-1$
      else {
        fromList = fromList.concat(", com.mg.merp.docprocess.model.DocAction da "); //$NON-NLS-1$
        whereText.append(" and (da.DocHead = d) and (da.Stage = :docProcessStage) and "); //$NON-NLS-1$
        StringBuilder stageStateWhereClause = new StringBuilder();
        if (isDpsCompletedPartly)
          stageStateWhereClause.append("(da.StageState = com.mg.merp.docprocess.model.StageState.PARTITION)"); //$NON-NLS-1$
        if (restDocument.getIsDpsCompleted()) {
          if (isDpsCompletedPartly)
            stageStateWhereClause.append(" or "); //$NON-NLS-1$
          stageStateWhereClause.append("(da.StageState = com.mg.merp.docprocess.model.StageState.COMPLETE)"); //$NON-NLS-1$
        }
        whereText.append("(").append(stageStateWhereClause).append(")"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  protected DocSection getRestDocSection() {
    return ((Document) service).getDocSection();
  }

  public void onActionExecuteDocFlow(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length >= 1)
      DocFlowHelper.execute(docIds[0]);
  }

  public void onActionRollbackDocFlow(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length >= 1)
      DocFlowHelper.rollback(docIds[0]);
  }

  public void onActionDocFlowHistory(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length >= 1)
      DocFlowHelper.showDocumentHistory(docIds[0]);
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public void onActionShowDocumentPattern(WidgetEvent event) {
    Document document = (Document) service;
    Form form = ApplicationDictionaryLocator.locate().getBrowseForm(document.getPatternService(), null);
    form.run();
  }

  /**
   * Обработка события добавления документа по образцу
   *
   * @param event - событие
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public void onActionInsertDocumentPattern(WidgetEvent event) throws Exception {
    Document document = (Document) service;
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.document.support.ui.UniversalDocModelSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        internalInsertDocumentPattern((DocHeadModel) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    Map<String, Object> context = new HashMap<String, Object>();
    context.put("DocSection", document.getDocSection()); //$NON-NLS-1$
    searchHelp.setImportContext(context);
    searchHelp.search();
  }

  /**
   * Создание документа по образцу и его редактирование
   *
   * @param docHeadModel - образец документа
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private void internalInsertDocumentPattern(DocHeadModel docHeadModel) {
    DocHead docHead = ((Document) service).createByPattern(docHeadModel, (Folder) folderEntity);

    MaintenanceHelper.add(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead, null, new MaintenanceFormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
       */
      public void canceled(MaintenanceFormEvent event) {
        // do nothing
      }

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
       */
      public void performed(MaintenanceFormEvent event) {
        table.refresh();
      }
    });
  }

  /**
   * Обработка события КМ "Показать оригинал"
   *
   * @param event - событие
   */
  public void onActionShowOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.show(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), (AttachmentHandler) service);
  }

  /**
   * Обработка события КМ "Загрузить оригинал в систему"
   *
   * @param event - событие
   */
  public void onActionDownloadOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.download(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), (AttachmentHandler) service);
  }

  /**
   * Обработка события КМ "Сохранить оригинал как..."
   *
   * @param event - событие
   */
  public void onActionUploadOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.upload(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), (AttachmentHandler) service);
  }

  /**
   * Обработка события КМ "Удалить оригинал"
   *
   * @param event - событие
   */
  public void onActionRemoveOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.remove(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys(), (AttachmentHandler) service);
  }

  /**
   * Обработка события КМ "Создать ГИО"
   *
   * @param event - событие
   */
  public void onActionCreateLBSchedule(WidgetEvent event) {
    getLBScheduleManager().createLBSchedule(getSearchedDocHead());
  }

  /**
   * Обработка события КМ "Открыть ГИО"
   *
   * @param event - событие
   */
  public void onActionOpenLBSchedule(WidgetEvent event) {
    getLBScheduleManager().openLBSchedule(getSearchedDocHead());
  }

  /**
   * Обработка события КМ "Удалить ГИО"
   *
   * @param event - событие
   */
  public void onActionRemoveLBSchedule(WidgetEvent event) {
    final DocHead searchedDocHead = getSearchedDocHead();
    if (searchedDocHead == null)
      return;

    final String ERASE_LBSCHEDULE_QUESTION = com.mg.merp.document.support.Messages.getInstance().getMessage(com.mg.merp.document.support.Messages.ERASE_LBSCHEDULE_QUESTION);
    Messages msg = Messages.getInstance();
    final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT);
    UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(Messages.ERASE_ALERT_TITLE), ERASE_LBSCHEDULE_QUESTION, yesButton, msg.getMessage(Messages.NO_BUTTON_TEXT), new AlertListener() {
      public void alertClosing(String value) {
        if (value.equals(yesButton)) {
          getLBScheduleManager().removeLBSchedule(searchedDocHead);
        }
      }
    });
  }

  /**
   * Обработка события "Показать документ-основание"
   *
   * @param event событие
   */
  public void onActionShowBaseDocument(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length >= 1)
      DocumentUtils.viewBaseDocument((DocHead) service.load(docIds[0]));
  }

  /**
   * Обработка события "Показать контракт"
   *
   * @param event событие
   */
  public void onActionShowContract(WidgetEvent event) {
    Serializable[] docIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (docIds.length >= 1)
      DocumentUtils.viewContract((DocHead) service.load(docIds[0]));
  }

  private LBScheduleManager getLBScheduleManager() {
    return LBScheduleManagerLocator.locate();
  }

  private DocHead getSearchedDocHead() {
    PersistentObject[] searchedEntities = getSearchedEntities();
    if (searchedEntities != null && searchedEntities.length > 0)
      return (DocHead) searchedEntities[0];
    else
      return null;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#getRestrictionForm()
   */
  @Override
  protected RestrictionForm getRestrictionForm() {
    DocumentRest docRest = (DocumentRest) super.getRestrictionForm();
    docRest.setDocSection(getRestDocSection());
    return docRest;
  }

}
