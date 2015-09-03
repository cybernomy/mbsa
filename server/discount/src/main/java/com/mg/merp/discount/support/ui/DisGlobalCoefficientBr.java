/*
 * DisGlobalCoefficientBr.java
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
package com.mg.merp.discount.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.discount.model.Coefficient;

/**
 * Браузер коеффициентов каталога
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: DisGlobalCoefficientBr.java,v 1.2 2008/05/21 13:28:37 alikaev Exp $ 
 */
public class DisGlobalCoefficientBr extends DefaultPlainBrowseForm {
	
	private final String INIT_QUERY_TEXT = "select %s from Coefficient c %s %s ";
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);					
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		StringBuilder whereText = new StringBuilder(" where 0=0 ");
		whereText.append(" and c.Card is null ");
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Coefficient.class, "Id", "c.Id", true));
				result.add(new TableEJBQLFieldDef(Coefficient.class, "Catalog", "cat.Code", "left join c.Catalog as cat", false));
				result.add(new TableEJBQLFieldDef(Coefficient.class, "CatalogFolder", "cf.FName", "left join c.CatalogFolder as cf", false));
				result.add(new TableEJBQLFieldDef(Coefficient.class, "Coefficient", "c.Coefficient", false));
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
