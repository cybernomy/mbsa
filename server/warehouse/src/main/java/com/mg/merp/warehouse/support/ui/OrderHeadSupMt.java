/*
 * OrderHeadSupMt.java
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
package com.mg.merp.warehouse.support.ui;

import java.util.Set;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.OrderSpecSupServiceLocal;

/**
 * Контроллер формы поддержки заказов постащикам
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderHeadSupMt.java,v 1.8 2008/12/25 10:26:25 safonov Exp $
 */
public class OrderHeadSupMt extends GoodsDocumentMaintenanceForm {
	/**
	 * контекст импорта для SearchHelp поля "Ответственный"
	 */
	protected String[] contractorResponsibleKinds;
	
	public OrderHeadSupMt() throws Exception {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
		
		specService = ((OrderSpecSupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/OrderSpecSup"));

		spec.initController(specService, new OrderDocSpecMaintenanceEJBQLTableModel() {			
			
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
			}

		});

		addMasterModelListener(spec);
	}

}


