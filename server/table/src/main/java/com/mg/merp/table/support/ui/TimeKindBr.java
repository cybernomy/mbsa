/*
 * TimeKindBr.java
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
package com.mg.merp.table.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.table.model.TimeKind;

/**
 * Браузер типов времени
 * 
 * @author leonova
 * @version $Id: TimeKindBr.java,v 1.1 2006/08/29 12:48:57 leonova Exp $ 
 */
public class TimeKindBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from TimeKind tk";
	
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
				result.add(new TableEJBQLFieldDef(TimeKind.class, "Id", "tk.Id", true));
				result.add(new TableEJBQLFieldDef(TimeKind.class, "Code", "tk.Code", false));
				result.add(new TableEJBQLFieldDef(TimeKind.class, "Name", "tk.Name", false));
				result.add(new TableEJBQLFieldDef(TimeKind.class, "Priority", "tk.Priority", false));
				result.add(new TableEJBQLFieldDef(TimeKind.class, "IsWholeDay", "tk.IsWholeDay", false));				
				result.add(new TableEJBQLFieldDef(TimeKind.class, "MnemoCode", "tk.MnemoCode", false));
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

