/*
 * MeasureConversionServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.MeasureConversion;

import java.math.BigDecimal;

/**
 * Сервис бизнес-компонета преобразования ЕИ
 *
 * @author leonova
 * @version $Id: MeasureConversionServiceLocal.java,v 1.2 2006/12/02 13:32:40 safonov Exp $
 */
public interface MeasureConversionServiceLocal
    extends
    com.mg.framework.api.DataBusinessObjectService<MeasureConversion, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/reference/MeasureConversion";

  /**
   * преобразование ЕИ
   *
   * @param measureFrom ЕИ источник
   * @param measureTo   ЕИ приемник
   * @param catalog     позиция каталога для которой происходит преобразование
   * @param convTime    время на которое происходит преобразование
   * @param valueFrom   количество в базовой ЕИ
   * @return количество в дополнительной ЕИ
   * @throws InvalidMeasureConversion в случае ошибок преобразования
   */
  BigDecimal conversion(Measure measureFrom, Measure measureTo,
                        Catalog catalog, java.util.Date convTime, BigDecimal valueFrom)
      throws InvalidMeasureConversion;

}
