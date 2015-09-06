/*
 * ContractConfigMenuCommand.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.ContractConfigServiceLocal;
import com.mg.merp.contract.model.ContractConfig;
import com.mg.merp.core.model.SysClient;

import java.util.Map;

/**
 * Контроллер комманды меню "Конфигурация модуля <Контракты>"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractConfigMenuCommand.java,v 1.3 2008/08/15 14:49:36 sharapov Exp $
 */
public class ContractConfigMenuCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    ContractConfigServiceLocal сontractConfigService = (ContractConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/contract/ContractConfig"); //$NON-NLS-1$
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    ContractConfig config = сontractConfigService.load(sysClient.getId());
    if (config != null)
      MaintenanceHelper.edit(сontractConfigService, sysClient.getId(), null, null);
    else {
      config = сontractConfigService.initialize();
      MaintenanceHelper.add(сontractConfigService, config, null, null);
    }
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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/contract/ContractConfig", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
  }

}
