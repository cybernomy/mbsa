/*
 * ContractPhaseMt.java
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
package com.mg.merp.contract.support.ui;

import java.math.BigDecimal;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.PhaseServiceLocal;
import com.mg.merp.contract.model.CalcSumKind;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PlanAndFactSumsByKindResult;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Контроллер формы поддержки бизнес-компонента "Этапы контракта"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractPhaseMt.java,v 1.9 2008/03/11 09:53:15 sharapov Exp $
 */
public class ContractPhaseMt extends DefaultMaintenanceForm implements MasterModelListener {

	private PhasePlanItemServiceLocal planService;
	private ContractServiceLocal contractService;

	private MaintenanceTableController shipped;
	protected AttributeMap shippedProperties = new LocalDataTransferObject();

	private MaintenanceTableController receiveGood;
	protected AttributeMap receiveGoodProperties = new LocalDataTransferObject();

	private MaintenanceTableController receive;
	protected AttributeMap receiveProperties = new LocalDataTransferObject();

	private MaintenanceTableController shippedGood;
	protected AttributeMap shippedGoodProperties = new LocalDataTransferObject();

	private BigDecimal planRest = BigDecimal.ZERO;
	private BigDecimal factRest = BigDecimal.ZERO;

	private final String ITEM_KIND = "Kind"; //$NON-NLS-1$
	private final String PARAM_NAME = "phase"; //$NON-NLS-1$
	private final String PROPERTY_NAME = "ContractPhase"; //$NON-NLS-1$
	private final static String DOCUMENT_ATTRIBUTE_NAME = "Document"; //$NON-NLS-1$
	private final static String DOCUMENT_TYPE_WIDGET_NAME = "DocType"; //$NON-NLS-1$
	private final static String DOCUMENT_NUMBER_WIDGET_NAME = "DocNumber"; //$NON-NLS-1$
	private final static String DOCUMENT_DATE_WIDGET_NAME = "DocDate"; //$NON-NLS-1$


	public ContractPhaseMt() throws Exception{
		setMasterDetail(true);
		planService = (PhasePlanItemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PhasePlanItemServiceLocal.SERVICE_NAME);
		contractService = (ContractServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ContractServiceLocal.LOCAL_SERVICE_NAME);
		
		shippedProperties.put(ITEM_KIND, ItemKind.SHIPPED);	
		shipped = new MaintenanceTableController(shippedProperties);
		shipped.initController(planService, new PlanItemMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from PhasePlanItem ppi %s where ppi.ContractPhase = :phase and ppi.Kind = 0"; //$NON-NLS-1$

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
		addMasterModelListener(shipped);

		receiveGoodProperties.put(ITEM_KIND, ItemKind.RECEIVEGOOD);		
		receiveGood = new MaintenanceTableController(receiveGoodProperties);
		receiveGood.initController(planService, new PlanItemMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from PhasePlanItem ppi %s where ppi.ContractPhase = :phase and ppi.Kind = 3"; //$NON-NLS-1$

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
		addMasterModelListener(receiveGood);

		receiveProperties.put(ITEM_KIND, ItemKind.RECEIVE);
		receive = new MaintenanceTableController(receiveProperties);
		receive.initController(planService, new PlanItemMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from PhasePlanItem ppi %s where ppi.ContractPhase = :phase and ppi.Kind = 1"; //$NON-NLS-1$

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
		addMasterModelListener(receive);

		shippedGoodProperties.put(ITEM_KIND, ItemKind.SHIPPEDGOOD);
		shippedGood = new MaintenanceTableController(shippedGoodProperties);
		shippedGood.initController(planService, new PlanItemMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from PhasePlanItem ppi %s where ppi.ContractPhase = :phase and ppi.Kind = 2"; //$NON-NLS-1$

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
		addMasterModelListener(shippedGood);
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		shippedProperties.put(PROPERTY_NAME, event.getEntity());
		receiveGoodProperties.put(PROPERTY_NAME, event.getEntity());
		receiveProperties.put(PROPERTY_NAME, event.getEntity());
		shippedGoodProperties.put(PROPERTY_NAME, event.getEntity());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		refreshRestSumElements();
		if(((Phase) getEntity()).getCalcSumKind() == CalcSumKind.PHASESAGGREGATE) {
			refreshPhaseSumElements();	
			refreshSum();
		}
		view.flushModel();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		if(((Phase) getEntity()).getDocument() != null)
			setDocumentFieldsEnabled(false);
	}
	
	/**
	 * Сделать доступными/недоступными для редактирования поля с указанием типа, номера и даты документа
	 * @param isEnabled - признак доступности для редактирования
	 */
	protected void setDocumentFieldsEnabled(boolean isEnabled) {
		view.getWidget(DOCUMENT_TYPE_WIDGET_NAME).setReadOnly(!isEnabled);
		view.getWidget(DOCUMENT_NUMBER_WIDGET_NAME).setEnabled(isEnabled);
		view.getWidget(DOCUMENT_DATE_WIDGET_NAME).setReadOnly(!isEnabled);
	}

	/**
	 * Обработчик кнопки "Расчитать"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionCalculateSum(WidgetEvent event) throws Exception {
		if(((Phase) getEntity()).getId() == null)
			return;

		if(((Phase) getEntity()).getCalcSumKind() == CalcSumKind.PHASESAGGREGATE) 
			refreshPhaseSumElements();	
		else 
			view.flushForm();
		
		refreshSum();
		refreshPlanAndFactSumElements();
		view.flushModel();
	}
	
	private void refreshPlanAndFactSumElements() {
		// расчет сумм по плану/фактически
		PlanAndFactSumsByKindResult planAndFactSumsByKind = ((PhaseServiceLocal) getService()).getTotalPlanAndFactSumsByKind((Phase) getEntity());
		
		((Phase) getEntity()).setItemShippedPayment(planAndFactSumsByKind.getPlanShippedPayment());
		((Phase) getEntity()).setItemReceivedPayment(planAndFactSumsByKind.getPlanReceivedPayment());
		((Phase) getEntity()).setItemShippedGood(planAndFactSumsByKind.getPlanShippedGood());
		((Phase) getEntity()).setItemReceivedGood(planAndFactSumsByKind.getPlanReceivedGood());
		
		((Phase) getEntity()).setFactShippedPayment(planAndFactSumsByKind.getFactShippedPayment());
		((Phase) getEntity()).setFactReceivedPayment(planAndFactSumsByKind.getFactReceivedPayment());
		((Phase) getEntity()).setFactShippedGood(planAndFactSumsByKind.getFactShippedGood());
		((Phase) getEntity()).setFactReceivedGood(planAndFactSumsByKind.getFactReceivedGood());
		
		refreshRestSumElements();
	}

	private void refreshPhaseSumElements() {
		BigDecimal[] sums = ((PhaseServiceLocal) getService()).calculateTotalPhaseSumByKind((Phase) getEntity());
		((Phase) getEntity()).setShippedPayment(sums[0]);
		((Phase) getEntity()).setReceivedPayment(sums[1]);
		((Phase) getEntity()).setShippedGood(sums[2]);
		((Phase) getEntity()).setReceivedGood(sums[3]);
	}

	private void refreshSum() {
		((PhaseServiceLocal) getService()).adjust((Phase) getEntity());
	}

	private void refreshRestSumElements() {
		// расчет остатков по плану
		BigDecimal shippedPaymentSum = ((Phase) getEntity()).getItemShippedPayment();
		BigDecimal receivedGoodSum = ((Phase) getEntity()).getItemReceivedGood();
		BigDecimal receivedPaymentSum = ((Phase) getEntity()).getItemReceivedPayment();
		BigDecimal shippedGoodSum = ((Phase) getEntity()).getItemShippedGood();
		planRest = contractService.calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
		// расчет остатков по факту
		shippedPaymentSum = ((Phase) getEntity()).getFactShippedPayment();
		receivedGoodSum = ((Phase) getEntity()).getFactReceivedGood();
		receivedPaymentSum = ((Phase) getEntity()).getFactReceivedPayment();
		shippedGoodSum = ((Phase) getEntity()).getFactShippedGood();
		factRest = contractService.calculateRestSum(shippedPaymentSum, receivedGoodSum, receivedPaymentSum, shippedGoodSum);
	}
	
	/**
	 * Обработчик просмотра документа
	 * @param event - событие
	 */
	public void onActionViewDocument(WidgetEvent event) {
		DocumentUtils.viewDocument(getEntity(), DOCUMENT_ATTRIBUTE_NAME);
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
