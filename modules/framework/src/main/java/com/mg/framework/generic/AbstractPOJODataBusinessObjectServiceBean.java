/*
 * AbstractPOJODataBusinessObjectServiceBean.java
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
package com.mg.framework.generic;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor;
import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.Example;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.service.DataBusinessServiceInterceptorManagerLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.BusinessObjectUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;

import org.hibernate.PersistentObjectException;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;

/**
 * Абстрактная реализация бизнес-компонента поддерживающий основную функциональность взаимодействия
 * с постоянным хранилищем. При создании бизнес-компонентов необходимо указать тип <code><T></code>
 * объекта сущности и тип <code><ID></code> первичного ключа.
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractPOJODataBusinessObjectServiceBean.java,v 1.22 2008/07/02 14:21:27 safonov
 *          Exp $
 */
public abstract class AbstractPOJODataBusinessObjectServiceBean<T extends PersistentObject, ID extends Serializable> extends
    AbstractPOJOBusinessObjectStatelessServiceBean implements
    DataBusinessObjectService<T, ID> {
  /**
   * тип объекта сущности
   */
  private Class<T> persistentClass = null;

  /**
   * генерация события инициализации объекта
   *
   * @param entity объект
   */
  private void fireInitialize(final T entity) {
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnInitialize(this, entity))
      onInitialize(entity);
  }

  /**
   * генерация события связывания с текущим перманентным контекстом
   *
   * @param entity объект
   */
  private void fireCreate(final T entity) {
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnCreate(this, entity))
      onCreate(entity);
  }

  /**
   * генерация события объединение объекта сущности с текущим перманентным контекстом
   *
   * @param entity объект сущность
   */
  private void fireStore(final T entity) {
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnStore(this, entity))
      onStore(entity);
  }

  /**
   * генерация события удаления объекта сущности
   *
   * @param entity объект сущность
   */
  private void fireErase(T entity) {
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnErase(this, entity))
      onErase(entity);
  }

  /**
   * реализация инициализации
   *
   * @return объект
   */
  private T internalInitialize() {
    T result = instantiateEntity();
    fireInitialize(result);
    return result;
  }

  /**
   * реализация инициализации с дополнительными данными
   *
   * @param attributes значения свойств объекта
   * @return объект
   */
  private T internalInitialize(AttributeMap attributes) {
    T result = instantiateEntity();
    result.setAttributes(attributes);
    fireInitialize(result);
    return result;
  }

  /**
   * реализация связывания объекта
   *
   * @param entity объект
   * @return первичный ключ
   */
  @SuppressWarnings("unchecked")
  private ID internalCreate(T entity) {
    if (entity == null)
      return null;

    internalValidate(entity);
    fireCreate(entity);
    try {
      getPersistentManager().persist(entity);
    } catch (PersistentObjectException e) {
      getPersistentManager().merge(entity);
    }
    ID result = (ID) entity.getPrimaryKey();
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnPostCreate(this, entity))
      onPostCreate(entity);
    try {
      if (entity instanceof EntityCustomFieldsStorageAccessor)
        CustomFieldsManagerLocator.locate().storeValues(((EntityCustomFieldsStorageAccessor) entity).getStorage(), this, result);
    } catch (Exception e) {
      getLogger().error("Error during custom fields storing", e);
    }
    return result;
  }

  /**
   * реализация объединения
   *
   * @param entity объект сущность
   * @return объект сущность связанный с контекстом
   */
  private T internalStore(T entity) {
    if (entity == null)
      return null;

    internalValidate(entity);
    fireStore(entity);
    T result = getPersistentManager().merge(entity);
    try {
      if (entity instanceof EntityCustomFieldsStorageAccessor) {
        //сравниваем именно объекты, если entity был в сессии, то его и вернут, если нет, то в новом объекте установим хранилище
        if (result != entity)
          ((EntityCustomFieldsStorageAccessor) result).setStorage(((EntityCustomFieldsStorageAccessor) entity).getStorage());
        CustomFieldsManagerLocator.locate().storeValues(((EntityCustomFieldsStorageAccessor) result).getStorage(), this, result.getPrimaryKey());
      }
    } catch (Exception e) {
      getLogger().error("Error during custom fields storing", e);
    }
    return result;
  }

  /**
   * реализация удаления
   *
   * @param entity объект сущность
   */
  private void internalErase(T entity) {
    if (entity == null)
      return;

    fireErase(entity);
    getPersistentManager().remove(entity);
    try {
      if (entity instanceof EntityCustomFieldsStorageAccessor)
        CustomFieldsManagerLocator.locate().removeValues(this, entity.getPrimaryKey());
    } catch (Exception e) {
      getLogger().error("Error during custom fields removing", e);
    }
  }

  /**
   * реализация удаления по первичному ключу
   *
   * @param primaryKey первичный ключ
   */
  private void internalErase(ID primaryKey) {
    internalErase(internalLoad(primaryKey));
  }

  /**
   * реализация загрузки объекта сущности
   *
   * @param primaryKey первичный ключ
   * @return объект сущность
   */
  private T internalLoad(ID primaryKey) {
    T entity = getPersistentManager().find(getEntityClass(), primaryKey);
    try {
      if (entity instanceof EntityCustomFieldsStorageAccessor)
        ((EntityCustomFieldsStorageAccessor) entity).setStorage(CustomFieldsManagerLocator.locate().createStorage(this, primaryKey));
    } catch (Exception e) {
      getLogger().error("Error during custom fields loading", e);
    }
    return entity;
  }

  /**
   * реализация контроля данных
   *
   * @param entity объект или объект сущность
   */
  private void internalValidate(T entity) {
    ValidationContext context = new ValidationContext();
    if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnValidate(this, context, entity))
      onValidate(context, entity);
    context.validate();
  }

  /**
   * реализация копирования
   *
   * @param entity    объект или объект сущность
   * @param deepClone копировать объекты детали
   * @return объект или объект сущность
   */
  @SuppressWarnings("unchecked")
  private T internalClone(T entity, boolean deepClone, AttributeMap attributes) {
    if (entity == null)
      return null;

    T entityClone = (T) entity.cloneEntity(attributes);
    if (entityClone != null) {
      //копируем пользовательские поля
      if (entity instanceof EntityCustomFieldsStorageAccessor)
        CustomFieldsManagerLocator.locate().cloneValues(this, entity, this, entityClone);

      if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnClone(this, entityClone))
        onClone(entityClone);
      create(entityClone);
      if (deepClone)
        doDeepClone(entity, entityClone);
    }
    return entityClone;
  }

  /**
   * загрузка пользовательских полей
   *
   * @param entities список сущностей
   * @return список сущностей с пользовательскими полями
   */
  private List<T> loadCustomFields(List<T> entities) {
    try {
      if (!entities.isEmpty()) {
        CustomFieldsManager customFieldsManager = CustomFieldsManagerLocator.locate();
        for (T entity : entities)
          if (entity instanceof EntityCustomFieldsStorageAccessor)
            ((EntityCustomFieldsStorageAccessor) entity).setStorage(customFieldsManager.createStorage(this, entity.getPrimaryKey()));
      }
    } catch (Exception e) {
      getLogger().error("Error during custom fields loading", e);
    }
    return entities;
  }

  /**
   * получить менеджера перманентного хранилища
   *
   * @return менеджер
   */
  protected PersistentManager getPersistentManager() {
    return ServerUtils.getPersistentManager();
  }

  /**
   * реализация полного копирования, наследники должны переопределять данный метод для копирования
   * своих "деталей"
   *
   * @param entity      сущность
   * @param entityClone сущность клон
   */
  protected void doDeepClone(T entity, T entityClone) {

  }

  /**
   * реализация перемещения сущности, в стандарном исполнении перенос производися по атрибуту
   * Folder, если объект имеет отличную реализацию, то необходимо переопределять данный метод
   *
   * @param primaryKeys  список объектов для переноса
   * @param targetEntity объект назначения
   */
  protected boolean doMove(List<ID> primaryKeys, Object targetEntity) {
    boolean result = false;
    for (ID key : primaryKeys) {
      T entity = load(key);
      if (entity.hasAttribute("Folder")) {
        entity.setAttribute("Folder", targetEntity);
        result = true;
      }
    }
    return result;
  }

  /**
   * Связать объект с текущим перманентным контекстом. После вызова метода объект будет управляемым,
   * однако может не находиться в постоянном хранилище до синхронизации контекста с хранилищем.
   *
   * @param entity объект
   * @return первичный ключ объекта сущности
   */
  protected ID doCreate(T entity) {
    return internalCreate(entity);
  }

  /**
   * Удалить объект сущность из текущего перманентного контекста и постоянного хранилища по
   * первичному ключу. Объект будет присутствовать в постоянном хранилище  до синхронизации
   * контекста с хранилищем.
   *
   * @param primaryKey первичный ключ
   */
  protected void doErase(ID primaryKey) {
    internalErase(primaryKey);
  }

  /**
   * Удалить объект сущность из текущего перманентного контекста и постоянного хранилища. Объект
   * будет присутствовать в постоянном хранилище  до синхронизации контекста с хранилищем.
   *
   * @param entity объект сущность
   */
  protected void doErase(T entity) {
    internalErase(entity);
  }

  /**
   * Инициализация объекта, объект будет инициализирован данными зависищами от конкретной реализации
   * бизнес-компонента. В большинстве случаев первичный ключ не установлен.
   *
   * @return объект
   */
  protected T doInitialize() {
    return internalInitialize();
  }

  /**
   * Инициализация объекта, объект будет инициализирован данными зависищами от конкретной реализации
   * бизнес-компонента и данными из параметра <code>attributes</code>. В большинстве случаев
   * первичный ключ не установлен.
   *
   * @param attributes карта наименований свойств объекта и значений
   * @return объект
   */
  protected T doInitialize(AttributeMap attributes) {
    return internalInitialize(attributes);
  }

  /**
   * Загрузка объекта сущности по первичному ключу, возможно сущность будет загружена из текущего
   * контекста или кэша.
   *
   * @return объект сущность или <code>null</code> если не найдена в хранилище
   */
  protected T doLoad(ID primaryKey) {
    return internalLoad(primaryKey);
  }

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
  protected T doStore(T entity) {
    return internalStore(entity);
  }

  /**
   * Копирование объекта сущности, полученная копия будет связана с текущим перманентным контекстом
   *
   * @param entity     - сущность для копирования
   * @param deepClone  - признак копирования сущностей ссылающихся на <code>entity</code>
   * @param attributes - карта наименований свойств объекта и значений, может быть
   *                   <code>null</code>
   * @return сущность или <code>null</code> если копирование не поддерживается или невозможно
   */
  protected T doClone(T entity, boolean deepClone, AttributeMap attributes) {
    return internalClone(entity, deepClone, attributes);
  }

  //default events

  //callbacks

  /**
   * обработчик события инициализации объекта, в переопределенных методах необходимо устанавливать
   * значения свойств объекта, возможно некоторые свойства уже будут установлены если инициализация
   * происходит через метод {@link #initialize(AttributeMap)}
   *
   * @param entity объект
   */
  protected void onInitialize(final T entity) {

  }

  /**
   * обработчик события связывания объекта с текущим перманентным контекстом, происходит
   * непосредственно перед занесением в контекст, в переопределенных методах возможны действия по
   * инициализации и дополнительные действия по обработке события
   *
   * @param entity объект
   */
  protected void onCreate(final T entity) {

  }

  /**
   * обработчик события связывания объекта с текущим перманентным контекстом, происходит после
   * занесением в контекст, в переопределенных методах возможны дополнительные действия по обработке
   * события
   *
   * @param entity объект
   */
  protected void onPostCreate(final T entity) {

  }

  /**
   * обработчик события объединение объекта сущности с текущим перманентным контекстом, происходит
   * непосредственно перед объединением с контекстом, в переопределенных методах возможны действия
   * по инициализации и дополнительные действия по обработке события
   *
   * @param entity объект сущность
   */
  protected void onStore(final T entity) {

  }

  /**
   * обработчик события на удаление объекта сущности, происходит непосредственно перед удалением из
   * контекста, в переопределенных методах возможны действия по вроверки возможности удаления
   * объекта.
   *
   * @param entity объект сущность
   */
  protected void onErase(T entity) {

  }

  /**
   * обработчик события контроля данных, происходит перед событиями связывания и объединения с
   * текущим перманентным контекстом, т.е. перед вызовом обработчиков {@link
   * #onCreate(PersistentObject)} и {@link #onStore(PersistentObject)}
   *
   * @param context контекст контроля данных
   * @param entity  объект или объект сущность
   */
  protected void onValidate(ValidationContext context, T entity) {

  }

  /**
   * обработчик события копирования объекта сущности, происходит непосредственно перед объединением
   * с контекстом, в переопределенных методах возможны действия по инициализации и дополнительные
   * действия по обработке события
   */
  protected void onClone(final T entity) {

  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#initialize()
   */
  @PermitAll
  public T initialize() {
    return doInitialize();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#create(T)
   */
  public ID create(T entity) {
    return doCreate(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#store(T)
   */
  public T store(T entity) {
    return doStore(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#erase(T)
   */
  public void erase(T entity) {
    doErase(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#load(ID)
   */
  public T load(ID primaryKey) {
    return doLoad(primaryKey);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#instantiateEntity()
   */
  @PermitAll
  public T instantiateEntity() {
    try {
      T result = getEntityClass().newInstance();
      try {
        if (result instanceof EntityCustomFieldsStorageAccessor)
          ((EntityCustomFieldsStorageAccessor) result).setStorage(CustomFieldsManagerLocator.locate().createStorage(this, null));
      } catch (Exception e) {
        getLogger().error("Error during custom fields descriptor loading", e);
      }
      return result;
    } catch (InstantiationException e) {
      throw new ApplicationException(e);
    } catch (IllegalAccessException e) {
      throw new ApplicationException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#initialize(com.mg.framework.api.AttributeMap)
   */
  @PermitAll
  public T initialize(AttributeMap attributes) {
    return doInitialize(attributes);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#erase(java.lang.Object)
   */
  public void erase(ID primaryKey) {
    doErase(primaryKey);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#findByCriteria(com.mg.framework.api.orm.Criterion...)
   */
  @PermitAll
  public List<T> findByCriteria(Criterion... criteria) {
    return loadCustomFields(OrmTemplate.getInstance().findByCriteria(getEntityClass(), criteria));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#findByExample(T, java.lang.String[])
   */
  @PermitAll
  public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
    List<T> result = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(getEntityClass()).add(Example.create(exampleInstance, excludeProperty, null, null, false)));
    return loadCustomFields(result);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#getEntityClass()
   */
  @PermitAll
  public Class<T> getEntityClass() {
    if (persistentClass == null)
      persistentClass = BusinessObjectUtils.getBusinessServiceEntityClass(this);
    return persistentClass;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#clone(com.mg.framework.api.orm.PersistentObject, boolean, com.mg.framework.api.AttributeMap)
   */
  @PermitAll
  public T clone(T entity, boolean deepClone, AttributeMap attributes) {
    //используем для копирование доступ на создание
    try {
      SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));
    } catch (SecurityException e) {
      //обернем чтобы не возникал откат транзакции в контейнере
      throw new com.mg.framework.api.SecurityException(Messages.getInstance().getMessage(Messages.NO_PERMISSION), e);
    }
    return doClone(entity, deepClone, attributes);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.MovableDataBusinessObjectService#move(com.mg.framework.api.orm.PersistentObject, java.lang.Object)
   */
  @PermitAll
  public boolean move(List<ID> primaryKeys, Object targetEntity) {
    return doMove(primaryKeys, targetEntity);
  }

}
