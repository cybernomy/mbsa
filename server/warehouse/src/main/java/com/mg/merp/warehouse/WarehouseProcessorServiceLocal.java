/* WarehouseProcessorServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.framework.api.BusinessObjectService;

import org.jboss.annotation.ejb.Local;

/**
 * Сервис "Процессор управления запасами"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessorServiceLocal.java,v 1.2 2007/02/22 09:49:26 poroxnenko Exp $
 */
@Local
public interface WarehouseProcessorServiceLocal extends BusinessObjectService, WarehouseProcessor {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/warehouse/WarehouseProcessor";
}
