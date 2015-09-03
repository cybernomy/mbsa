/*
 * CountryBr.java
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
package com.mg.merp.reference.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.Country;

/**
 * Браузер стран
 * 
 * @author leonova
 * @version $Id: CountryBr.java,v 1.2 2006/08/11 07:19:06 leonova Exp $ 
 */
public class CountryBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Country c";
	
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);					
		return String.format(INIT_QUERY_TEXT, fieldsList);	
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
				result.add(new TableEJBQLFieldDef(Country.class, "Id", "c.Id", true));
				result.add(new TableEJBQLFieldDef(Country.class, "CCode", "c.CCode", false));
				result.add(new TableEJBQLFieldDef(Country.class, "CName", "c.CName", false));
				result.add(new TableEJBQLFieldDef(Country.class, "UniversalCode", "c.UniversalCode", false));
				result.add(new TableEJBQLFieldDef(Country.class, "UniversalAbbr", "c.UniversalAbbr", false));
				result.add(new TableEJBQLFieldDef(Country.class, "UniversalNumber", "c.UniversalNumber", false));
				result.add(new TableEJBQLFieldDef(Country.class, "Capital", "c.Capital", false));				
				result.add(new TableEJBQLFieldDef(Country.class, "PhoneCode", "c.PhoneCode", false));		
				result.add(new TableEJBQLFieldDef(Country.class, "FullName", "c.FullName", false));
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
