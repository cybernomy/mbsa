/*
 * ConfigurationHelper.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.support.ConfigurationImpl;
import com.mg.merp.warehouse.model.WarehouseConfig;

/**
 * Класс помощник конфигурации модуля "Управление запасами"
 *
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationHelper.java,v 1.2 2007/03/26 10:53:35 safonov Exp $
 */
public class ConfigurationHelper {

  /**
   * получить конфигурацию модуля
   *
   * @return конфигурация
   */
  public static WarehouseConfig getConfiguration() {
    return ServerUtils.getPersistentManager().find(WarehouseConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
  }

  public static Configuration getDocumentConfiguration() {
    WarehouseConfig cfg = getConfiguration();
    return new ConfigurationImpl(cfg.getBaseCurrency(), cfg.getNatCurrency(), cfg.getCurrencyPrec(), cfg.getCurrencyRateAuthority(), cfg.getCurrencyRateType());
  }

}
