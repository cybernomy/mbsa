/**
 * FormBr.java
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
package com.mg.merp.core.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.core.model.Form;

/**
 * ������� ���� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FormBr.java,v 1.1 2008/03/03 13:05:04 safonov Exp $
 */
public class FormBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from com.mg.merp.core.model.Form f %s";	
	
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
				result.add(new TableEJBQLFieldDef(Form.class, "Id", "f.Id", true));
				result.add(new TableEJBQLFieldDef(Form.class, "ApplicationLayer", "f.ApplicationLayer", false));				
				result.add(new TableEJBQLFieldDef(Form.class, "SysClass", "f.SysClass", false));
				result.add(new TableEJBQLFieldDef(Form.class, "Role", "r.Name", " left join f.Role as r ", false));
				result.add(new TableEJBQLFieldDef(Form.class, "User", "u.Name", " left join f.User as u ", false));
				result.add(new TableEJBQLFieldDef(Form.class, "Name", "f.Name", false));
				result.add(new TableEJBQLFieldDef(Form.class, "Implementation", "f.Implementation", false));				
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
