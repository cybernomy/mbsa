/*
 * ApplicationDictionary.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;

/**
 * Репозитарий метаданных системы
 *
 * @author Oleg V. Safonov $Id: ApplicationDictionary.java,v 1.7 2008/03/03 13:11:02 safonov Exp $
 */
public interface ApplicationDictionary {

  /**
   * Получить домен по имени
   *
   * @param name имя домена
   * @return домен
   */
  Domain getDomain(String name);

  /**
   * Получить элемент данных по имени
   *
   * @param name имя элемента данных
   * @return элемент данных
   */
  DataItem getDataItem(String name);

  /**
   * Получить метаданные атрибута объекта-сущности
   *
   * @param entityClazz  класс объекта-сущности
   * @param propertyName имя атрибута
   * @return метаданные атрибута
   */
  FieldMetadata getFieldMetadata(Class<? extends PersistentObject> entityClazz, String propertyName);

  /**
   * Получить метаданные атрибута
   *
   * @param propertyMetadata свойства атрибута
   * @return метаданные атрибута
   */
  FieldMetadata getFieldMetadata(ReflectionMetadata propertyMetadata);

  /**
   * Создает форму сопровождения для бизнес-компонента
   *
   * @param service  бизнес-компонент
   * @param formName имя формы, может быть <code>null</code> или пустой строкой
   * @return форма
   */
  com.mg.framework.api.ui.Form getMaintenaceForm(DataBusinessObjectService<?, ?> service, String formName);

  /**
   * Создает форму списка для бизнес-компонента
   *
   * @param service  бизнес-компонент
   * @param formName имя формы, может быть <code>null</code> или пустой строкой
   * @return форма
   */
  com.mg.framework.api.ui.Form getBrowseForm(DataBusinessObjectService<?, ?> service, String formName);

  /**
   * Создает окно приложения
   *
   * @param windowName имя окна приложения
   * @return окно
   */
  com.mg.framework.api.ui.Form getWindow(String windowName);

  /**
   * Сбросить кэш окон приложения
   */
  void invalidateWindowCache();

  /**
   * Создает бизнес-компонент
   *
   * @param name имя бизнес компонента
   * @return бизнес компонент
   */
  BusinessObjectService getBusinessService(String name);
}
