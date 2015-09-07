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
package com.mg.merp.finance.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.finance.model.FinConfig;

/**
 * Класс помощник конфигурации модуля "Финансы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ConfigurationHelper.java,v 1.1 2007/08/17 09:09:18 alikaev Exp $
 */
public class ConfigurationHelper {
  /**
   * получить конфигурацию модуля
   *
   * @return конфигурация
   */
  public static FinConfig getConfiguration() {
    return ServerUtils.getPersistentManager().find(FinConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
  }


}
