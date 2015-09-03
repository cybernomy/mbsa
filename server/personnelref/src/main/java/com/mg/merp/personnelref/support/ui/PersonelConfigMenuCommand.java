/*
 * PersonelConfigMenuCommand.java
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

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.personnelref.PersonnelConfigServiceLocal;

/**
 * Контроллер комманды меню "Конфигурация модуля <Управление персоналом>"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PersonelConfigMenuCommand.java,v 1.2 2008/08/15 15:07:15 sharapov Exp $
 */
public class PersonelConfigMenuCommand implements MenuCommand {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#execute()
	 */
	public void execute() throws Exception {
		PersonnelConfigServiceLocal personnelConfigService = (PersonnelConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelConfig");
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant(); 
		MaintenanceHelper.edit(personnelConfigService, sysClient.getId(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#init(java.util.Map)
	 */
	public void init(Map<String, String> arg0) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#isPermitted()
	 */
	public boolean isPermitted() {
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/personnelref/PersonnelConfig", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
	}

}
