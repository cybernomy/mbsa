/*
 * WeekCalendarServiceLocal.java
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

import com.mg.merp.mfreference.model.WeekCalendar;

/**
 * Бизнес-компонент "Недельный календарь"
 *
 * @author leonova
 * @version $Id: WeekCalendarServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface WeekCalendarServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<WeekCalendar, Integer> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/mfreference/WeekCalendar";

}
