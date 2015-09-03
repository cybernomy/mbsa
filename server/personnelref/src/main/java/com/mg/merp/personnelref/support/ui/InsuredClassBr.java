/*
 * InsuredClassBr.java
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
import com.mg.merp.personnelref.model.InsuredClass;

/**
 * Браузер категорий плательщиков страховых взносов
 * 
 * @author leonova
 * @version $Id: InsuredClassBr.java,v 1.1 2006/08/03 11:24:18 leonova Exp $ 
 */
public class InsuredClassBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select inclass.Id";
	private String fieldsList;
	
	@Override
	protected String createQueryText() {
		return DatabaseUtils.embedAddinFieldsBrowseEJBQL(INIT_QUERY_TEXT.concat(fieldsList).concat(" from InsuredClass inclass order by inclass.Id"), service, "inclass.Id", getFieldsSet()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected Set<String> getDefaultFieldsSet() {
		Set<String> result = super.getDefaultFieldsSet();		
		result.addAll(StringUtils.split("Id,CCode,CName,InsuredPercent,EmployerPercent", ",")); //$NON-NLS-1$ //$NON-NLS-2$
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
				addColumnDef(InsuredClass.class, "Id", null); //$NON-NLS-1$
				if (fields.contains("CCode")) {
					fieldsList = fieldsList.concat(", inclass.CCode");
					addColumnDef(InsuredClass.class, "CCode", null); //$NON-NLS-1$
				}	
				if (fields.contains("CName")) {
					fieldsList = fieldsList.concat(", inclass.CName");
					addColumnDef(InsuredClass.class, "CName", null); //$NON-NLS-1$
				}
				if (fields.contains("InsuredPercent")) {
					fieldsList = fieldsList.concat(", inclass.InsuredPercent");
					addColumnDef(InsuredClass.class, "InsuredPercent", null); //$NON-NLS-1$
				}		
				if (fields.contains("EmployerPercent")) {
					fieldsList = fieldsList.concat(", inclass.EmployerPercent");
					addColumnDef(InsuredClass.class, "EmployerPercent", null); //$NON-NLS-1$
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
