/*
 * TaxScaleServiceLocal.java
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
package com.mg.merp.salary;

import com.mg.merp.salary.model.TaxScale;

/**
 * Сервис бизнес-компонента "Налоговые шкалы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TaxScaleServiceLocal.java,v 1.3 2007/07/17 08:34:08 sharapov Exp $
 */
public interface TaxScaleServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TaxScale, Integer> {

  /**
   * Имя сервиса
   */
  final static String LOCAL_SERVICE_NAME = "merp/salary/TaxScale"; //$NON-NLS-1$

}
