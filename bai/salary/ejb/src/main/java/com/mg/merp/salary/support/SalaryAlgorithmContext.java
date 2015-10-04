/*
 * SalaryAlgorithmContext.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.support;

import com.mg.framework.api.ApplicationException;

import java.util.Date;

/**
 * Класс контекста выполнения расчета зарплаты
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SalaryAlgorithmContext {

  /**
   * @return идентификатор текущего ш/р
   */
  final public int getStaffListId() throws ApplicationException {
    //return nativeGetStaffListId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор образца н/у из л/с сотрудника
   */
  final public int getFeeModelId() throws ApplicationException {
    //return nativeGetFeeModelId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор текущей р/в
   */
  final public int getPayRollId() throws ApplicationException {
    //return nativeGetPayRollId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор текущего р/л
   */
  final public int getCalcListId() throws ApplicationException {
    //return nativeGetCalcListId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор текущего н/у
   */
  final public int getCalcListFeeId() throws ApplicationException {
    //return nativeGetCalcListFeeId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор исполнения должности, по которой производится расчет
   */
  final public int getPositionFillId() throws ApplicationException {
    //return nativeGetPositionFillId(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор л/с сотрудника
   */
  final public int getPersonalAccountId() throws ApplicationException {
    //return nativeGetPersonalAccountId(handle);
    //TODO
    return 0;
  }

  /**
   * @return начислено с
   */
  final public Date getBeginDate() throws ApplicationException {
    //return new Date(nativeGetBeginDate(handle));
    //TODO
    return null;
  }

  /**
   * @return начислено по
   */
  final public Date getEndDate() throws ApplicationException {
    //return new Date(nativeGetEndDate(handle));
    //TODO
    return null;
  }

  /**
   * @return за период с
   */
  final public Date getPeriodBeginDate() throws ApplicationException {
    //return new Date(nativeGetPeriodBeginDate(handle));
    //TODO
    return null;
  }

  /**
   * @return за период по
   */
  final public Date getPeriodEndDate() throws ApplicationException {
    //return new Date(nativeGetPeriodEndDate(handle));
    //TODO
    return null;
  }

  /**
   * @return идентификатор аналитики состава затрат 1-го уровня
   */
  final public int getCostsAnl1Id() throws ApplicationException {
    //return nativeGetCostsAnl1Id(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор аналитики состава затрат 2-го уровня
   */
  final public int getCostsAnl2Id() throws ApplicationException {
    //return nativeGetCostsAnl2Id(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор аналитики состава затрат 3-го уровня
   */
  final public int getCostsAnl3Id() throws ApplicationException {
    //return nativeGetCostsAnl3Id(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор аналитики состава затрат 4-го уровня
   */
  final public int getCostsAnl4Id() throws ApplicationException {
    //return nativeGetCostsAnl4Id(handle);
    //TODO
    return 0;
  }

  /**
   * @return идентификатор аналитики состава затрат 5-го уровня
   */
  final public int getCostsAnl5Id() throws ApplicationException {
    //return nativeGetCostsAnl5Id(handle);
    //TODO
    return 0;
  }

  /**
   * Проверка аналитики состава затрат
   *
   * @return <code>true</code> если все поля аналитики состава затрат = 0
   */
  final public boolean isEmptyCostsAnl() throws ApplicationException {
    //return nativeisEmptyCostsAnl(handle);
    //TODO
    return false;
  }

}
