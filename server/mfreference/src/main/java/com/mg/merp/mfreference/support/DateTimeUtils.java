/*
 * DateTimeUtils.java
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

package com.mg.merp.mfreference.support;

import java.util.Calendar;
import java.util.Locale;

/**
 * Утилита обработки дат
 *
 * @author Artem V. Sharapov
 * @version $Id: DateTimeUtils.java,v 1.2 2007/07/30 10:24:41 safonov Exp $
 * @deprecated use {@link com.mg.framework.utils.DateTimeUtils} instead
 */
@Deprecated
public class DateTimeUtils {

  /**
   * Определяет дату начала года
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return начало года
   */
  public static java.util.Date startOfTheYear(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.DATE, 1);//начало года
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    return calendar.getTime();
  }

  /**
   * Определяет конец года
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return конец года (31.12.XX 23:59:59)
   */
  public static java.util.Date endOfTheYear(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.MONTH, Calendar.DECEMBER);//конец года
    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();
  }

  /**
   * Определяет конец месяца
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата установленная в конец месяца (последнее число месяца XX.XX.XX 23:59:59)
   */
  public static java.util.Date endOfTheMonth(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();
  }

  /**
   * Определяет конец недели
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата установленная в конец недели (воскресение 23:59:59)
   */
  public static java.util.Date endOfTheWeek(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();
  }

  /**
   * Определяет начало недели
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата установленная в начало недели (понедельник)
   */
  public static java.util.Date startOfTheWeek(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return calendar.getTime();
  }

  /**
   * Увеличивает неделю на число заданное параметром amount
   * @param date
   * @param amount - шаг увеличения
   * @param locale - локаль временных(даты, времени) параметров
   * @return дату увеличенную на число заданное параметром amount
   */
//	public static java.util.Date incWeek(java.util.Date date, int amount, Locale locale) {
//		Calendar calendar = Calendar.getInstance(locale);
//		calendar.setTime(date);
//		calendar.add(Calendar.WEEK_OF_MONTH, amount);
//		return calendar.getTime();
//	}

  /**
   * Определяет начало месяца
   *
   * @return дату установленную в начало месяца (01.XX.XX)
   */
  public static java.util.Date startOfTheMonth(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    calendar.set(Calendar.DATE, 1);
    return calendar.getTime();
  }

  /**
   * Увеличивает год на число заданное параметром amount
   * @param date
   * @param amount - шаг инкрементации
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата с увеличенным годом
   */
//	public static java.util.Date incYear(java.util.Date date, int amount, Locale locale) {
//		Calendar calendar = Calendar.getInstance(locale);
//		calendar.setTime(date);
//		calendar.add(Calendar.YEAR, amount);
//		return calendar.getTime();
//	}

  /**
   * Увеличивает месяц на число заданное параметром amount
   * @param date
   * @param amount - шаг инкрементации
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата с увеличенным месяцом
   */
//	public static java.util.Date incMonth(java.util.Date date, int amount, Locale locale) {
//		Calendar calendar = Calendar.getInstance(locale);
//		calendar.setTime(date);
//		calendar.add(Calendar.MONTH, amount);
//		return calendar.getTime();
//	}

  /**
   * Увеличивает день на число заданное параметром amount
   * @param date
   * @param amount - шаг инкрементации
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата с увеличенным днем
   */
//	public static java.util.Date incDay(java.util.Date date, int amount, Locale locale) {
//		Calendar calendar = Calendar.getInstance(locale);
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, amount);
//		return calendar.getTime();
//	}

  /**
   * Определяет полугодие
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return полугодие (первое или второе)
   */
  public static int halfYearOf(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) < 6 ? 1 : 2;
  }

  /**
   * Определяет квартал
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return квартал
   */
  public static int qurterOf(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    return calendar.get(Calendar.MONTH) / 3 + 1;
  }

  /**
   * Определяет номер недели года
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return номер недели года
   */
  public static int weekOfYear(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  /**
   * Определяет начало полугодия
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата начала полугодия
   */
  public static java.util.Date beginOfTheHalfYear(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    if (calendar.get(Calendar.MONTH) <= 6) {
      calendar.set(Calendar.MONTH, Calendar.JANUARY);//начало 1 полугодия
      calendar.set(Calendar.DATE, 1);
      return calendar.getTime();
    } else {
      calendar.set(Calendar.MONTH, Calendar.JULY);//начало 2 полугодия
      calendar.set(Calendar.DATE, 1);
      return calendar.getTime();
    }
  }

  /**
   * Определяет начало квартала
   *
   * @param locale - локаль временных(даты, времени) параметров
   * @return дата начала квартала
   */
  public static java.util.Date beginOfTheQuarter(java.util.Date date, Locale locale) {
    Calendar calendar = Calendar.getInstance(locale);
    calendar.setTime(date);
    int quarter = ((calendar.get(Calendar.MONTH)) / 3) + 1;
    calendar.set(Calendar.MONTH, quarter * 3 - 3);//начало квартала
    calendar.set(Calendar.DATE, 1);
    return calendar.getTime();
  }
}
