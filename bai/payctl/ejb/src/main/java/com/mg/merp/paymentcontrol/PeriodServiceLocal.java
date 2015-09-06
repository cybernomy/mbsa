/*
 * PeriodServiceLocal.java
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
 */
package com.mg.merp.paymentcontrol;

import com.mg.merp.paymentcontrol.model.PmcPeriod;

import java.util.List;

/**
 * Сервис бизнес-компонента "Периоды планирования"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface PeriodServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PmcPeriod, Integer> {

  /**
   * локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Period"; //$NON-NLS-1$

  /**
   * Создать периоды планирования (с заданным интервалом)
   *
   * @param isPmcYear       - годовой интервал
   * @param isPmcHalfYear   - полу-годовой интервал
   * @param isPmcQuarter    - квартальный интервал
   * @param isPmcMonth      - месячный интервал
   * @param isPmcTenDays    - интервал декада
   * @param isPmcWeek       - недельный интервал
   * @param isPmcDay        - дневной интервал
   * @param beginDate       - дата начала формирования
   * @param upLevelQuantity - количество периодов (верхнего уровня)
   * @param Parent          - родительский узел
   */
  void createPeriods(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, java.lang.Integer upLevelQuantity, com.mg.merp.paymentcontrol.model.PmcPeriod Parent);

  /**
   * Получить список вложенных периодов планирования (не рекурсивно)
   *
   * @param parentPeriod - для периода
   * @return список дочерних периодов
   */
  List<PmcPeriod> getNestedPeriods(PmcPeriod parentPeriod);

}
