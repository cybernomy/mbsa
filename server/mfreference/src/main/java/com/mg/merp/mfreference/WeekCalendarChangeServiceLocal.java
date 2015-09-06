/*
 * WeekCalendarChangeServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference;

import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.model.WeekCalendarChange;

import java.util.Date;

/**
 * Бизнес-компонент "Изменения недельного календаря"
 *
 * @author leonova
 * @version $Id: WeekCalendarChangeServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface WeekCalendarChangeServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<WeekCalendarChange, Integer> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/mfreference/WeekCalendarChange";

  /**
   * получение дневного календаря
   *
   * @param weekCalId  недельный календарь
   * @param searchDate дата
   * @return дневной календарь или <code>null</code> если не найден
   */
  DayCalendar getDayCalendar(int weekCalId, Date searchDate);
}
