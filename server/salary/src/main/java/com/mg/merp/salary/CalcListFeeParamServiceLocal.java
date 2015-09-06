/*
 * CalcListFeeParamServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.merp.salary.model.CalcListFeeParam;

/**
 * Сервис бизнес-компонента "Параметры начислений удержаний в расчетных листках"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListFeeParamServiceLocal.java,v 1.3 2008/02/01 10:18:12 safonov Exp $
 */
public interface CalcListFeeParamServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcListFeeParam, Integer> {

  /**
   * Имя сервиса
   */
  final static String LOCAL_SERVICE_NAME = "merp/salary/CalcListFeeParam"; //$NON-NLS-1$

}
