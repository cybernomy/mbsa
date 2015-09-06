/*
 * CalcListFeeServiceLocal.java
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

import com.mg.merp.salary.model.CalcListFee;

/**
 * Сервис бизнес-компонента "Начисления/ударжания расчетных листков"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListFeeServiceLocal.java,v 1.2 2007/07/09 08:20:19 sharapov Exp $
 */
public interface CalcListFeeServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcListFee, Integer> {

  /**
   * Имя сервиса
   */
  final static String LOCAL_SERVICE_NAME = "merp/salary/CalcListFee"; //$NON-NLS-1$

  public void recalc(int calcListId, int calcListFeeId) throws com.mg.framework.api.ApplicationException;

}
