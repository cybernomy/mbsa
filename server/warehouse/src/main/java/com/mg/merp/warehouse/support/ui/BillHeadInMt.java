/*
 * BillHeadInMt.java
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
package com.mg.merp.warehouse.support.ui;

import java.util.Set;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;
import com.mg.merp.warehouse.BillSpecInServiceLocal;

/**
 * Контроллер формы поддержки входящих счетов
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: BillHeadInMt.java,v 1.8 2008/12/25 10:26:25 safonov Exp $
 */
public class BillHeadInMt extends GoodsDocumentMaintenanceForm implements MasterModelListener {
	protected String[] contractorResponsibleKinds;

	public BillHeadInMt() throws Exception {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorResponsibleKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE, ContractorSearchForm.CONTRACTOR_ORGUNIT};

		specService = ((BillSpecInServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/BillSpecIn")); //$NON-NLS-1$

		spec.initController(specService, new BillSpecMaintenanceEJBQLTableModel() {			

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(super.getDefaultFieldDefsSet(), service);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.WarehouseDocSpecMaintenanceEJBQLTableModel#isAddSrcMolFieldDef()
			 */
			@Override
			protected boolean isAddSrcMolFieldDef() {
				return false;
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.WarehouseDocSpecMaintenanceEJBQLTableModel#isAddSrcStockFieldDef()
			 */
			@Override
			protected boolean isAddSrcStockFieldDef() {
				return false;
			}

		});

		addMasterModelListener(spec);
	}

}
