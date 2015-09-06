/*
 * PmcPlaningDlg.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityModelServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.PeriodServiceLocal;
import com.mg.merp.paymentcontrol.PmcHelperListener;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.SelectionRowListener;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PlanPaymentItem;
import com.mg.merp.paymentcontrol.model.PmcConfig;
import com.mg.merp.paymentcontrol.model.PmcPeriod;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TurnResult;
import com.mg.merp.paymentcontrol.model.Version;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Контроллер формы "Планирование платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PmcPlaningDlg.java,v 1.2 2007/06/04 16:09:00 sharapov Exp $
 */
public class PmcPlaningDlg extends AbstractForm {

  @DataItemName("PaymentControl.PmcPeriod") //$NON-NLS-1$
  private PmcPeriod horizont;
  private Integer level;
  private Integer versionId;

  private Integer planPaymentItemGenId = 0;
  private Date executionDateFrom;
  private Date executionDateTill;
  private Integer executionResourceId;
  private Integer resourceFolderId;
  private PlanPaymentItem selectedPlanPaymentItem;
  private List<PlanPaymentItem> planPaymentList;

  private PersistentObject folderEntity = null;
  private DefaultTableController planTable;
  private PmcPlaningRest pmcPlaningRest = (PmcPlaningRest) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcPlaningRest.mfd.xml"); //$NON-NLS-1$

  private MaintenanceTableController liabilityTable;
  private AttributeMap liabilityServiceProperties = new LocalDataTransferObject();
  private LiabilityServiceLocal liabilityService = (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Liability"); //$NON-NLS-1$
  private LiabilityRest liabilityRestForm = (LiabilityRest) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/LiabilityRest.mfd.xml"); //$NON-NLS-1$

  private MaintenanceTableController executionTable;
  private AttributeMap executionServiceProperties = new LocalDataTransferObject();
  private ExecutionServiceLocal executionService = (ExecutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Execution"); //$NON-NLS-1$

  private PeriodServiceLocal periodService = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PeriodServiceLocal.LOCAL_SERVICE_NAME);
  private ResourceServiceLocal resourceService = (ResourceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ResourceServiceLocal.LOCAL_SERVICE_NAME);


  public PmcPlaningDlg() {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    planTable = new DefaultTableController(new PlanPaymentTableModel(new SelectionRowListener() {

      /* (non-Javadoc)
       * @see com.mg.merp.paymentcontrol.SelectionRowListener#selectedRowChange(com.mg.merp.paymentcontrol.support.ui.PlanPaymentItem)
       */
      public void selectedRowChange(PlanPaymentItem selectedItem) {
        selectedPlanPaymentItem = selectedItem;
        executionDateFrom = selectedItem.getDateFrom();
        executionDateTill = selectedItem.getDateTill();
        executionResourceId = selectedItem.getResourceId();
        resourceFolderId = selectedItem.getResourceFolderId();
        if (executionResourceId != null || resourceFolderId != null)
          executionTable.refresh();
      }

    }));

    pmcPlaningRest.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        globalRefresh();
      }
    });

    liabilityServiceProperties.put("Folder", liabilityService.getRootFolder());
    liabilityServiceProperties.put("Version.Id", versionId); //$NON-NLS-1$
    liabilityServiceProperties.put("IsShared", false); //$NON-NLS-1$
    liabilityTable = new MaintenanceTableController(liabilityServiceProperties);
    liabilityTable.initController(liabilityService, new DefaultMaintenanceEJBQLTableModel() {

      private String INIT_QUERY_TEXT = "select distinct %s from Liability l left join l.Version v %s where l.IsModel = 0 %s"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

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
        result.add(new TableEJBQLFieldDef(Liability.class, "Id", "l.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Priority", "l.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Num", "l.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "Receivable", "l.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "RegDate", "l.RegDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DateToExecute", "l.DateToExecute", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "SumCur", "l.SumCur", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "From", "f.Code", "left join l.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "FromBankAcc", "fa.Name", "left join l.FromBankAcc as fa", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "To", "t.Code", "left join l.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "ToBankAcc", "ta.Name", "left join l.ToBankAcc as ta", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurCode", "l.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurRateAuthority", "ca.Code", "left join l.CurRateAuthority as ca", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "CurRateType", "ct.Code", "left join l.CurRateType as ct", false));                     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "PaymentDelay", "l.PaymentDelay", false));                     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocDate", "l.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocNumber", "l.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "DocType", "dt.Code", "left join l.DocType as dt", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocDate", "l.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocNumber", "l.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "BaseDocType", "bdt.Code", "left join l.BaseDocType as bdt", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractDate", "l.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractNumber", "l.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "ContractType", "crt.Code", "left join l.ContractType as crt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "Comments", "l.Comments", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Liability.class, "PrefResource", "pr.Name", "left join l.PrefResource as pr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Liability.class, "PrefResourceFolder", "prf.FName", "left join l.PrefResourceFolder as prf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, liabilityService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        if (horizont != null)
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);

        paramsName.clear();
        paramsValue.clear();
        StringBuilder whereText = new StringBuilder().
            append(" and ((l.Version is null and l.IsShared = 1) or (v.Id = ").append(versionId).append(")) "); //$NON-NLS-1$ //$NON-NLS-2$

        if (liabilityRestForm.getDateToExecute1() != null || liabilityRestForm.getDateToExecute2() != null)
          whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.DateToExecute", liabilityRestForm.getDateToExecute1(), liabilityRestForm.getDateToExecute2(), "dateToExecute1", "dateToExecute2", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        else
          whereText.append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.DateToExecute", horizont.getDateFrom(), horizont.getDateTill(), "dateFrom", "dateTill", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        whereText.append(DatabaseUtils.formatEJBQLHierarchyRestriction(false, "l.Folder", 0, "folder", folderEntity, paramsName, paramsValue, false)).  //$NON-NLS-1$ //$NON-NLS-2$
            append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.RegDate", liabilityRestForm.getRegDate1(), liabilityRestForm.getRegDate2(), "regDate1", "regDate2", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            append(DatabaseUtils.formatEJBQLObjectRangeRestriction("l.SumCur", liabilityRestForm.getSum1(), liabilityRestForm.getSum2(), "sum1", "sum2", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            append(DatabaseUtils.formatEJBQLObjectRestriction("l.To", liabilityRestForm.getToCode(), "toCode", paramsName, paramsValue, false)).         //$NON-NLS-1$ //$NON-NLS-2$
            append(DatabaseUtils.formatEJBQLObjectRestriction("l.From", liabilityRestForm.getFromCode(), "fromCode", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
            append(DatabaseUtils.formatEJBQLAddinFieldsRestriction(liabilityService, "l.Id", liabilityRestForm.getAddinFieldsRestriction(), false)); //$NON-NLS-1$

        if (liabilityRestForm.isLesExecuted() || liabilityRestForm.isLesPartExecuted() || liabilityRestForm.isLesNotExecuted()) {
          boolean isFirstCondition = true;
          final String initSubQueryText = " (select nvl(sum(e1.SumCur), 0) from Execution e1 where e1.Liability = l) "; //$NON-NLS-1$
          whereText.append(" and "); //$NON-NLS-1$

          if (liabilityRestForm.isLesExecuted()) {
            whereText.append(initSubQueryText).append(" >= l.SumCur "); //$NON-NLS-1$
            isFirstCondition = false;
          }
          if (liabilityRestForm.isLesPartExecuted()) {
            if (!isFirstCondition)
              whereText.append(" or "); //$NON-NLS-1$
            whereText.append(initSubQueryText).append(" < l.SumCur and ").append(initSubQueryText).append(" > 0 "); //$NON-NLS-1$ //$NON-NLS-2$
            isFirstCondition = false;
          }
          if (liabilityRestForm.isLesNotExecuted()) {
            if (!isFirstCondition)
              whereText.append(" or "); //$NON-NLS-1$
            whereText.append(initSubQueryText).append(" = 0 "); //$NON-NLS-1$
          }
        }
        return String.format(INIT_QUERY_TEXT.toString(), fieldsList, fromList, whereText.toString());
      }
    });
    liabilityTable.setRestrictionForm(liabilityRestForm);

    executionTable = new MaintenanceTableController(executionServiceProperties);
    executionTable.initController(executionService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from Execution e %s %s"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

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
        result.add(new TableEJBQLFieldDef(Execution.class, "Id", "e.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "PlanDate", "e.PlanDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "FactDate", "e.FactDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "SumNat", "e.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "SumCur", "e.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "CurCode", "e.CurCode.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "CurRateAuthority", "ca.Code", "left join e.CurRateAuthority as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Execution.class, "CurRateType", "ct.Code", "left join e.CurRateType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Execution.class, "ResourceFolder", "f.FName", "left join e.ResourceFolder as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Execution.class, "Resource", "r.Name", "left join e.Resource as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Execution.class, "Receivable", "e.Receivable", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "DocCreated", "e.DocCreated", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "DocProcessed", "e.DocProcessed", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Execution.class, "Approved", "e.Approved", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, executionService);
      }


      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        if (horizont != null && executionDateFrom != null && executionDateTill != null)
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        StringBuilder whereText = new StringBuilder(" where ") //$NON-NLS-1$
            .append(DatabaseUtils.formatEJBQLObjectRangeRestriction("e.PlanDate", executionDateFrom, executionDateTill, "dateFrom", "dateTill", paramsName, paramsValue, true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        if (executionResourceId != null)
          whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("e.Resource.Id", executionResourceId, "executionResourceId", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
        if (resourceFolderId != null)
          whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("e.ResourceFolder.Id", resourceFolderId, "resourceFolderId", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
      }
    });

    super.doOnRun();
    PopupMenu popupMenu = view.getWidget("executionTable").getPopupMenu(); //$NON-NLS-1$
    popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setVisible(false);
    popupMenu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setVisible(false);
    popupMenu.getMenuItem(MaintenanceTable.RESTRICTION_MENU_ITEM).setVisible(false);
    popupMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setVisible(false);
  }

  /**
   * Обработчик кнопки "Исполнить обязательство"
   *
   * @param event - событие
   */
  public void onActionExecuteLiability(WidgetEvent event) {
    Serializable[] liabilityIds = ((DefaultMaintenanceEJBQLTableModel) liabilityTable.getModel()).getSelectedPrimaryKeys();
    if (liabilityIds != null && liabilityIds.length > 0 && selectedPlanPaymentItem != null) {
      PmcHelper.executeLiabilityByPmcPlaning((Integer) liabilityIds[0], selectedPlanPaymentItem, versionId, new PmcHelperListener() {

        public void complete() {
          recalcCurrentBranch(selectedPlanPaymentItem);
          refreshPlanTable();
        }
      });
    }
  }

  /**
   * Обработчик КМ "Условия отбора"
   *
   * @param event - событие
   */
  public void onActionShowRestriction(WidgetEvent event) {
    pmcPlaningRest.run(true);
  }

  /**
   * Обработчик КМ "Обновить"
   *
   * @param event - событие
   */
  public void onActionPlanTableRefresh(WidgetEvent event) {
    globalRefresh();
  }

  /**
   * Обработчик кнопки "Переместить средства"
   *
   * @param event - событие
   */
  public void onActionTransferResources(WidgetEvent event) {
    if (selectedPlanPaymentItem != null)
      PmcHelper.transferResourses(selectedPlanPaymentItem.getResourceId(), selectedPlanPaymentItem.getResourceFolderId(), versionId, selectedPlanPaymentItem.getDateFrom(), new PmcHelperListener() {

        public void complete() {
          recalcCurrentBranch(selectedPlanPaymentItem);
          refreshPlanTable();
          executionTable.refresh();
          liabilityTable.refresh();
        }
      });
  }

  /**
   * Обработчик пункта КМ "Утвердить"
   *
   * @param event - событие
   */
  public void onActionApprove(WidgetEvent event) {
    Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
    if (executionIds != null && executionIds.length > 0) {
      executionService.setApproved(executionIds, true);
      executionTable.refresh();
    }
  }

  /**
   * Обработчик пункта КМ "Снять утверждение"
   *
   * @param event - событие
   */
  public void onActionDisApprove(WidgetEvent event) {
    Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
    if (executionIds != null && executionIds.length > 0) {
      executionService.setApproved(executionIds, false);
      executionTable.refresh();
    }
  }

  /**
   * Обработчик пункта КМ "Удалить" (исполнение)
   *
   * @param event - событие
   */
  public void onActionRemove(WidgetEvent event) {
    Serializable[] executionIds = ((DefaultMaintenanceEJBQLTableModel) executionTable.getModel()).getSelectedPrimaryKeys();
    if (executionIds != null && executionIds.length > 0 && selectedPlanPaymentItem != null) {
      PmcHelper.removeExecution(executionIds[0], versionId, new PmcHelperListener() {

        public void complete() {
          executionTable.refresh();
          recalcCurrentBranch(selectedPlanPaymentItem);
          refreshPlanTable();
        }
      });
    }
  }

  private void processData() {
    if (level == null)
      return;

    planPaymentList = new ArrayList<PlanPaymentItem>();

    List<PmcPeriod> nestedPeriods = periodService.getNestedPeriods(horizont);
    for (PmcPeriod period : nestedPeriods)
      iterateResources((Integer) 1, null, period, planPaymentList);

    if (planPaymentList.isEmpty())
      return;

    applyConditions();

    int i = 0;
    for (PlanPaymentItem item : planPaymentList)
      planPaymentList.set(i++, recalcCurrentRow(item));
  }

  private void refreshPlanTable() {
    if (planPaymentList != null) {
      ((PlanPaymentTableModel) planTable.getModel()).setRowList(planPaymentList);
      ((PlanPaymentTableModel) planTable.getModel()).fireModelChange();
    }
  }

  private void globalRefresh() {
    if (horizont != null) {
      processData();
      refreshPlanTable();
      liabilityTable.refresh();
    }
  }

  private void iterateResources(Integer level, Integer parentId, PmcPeriod nestedPeriod, List<PlanPaymentItem> planPaymentItemList) {
    if (level > this.level)
      return;

    PmcConfig pmcConfig = getModuleConfiguration();

    List<Folder> resurceFolders = resourceService.getResurceGroups(parentId);
    if (resurceFolders != null && !resurceFolders.isEmpty())
      for (Folder resurceFolder : resurceFolders) {
        PlanPaymentItem planPaymentItem = new PlanPaymentItem();

        planPaymentItem.setId(genPlanPaymentItemId());
        planPaymentItem.setLevel(level);
        planPaymentItem.setPeriodId(nestedPeriod.getId());
        planPaymentItem.setPeriod(nestedPeriod.getName());
        planPaymentItem.setDateFrom(nestedPeriod.getDateFrom());
        planPaymentItem.setDateTill(nestedPeriod.getDateTill());
        planPaymentItem.setCurrencyCode(pmcConfig.getCurrency().getCode());
        planPaymentItem.setResourceFolderId(resurceFolder.getId());
        planPaymentItem.setSaveResourceFolderId(resurceFolder.getId());
        planPaymentItem.setResourceId(null);
        planPaymentItem.setResource(resurceFolder.getFName());

        planPaymentItemList.add(planPaymentItem);

        iterateResources(level + 1, resurceFolder.getId(), nestedPeriod, planPaymentItemList);
      }

    List<PmcResource> resurces = resourceService.getResourcesByGroup(parentId);
    if (resurces != null && !resurces.isEmpty())
      for (PmcResource resource : resurces) {
        PlanPaymentItem planPaymentItem = new PlanPaymentItem();

        planPaymentItem.setId(genPlanPaymentItemId());
        planPaymentItem.setLevel(level);
        planPaymentItem.setPeriodId(nestedPeriod.getId());
        planPaymentItem.setPeriod(nestedPeriod.getName());
        planPaymentItem.setDateFrom(nestedPeriod.getDateFrom());
        planPaymentItem.setDateTill(nestedPeriod.getDateTill());
        planPaymentItem.setCurrencyCode(resource.getCurCode() == null ? null : resource.getCurCode().getCode());
        planPaymentItem.setResourceFolderId(null);
        planPaymentItem.setSaveResourceFolderId(parentId);
        planPaymentItem.setResourceId(resource.getId());
        planPaymentItem.setResource(resource.getName());

        planPaymentItemList.add(planPaymentItem);
      }
  }

  private void applyConditions() {
    if (pmcPlaningRest.getResource() != null) {
      ListIterator<PlanPaymentItem> listIterator = planPaymentList.listIterator();
      while (listIterator.hasNext()) {
        PlanPaymentItem item = listIterator.next();
        if (item.getResourceId() == null)
          listIterator.remove();
        else if ((item.getResourceId().compareTo(pmcPlaningRest.getResource().getId()) == 0 && pmcPlaningRest.isExclude()) || (item.getResourceId().compareTo(pmcPlaningRest.getResource().getId()) != 0 && !pmcPlaningRest.isExclude()))
          listIterator.remove();
      }
    } else {
      if (pmcPlaningRest.getResourceFolder() != null) {
        ListIterator<PlanPaymentItem> listIterator = planPaymentList.listIterator();
        while (listIterator.hasNext()) {
          PlanPaymentItem item2 = listIterator.next();
          if ((item2.getSaveResourceFolderId().compareTo(pmcPlaningRest.getResourceFolder().getId()) == 0 && pmcPlaningRest.isExclude()) || (item2.getSaveResourceFolderId().compareTo(pmcPlaningRest.getResourceFolder().getId()) != 0 && !pmcPlaningRest.isExclude()))
            listIterator.remove();
        }
      }
    }
  }

  /**
   * Расчитать строку оборотки
   *
   * @param item - строка оборотки
   */
  private PlanPaymentItem recalcCurrentRow(PlanPaymentItem item) {
    if (isConcretResource(item)) {
      TurnResult resourceTurn = resourceService.getTurnByResource(item.getResourceId(), versionId, item.getDateFrom(), item.getDateTill());
      item.setBeginSaldo(resourceTurn.getBeginSaldo());
      item.setIncome(resourceTurn.getIncome());
      item.setExpense(resourceTurn.getExpense());
      item.setEndSaldo(resourceTurn.getEndSaldo());
    } else {
      TurnResult resourceGroupTurn = resourceService.getTurnByResourceGroup(item.getResourceFolderId(), versionId, item.getDateFrom(), item.getDateTill());
      item.setBeginSaldo(resourceGroupTurn.getBeginSaldo());
      item.setIncome(resourceGroupTurn.getIncome());
      item.setExpense(resourceGroupTurn.getExpense());
      item.setEndSaldo(resourceGroupTurn.getEndSaldo());
    }
    return item;
  }

  private void recalcCurrentBranch(PlanPaymentItem item) {
    Integer currentPeriodId = item.getPeriodId();
    int currentIndex = planPaymentList.indexOf(item);
    planPaymentList.set(currentIndex, recalcCurrentRow(item));

    ListIterator<PlanPaymentItem> listIterator = planPaymentList.listIterator(currentIndex);

    while (listIterator.hasPrevious()) {
      PlanPaymentItem iterateItem = listIterator.previous();
      if (iterateItem.getPeriodId() == currentPeriodId) {
        if (iterateItem.getResourceFolderId() != null)
          listIterator.set(recalcCurrentRow(iterateItem));
      } else
        break;
    }
  }

  private boolean isConcretResource(PlanPaymentItem planPaymentItem) {
    if (planPaymentItem.getResourceId() != null)
      return true;
    else
      return false;
  }

  private Integer genPlanPaymentItemId() {
    return planPaymentItemGenId++;
  }

  private PmcConfig getModuleConfiguration() {
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    return ServerUtils.getPersistentManager().find(PmcConfig.class, sysClient.getId());
  }

  /**
   * Обработчик пункта КМ "Поддержка образцов"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionShowLiabilityModel(WidgetEvent event) throws Exception {
    final LiabilityModelServiceLocal service = (LiabilityModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/LiabilityModel"); //$NON-NLS-1$
    LiabilityModelBr form = (LiabilityModelBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    form.run();
  }

  /**
   * Обработчик пункта КМ "Вставка с образцом"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionInsertModel(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentcontrol.support.ui.LiabilityModelSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchPerformed(SearchHelpEvent event) {
        doOnActionInsertModel((Liability) event.getItems()[0]);
      }

      public void searchCanceled(SearchHelpEvent event) {
        //do nothing
      }
    });
    searchHelp.search();
  }

  /**
   * Создание обязательства по образцу
   *
   * @param model - образец
   */
  private void doOnActionInsertModel(Liability model) {
    Liability liability = liabilityService.createByPattern(model, liabilityService.getRootFolder());
    liability.setVersion((Version) ServerUtils.getPersistentManager().find(Version.class, versionId));
    liability.setIsShared(false);

    MaintenanceHelper.add(liabilityService, liability, null, new MaintenanceFormActionListener() {

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
        liabilityTable.refresh();
      }
    });
  }

  /**
   * @return the horizont
   */
  public PmcPeriod getHorizont() {
    return horizont;
  }

  /**
   * @param horizont the horizont to set
   */
  public void setHorizont(PmcPeriod horisont) {
    this.horizont = horisont;
  }

  /**
   * @return the level
   */
  public Integer getLevel() {
    return level;
  }

  /**
   * @param level the level to set
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * @return the versionId
   */
  public Integer getVersionId() {
    return versionId;
  }

  /**
   * @param versionId the versionId to set
   */
  public void setVersionId(Integer versionId) {
    this.versionId = versionId;
  }

}
