/*
 * OrderHeadCusMt.java
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
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.OrderSpecCusServiceLocal;
import com.mg.merp.warehouse.generic.ui.AbstractWarehouseDocumentMaintenaceForm;
import com.mg.merp.warehouse.model.OrderSpec;

/**
 * Контроллер формы поддержки заказов покупаталей
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderHeadCusMt.java,v 1.9 2008/12/25 10:26:56 safonov Exp $
 */
public class OrderHeadCusMt extends AbstractWarehouseDocumentMaintenaceForm {
	/**
	 * контекст импорта для SearchHelp поля "Ответственный"
	 */
	protected String[] contractorResponsibleKinds;
	
	public OrderHeadCusMt() throws Exception {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};		
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
		
		specService = ((OrderSpecCusServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/OrderSpecCus"));

		spec.initController(specService, new OrderDocSpecMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.OrderDocSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(OrderSpec.class, "Discount", "ds.Discount", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}			

		});

		addMasterModelListener(spec);
	}

}


