/*
 * MaintenanceHelper.java
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
package com.mg.framework.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MaintenanceForm;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.service.ApplicationDictionaryLocator;

import java.io.Serializable;

/**
 * Класс помощник для интерактивных действий над бизнес-компонентом
 *
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceHelper.java,v 1.9 2007/08/10 13:11:27 safonov Exp $
 */
public class MaintenanceHelper implements Serializable {

  /**
   * интерактивное добавление экземпляра бизнес-компонента
   *
   * @param <T>      тип бизнес-компонента
   * @param <ID>     тип идентификатора
   * @param service  бизнес-компонент
   * @param entity   объект сущность
   * @param formName имя формы поддержки, может быть <code>null</code>, в этом случае берется форма
   *                 по умолчанию
   * @param listener слушатель событий формы поддержки, может быть <code>null</code>
   */
  public static void add(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                         PersistentObject entity, final String formName, final MaintenanceFormActionListener listener) {
    if (service == null)
      throw new IllegalArgumentException("Service can't be null");
    internalShowMaintenanceForm(service, entity,
        MaintenanceAction.ADD, formName, listener);
  }

  /**
   * интерактивное добавление экземпляра бизнес-компонента
   *
   * @param <T>          тип бизнес-компонента
   * @param <ID>         тип идентификатора
   * @param service      бизнес-компонент
   * @param uiProperties инициализирующие свойства, может быть <code>null</code>
   * @param formName     имя формы поддержки, может быть <code>null</code>, в этом случае берется
   *                     форма по умолчанию
   * @param listener     слушатель событий формы поддержки, может быть <code>null</code>
   * @throws IllegalArgumentException если сервис <code>null</code>
   */
  public static void add(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                         final AttributeMap uiProperties, final String formName, final MaintenanceFormActionListener listener) {
    add(service, service.initialize(uiProperties), formName, listener);
  }

  /**
   * интерактивное изменение экземпляра бизнес-компонента
   *
   * @param <T>        тип бизнес-компонента
   * @param <ID>       тип идентификатора
   * @param service    бизнес-компонент
   * @param primaryKey первичный ключ экземпляра бизнес-компонента
   * @param formName   имя формы поддержки, может быть <code>null</code>, в этом случае берется
   *                   форма по умолчанию
   * @param listener   слушатель событий формы поддержки, может быть <code>null</code>
   * @throws IllegalArgumentException если сервис <code>null</code>
   */
  @SuppressWarnings("unchecked")
  public static void edit(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                          final Serializable primaryKey, final String formName, final MaintenanceFormActionListener listener) {
    if (service == null)
      throw new IllegalArgumentException("Service can't be null");
    DataBusinessObjectService s = service;//prevent compiler error
    internalShowMaintenanceForm(service, s.load(primaryKey),
        MaintenanceAction.EDIT, formName, listener);
  }

  /**
   * интерактивное изменение экземпляра бизнес-компонента
   *
   * @param service  бизнес-компонент
   * @param entity   первичный ключ экземпляра бизнес-компонента
   * @param formName имя формы поддержки, может быть <code>null</code>, в этом случае берется форма
   *                 по умолчанию
   * @param listener слушатель событий формы поддержки, может быть <code>null</code>
   */
  public static void edit(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                          PersistentObject entity, final String formName, final MaintenanceFormActionListener listener) {
    if (service == null)
      throw new IllegalArgumentException("Service can't be null");
    internalShowMaintenanceForm(service, entity,
        MaintenanceAction.EDIT, formName, listener);
  }

  /**
   * интерактивное копирование экземпляра бизнес-компонента
   *
   * @param service    бизнес-компонент
   * @param primaryKey первичный ключ экземпляра бизнес-компонента
   * @param deepClone  признак копирования с объектами деталями
   * @param formName   имя формы поддержки, может быть <code>null</code>, в этом случае берется
   *                   форма по умолчанию
   * @param listener   слушатель событий формы поддержки, может быть <code>null</code>
   */
  @SuppressWarnings("unchecked")
  public static void clone(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                           final Serializable primaryKey, boolean deepClone, final String formName, final MaintenanceFormActionListener listener) {
    if (service == null)
      throw new IllegalArgumentException("Service can't be null");
    DataBusinessObjectService s = service;//prevent compiler error
    internalShowMaintenanceForm(service, s.clone(s.load(primaryKey), deepClone, null),
        MaintenanceAction.CLONE, formName, listener);
  }

  /**
   * интерактивный просмотр экземпляра бизнес-компонента
   *
   * @param <T>        тип бизнес-компонента
   * @param <ID>       тип идентификатора
   * @param service    бизнес-компонент
   * @param primaryKey первичный ключ экземпляра бизнес-компонента
   * @param formName   имя формы поддержки, может быть <code>null</code>, в этом случае берется
   *                   форма по умолчанию
   * @param listener   слушатель событий формы поддержки, может быть <code>null</code>
   * @throws IllegalArgumentException если сервис <code>null</code>
   */
  @SuppressWarnings("unchecked")
  public static void view(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                          final Serializable primaryKey, final String formName, final MaintenanceFormActionListener listener) {
    if (service == null)
      throw new IllegalArgumentException("Service can't be null");
    DataBusinessObjectService s = service;//prevent compiler error
    internalShowMaintenanceForm(service, s.load(primaryKey),
        MaintenanceAction.VIEW, formName, listener);
  }

  /**
   * реализация запуска формы поддержки
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private static void internalShowMaintenanceForm(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
                                                  final PersistentObject entity, final MaintenanceAction action, final String formName,
                                                  final MaintenanceFormActionListener listener) {
    //load form
    MaintenanceForm form = (MaintenanceForm) ApplicationDictionaryLocator.locate().getMaintenaceForm(service, formName);
    //set listener on action
    form.addMaintenanceFormActionListener(listener);
    //execute form
    form.execute((DataBusinessObjectService<PersistentObject, Serializable>) service, entity, action);
  }

}
