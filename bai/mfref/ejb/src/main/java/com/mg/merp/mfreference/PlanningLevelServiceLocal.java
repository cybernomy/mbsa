/*
 * PlanningLevelServiceLocal.java
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
package com.mg.merp.mfreference;

import com.mg.merp.mfreference.model.PlanningLevel;

/**
 * Сервис бизнес-компонента "Уровни планирования"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PlanningLevelServiceLocal.java,v 1.2 2007/02/19 12:55:14 sharapov Exp $
 */
public interface PlanningLevelServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PlanningLevel, Integer> {

  /**
   * Создать периоды уровня планирования
   *
   * @param planningLevel - уровень планирования
   * @param bucketLength  - длина периода в днях
   * @param bucketNumber  - количество периодов
   */
  void generateBuckets(PlanningLevel planningLevel, Integer bucketLength, Integer bucketNumber);

}
