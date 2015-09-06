/**
 * FinConfigMenuCommand.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.finance.FinConfigServiceLocal;

import java.util.Map;

/**
 * Контроллер комманды меню "Конфигурация модуля <Фин.учет>"
 *
 * @author Artem V. Sharapov
 * @version $Id: FinConfigMenuCommand.java,v 1.2 2008/08/18 06:23:16 sharapov Exp $
 */
public class FinConfigMenuCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    FinConfigServiceLocal finConfigService = (FinConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/FinConfig"); //$NON-NLS-1$
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    MaintenanceHelper.edit(finConfigService, sysClient.getId(), null, null);
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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/finance/FinConfig", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
  }

}
