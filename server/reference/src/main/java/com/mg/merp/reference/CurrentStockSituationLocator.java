/*
 * CurrentStockSituationLocator.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.reference;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;


/**
 * Локатор сервиса рассчёта количества на складах
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituationLocator.java,v 1.1 2007/04/05 12:25:41 poroxnenko Exp $
 */
public class CurrentStockSituationLocator {
  private static volatile CurrentStockSituation instance = null;

  /**
   * @return сервис рассчёта количества на складах
   */
  public static CurrentStockSituation locate() {
    if (instance == null)
      try {
        instance = (CurrentStockSituation) ServerUtils.createMBeanProxy(CurrentStockSituation.class, CurrentStockSituation.SERVICE_NAME);
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }
}
