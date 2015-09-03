/*
 * OrderHeadModelCusMt.java
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
package com.mg.merp.warehouse.support.ui;

import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.OrderSpecModelCusServiceLocal;

/**
 * Браузер образов заказов покупателей
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderHeadModelCusMt.java,v 1.8 2008/03/20 13:03:36 alikaev Exp $
 */
public class OrderHeadModelCusMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController orderSpecModelCus;
	private OrderSpecModelCusServiceLocal orderSpecModelCusService;
	protected AttributeMap orderSpecModelCusProperties = new LocalDataTransferObject();

	/**
	 * контекст импорта для SearchHelp поля "Ответственный"
	 */
	protected String[] contractorResponsibleKinds;
	/**
	 * контекст импорта для SearchHelp поля "через кого"
	 */
	protected String[] contractorThroughKinds;
	
	public OrderHeadModelCusMt() throws Exception {
		super();
		
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};		
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
		
		orderSpecModelCusService = (OrderSpecModelCusServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/OrderSpecModelCus"); //$NON-NLS-1$
		orderSpecModelCus = new MaintenanceTableController(orderSpecModelCusProperties);
		orderSpecModelCus.initController(orderSpecModelCusService, new OrderModelSpecMaintenanceEJBQLTableModel() {			
			OrderSpecModelCusServiceLocal specService;
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				super.getDefaultFieldDefsSet();

				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#createQueryText()
			 */
			@Override
			protected String createQueryText() {				
				super.createQueryText();
				paramsValue.add(getEntity());
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
			}
			
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override			
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
			
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}});
		addMasterModelListener(orderSpecModelCus);
		
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	
	public void masterChange(ModelChangeEvent event) {
		orderSpecModelCusProperties.put("DocHeadModel", event.getEntity());	 //$NON-NLS-1$
	}
	
}
