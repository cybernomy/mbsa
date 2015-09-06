/*
 * PmcConfigMenuCommand.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentcontrol.ConfigServiceLocal;

import java.util.Map;

/**
 * Контроллер комманды меню "Конфигурация модуля <Платежный календарь>"
 *
 * @author Artem V. Sharapov
 * @version $Id: PmcConfigMenuCommand.java,v 1.2 2008/08/15 14:57:38 sharapov Exp $
 */
public class PmcConfigMenuCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    ConfigServiceLocal configService = (ConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Config"); //$NON-NLS-1$
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    MaintenanceHelper.edit(configService, sysClient.getId(), null, null);
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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/paymentcontrol/Config", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
  }

}
