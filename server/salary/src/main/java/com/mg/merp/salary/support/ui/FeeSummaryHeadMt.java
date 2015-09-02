/*
 * FeeSummaryHeadMt.java
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
package com.mg.merp.salary.support.ui;

import java.util.Set;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.salary.FeeSummarySpecServiceLocal;
import com.mg.merp.salary.model.FeeSummarySpec;

/**
 * Контроллер формы поддержки сволов по н/у
 * 
 * @author leonova
 * @version $Id: FeeSummaryHeadMt.java,v 1.6 2008/12/25 10:21:25 safonov Exp $
 */
public class FeeSummaryHeadMt extends GoodsDocumentMaintenanceForm {
	
	public FeeSummaryHeadMt() throws Exception {
		super();
		specService = ((FeeSummarySpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/FeeSummarySpec"));

		spec.initController(specService, new GoodsDocSpecMaintenanceEJBQLTableModel() {			
			
			/* (non-Javadoc)
			 * @see com.mg.merp.warehouse.support.ui.GoodsDocMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
			 */			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();		
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false));
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false));
				result.add(new TableEJBQLFieldDef(FeeSummarySpec.class, "CostsAnl1", "anl1.ACode", "left join ds.CostsAnl1 as anl1", false));
				result.add(new TableEJBQLFieldDef(FeeSummarySpec.class, "CostsAnl2", "anl2.ACode", "left join ds.CostsAnl2 as anl2", false));
				result.add(new TableEJBQLFieldDef(FeeSummarySpec.class, "CostsAnl3", "anl3.ACode", "left join ds.CostsAnl3 as anl3", false));
				result.add(new TableEJBQLFieldDef(FeeSummarySpec.class, "CostsAnl4", "anl4.ACode", "left join ds.CostsAnl4 as anl4", false));
				result.add(new TableEJBQLFieldDef(FeeSummarySpec.class, "CostsAnl5", "anl5.ACode", "left join ds.CostsAnl5 as anl5", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
			 */
			@Override
			protected String getDocSpecModelName() {
				return FeeSummarySpec.class.getName();
			}

		});

		addMasterModelListener(spec);
	}

}

