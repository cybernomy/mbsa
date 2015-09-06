/*
 * ContractMt.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.contract.ModifyDocumentServiceLocal;
import com.mg.merp.contract.PhaseFactItemServiceLocal;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.PhaseServiceLocal;
import com.mg.merp.contract.model.CalcSumKind;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.ModifyDocument;
import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.support.ui.AttachmentHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Контракты"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: ContractMt.java,v 1.18 2008/03/13 14:40:15 sharapov Exp $
 */
public class ContractMt extends DefaultMaintenanceForm implements MasterModelListener {

  private static final String BASE_DOC_TYPE_WIDGET_NAME = "BaseDocType"; //$NON-NLS-1$
  private static final String BASE_DOC_NUMBER_WIDGET_NAME = "BaseDocNumber"; //$NON-NLS-1$
  private static final String BASE_DOC_DATE_WIDGET_NAME = "BaseDocDate"; //$NON-NLS-1$
  private static final String DOCUMENT_ATTRIBUTE_NAME = "Document"; //$NON-NLS-1$
  private final String PLAN_TABLE_WIDGET = "phaseItemPlan"; //$NON-NLS-1$
  private final String PARAM_NAME = "dochead"; //$NON-NLS-1$
  private final String PROPERTY_NAME = "DocHead"; //$NON-NLS-1$
  protected AttributeMap phasesProperties = new LocalDataTransferObject();
  protected AttributeMap modifyDocumentProperties = new LocalDataTransferObject();
  protected AttributeMap phaseItemFactProperties = new LocalDataTransferObject();
  protected AttributeMap phaseItemPlanProperties = new LocalDataTransferObject();
  private MaintenanceTableController phases;
  private PhaseServiceLocal phasesService;
  private MaintenanceTableController modifyDocument;
  private ModifyDocumentServiceLocal modifyDocumentService;
  private MaintenanceTableController phaseItemFact;
  private PhaseFactItemServiceLocal phaseItemFactService;
  private MaintenanceTableController phaseItemPlan;
  private PhasePlanItemServiceLocal phaseItemPlanService;
  private BigDecimal conractRest = new BigDecimal(0);
  private BigDecimal planRest = new BigDecimal(0);
  private BigDecimal factRest = new BigDecimal(0);


  public ContractMt() throws Exception {
    setMasterDetail(true);
    phasesService = (PhaseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/Phase"); //$NON-NLS-1$
    phases = new MaintenanceTableController(phasesProperties);
    phases.initController(phasesService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Phase p %s where p.Avoid = 0 AND p.DocHead = :dochead"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(PARAM_NAME);
        paramsValue.add(getEntity());
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
        result.add(new TableEJBQLFieldDef(Phase.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "PhaseNumber", "p.PhaseNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "SumCur", "p.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "Company", "com.Code", "left join p.Company as com", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Phase.class, "Contractor", "contr.Code", "left join p.Contractor as contr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Phase.class, "ShippedPayment", "p.ShippedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "ShippedGood", "p.ShippedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "ReceivedPayment", "p.ReceivedPayment", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Phase.class, "ReceivedGood", "p.ReceivedGood", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, phasesService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(phases);

    modifyDocumentService = (ModifyDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/ModifyDocument"); //$NON-NLS-1$
    modifyDocument = new MaintenanceTableController(modifyDocumentProperties);
    modifyDocument.initController(modifyDocumentService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from ModifyDocument md %s where md.DocHead = :dochead"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(PARAM_NAME);
        paramsValue.add(getEntity());
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
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "Id", "md.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "DocType", "mdt.Code", "left join md.DocType as mdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "DocNumber", "md.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "DocDate", "md.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "ModifyDesc", "md.ModifyDesc", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(ModifyDocument.class, "Comment", "md.Comment", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, modifyDocumentService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(modifyDocument);

    phaseItemFactService = (PhaseFactItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhaseFactItem"); //$NON-NLS-1$
    phaseItemFact = new MaintenanceTableController(phaseItemFactProperties);
    phaseItemFact.initController(phaseItemFactService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PhaseFactItem ppi %s where ppi.DocHead = :dochead"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(PARAM_NAME);
        paramsValue.add(getEntity());
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
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "Id", "ppi.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "Kind", "ppi.Kind", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "RegDate", "ppi.RegDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "DocDate", "ppi.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "DocType", "dt.Code", "left join ppi.DocType as dt", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "DocNumber", "ppi.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "Contractor", "contr.Code", "left join ppi.Contractor as contr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "FactSum", "ppi.FactSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PhaseFactItem.class, "DistSum", "ppi.DistSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, phaseItemFactService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(phaseItemFact);

    phaseItemPlanService = (PhasePlanItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhasePlanItem"); //$NON-NLS-1$
    phaseItemPlan = new MaintenanceTableController(phaseItemPlanProperties);
    phaseItemPlan.initController(phaseItemPlanService, new PlanItemMaintenanceEJBQLTableModel() {

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add(PARAM_NAME);
        paramsValue.add(getEntity());
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
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(phaseItemPlan);
    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    phasesProperties.put(PROPERTY_NAME, event.getEntity());
    modifyDocumentProperties.put(PROPERTY_NAME, event.getEntity());
    phaseItemPlanProperties.put("ContractPhase", event.getEntity()); //$NON-NLS-1$
    phaseItemFactProperties.put(PROPERTY_NAME, event.getEntity());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    MaintenanceAction mtAction = getAction();
    DocHead contract = (DocHead) getEntity();
    //проверим доступность редактирования документа
    if (MaintenanceAction.EDIT == mtAction)
      DocFlowHelper.checkStatus(contract);

    super.doOnRun();
    view.getWidget(PLAN_TABLE_WIDGET).setReadOnly(true);
    if (MaintenanceAction.EDIT == mtAction) {
      if (contract.getBaseDocument() != null)
        setBaseDocumentFieldsEnabled(false);
    }
    refreshRestSumElements();
    if (((Contract) contract).getCalcSumKind() == CalcSumKind.PHASESAGGREGATE) {
      refreshRestSumElements();
      refreshContractSumElements();
      view.flushModel();
    }
  }

  /**
   * Обработка события "Аннулировать" (Этап)
   *
   * @param event - событие
   */
  public void onActionMakeAvoid(WidgetEvent event) throws Exception {
    if (getEntity().getAttribute("Id") == null) //$NON-NLS-1$
      return;

    Serializable[] selectedIds = ((MaintenanceTableModel) phases.getModel()).getSelectedPrimaryKeys();
    if (selectedIds != null && selectedIds.length > 0) {
      Phase phaseItem = ServerUtils.getPersistentManager().find(Phase.class, selectedIds[0]);
      ((PhaseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PhaseServiceLocal.LOCAL_SERVICE_NAME)).madeAvoid(phaseItem);
      phases.refresh();
    }
  }

  /**
   * Обработка события "Аннулированные" (Этапы)
   *
   * @param event - событие
   */
  public void onActionShowAvoid(WidgetEvent event) throws Exception {
    if (getEntity().getAttribute("Id") == null) //$NON-NLS-1$
      return;
    AvoidPhaseDlg dialog = (AvoidPhaseDlg) UIProducer.produceForm("com/mg/merp/contract/resources/AvoidPhaseDlg.mfd.xml"); //$NON-NLS-1$;
    dialog.setDocHead((DocHead) getEntity());
    dialog.addCloseActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        phases.refresh();
      }
    });
    dialog.run();
  }

  /**
   * Обработка события "Распределить" (автоматически)
   *
   * @param event - событие
   */
  public void onActionAutoDistribution(WidgetEvent event) throws Exception {
    PhaseFactItem phaseFactItem = getSelectedFactItem();
    if (phaseFactItem != null) {
      getPhaseFactItemService().autoDistributionFactSum(phaseFactItem);
      refreshPlanAndFactTables();
    }
  }

  /**
   * Обработка события "Аннулировать распределение"
   *
   * @param event - событие
   */
  public void onActionAvoidDistribution(WidgetEvent event) throws Exception {
    PhaseFactItem phaseFactItem = getSelectedFactItem();
    if (phaseFactItem != null) {
      getPhaseFactItemService().avoidDistributionFactSum(phaseFactItem.getId());
      refreshPlanAndFactTables();
    }
  }

  /**
   * Обработка события "Распределить вручную"
   *
   * @param event - событие
   */
  public void onActionManualDistribution(WidgetEvent event) throws Exception {
    final PhaseFactItem factItem = getSelectedFactItem();
    if (factItem == null)
      return;
    if (factItem.getDistSum().compareTo(factItem.getFactSum()) < 0) {
      final ManualDistributionDlg dialog = (ManualDistributionDlg) UIProducer.produceForm("com/mg/merp/contract/resources/ManualDistributionDlg.mfd.xml"); //$NON-NLS-1$;
      dialog.addCloseActionListener(new FormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
         */
        public void actionPerformed(FormEvent event) {
          ManualDistributionData[] manualDistributionData = dialog.getManualDistributionItems();
          if (manualDistributionData != null) {
            getPhaseFactItemService().manualDistributionFactSum(manualDistributionData, factItem.getId());
            refreshPlanAndFactTables();
          }
        }
      });
      dialog.setParams((DocHead) getEntity(), factItem.getKind(), factItem.getFactSum().subtract(factItem.getDistSum()));
      dialog.run();
    }
  }

  /**
   * Обработка события "Показать документ" (фактического пункта контракта)
   *
   * @param event - событие
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  public void onActionShowDocument(WidgetEvent event) throws Exception {
    PhaseFactItem factItem = getSelectedFactItem();
    if (factItem != null)
      DocumentUtils.viewDocument(factItem, DOCUMENT_ATTRIBUTE_NAME);
  }


  private PhaseFactItem getSelectedFactItem() {
    Serializable[] factItemId = ((MaintenanceTableModel) phaseItemFact.getModel()).getSelectedPrimaryKeys();
    if (factItemId != null && factItemId.length > 0)
      return getPhaseFactItemService().load((Integer) factItemId[0]);
    else
      return null;
  }

  private PhaseFactItemServiceLocal getPhaseFactItemService() {
    return (PhaseFactItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/PhaseFactItem"); //$NON-NLS-1$
  }

  private void refreshPlanAndFactTables() {
    ServerUtils.getPersistentManager().flush();
    phaseItemFact.refresh();
    phaseItemPlan.refresh();
  }

  /**
   * Обработчик кнопки "Расчитать"
   *
   * @param event - событие
   */
  public void onActionCalculateSum(WidgetEvent event) throws Exception {
    view.flushForm();
    if (((Contract) getEntity()).getId() == null)
      return;
    if (((Contract) getEntity()).getCalcSumKind() == CalcSumKind.MANUAL)
      refreshContractRestSum();
    else
      refreshContractSumElements();
    refreshPlanSumElements();
    refreshFactSumElements();
    view.flushModel();
  }

  private void refreshContractSumElements() {
    // расчет сумм по контракту
    ((ContractServiceLocal) getService()).adjust((Contract) getEntity());
    refreshContractRestSum();
  }

  private void refreshContractRestSum() {
    // расчет остатков по контракту
    BigDecimal shippedPaymentSum = ((Contract) getEntity()).getShippedPayment();
    BigDecimal receivedGoodSum = ((Contract) getEntity()).getReceivedGood();
    BigDecimal receivedPaymentSum = ((Contract) getEntity()).getReceivedPayment();
    BigDecimal shippedGoodSum = ((Contract) getEntity()).getShippedGood();
    conractRest = ((ContractServiceLocal) getService()).calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
  }

  private void refreshRestSumElements() {
    // расчет остатков по плану
    BigDecimal shippedPaymentSum = ((Contract) getEntity()).getPhaseShippedPayment();
    BigDecimal receivedGoodSum = ((Contract) getEntity()).getPhaseReceivedGood();
    BigDecimal receivedPaymentSum = ((Contract) getEntity()).getPhaseReceivedPayment();
    BigDecimal shippedGoodSum = ((Contract) getEntity()).getPhaseShippedGood();
    planRest = ((ContractServiceLocal) getService()).calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
    // расчет остатков по факту
    shippedPaymentSum = ((Contract) getEntity()).getFactShippedPayment();
    receivedGoodSum = ((Contract) getEntity()).getFactReceivedGood();
    receivedPaymentSum = ((Contract) getEntity()).getFactReceivedPayment();
    shippedGoodSum = ((Contract) getEntity()).getFactShippedGood();
    factRest = ((ContractServiceLocal) getService()).calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
    view.flushModel();
  }

  private void refreshPlanSumElements() {
    // расчет сумм по плану
    BigDecimal[] planSums = ((ContractServiceLocal) getService()).calculateTotalPlanSum((DocHead) getEntity());
    ((Contract) getEntity()).setPhaseShippedPayment(planSums[0]);
    ((Contract) getEntity()).setPhaseReceivedPayment(planSums[1]);
    ((Contract) getEntity()).setPhaseShippedGood(planSums[2]);
    ((Contract) getEntity()).setPhaseReceivedGood(planSums[3]);
    // расчет остатков по плану
    BigDecimal shippedPaymentSum = ((Contract) getEntity()).getPhaseShippedPayment();
    BigDecimal receivedGoodSum = ((Contract) getEntity()).getPhaseReceivedGood();
    BigDecimal receivedPaymentSum = ((Contract) getEntity()).getPhaseReceivedPayment();
    BigDecimal shippedGoodSum = ((Contract) getEntity()).getPhaseShippedGood();
    planRest = ((ContractServiceLocal) getService()).calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
  }

  private void refreshFactSumElements() {
    // расчет сумм по факту
    BigDecimal[] factSums = ((ContractServiceLocal) getService()).calculateTotalFactSum((DocHead) getEntity());
    ((Contract) getEntity()).setFactShippedPayment(factSums[0]);
    ((Contract) getEntity()).setFactReceivedPayment(factSums[1]);
    ((Contract) getEntity()).setFactShippedGood(factSums[2]);
    ((Contract) getEntity()).setFactReceivedGood(factSums[3]);
    // расчет остатков по факту
    BigDecimal shippedPaymentSum = ((Contract) getEntity()).getFactShippedPayment();
    BigDecimal receivedGoodSum = ((Contract) getEntity()).getFactReceivedGood();
    BigDecimal receivedPaymentSum = ((Contract) getEntity()).getFactReceivedPayment();
    BigDecimal shippedGoodSum = ((Contract) getEntity()).getFactShippedGood();
    factRest = ((ContractServiceLocal) getService()).calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
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

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    view.getWidget("History").setEnabled(!readOnly); //$NON-NLS-1$
    view.getWidget(PLAN_TABLE_WIDGET).setReadOnly(true);
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
   * Обработчик события "Показать оригинал"
   *
   * @param event - событие
   */
  public void onActionShowOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.show(((MaintenanceTableModel) modifyDocument.getModel()).getSelectedPrimaryKeys(), ModifyDocumentServiceLocal.LOCAL_SERVICE_NAME);
  }

  /**
   * Обработчик события "Загрузить оригинал как..."
   *
   * @param event - событие
   */
  public void onActionDownloadOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.download(((MaintenanceTableModel) modifyDocument.getModel()).getSelectedPrimaryKeys(), ModifyDocumentServiceLocal.LOCAL_SERVICE_NAME);
  }

  /**
   * Обработчик события "Сохранить оригинал..."
   *
   * @param event - событие
   */
  public void onActionUploadOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.upload(((MaintenanceTableModel) modifyDocument.getModel()).getSelectedPrimaryKeys(), ModifyDocumentServiceLocal.LOCAL_SERVICE_NAME);
  }

  /**
   * Обработчик события "Удалить оригинал"
   *
   * @param event - событие
   */
  public void onActionRemoveOriginal(WidgetEvent event) throws Exception {
    AttachmentHelper.remove(((MaintenanceTableModel) modifyDocument.getModel()).getSelectedPrimaryKeys(), ModifyDocumentServiceLocal.LOCAL_SERVICE_NAME);
  }

  /**
   * Обработчик события просмотра документа-основания
   *
   * @param event - событие
   */
  public void onActionViewBaseDocument(WidgetEvent event) {
    DocumentUtils.viewBaseDocument((DocHead) getEntity());
  }

  /**
   * @return the conractRest
   */
  public BigDecimal getConractRest() {
    return conractRest;
  }

  /**
   * @param conractRest the conractRest to set
   */
  public void setConractRest(BigDecimal conractRest) {
    this.conractRest = conractRest;
  }

  /**
   * @return the factRest
   */
  public BigDecimal getFactRest() {
    return factRest;
  }

  /**
   * @param factRest the factRest to set
   */
  public void setFactRest(BigDecimal factRest) {
    this.factRest = factRest;
  }

  /**
   * @return the planRest
   */
  public BigDecimal getPlanRest() {
    return planRest;
  }

  /**
   * @param planRest the planRest to set
   */
  public void setPlanRest(BigDecimal planRest) {
    this.planRest = planRest;
  }

}