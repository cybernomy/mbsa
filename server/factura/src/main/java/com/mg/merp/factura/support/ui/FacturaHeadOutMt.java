/*
 * FacturaHeadMt.java
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
package com.mg.merp.factura.support.ui;

import java.util.Set;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.factura.FacturaSpecOutServiceLocal;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки исходящих счет - фактур
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: FacturaHeadOutMt.java,v 1.8 2008/12/25 10:15:04 safonov Exp $
 */
public class FacturaHeadOutMt extends GoodsDocumentMaintenanceForm implements MasterModelListener {
	
	public FacturaHeadOutMt() throws Exception {
		super();
		contractorToKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER};
		specService = ((FacturaSpecOutServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/factura/FacturaSpecOut"));

		spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {			
					
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();		
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcStock", "ss.Code", "left join ds.SrcStock as ss", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcMol", "sm.Code", "left join ds.SrcMol as sm", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "DstStock", "dst.Code", "left join ds.DstStock as dst", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "DstMol", "dm.Code", "left join ds.DstMol as dm", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

		});

		addMasterModelListener(spec);
	}

}



