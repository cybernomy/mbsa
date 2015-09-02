/*
 * AccConfigMenuCommand.java
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
package com.mg.merp.account.support.ui;

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.AccountConfigServiceLocal;
import com.mg.merp.core.model.SysClient;

/**
 * Контроллер комманды меню "Конфигурация модуля <Бух.учет>"
 * 
 * @author Artem V. Sharapov
 * @version $Id: AccConfigMenuCommand.java,v 1.2 2008/08/15 15:04:41 sharapov Exp $
 */
public class AccConfigMenuCommand implements MenuCommand {

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#execute()
	 */
	public void execute() throws Exception {
		AccountConfigServiceLocal accountConfigService = (AccountConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AccountConfig"); //$NON-NLS-1$
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		MaintenanceHelper.edit(accountConfigService, sysClient.getId(), null, null);
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
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/account/AccountConfig", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
	}

}
