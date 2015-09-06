/*
 * EconomicOperBr.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBoxMenuItem;
import com.mg.framework.api.ui.widget.SplitPane;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.OperationModelServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.support.Messages;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.support.CoreUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера бизнес-компонента "Хозяйственные операции"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: EconomicOperBr.java,v 1.14 2009/03/11 13:40:30 sharapov Exp $
 */
public class EconomicOperBr extends DefaultHierarchyBrowseForm {
  /**
   * наименование таблицы спецификаций
   */
  protected final String SPEC_TABLE_WIDGET = "spec";
  private final String INIT_QUERY_TEXT = "select distinct %s from EconomicOper eo %s %s"; //$NON-NLS-1$
  private final String IS_SHOW_SPEC_TABLE = "isShowSpecTable";
  private final String SPLIT_CONTAINER_NAME = "tables";
  private final String VIEW_DOCUMENTLINE_LIST_MENUITEM = "viewDocumentLineList";
  /**
   * таблица спецификаций
   */
  protected MaintenanceTableController spec;

  /**
   * Признак отображения таблицы спецификаций
   */
  protected boolean isShowSpecTable = false;

  /**
   * инициализирующие свойства спецификации
   */
  protected AttributeMap specProperties = new LocalDataTransferObject();
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private StringBuilder accWhereText = new StringBuilder();
  private EconomicSpecServiceLocal specService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$


  @SuppressWarnings("unchecked")
  public EconomicOperBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", OperationServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/account/resources/EconomicOperRest.mfd.xml"; //$NON-NLS-1$

    spec = new MaintenanceTableController(specProperties); //createEconomicSpecTableController();
    table.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        if (isShowSpecTable) {
          specProperties.put("EconomicOper.Id", event.getModelKey());
          spec.fireMasterChange(event);
        }
      }
    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    isShowSpecTable = view.getUIProfile().getProperty(IS_SHOW_SPEC_TABLE, false);
    spec.initController(specService, createGoodsDocSpecTableModel());
    super.doOnRun();
    ((CheckBoxMenuItem) view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(VIEW_DOCUMENTLINE_LIST_MENUITEM)).setSelected(isShowSpecTable);
    setVisibleSpec(isShowSpecTable);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster()
   */
  @Override
  protected void initializeMaster(PersistentObject master) {
    uiProperties.put("Folder", master); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() throws ApplicationException {
    return CoreUtils.loadFolderHierarchy(OperationServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    EconomicOperRest restForm = (EconomicOperRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "eo.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("eo.KeepDate", restForm.getDateFrom(), restForm.getDateTill(), "dateFrom", "dateTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLStringRestriction("eo.BaseDocNumber", restForm.getBaseDocNumber(), "baseDocNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.BaseDocType", restForm.getBaseDocType(), "baseDocType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.BaseDocDate", restForm.getBaseDocDate(), "baseDocDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("eo.ConfirmDocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.ConfirmDocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.ConfirmDocDate", restForm.getDocDate(), "docDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("eo.ContractNumber", restForm.getContractNumber(), "contractNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.ContractType", restForm.getContractType(), "contractType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.ContractDate", restForm.getContractDate(), "contractDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.SpecMark", restForm.getSpecMark(), "specMark", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("eo.Summa", restForm.getFromSum(), restForm.getToSum(), "fromSum", "toSum", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.From", restForm.getFromCode(), "fromCode", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("eo.To", restForm.getToCode(), "toCode", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("es.Catalog", restForm.getCatalogCode(), "catalogCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("es.Catalog.Folder", restForm.getCatalogFolder(), "catalogFolder", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "eo.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

    accWhereText.setLength(0);
    Set<Integer> accDbIdList = restForm.getSelectedAccDbIds();
    if (!accDbIdList.isEmpty()) {
      prepareAccWhereText("es.AccDb.Id in (:accDbIdList)", "accDbIdList", accDbIdList, true);
      prepareAccWhereText("es.AnlDb1.Id in (:anlDb1IdList)", "anlDb1IdList", restForm.getSelectedDbAnl1Ids(), false);
      prepareAccWhereText("es.AnlDb2.Id in (:anlDb2IdList)", "anlDb2IdList", restForm.getSelectedDbAnl2Ids(), false);
      prepareAccWhereText("es.AnlDb3.Id in (:anlDb3IdList)", "anlDb3IdList", restForm.getSelectedDbAnl3Ids(), false);
      prepareAccWhereText("es.AnlDb4.Id in (:anlDb4IdList)", "anlDb4IdList", restForm.getSelectedDbAnl4Ids(), false);
      prepareAccWhereText("es.AnlDb5.Id in (:anlDb5IdList)", "anlDb5IdList", restForm.getSelectedDbAnl5Ids(), false);
    }
    Set<Integer> accKtIdList = restForm.getSelectedAccKtIds();
    if (!accKtIdList.isEmpty()) {
      prepareAccWhereText("es.AccKt.Id in (:accKtIdList)", "accKtIdList", accKtIdList, accDbIdList.isEmpty());
      prepareAccWhereText("es.AnlKt1.Id in (:anlKt1IdList)", "anlKt1IdList", restForm.getSelectedKtAnl1Ids(), false);
      prepareAccWhereText("es.AnlKt2.Id in (:anlKt2IdList)", "anlKt2IdList", restForm.getSelectedKtAnl2Ids(), false);
      prepareAccWhereText("es.AnlKt3.Id in (:anlKt3IdList)", "anlKt3IdList", restForm.getSelectedKtAnl3Ids(), false);
      prepareAccWhereText("es.AnlKt4.Id in (:anlKt4IdList)", "anlKt4IdList", restForm.getSelectedKtAnl4Ids(), false);
      prepareAccWhereText("es.AnlKt5.Id in (:anlKt5IdList)", "anlKt5IdList", restForm.getSelectedKtAnl5Ids(), false);
    }
    if (accWhereText.length() != 0)
      whereText = whereText.append(" and ( ").append(accWhereText).append(" )");

    if (whereText.indexOf("Catalog") != -1 || accWhereText.length() != 0) {
      fromList = (", EconomicSpec as es ").concat(fromList);
      whereText = whereText.append(" and es.EconomicOper = eo.id ");
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

  private void prepareAccWhereText(String wherePartText, String paramName, Collection<Integer> idList, boolean isFirstRestriction) {
    if (!idList.isEmpty()) {
      paramsName.add(paramName);
      paramsValue.add(idList);
      if (!isFirstRestriction)
        accWhereText.append(" and ");
      accWhereText.append(wherePartText);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "Id", "eo.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "KeepDate", "eo.KeepDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "Comment", "eo.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "SpecMark", "sm.Code", "left join eo.SpecMark as sm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocType", "bdt.Code", "left join eo.BaseDocType as bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocDate", "eo.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocNumber", "eo.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractType", "ct.Code", "left join eo.ContractType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractNumber", "eo.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractDate", "eo.ContractDate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocType", "cdt.Code", "left join eo.ConfirmDocType as cdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocNumber", "eo.ConfirmDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocDate", "eo.ConfirmDocDate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "Summa", "eo.Summa", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "From", "f.Code", "left join eo.From as f", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicOper.class, "To", "t.Code", "left join eo.To as t", false));                     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    };
  }

  public void onActionShowEconomicOper(WidgetEvent event) throws Exception {
    final OperationModelServiceLocal service = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/OperationModel"); //$NON-NLS-1$
    EconomicOperModelBr form = (EconomicOperModelBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    form.run();
  }

  /**
   * Обработка события выбора "Вставка с образцом"
   *
   * @param event - событие
   */
  public void onActionInsertEconomicOperPattern(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.account.support.ui.EconomicModelSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        internalInsertEconomicOperPattern((EconomicOperModel) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * создание хоз. операции по образцу и ее редактирование
   *
   * @param model - образец хоз. операции
   */
  private void internalInsertEconomicOperPattern(EconomicOperModel model) {
    model = ServerUtils.getPersistentManager().find(EconomicOperModel.class, model.getId());
    final EconomicOper economicOper = ((OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation")).createByPattern(model, (Folder) folderEntity); //$NON-NLS-1$

    MaintenanceHelper.edit(service, economicOper.getId(), null, new MaintenanceFormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
       */
      public void canceled(MaintenanceFormEvent event) {
        // текущая реализация не поддерживает обработку события "Отмена"
        // при отмене операции "Вставка с образцом" нужно удалить вручную созданную сущность
        service.erase(economicOper.getId());
        table.refresh();
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
   * Обработчик копирование со сторонированием
   */
  public void onActionСloneStorno(WidgetEvent event) {
    final OperationServiceLocal operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation");
    if (getSearchedEntities().length == 1) {
      final EconomicOper economicOper = operationService.storno((EconomicOper) getSearchedEntities()[0]);
      if (economicOper != null) {
        ServerUtils.getPersistentManager().flush();
        MaintenanceHelper.edit(operationService, economicOper.getId(), null, new MaintenanceFormActionListener() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
           */
          public void canceled(MaintenanceFormEvent event) {
            operationService.erase(economicOper.getId());
          }

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
           */
          public void performed(MaintenanceFormEvent event) {
            table.refresh();
          }
        });
      }
    } else
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_SEARCHED_ENTITIES));
  }

  /**
   * Обработчик пункта КМ "Показать спецификацию"
   *
   * @param event - событие
   */
  protected void onActionViewDocumentLineList(WidgetEvent event) {
    boolean selected = ((CheckBoxMenuItem) event.getWidget()).isSelected();
    if (isShowSpecTable != selected) {
      if (selected) {
        Serializable[] economicOperIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
        if (economicOperIds.length == 1) {
          Serializable economicOperId = economicOperIds[0];
          ((MaintenanceTableModel) spec.getModel()).setCurrentMaster(economicOperId);
          spec.refresh();
        }
      }
      isShowSpecTable = selected;
      setVisibleSpec(isShowSpecTable);
    }
  }

  /**
   * Установка значения видимости таблицы
   *
   * @param isVisible - <code>true</code> - видна, иначе не видна
   */
  private void setVisibleSpec(boolean isVisible) {
    Widget widget = view.getWidget(SPEC_TABLE_WIDGET);
    if (widget != null)
      widget.setVisible(isVisible);
    this.isShowSpecTable = isVisible;
    view.getUIProfile().setProperty(IS_SHOW_SPEC_TABLE, isVisible);
    ((SplitPane) view.getWidget(SPLIT_CONTAINER_NAME)).setDividerLocation(isVisible ? 62 : 100);
  }

  protected DefaultMaintenanceEJBQLTableModel createGoodsDocSpecTableModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from EconomicSpec es %s where es.EconomicOper.Id = :economicOperId"; //$NON-NLS-1$

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Id", "es.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccDb", "es.AccDb.Acc", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccKt", "es.AccKt.Acc", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb1", "db1.Code", "left join es.AnlDb1 as db1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb2", "db2.Code", "left join es.AnlDb2 as db2", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb3", "db3.Code", "left join es.AnlDb3 as db3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb4", "db4.Code", "left join es.AnlDb4 as db4", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlDb5", "db5.Code", "left join es.AnlDb5 as db5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt1", "kt1.Code", "left join es.AnlKt1 as kt1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt2", "kt2.Code", "left join es.AnlKt2 as kt2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt3", "kt3.Code", "left join es.AnlKt3 as kt3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt4", "kt4.Code", "left join es.AnlKt4 as kt4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AnlKt5", "kt5.Code", "left join es.AnlKt5 as kt5", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog", "cat.Code", "left join es.Catalog as cat", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog.FullName", "es.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Catalog.Measure1", "meas.Code", "left join es.Catalog.Measure1 as meas", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "AccDb.Currency", "cur.Code", "left join es.AccDb.Currency as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "CurCource", "es.CurCource", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "SummaCur", "es.SummaCur", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "SummaNat", "es.SummaNat", false));     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(EconomicSpec.class, "Quantity", "es.Quantity", false));                     //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (getMasterKey() != null)
          setQuery(createQueryText(), new String[]{"economicOperId"}, new Object[]{getMasterKey()});
        else {
          this.rowList.clear();
          fireModelChange();
        }
      }
    };
  }
}