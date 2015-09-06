/*
 * ScheduleHeadServiceLocal.java
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
package com.mg.merp.table;

import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;

import java.util.Date;
import java.util.List;

/**
 * Бизнес-компонент "Графики работ в табельном учете"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleHeadServiceLocal.java,v 1.2 2008/08/12 14:00:54 sharapov Exp $
 */
public interface ScheduleHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ScheduleHead, Integer> {

  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/table/ScheduleHead"; //$NON-NLS-1$

  /**
   * Имя метода "Создать график по образцу"
   */
  static final String CREATE_BY_PATTERN_METHOD_NAME = "createByPattern"; //$NON-NLS-1$

  /**
   * Создать график по образцу
   *
   * @param scheduleHead - заголовок графика в табельном учете
   * @param patternHead  - заголовок образца
   * @param initialShift - смещение(для сменных графиков)
   * @param beginDate    - дата с
   * @param endDate      - дата по
   * @return список позиций спецификации графика в табельном учете
   */
  List<ScheduleSpec> createByPattern(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate);

}
