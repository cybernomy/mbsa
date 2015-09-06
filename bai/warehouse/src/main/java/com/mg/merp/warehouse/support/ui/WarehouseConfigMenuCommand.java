/*
 * WarehouseConfigMenuCommand.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.warehouse.WarehouseConfigServiceLocal;
import com.mg.merp.warehouse.model.WarehouseConfig;

import java.util.Map;

/**
 * Контроллер комманды меню "Конфигурация модуля <Склады, снабжение, сбыт>"
 *
 * @author Artem V. Sharapov
 * @version $Id: WarehouseConfigMenuCommand.java,v 1.3 2009/01/23 13:06:57 safonov Exp $
 */
public class WarehouseConfigMenuCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    WarehouseConfigServiceLocal warehouseConfigService = (WarehouseConfigServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/warehouse/WarehouseConfig"); //$NON-NLS-1$
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    WarehouseConfig config = warehouseConfigService.load(sysClient.getId());
    if (config == null) {
      config = warehouseConfigService.initialize();
      config.setSysClientId(sysClient.getId());
      MaintenanceHelper.add(warehouseConfigService, config, null, null);
    } else
      MaintenanceHelper.edit(warehouseConfigService, sysClient.getId(), null, null);

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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/warehouse/WarehouseConfig", BusinessMethodPermission.LOAD_METHOD)); //$NON-NLS-1$
  }

}
