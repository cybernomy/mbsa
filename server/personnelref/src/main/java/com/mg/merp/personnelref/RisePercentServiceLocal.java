/*
 * RisePercentServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.RisePercent;

/**
 * Бизнес-компонент "Процент надбавки"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: RisePercentServiceLocal.java,v 1.2 2007/11/08 06:53:56 sharapov Exp $
 */
public interface RisePercentServiceLocal extends com.mg.framework.api.DataBusinessObjectService<RisePercent, Integer> {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/personnelref/RisePercent"; //$NON-NLS-1$

}
