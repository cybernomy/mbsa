/*
 * PersonnelrefUtils.java
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
package com.mg.merp.personnelref.support;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Утилиты модуля "Справочник персонала"
 *
 * @author Artem V. Sharapov
 * @version $Id: PersonnelrefUtils.java,v 1.2 2007/08/22 08:24:20 sharapov Exp $
 */
public class PersonnelrefUtils {

  /**
   * Среднее количество дней в году
   */
  private final static double AVERAGE_DAYS_IN_YEAR = 365.25;

  private static final String BEGIN_DATE = "BeginDate"; //$NON-NLS-1$
  private static final String END_DATE = "EndDate"; //$NON-NLS-1$


  /**
   * Проверить даты начала и окончания интервала на null и заменить null-значение максимальной и
   * минимальной датой соответственно
   *
   * @param entity - проверяемая сущность
   */
  public static void checkDateInterval(PersistentObject entity) {
    if (entity.hasAttribute(BEGIN_DATE)) {
      Date beginDate = (Date) entity.getAttribute(BEGIN_DATE);
      if (beginDate == null || beginDate.compareTo(DateTimeUtils.MAX_DATE) >= 0)
        entity.setAttribute(BEGIN_DATE, DateTimeUtils.ZERO_DATE);
    }

    if (entity.hasAttribute(END_DATE)) {
      Date endDate = (Date) entity.getAttribute(END_DATE);
      if (endDate == null || endDate.compareTo(DateTimeUtils.ZERO_DATE) <= 0)
        entity.setAttribute(END_DATE, DateTimeUtils.MAX_DATE);
    }
  }

  /**
   * Получить максимальную дату из списка дат
   *
   * @param dates - список дат
   * @return максимальная дата
   */
  public static Date getMaxDate(Date... dates) {
    return getMinOrMaxDate(true, dates);
  }

  /**
   * Получить минимальную дату из списка дат
   *
   * @param dates - список дат
   * @return минимальная дата
   */
  public static Date getMinDate(Date... dates) {
    return getMinOrMaxDate(false, dates);
  }

  private static Date getMinOrMaxDate(boolean isMaxDateTarget, Date... dates) {
    Date targetDate = null;
    if (dates != null && dates.length > 0) {
      targetDate = dates[0];
      for (int i = 1; i < dates.length; i++) {
        if (dates[i] == null)
          continue;
        if (targetDate == null && dates[i] != null)
          targetDate = dates[i];

        if (isMaxDateTarget) {
          if (dates[i].compareTo(targetDate) > 0)
            targetDate = dates[i];
        } else {
          if (dates[i].compareTo(targetDate) < 0)
            targetDate = dates[i];
        }
      }
    }
    return targetDate;
  }

  /**
   * Получить разницу (количество дней) между годами
   *
   * @return разница(количество дней) между годами
   */
  public static double getYearSpan(Date dateTill, Date dateFrom) {
    return DateTimeUtils.getDaysBetween(dateTill, dateFrom) / AVERAGE_DAYS_IN_YEAR;
  }

  /**
   * Получить количество выходных дней(суббот и воскресений) за период
   *
   * @param beginDate - дата начала периода
   * @param endDate   - дата окончания периода
   * @return количество выходных дней(суббот и воскресений) за период
   */
  public static int getWeekendCount(Date beginDate, Date endDate) {
    int weekendCount = 0;
    Date currentDate = beginDate;
    while (currentDate.compareTo(endDate) < 0) {
      if (isWeekend(currentDate))
        weekendCount++;
      currentDate = DateTimeUtils.incDay(currentDate, 1);
    }
    return weekendCount;
  }

  /**
   * @param date - дата
   * @return true - если выходной(суббота, воскресенье); false - если будний день
   */
  public static boolean isWeekend(Date date) {
    Calendar calendar = Calendar.getInstance(ServerUtils.getUserLocale());
    calendar.setTime(date);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
      return true;
    else
      return false;
  }

}
