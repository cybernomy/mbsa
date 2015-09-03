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

import java.util.Locale;

import com.mg.framework.api.ui.SearchHelp;

/**
 * Элемент данных предназначен для описания объектов используемых в системе
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataItem.java,v 1.4 2007/01/25 14:52:09 safonov Exp $
 */
public interface DataItem {
	
	/**
     * Наименование
     * 
     * @return
     */
    String getName();
    
    /**
     * Описание
     * 
     * @return
     */
    String getDescription();
    
    /**
     * Тип элемента данных
     * 
     * @return
     */
    DataItemKind getKind();
    
    /**
     * Домен используемый для описания данного элемента данных
     * 
     * @return
     */
    Domain getDomain();
    
    /**
     * Планируемое название объекта ссылающегося на данный элемент, использовать
     * только английский язык, настоятельно рекомендуется придерживаться Java naming conventions
     * 
     * @return
     */
    String getDefaultComponentName();
    
    /**
     * Возвращает краткую метку, используется текущая locale, смотри {@link #getShortLabel(Locale)}
     * 
     * @return
     */
    String getShortLabel();
    
    /**
     * Возвращает краткую метку, используется как метка для элементов пользовательского
     * интерфейса
     * 
     * @param locale
     * @return
     */
    String getShortLabel(Locale locale);
    
    int getShortLabelMaxLength();

    /**
     * Возвращает среднюю метку, используется текущая locale, смотри {@link #getMediumLabel(Locale)}
     * 
     * @return
     */    
    String getMediumLabel();
    
    /**
     * Возвращает среднюю метку, используется как метка для элементов пользовательского
     * интерфейса, если не установлена, то возвращается краткая метка
     * 
     * @param locale
     * @return
     */
    String getMediumLabel(Locale locale);
    
    int getMediumLabelMaxLength();
    
    /**
     * Возвращает длинную метку, используется текущая locale, смотри {@link #getLongLabel(Locale)}
     * 
     * @return
     */    
    String getLongLabel();
    
    /**
     * Возвращает длинную метку, используется как метка для элементов пользовательского
     * интерфейса, если не установлена, то возвращается средняя метка
     * 
     * @param locale
     * @return
     */
    String getLongLabel(Locale locale);
    
    int getLongLabelMaxLength();
    
    /**
     * Возвращает заголовок, используется текущая locale, смотри {@link #getHeader(Locale)}
     * 
     * @return
     */
    String getHeader();
    
    /**
     * Возвращает заголовок, используется в качестве заголовка колонок списков и
     * таблиц
     * 
     * @param locale
     * @return
     */
    String getHeader(Locale locale);
    
    int getHeaderMaxLength();
    
    /**
     * Возвращает метку отчетов, используется текущая locale, смотри {@link #getReportLabel(Locale)}
     * 
     * @return
     */
    String getReportLabel();
    
    /**
     * Возвращает метку отчетов, используется в качестве меток элементов при
     * выводе отчетов
     * 
     * @param locale
     * @return
     */
    String getReportLabel(Locale locale);
    
    int getReportLabelMaxLength();
    
    /**
     * Возвращает документацию данного элемента
     * 
     * @return
     */
    DataItemDocumentation getDocumentation();
    
    /**
     * Возвращает SearchHelp
     * 
     * @return
     */
    SearchHelp getSearchHelp();

    /**
     * Возвращает имя SearchHelp, используйте {@link com.mg.framework.support.metadata.SearchHelpProcessor SearchHelpProcessor}
     * для создания реализации 
     * 
     * @return	имя класса реализации
     */
    String getSearchHelpName();
    
    /**
     * Возвращает описатель представления объекта сущности в пользовательском
     * интерфейсе
     * 
     * @return
     */
    EntityText getEntityText();

    /**
     * Возвращает список свойств сущности для отображения
     * 
     * @return
     */
    String getEntityPropertyText();
    
    /**
     * Возвращает формат для отображения сущности
     * 
     * @return
     */
    String getEntityTextFormat();
    
    /**
     * Возвращает параметр значением которого будет заполнен объект, значение
     * можно взять из сервиса хранения текущих параметров
     * 
     * @return
     */
    String getAssignParameterName();
    
    /**
     * Признак возможности изменения значения поля, если установлен в <code>true</code> то значение
     * не может быть изменено
     * 
     * @return
     */
    boolean isReadOnly();
}
