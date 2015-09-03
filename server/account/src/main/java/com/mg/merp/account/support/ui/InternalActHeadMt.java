/*
 * AdvanceRepHeadMt.java
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
package com.mg.merp.account.support.ui;

import java.util.Set;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.InternalActSpecServiceLocal;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки фнутренних актов
 * 
 * @author leonova
 * @version $Id: InternalActHeadMt.java,v 1.7 2008/12/25 10:12:32 safonov Exp $
 */
public class InternalActHeadMt extends GoodsDocumentMaintenanceForm {
	
	public InternalActHeadMt() throws Exception {
		super();
		contractorThroughKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		
		specService = ((InternalActSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InternalActSpec"));

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
				result.add(new TableEJBQLFieldDef(DocSpec.class, "BestBefore", "ds.BestBefore", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

		});

		addMasterModelListener(spec);
	}

}

