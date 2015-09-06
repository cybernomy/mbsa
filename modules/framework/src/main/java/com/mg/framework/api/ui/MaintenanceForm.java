/*
 * MaintenanceForm.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;

import java.io.Serializable;

/**
 * Стандартная форма поддержки для бизнес-компонента. Выполняются стандартные интерактивные операции
 * над объектом-сущностью бизнес-компонента создать, изменить, просмотреть.
 *
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceForm.java,v 1.2 2008/03/07 12:53:39 safonov Exp $
 */
public interface MaintenanceForm extends Form {

  /**
   * добавить слушателя на событие формы поддержки
   *
   * @param listener слушатель
   */
  void addMaintenanceFormActionListener(final MaintenanceFormActionListener listener);

  /**
   * удалить слушателя на событие формы поддержки
   *
   * @param listener слушатель
   */
  void removeMaintenanceFormActionListener(final MaintenanceFormActionListener listener);

  /**
   * запуск формы поддержки
   *
   * @param service бизнес-компонент
   * @param entity  объект-сущность
   * @param action  действие
   */
  void execute(final DataBusinessObjectService<PersistentObject, Serializable> service,
               final PersistentObject entity, final MaintenanceAction action);

  /**
   * режим обновления сущности в форме поддержки
   */
  enum RefreshMode {
    /**
     * обновлять после создания в хранилище
     */
    AFTER_CREATE,
    /**
     * обновлять после изменения в хранилище
     */
    AFTER_STORE
  }

}
