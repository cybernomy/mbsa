/*
 * PhaseFactItemMt.java
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
import com.mg.merp.document.support.DocumentUtils;

/**
 * Контроллер формы поодержки пунктов контракта
 * 
 * @author leonova
 * @version $Id: PhaseItemMt.java,v 1.6 2008/02/19 05:19:25 alikaev Exp $
 */
public class PhaseItemMt extends DefaultMaintenanceForm implements MasterModelListener {

	private ContractSpecServiceLocal specService;

	private MaintenanceTableController specFact;	
	protected AttributeMap specFactProperties = new LocalDataTransferObject();

	private MaintenanceTableController specPlan;	
	protected AttributeMap specPlanProperties = new LocalDataTransferObject();
	
	private final static String DOCUMENT_ATTRIBUTE_NAME = "Document"; //$NON-NLS-1$

	public PhaseItemMt() throws Exception {
		setMasterDetail(true);
		specService = (ContractSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/ContractSpec"); //$NON-NLS-1$
		specFact = new MaintenanceTableController(specFactProperties);
		specFact.initController(specService, new ContratcSpectMaintenanceEJBQLTableModel() {
			
			private final String INIT_QUERY_TEXT = "select %s from ContractSpec cs %s where cs.PhaseItemFact = :phaseitemfact"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("phaseitemfact"); //$NON-NLS-1$
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
		addMasterModelListener(specFact);

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
		specFactProperties.put("PhaseItemFact", event.getEntity()); //$NON-NLS-1$
		specPlanProperties.put("PhaseItemPlan", event.getEntity()); //$NON-NLS-1$
	}

	/**
	 * Обработчик просмотра документа
	 * @param event - событие
	 */
	public void onActionViewDocument(WidgetEvent event) {
		DocumentUtils.viewDocument(getEntity(), DOCUMENT_ATTRIBUTE_NAME);
	}

}