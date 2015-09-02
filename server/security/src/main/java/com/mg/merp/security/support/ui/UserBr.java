/*
 * UserBr.java
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

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.UserServiceLocal;
import com.mg.merp.security.model.SecUser;

/**
 * Браузер пользователей
 * 
 * @author leonova
 * @version $Id: UserBr.java,v 1.5 2008/12/09 10:31:01 safonov Exp $ 
 */
public class UserBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from SecUser u";
	
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
				result.add(new TableEJBQLFieldDef(SecUser.class, "Id", "u.Id", true));
				result.add(new TableEJBQLFieldDef(SecUser.class, "Name", "u.Name", false));
				result.add(new TableEJBQLFieldDef(SecUser.class, "FullName", "u.FullName", false));		
				//result.add(new TableEJBQLFieldDef(SecUser.class, "SmartCardOnly", "u.SmartCardOnly", false));
				result.add(new TableEJBQLFieldDef(SecUser.class, "Email", "u.Email", false));
				result.add(new TableEJBQLFieldDef(SecUser.class, "Mobile", "u.Mobile", false));				
				result.add(new TableEJBQLFieldDef(SecUser.class, "Pager", "u.Pager", false));
				//result.add(new TableEJBQLFieldDef(SecUser.class, "UseRemoteProfile", "u.UseRemoteProfile", false));				
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

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		PopupMenu pm = view.getWidget(TABLE_WIDGET).getPopupMenu();
		if (pm != null) {
			UIUtils.setEnabledProperty(pm.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
			UIUtils.setEnabledProperty(pm.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM), false);
			UIUtils.setEnabledProperty(pm.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM), false);
		}
		UIUtils.setEnabledProperty(view.getWidget("addButton"), false);
		UIUtils.setEnabledProperty(view.getWidget("cloneButton"), false);
		UIUtils.setEnabledProperty(view.getWidget("deepCloneButton"), false);
	}

	public void onActionCreateUser(WidgetEvent event) {
		final CreateUserForm form = (CreateUserForm) UIProducer.produceForm("com/mg/merp/security/resources/CreateUserForm.mfd.xml");
		form.addOkActionListener(new FormActionListener() {

			public void actionPerformed(FormEvent event) {
				ServerUtils.getSecuritySystem().createUser(form.getName(), form.getPassw());
				table.refresh();
			}
			
		});
		form.execute();
	}

	public void onActionChangePassword(WidgetEvent event) {
		final Serializable[] keys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys.length == 0)
			return;
		
		final ChangePasswordForm form = (ChangePasswordForm) UIProducer.produceForm("com/mg/merp/security/resources/ChangePasswordForm.mfd.xml");
		form.addOkActionListener(new FormActionListener() {

			public void actionPerformed(FormEvent event) {
				SecUser user = ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).load((Integer) keys[0]);
				ServerUtils.getSecuritySystem().changePassword(user.getName(), form.getPassw());
			}
			
		});
		form.execute();
	}

}
