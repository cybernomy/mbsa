/*
 * GroupBr.java
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
package com.mg.merp.security.support.ui;

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.model.Groups;

/**
 * Браузер групп пользователей
 * 
 * @author leonova
 * @version $Id: GroupBr.java,v 1.2 2007/02/24 14:23:54 safonov Exp $ 
 */
public class GroupBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Groups g";
	
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
				result.add(new TableEJBQLFieldDef(Groups.class, "Id", "g.Id", true));
				result.add(new TableEJBQLFieldDef(Groups.class, "Name", "g.Name", false));
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

	public void onActionShowPermission(WidgetEvent event) {
		Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length != 0) {
			((RolePermissionsForm) UIProducer.produceForm("com/mg/merp/security/resources/RolePermissionsForm.mfd.xml"))
				.execute(ServerUtils.getPersistentManager().find(Groups.class, keys[0]));
		}
	}

	public void onActionShowSubsystem(WidgetEvent event) {
		Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length != 0) {
			((SubsystemPermissionsForm) UIProducer.produceForm("com/mg/merp/security/resources/SubsystemPermissionsForm.mfd.xml"))
				.execute(ServerUtils.getPersistentManager().find(Groups.class, keys[0]));
		}
	}

}
