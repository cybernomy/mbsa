/*
 * ConfigServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import com.mg.merp.paymentcontrol.model.PmcConfig;

/**
 * Сервис бизнес-компонента "Конфигурация модуля <Платежный календарь>"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ConfigServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface ConfigServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PmcConfig, Integer> {

  /**
   * локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Config"; //$NON-NLS-1$

}
