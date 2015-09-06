/*
 * ConfigurationServiceSupport.java
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
package com.mg.merp.warehouse.support.jboss;

import com.mg.merp.warehouse.ConfigurationService;
import com.mg.merp.warehouse.model.WarehouseConfig;
import com.mg.merp.warehouse.support.ConfigurationServiceImpl;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса конфигурации модуля "Управление запасами" для контейнера JBoss
 *
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationServiceSupport.java,v 1.1 2006/12/12 15:31:10 safonov Exp $
 */
@Service(objectName = ConfigurationServiceMBean.SERVICE_NAME)
@Management(ConfigurationServiceMBean.class)
public class ConfigurationServiceSupport extends ServiceMBeanSupport implements
    ConfigurationServiceMBean {
  private ConfigurationService delegate = new ConfigurationServiceImpl();

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.ConfigurationService#load()
   */
  public WarehouseConfig load() {
    return delegate.load();
  }

}
