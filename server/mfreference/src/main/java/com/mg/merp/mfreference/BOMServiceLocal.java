/*
 * BOMServiceLocal.java
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

import com.mg.merp.mfreference.model.Bom;

import java.util.Date;

/**
 * Бизнес-компонент "Состав изделия"
 *
 * @author leonova
 * @version $Id: BOMServiceLocal.java,v 1.4 2007/08/06 12:16:10 safonov Exp $
 */
public interface BOMServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Bom, Integer> {
  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/mfreference/BOM";

  /**
   * тип папки для составов изделий
   */
  final static short FOLDER_PART = 12001;

  /**
   * Расчет параметров времени выполнения
   *
   * @param actualityDate дата актуальности
   * @param catalogList   список позиций каталога
   */
  void calculateOperLeadTimes(Date actualityDate, int[] catalogList);

  /**
   * поиск текущего состава изделия
   *
   * @param catalogId идентификатор позиции каталога
   * @return состав изделия или <code>null</code> если не найден
   */
  Bom findCurrentBOM(int catalogId);

  /**
   * поиск стандартного состава изделия
   *
   * @param catalogId идентификатор позиции каталога
   * @return состав изделия или <code>null</code> если не найден
   */
  Bom findStandartBOM(int catalogId);

  /**
   * изменение даты расчета нормативной себестоимости
   *
   * @param bom  состав изделия
   * @param date дата расчета
   */
  void updateRollupDateTime(Bom bom, Date date);

}
