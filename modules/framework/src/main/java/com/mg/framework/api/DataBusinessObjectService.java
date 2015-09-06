/*
 * DataBusinessObjectService.java
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
package com.mg.framework.api;

import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.PersistentObject;

import java.io.Serializable;
import java.util.List;

/**
 * Бизнес-компонент поддерживающий основную функциональность взаимодействия с постоянным хранилищем.
 * Имеет базовые методы по созданию, модификации, удалению и поиска сущностей.
 *
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessObjectService.java,v 1.13 2008/03/07 12:31:33 safonov Exp $
 */
public interface DataBusinessObjectService<T extends PersistentObject, ID extends Serializable> extends BusinessObjectService {
  @Deprecated
  public static final int MIDAS_FORMAT = 0;
  @Deprecated
  public static final int INTERNAL_LEGACY_FORMAT = 1;

  /**
   * Возвращает тип объекта сущности
   *
   * @return тип объекта сущности
   */
  Class<T> getEntityClass();

  /**
   * Создание экземпляра объекта, без процедуры инициализации.
   *
   * @return объект
   */
  T instantiateEntity();

  /**
   * Инициализация объекта, объект будет инициализирован данными зависищами от конкретной реализации
   * бизнес-компонента. В большинстве случаев первичный ключ не установлен.
   *
   * @return объект
   */
  T initialize();

  /**
   * Инициализация объекта, объект будет инициализирован данными зависищами от конкретной реализации
   * бизнес-компонента и данными из параметра <code>attributes</code>. В большинстве случаев
   * первичный ключ не установлен.
   *
   * @param attributes карта наименований свойств объекта и значений
   * @return объект
   */
  T initialize(AttributeMap attributes);

  /**
   * Связать объект с текущим перманентным контекстом. После вызова метода объект будет управляемым,
   * однако может не находиться в постоянном хранилище до синхронизации контекста с хранилищем.
   *
   * @param entity объект
   * @return первичный ключ объекта сущности
   */
  ID create(T entity);

  /**
   * Объединение объекта сущности с текущим перманентным контекстом. После вызова метода
   * возвращаемый объект будет управляемым, однако новое состояние объекта может не находиться в
   * постоянном хранилище до синхронизации контекста с хранилищем. В случае если <code>entity</code>
   * был отсоединен от контекста, то будет создан новый объект сущность который будет являться
   * копией <code>entity</code>.
   *
   * @param entity объект сущность
   * @return объект сущность связанный с текущим перманентным контекстом
   */
  public T store(T entity);

  /**
   * Удалить объект сущность из текущего перманентного контекста и постоянного хранилища. Объект
   * будет присутствовать в постоянном хранилище  до синхронизации контекста с хранилищем.
   *
   * @param entity объект сущность
   */
  void erase(T entity);

  /**
   * Удалить объект сущность из текущего перманентного контекста и постоянного хранилища по
   * первичному ключу. Объект будет присутствовать в постоянном хранилище  до синхронизации
   * контекста с хранилищем.
   *
   * @param primaryKey первичный ключ
   */
  void erase(ID primaryKey);

  /**
   * Загрузка объекта сущности по первичному ключу, возможно сущность будет загружена из текущего
   * контекста или кэша.
   *
   * @return объект сущность или <code>null</code> если не найдена в хранилище
   */
  T load(ID primaryKey);

  /**
   * Поиск объектов сущностей по образцу
   *
   * @param exampleInstance образец для поиска
   * @param excludeProperty свойства, значения которых не учитываются при поиске
   * @return список объектов сущностей
   */
  List<T> findByExample(T exampleInstance, String[] excludeProperty);

  /**
   * Поиск объектов сущностей по критериям
   *
   * @param criteria критерии поиска
   * @return список объектов сущностей
   */
  List<T> findByCriteria(Criterion... criteria);

  /**
   * Копирование объекта сущности, полученная копия будет связана с текущим перманентным контекстом
   *
   * @param entity     сущность для копирования
   * @param deepClone  признак копирования сущностей ссылающихся на <code>entity</code>
   * @param attributes карта наименований свойств объекта и значений, может быть <code>null</code>
   * @return сущность или <code>null</code> если копирование не поддерживается или невозможно
   */
  T clone(T entity, boolean deepClone, AttributeMap attributes);

  /**
   * перемещение объекта сущности, реализация зависит от специфики бизнес-компонента, некоторые
   * компоненты не поддерживают данную операцию
   *
   * @return <code>true</code> если компонент поддерживает данную операцию и она была выполнена
   */
  boolean move(List<ID> primaryKeys, Object targetEntity);

}
