/*
 * KladrServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.Kladr;

/**
 * Сервис бизнес-компонента "Классификатор адресов (КЛАДР)"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: KladrServiceLocal.java,v 1.2 2007/07/16 13:12:39 sharapov Exp $
 */
public interface KladrServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Kladr, Integer> {

  /**
   * Имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/personnelref/Kladr"; //$NON-NLS-1$

  // Уровни классификатора адресов
  static final int REGION_LEVEL = 1;
  static final int DISTRICT_LEVEL = 2;
  static final int CITY_LEVEL = 3;
  static final int AREA_LEVEL = 4;
  static final int STREET_LEVEL = 5;

  // Инициализирующие значения кодов
  static final String INIT_KLADR_CODE = "000000000000000"; //$NON-NLS-1$
  static final String INIT_REGION_CODE = "00"; //$NON-NLS-1$
  static final String INIT_DISTRICT_CODE = "000"; //$NON-NLS-1$
  static final String INIT_CITY_CODE = "000"; //$NON-NLS-1$
  static final String INIT_AREA_CODE = "000"; //$NON-NLS-1$
  static final String INIT_STREET_CODE = "0000"; //$NON-NLS-1$

  // Маски инициализирующих значений кодов
  static final String INIT_REGION_CODE_MASK = "__"; //$NON-NLS-1$
  static final String INIT_DISTRICT_CODE_MASK = "___"; //$NON-NLS-1$
  static final String INIT_CITY_CODE_MASK = "___"; //$NON-NLS-1$
  static final String INIT_AREA_CODE_MASK = "___"; //$NON-NLS-1$

  // Подмаски уровней классификатора
  static final String REGION_SUB_MASK = "__000000000__"; //$NON-NLS-1$
  static final String DISTRICT_SUB_MASK = "___000000__"; //$NON-NLS-1$
  static final String CITY_SUB_MASK = "___000__"; //$NON-NLS-1$
  static final String AREA_SUB_MASK = "_____"; //$NON-NLS-1$
  static final String STREET_SUB_MASK = "______"; //$NON-NLS-1$

}
