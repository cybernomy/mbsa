/*
 * DataItemName.java
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

import com.mg.framework.api.ui.SearchHelp;

import java.util.Locale;

/**
 * Элемент данных предназначен для описания объектов используемых в системе
 *
 * @author Oleg V. Safonov
 * @version $Id: DataItem.java,v 1.4 2007/01/25 14:52:09 safonov Exp $
 */
public interface DataItem {

  /**
   * Наименование
   */
  String getName();

  /**
   * Описание
   */
  String getDescription();

  /**
   * Тип элемента данных
   */
  DataItemKind getKind();

  /**
   * Домен используемый для описания данного элемента данных
   */
  Domain getDomain();

  /**
   * Планируемое название объекта ссылающегося на данный элемент, использовать только английский
   * язык, настоятельно рекомендуется придерживаться Java naming conventions
   */
  String getDefaultComponentName();

  /**
   * Возвращает краткую метку, используется текущая locale, смотри {@link #getShortLabel(Locale)}
   */
  String getShortLabel();

  /**
   * Возвращает краткую метку, используется как метка для элементов пользовательского интерфейса
   */
  String getShortLabel(Locale locale);

  int getShortLabelMaxLength();

  /**
   * Возвращает среднюю метку, используется текущая locale, смотри {@link #getMediumLabel(Locale)}
   */
  String getMediumLabel();

  /**
   * Возвращает среднюю метку, используется как метка для элементов пользовательского интерфейса,
   * если не установлена, то возвращается краткая метка
   */
  String getMediumLabel(Locale locale);

  int getMediumLabelMaxLength();

  /**
   * Возвращает длинную метку, используется текущая locale, смотри {@link #getLongLabel(Locale)}
   */
  String getLongLabel();

  /**
   * Возвращает длинную метку, используется как метка для элементов пользовательского интерфейса,
   * если не установлена, то возвращается средняя метка
   */
  String getLongLabel(Locale locale);

  int getLongLabelMaxLength();

  /**
   * Возвращает заголовок, используется текущая locale, смотри {@link #getHeader(Locale)}
   */
  String getHeader();

  /**
   * Возвращает заголовок, используется в качестве заголовка колонок списков и таблиц
   */
  String getHeader(Locale locale);

  int getHeaderMaxLength();

  /**
   * Возвращает метку отчетов, используется текущая locale, смотри {@link #getReportLabel(Locale)}
   */
  String getReportLabel();

  /**
   * Возвращает метку отчетов, используется в качестве меток элементов при выводе отчетов
   */
  String getReportLabel(Locale locale);

  int getReportLabelMaxLength();

  /**
   * Возвращает документацию данного элемента
   */
  DataItemDocumentation getDocumentation();

  /**
   * Возвращает SearchHelp
   */
  SearchHelp getSearchHelp();

  /**
   * Возвращает имя SearchHelp, используйте {@link com.mg.framework.support.metadata.SearchHelpProcessor
   * SearchHelpProcessor} для создания реализации
   *
   * @return имя класса реализации
   */
  String getSearchHelpName();

  /**
   * Возвращает описатель представления объекта сущности в пользовательском интерфейсе
   */
  EntityText getEntityText();

  /**
   * Возвращает список свойств сущности для отображения
   */
  String getEntityPropertyText();

  /**
   * Возвращает формат для отображения сущности
   */
  String getEntityTextFormat();

  /**
   * Возвращает параметр значением которого будет заполнен объект, значение можно взять из сервиса
   * хранения текущих параметров
   */
  String getAssignParameterName();

  /**
   * Признак возможности изменения значения поля, если установлен в <code>true</code> то значение не
   * может быть изменено
   */
  boolean isReadOnly();
}
