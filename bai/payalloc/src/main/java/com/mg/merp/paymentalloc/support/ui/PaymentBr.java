/*
 * PaymentBr.java
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
package com.mg.merp.paymentalloc.support.ui;

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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.paymentalloc.PaymentModelServiceLocal;
import com.mg.merp.paymentalloc.PaymentServiceLocal;
import com.mg.merp.paymentalloc.model.Payment;

import org.apache.commons.lang.BooleanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера бизнес-компонента "Журнал платежей"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PaymentBr.java,v 1.11 2008/03/18 15:53:41 alikaev Exp $
 */
public class PaymentBr extends DefaultHierarchyBrowseForm {

  private final String INIT_QUERY_TEXT = "select distinct %s from Payment p %s where p.IsModel = 0 %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public PaymentBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
    treeUIProperties.put("FolderType", PaymentServiceLocal.FOLDER_PART); //$NON-NLS-1$
    tree.setParentPropertyName("Folder.Id"); //$NON-NLS-1$
    restrictionFormName = "com/mg/merp/paymentalloc/resources/PaymentRest.mfd.xml"; //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#initializeMaster(java.io.Serializable)
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
    return CoreUtils.loadFolderHierarchy(PaymentServiceLocal.FOLDER_PART);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /*
       * (non-Javadoc)
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
        result.add(new TableEJBQLFieldDef(Payment.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Planned", "p.Planned", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "PDate", "p.PDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurCode", "p.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRateType", "p.CurRateType.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRateAuthority", "p.CurRateAuthority.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "CurRate", "p.CurRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "SumCur", "p.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "SumNat", "p.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocType", "dt.Code", "left join p.DocType dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocNumber", "p.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "DocDate", "p.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocType", "bdt.Code", "left join p.BaseDocType bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocNumber", "p.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "BaseDocDate", "p.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractType", "ct.Code", "left join p.ContractType ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractNumber", "p.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractDate", "p.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractorFrom", "cfrom.Code", "left join p.ContractorFrom as cfrom", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "ContractorTo", "cto.Code", "left join p.ContractorTo as cto", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Payment.class, "Description", "p.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Payment.class, "Comments", "p.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setQuery(java.lang.String)
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    StringBuilder whereText = new StringBuilder();
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

    paramsName.clear();
    paramsValue.clear();
    PaymentRest restForm = (PaymentRest) this.getRestrictionForm();

    whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(((HierarchyRestrictionSupport) restForm).isUseHierarchy(), "p.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("p.PDate", restForm.getDate1(), restForm.getDate2(), "date1", "date2", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRangeRestriction("p.SumNat", restForm.getSum1(), restForm.getSum2(), "sum1", "sum2", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.ContractorFrom", restForm.getFromCode(), "fromCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.ContractorTo", restForm.getToCode(), "toCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("p.Description", restForm.getDescription(), "description", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("p.Name", restForm.getName(), "name", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.DocType", restForm.getDocType(), "docType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("p.DocNumber", restForm.getDocNumber(), "docNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.DocDate", restForm.getDocDate(), "docDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.ContractType", restForm.getContractType(), "contractType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("p.ContractNumber", restForm.getContractNumber(), "contractNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.ContractDate", restForm.getContractDate(), "contractDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.BaseDocType", restForm.getBaseDocType(), "baseDocType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("p.BaseDocNumber", restForm.getBaseDocNumber(), "baseDocNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("p.BaseDocDate", restForm.getBaseDocDate(), "baseDocDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocHead.DocType", restForm.getLinkedType(), "linkedType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLStringRestriction("d.DocHead.DocNumber", restForm.getLinkedNumber(), "linkedNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLObjectRestriction("d.DocHead.DocDate", restForm.getLinkedDate(), "linkedDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
        append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "p.Id", restForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

    if (restForm.getLinkedDate() != null || restForm.getLinkedType() != null || !StringUtils.stringNullOrEmpty(restForm.getLinkedNumber())) { //$NON-NLS-1$
      fromList = (", TransactHead as d ").concat(fromList); //$NON-NLS-1$
      whereText.append(" and d.Payment = p.Id "); //$NON-NLS-1$
    }
    if (restForm.getKind() != 0)
      whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("p.Planned", BooleanUtils.toBoolean(restForm.getKind(), 1, 2), "kind", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
    if (restForm.getAllocKind() != 0) {
      if (!fromList.contains("TransactHead")) { //$NON-NLS-1$
        fromList = (", TransactHead as d ").concat(fromList); //$NON-NLS-1$
        whereText.append(" and d.Payment = p.Id "); //$NON-NLS-1$
      }
      if (restForm.getAllocKind() == 1)
        whereText.append(" and d.AllocSumNat = d.TotalSumNat "); //$NON-NLS-1$
      if (restForm.getAllocKind() == 2)
        whereText.append(" and d.AllocSumNat <> d.TotalSumNat "); //$NON-NLS-1$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /**
   * Обработчик пункта КМ "Образцы записей"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionShowModel(WidgetEvent event) throws Exception {
    final PaymentModelServiceLocal service = (PaymentModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentalloc/PaymentModel"); //$NON-NLS-1$
    PaymentModelBr form = (PaymentModelBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    form.run();
  }

  /**
   * Обработчик пункта КМ "Вставка с образцом"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionInsertModel(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentalloc.support.ui.PaymentModelSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {
      public void searchPerformed(SearchHelpEvent event) {
        doOnActionInsertModel((Payment) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Создание записи журнала по образцу
   *
   * @param model - образец
   */
  private void doOnActionInsertModel(Payment model) {
    PaymentServiceLocal service = (PaymentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentalloc/Payment"); //$NON-NLS-1$
    Payment payment = service.createByPattern(model, (Folder) folderEntity);
    MaintenanceHelper.add(service, payment, null, new MaintenanceFormActionListener() {

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

}

