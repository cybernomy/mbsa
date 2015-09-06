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
package com.mg.merp.personnelref.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.support.ConfigurationImpl;
import com.mg.merp.personnelref.model.PersonnelConfig;

/**
 * Класс помощник конфигурации модуля "Управление персоналом"
 *
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationHelper.java,v 1.1 2007/03/26 10:46:27 safonov Exp $
 */
public class ConfigurationHelper {

  /**
   * получить конфигурацию модуля
   */
  public static PersonnelConfig getConfiguration() {
    return ServerUtils.getPersistentManager().find(PersonnelConfig.class, ((SysClient) ServerUtils.getCurrentSession().getSystemTenant()).getId());
  }

  /**
   * получить конфигурацию документов данного модуля
   */
  public static Configuration getDocumentConfiguration() {
    PersonnelConfig cfg = getConfiguration();
    return new ConfigurationImpl(cfg.getBaseCurrency(), cfg.getNatCurrency(), cfg.getCurrencyPrec(), cfg.getCurRateAuthority(), cfg.getCurRateType());
  }

}
