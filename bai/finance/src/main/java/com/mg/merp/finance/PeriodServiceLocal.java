/*
 * PeriodServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.finance.model.FinPeriod;

import java.io.Serializable;
import java.util.Date;

/**
 * Бизнес-компонент "Периоды финансового учета"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/01/16 14:35:00 safonov Exp $
 */
public interface PeriodServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<FinPeriod, Integer> {

  /**
   * Закрыть период
   *
   * @param periodIds - Идентификаторы периодов фин. учета
   */
  void closePeriod(Serializable[] periodIds);

  /**
   * Открыть период
   *
   * @param periodIds - Идентификаторы периодов фин. учета
   */
  void openPeriod(Serializable[] periodIds);

  /**
   * найти по дате
   *
   * @param date дата
   * @return период или генерируется ИС если период не найден
   */
  FinPeriod findByDate(Date date);

  /**
   * проверка периода на доступность изменений
   *
   * @param date дата изменения
   */
  void checkPeriod(Date date);

  /**
   * проверка периода на доступность изменений
   *
   * @param periodId идентификатор периода
   */
  void checkPeriod(Integer periodId);

}
