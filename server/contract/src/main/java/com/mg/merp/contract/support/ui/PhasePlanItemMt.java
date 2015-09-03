/*
 * PhaseFactItemMt.java
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

import java.util.ArrayList;
import java.util.List;
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
import com.mg.merp.contract.ContractSpecServiceLocal;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.model.PhasePlanItem;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Контроллер формы поддержки планируемых пунктов контракта
 * 
 * @author Artem V. Sharapov
 * @version $Id: PhasePlanItemMt.java,v 1.1 2008/03/11 09:53:15 sharapov Exp $
 */
public class PhasePlanItemMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController specPlan;	
	protected AttributeMap specPlanProperties = new LocalDataTransferObject();
	private ContractSpecServiceLocal specService;
	
	private final static String DOCUMENT_ATTRIBUTE_NAME = "Document"; //$NON-NLS-1$
	private final static String DOCUMENT_TYPE_WIDGET_NAME = "DocType"; //$NON-NLS-1$
	private final static String DOCUMENT_NUMBER_WIDGET_NAME = "DocNumber"; //$NON-NLS-1$
	private final static String DOCUMENT_DATE_WIDGET_NAME = "DocDate"; //$NON-NLS-1$


	public PhasePlanItemMt() throws Exception {
		setMasterDetail(true);
		specService = (ContractSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ContractSpecServiceLocal.SERVICE_NAME);

		specPlan = new MaintenanceTableController(specPlanProperties);
		specPlan.initController(specService, new ContratcSpectMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from ContractSpec cs %s where cs.PhaseItemPlan = :phaseitemplan"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("phaseitemplan"); //$NON-NLS-1$
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
		addMasterModelListener(specPlan);
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		specPlanProperties.put("PhaseItemPlan", event.getEntity()); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		if(((PhasePlanItem) getEntity()).getDocument() != null)
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
	 * Обработчик просмотра документа
	 * @param event - событие
	 */
	public void onActionViewDocument(WidgetEvent event) {
		DocumentUtils.viewDocument(getEntity(), DOCUMENT_ATTRIBUTE_NAME);
	}

	/**
	 * Обработчик кнопки "Рассчитать"
	 * @param event - событие
	 */
	public void onActionAdjust(WidgetEvent event) {
		((PhasePlanItemServiceLocal) getService()).adjust((PhasePlanItem) getEntity());
	}

}
