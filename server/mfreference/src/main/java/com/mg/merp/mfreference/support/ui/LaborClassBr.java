/*
 * LaborClassBr.java
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
package com.mg.merp.mfreference.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.model.LaborClass;

/**
 * Браузер класс рабочей силы
 * 
 * @author leonova
 * @version $Id: LaborClassBr.java,v 1.3 2008/03/04 10:42:31 sharapov Exp $ 
 */
public class LaborClassBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from LaborClass lc %s";
	
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(LaborClass.class, "Id", "lc.Id", true));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "Description", "lc.Description", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhRate", "lc.LbrOhRate", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhAllocationFlag", "lc.LbrOhAllocationFlag", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhRateCurrency", "lc.LbrOhRateCurrency.Code", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhTimeUm", "lc.LbrOhTimeUm.Code", false));	
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhRatio", "lc.LbrOhRatio", false));
				//result.add(new TableEJBQLFieldDef(LaborClass.class, "LaborOverheadAllocationFlag", "lc.LbrOhAllocationFlag", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrRateCurrency", "lc.LbrRateCurrency.Code", false));
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrTimeUm", "lc.LbrTimeUm.Code", false));				
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrCostCategory", "lcc.Code", "left join lc.LbrCostCategory as lcc", false));	
				result.add(new TableEJBQLFieldDef(LaborClass.class, "LbrOhCostCategory", "locc.Code", "left join lc.LbrOhCostCategory as locc", false));					
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};
	}

}
