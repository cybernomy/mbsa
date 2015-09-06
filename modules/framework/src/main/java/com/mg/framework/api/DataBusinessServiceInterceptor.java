/**
 * DataBusinessServiceInterceptor.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;

import java.io.Serializable;

/**
 * Перехватчик действий бизнес-компонентов управляющих данными, реализации данного интерфейса
 * используются для обработки событий происходящих в бизнес-компонентах (создание, модификация,
 * удаление, копирование, проверка данных). Реализации методов перехватчика могут генерировать
 * неконтролируемые исключительные ситуации, вызывать методы объектов JNDI, JDBC, JMS, EJB,
 * выполнять объектные запросы. <strong>Внимание, в реализациях методов данного интерфейса запрещено
 * выполнять операции с UI</strong>
 *
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptor.java,v 1.1 2007/12/13 13:01:32 safonov Exp $
 */
public interface DataBusinessServiceInterceptor extends Serializable {

  /**
   * получить имя перехватчика
   *
   * @return имя перехватчика
   */
  String getName();

  /**
   * возвращает список имен обрабатываемых бизнес-компонентов
   *
   * @return список имен
   */
  String[] getHandledServices();

  /**
   * действие на событие "копирование"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onClone(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "создание"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onCreate(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "удаление"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onErase(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "инициализация"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onInitialize(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "после создания"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onPostCreate(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "изменение"
   *
   * @param service бизнес-компонент
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onStore(final DataBusinessObjectService<T, ID> service, final T entity);

  /**
   * действие на событие "контроль данных"
   *
   * @param service бизнес-компонент
   * @param context контекст контроля данных
   * @param entity  сущность
   * @return если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
   */
  <T extends PersistentObject, ID extends Serializable> boolean onValidate(final DataBusinessObjectService<T, ID> service, final ValidationContext context, final T entity);

}
