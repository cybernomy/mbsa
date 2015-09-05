/*
 * CustomUserActionMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.baiengine.support.ui;

import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.baiengine.CustomUserActionServiceLocal;
import com.mg.merp.baiengine.model.CustomUserAction;
import com.mg.merp.baiengine.model.CustomUserActionPermiss;
import com.mg.merp.security.model.Groups;

/**
 * Контроллер формы поддержки настраиваемых действий
 * 
 * @author leonova
 * @version $Id: CustomUserActionMt.java,v 1.2 2007/11/15 09:57:04 safonov Exp $
 */
public class CustomUserActionMt extends DefaultMaintenanceForm implements MasterModelListener {
	private DefaultTableController permissions;

	public CustomUserActionMt() throws Exception {
		setMasterDetail(true);
		permissions = new DefaultTableController(new PermissionsModel());
		addMasterModelListener(this);
	}

	private class PermissionsModel extends DefaultEntityListTableModel<CustomUserActionPermiss> {

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			List<CustomUserActionPermiss> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CustomUserActionPermiss.class)
					.add(Restrictions.eq("CustomUserAction", getEntity())));
			setEntityList(list);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		PopupMenu pm = view.getWidget("permissions").getPopupMenu();
		pm.getMenuItem("grantPermission").setReadOnly(readOnly);
		pm.getMenuItem("revokePermission").setReadOnly(readOnly);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		permissions.getModel().load();
	}

	/**
	 * обработчик добавления прав на действие
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onActionGrantPermission(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.security.support.ui.SecGroupSearchHelp");
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchCanceled(SearchHelpEvent event) {
			}

			public void searchPerformed(SearchHelpEvent event) {
				((CustomUserActionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CustomUserActionServiceLocal.SERVICE_NAME))
						.grantPermission(((CustomUserAction) getEntity()).getId(), ((Groups) event.getItems()[0]).getId());
				permissions.getModel().load();
			}
			
		});
		searchHelp.search();
	}

	/**
	 * обработчик удаления прав на действие
	 * 
	 * @param event
	 */
	public void onActionRevokePermission(WidgetEvent event) {
		CustomUserActionPermiss[] perms = ((PermissionsModel) permissions.getModel()).getSelectedEntities();		
		((CustomUserActionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CustomUserActionServiceLocal.SERVICE_NAME))
				.revokePermission(perms);
		permissions.getModel().load();
	}

}
