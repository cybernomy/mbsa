/*
 * BillHeadOutMt.java
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
import com.mg.merp.warehouse.BillSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.ui.AbstractWarehouseDocumentMaintenaceForm;
import com.mg.merp.warehouse.model.BillSpec;

/**
 * Контроллер формы поддержки исходящих счетов
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BillHeadOutMt.java,v 1.9 2008/12/25 10:26:56 safonov Exp $
 */
public class BillHeadOutMt extends AbstractWarehouseDocumentMaintenaceForm {
	protected String[] contractorResponsibleKinds;
	
	public BillHeadOutMt() throws Exception {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};
		
		specService = ((BillSpecOutServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/BillSpecOut"));

		spec.initController(specService, new BillSpecMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.BillSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(BillSpec.class, "Discount", "ds.Discount", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}
			
		});

		addMasterModelListener(spec);
	}

}
