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
package com.mg.merp.account;

import com.mg.merp.account.model.Period;

import java.io.Serializable;
import java.util.Date;

/**
 * Бизнес-компонент "Периоды бух.учета"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Oleg V. Safonov
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/01/16 11:39:21 safonov Exp $
 */
public interface PeriodServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Period, Integer> {
  /**
   * Открыть периоды бух.учета
   *
   * @param periods - набор периодов
   */
  void openPeriod(Serializable[] periods);

  /**
   * Закрыть периоды бух.учета
   *
   * @param periods - набор периодов
   */
  void closePeriod(Serializable[] periods);

  /**
   * найти период по дате, если период не найден то будет сгенерирована ИС
   *
   * @param date дата
   * @return период
   */
  Period findByDate(Date date);

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
