/*
 * ForeignLanguageBr.java
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
package com.mg.merp.personnelref.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.personnelref.model.ForeignLanguage;

/**
 * Браузер иностранных языков
 * 
 * @author leonova
 * @version $Id: ForeignLanguageBr.java,v 1.1 2006/08/03 11:23:49 leonova Exp $ 
 */
public class ForeignLanguageBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select forlang.Id";
	private String fieldsList;
	
	@Override
	protected String createQueryText() {
		return DatabaseUtils.embedAddinFieldsBrowseEJBQL(INIT_QUERY_TEXT.concat(fieldsList).concat(" from ForeignLanguage forlang order by forlang.Id"), service, "forlang.Id", getFieldsSet()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected Set<String> getDefaultFieldsSet() {
		Set<String> result = super.getDefaultFieldsSet();		
		result.addAll(StringUtils.split("Id,Name,Okin", ",")); //$NON-NLS-1$ //$NON-NLS-2$
		return DatabaseUtils.embedAddinFieldsDefaultFieldsSet(result, service);

	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#createColumnsModel()
			 */
			@Override
			protected void createColumnsModel() {
				//order is important	
				fieldsList = "";
				Set<String> fields = getFieldsSet();
				addColumnDef(ForeignLanguage.class, "Id", null); //$NON-NLS-1$
				if (fields.contains("Name")) {
					fieldsList = fieldsList.concat(", forlang.Name");
					addColumnDef(ForeignLanguage.class, "Name", null); //$NON-NLS-1$
				}
				if (fields.contains("Okin")) {
					fieldsList = fieldsList.concat(", forlang.Okin");
					addColumnDef(ForeignLanguage.class, "Okin", null); //$NON-NLS-1$
				}				
				addAddinFieldDef(service);
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
