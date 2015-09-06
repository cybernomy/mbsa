/*
 * FinOperBr.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.finance.OperationModelServiceLocal;
import com.mg.merp.finance.OperationServiceLocal;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.reference.support.ReferenceUtils;

import org.apache.commons.lang.BooleanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера бизнес-компонента "Финансовые операции"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FinOperBr.java,v 1.11 2007/03/12 07:39:36 sharapov Exp $
 */
public class FinOperBr extends DefaultHierarchyBrowseForm {
  private final String INIT_QUERY_TEXT = "select distinct %s from FinOperation fo %s %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public FinOperBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", OperationServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/finance/resources/FinOperRest.mfd.xml"; //$NON-NLS-1$
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
    return ReferenceUtils.loadFolderHierarchy(OperationServiceLocal.FOLDER_PART);
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
    FinOperRest restForm = (FinOperRest) getRestrictionForm();
    StringBuilder whereText = new StringBuilder(" where ").append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "fo.Folder", 0, "folder", folderEntity, paramsName, paramsValue, true)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("fo.KeepDate", restForm.getDateFrom(), restForm.getDateTill(), "dateFrom", "dateTo", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLStringRestriction("fo.BaseDocNumber", restForm.getBaseDocNumber(), "baseDocNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.BaseDocType", restForm.getBaseDocType(), "baseDocType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.BaseDocDate", restForm.getBaseDocDate(), "baseDocDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("fo.ConfirmDocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.ConfirmDocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.ConfirmDocDate", restForm.getDocDate(), "docDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("fo.ContractNumber", restForm.getContractNumber(), "contractNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.ContractType", restForm.getContractType(), "contractType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.ContractDate", restForm.getContractDate(), "contractDate", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.Currency", restForm.getCurCode(), "curCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("fo.SumCur", restForm.getFromSumCur(), restForm.getToSumCur(), "fromSumCur", "toSumCur", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("fo.SumNat", restForm.getFromSumNat(), restForm.getToSumNat(), "fromSumNat", "toSumNat", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.From", restForm.getFromCode(), "fromCode", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.To", restForm.getToCode(), "toCode", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fo.Responsible", restForm.getResponsibleCode(), "responsibleCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAcc", restForm.getSrcAcc(), "srcAcc", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl1", restForm.getAnlLevel1SrcId(), "anlLevel1SrcId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl2", restForm.getAnlLevel2SrcId(), "anlLevel2SrcId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl3", restForm.getAnlLevel3SrcId(), "anlLevel3SrcId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl4", restForm.getAnlLevel4SrcId(), "anlLevel4SrcId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl5", restForm.getAnlLevel5SrcId(), "anlLevel5SrcId", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAcc", restForm.getDstAcc(), "dstAcc", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAnl1", restForm.getAnlLevel1DstId(), "anlLevel1DstId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAnl2", restForm.getAnlLevel2DstId(), "anlLevel2DstId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAnl3", restForm.getAnlLevel3DstId(), "anlLevel3DstId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAnl4", restForm.getAnlLevel4DstId(), "anlLevel4DstId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("fs.DstAnl5", restForm.getAnlLevel5DstId(), "anlLevel5DstId", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "fo.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$
    if (restForm.getKind() != 0) {
      whereText = whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("fo.Planned", BooleanUtils.toBoolean(restForm.getKind(), 2, 1), "kind", paramsName, paramsValue, false));         //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (restForm.getSrcAcc() != null || restForm.getDstAcc() != null || restForm.getFeatAcc() != null) {
      fromList = fromList.concat(", Specification fs "); //$NON-NLS-1$
      whereText = whereText.append(" and fs.FinOper = fo.Id");     //$NON-NLS-1$

    }
    if (restForm.getFeatAcc() != null) {
      whereText = whereText.append(" and fs.Parent is not null "). //$NON-NLS-1$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAcc", restForm.getFeatAcc(), "featAcc", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl1", restForm.getAnlLevel1FeatId(), "anlLevel1FeatId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl2", restForm.getAnlLevel2FeatId(), "anlLevel2FeatId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl3", restForm.getAnlLevel3FeatId(), "anlLevel3FeatId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl4", restForm.getAnlLevel4FeatId(), "anlLevel4FeatId", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
          append(DatabaseUtils.formatEJBQLObjectRestriction("fs.SrcAnl5", restForm.getAnlLevel5FeatId(), "anlLevel5FeatId", paramsName, paramsValue, false));             //$NON-NLS-1$ //$NON-NLS-2$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
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
        result.add(new TableEJBQLFieldDef(FinOperation.class, "Id", "fo.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "Planned", "fo.Planned", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "KeepDate", "fo.KeepDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "Comment", "fo.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "SumNat", "fo.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "SumCur", "fo.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "CurRate", "fo.CurRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "Currency", "cur.Code", "left join fo.Currency as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "BaseDocType", "bdt.Code", "left join fo.BaseDocType as bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "BaseDocDate", "fo.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "BaseDocNumber", "fo.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ContractType", "ct.Code", "left join fo.ContractType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ContractNumber", "fo.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ContractDate", "fo.ContractDate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ConfirmDocType", "cdt.Code", "left join fo.ConfirmDocType as cdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ConfirmDocNumber", "fo.ConfirmDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "ConfirmDocDate", "fo.ConfirmDocDate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "From", "f.Code", "left join fo.From as f", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "To", "t.Code", "left join fo.To as t", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FinOperation.class, "Responsible", "res.Code", "left join fo.Responsible as res", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

  public void onActionShowFinOper(WidgetEvent event) throws Exception {
    final OperationModelServiceLocal service = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/OperationModel"); //$NON-NLS-1$
    FinOperModelBr form = (FinOperModelBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    form.run();
  }

  /**
   * Обработка события выбора пункта контекстного меню "Вставка с образцом"
   *
   * @param event - событие
   */
  public void onActionInsertFinOperPattern(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.finance.support.ui.FinOperModelSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        internalInsertFinOperPattern((OperationModel) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Создание операции по образцу и ее редактирование
   *
   * @param pattern - образец
   */
  private void internalInsertFinOperPattern(OperationModel pattern) {
    pattern = ServerUtils.getPersistentManager().find(OperationModel.class, pattern.getId());
    final FinOperation economicOper = ((OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Operation")).createByPattern(pattern, (Folder) folderEntity); //$NON-NLS-1$

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


}

