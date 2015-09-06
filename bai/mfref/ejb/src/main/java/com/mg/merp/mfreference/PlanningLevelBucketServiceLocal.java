/*
 * PlanningLevelBucketServiceLocal.java
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

import com.mg.merp.mfreference.model.PlanningLevelBucket;

import java.util.Date;

/**
 * Бизнес-компонент "Бакеты уровней планирования"
 *
 * @author leonova
 * @version $Id: PlanningLevelBucketServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface PlanningLevelBucketServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<PlanningLevelBucket, Integer> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/mfreference/PlanningLevelBucket";

  /**
   * получить ближайшую дату старта бакета к планируемой дате
   *
   * @param planningLevelId идентификатор уровня планирования
   * @param planningDate    дата планирования
   * @return ближайшая дата старта бакета или <code>null</code> если бакет не найден
   */
  Date nearestBucketStartDate(int planningLevelId, Date planningDate);

  /**
   * определение смещения бакета
   *
   * @param planningLevelId уровень планирования
   * @param offsetDate      дата
   * @return смещение бакета
   */
  short determineOffset(int planningLevelId, Date offsetDate);

  /**
   * определение диапазона бакета
   *
   * @param planningLevelId уровень планирования
   * @param bucketOffset    смещение бакета
   * @return диапазон бакета
   */
  BucketRange determineRange(int planningLevelId, short bucketOffset);

}
