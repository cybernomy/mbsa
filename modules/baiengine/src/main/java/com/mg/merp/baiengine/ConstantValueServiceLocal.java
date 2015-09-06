/*
 * ConstantValueServiceLocal.java
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
package com.mg.merp.baiengine;

import com.mg.merp.baiengine.model.ConstantValue;

/**
 * Бизнес-компонент "Значение константы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantValueServiceLocal.java,v 1.1 2007/08/21 12:55:46 alikaev Exp $
 */
public interface ConstantValueServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ConstantValue, Integer> {
  /**
   * Имя сервиса
   */
  final static String SERVICE_NAME = "merp/bai/ConstantValue";

}
