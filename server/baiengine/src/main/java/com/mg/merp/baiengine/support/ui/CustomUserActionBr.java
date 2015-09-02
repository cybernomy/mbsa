/*
 * CustomUserActionBr.java
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
package com.mg.merp.baiengine.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.baiengine.model.CustomUserAction;

/**
 * Ѕраузер настраиваемых действий пользовател€
 * 
 * @author leonova
 * @version $Id: CustomUserActionBr.java,v 1.1 2007/11/15 09:22:14 safonov Exp $ 
 */
public class CustomUserActionBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from CustomUserAction cua %s";
	
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
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Id", "cua.Id", true));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Code", "cua.Code", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Description", "cua.Description", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Caption", "cua.Caption", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "KeyStroke", "cua.KeyStroke", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "FromMenu", "cua.FromMenu", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "FromToolbar", "cua.FromToolbar", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "ForceRefresh", "cua.ForceRefresh", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "SeparatorBefore", "cua.SeparatorBefore", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "SeparatorAfter", "cua.SeparatorAfter", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Hint", "cua.Hint", false));
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "Priority", "cua.Priority", false));				
				result.add(new TableEJBQLFieldDef(CustomUserAction.class, "BAi", "bai.Code", "left join cua.BAi as bai", false));
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

