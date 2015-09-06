/*
 * Domain.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.metadata;

import java.util.List;


/**
 * Домен описывает информацию о типе, возможных значениях полей ссылающихся на данный домен
 *
 * @author Oleg V. Safonov
 * @version $Id: Domain.java,v 1.4 2008/03/03 13:11:02 safonov Exp $
 */
public interface Domain {

  /**
   * Наименование
   */
  String getName();

  /**
   * Описание
   */
  String getDescription();

  /**
   * Тип
   */
  BuiltInType getBuiltInType();

  /**
   * Длина в символах (для строкового типа)
   */
  int getLength();

  /**
   * Количество цифр
   */
  int getNumberOfPlaces();

  /**
   * Количество цифр после запятой
   */
  int getNumberOfDecimalPlaces();

  /**
   * Список фиксированных значений
   */
  List<FixedValue<?>> getFixedValues();

  /**
   * Значение по умолчанию
   */
  Object getDefaultValue();

  /**
   * Техническая документация
   */
  String getDocumentation();

  /**
   * Признак различия верхнего и нижнего регистра, если установлен в <code>false</code> то введенные
   * символы будут конвертированы в верхний регистр
   */
  boolean isLowercase();

  /**
   * Признак наличия значения, если установлен в <code>true</code> то значение не может быть
   * <code>null</code>
   */
  boolean isMandatory();

  /**
   * Процедура конвертации из значения пользовательского интерфейса в значение обрабатываемое
   * системой и обратно
   *
   * @return процедура конвертации
   * @see ConversionRoutine
   */
  ConversionRoutine<?, ?> getConversion();

  /**
   * Признак отрицательного значения, если установлен в <code>false</code> то значение может быть
   * только положительным
   */
  boolean isSign();
}
