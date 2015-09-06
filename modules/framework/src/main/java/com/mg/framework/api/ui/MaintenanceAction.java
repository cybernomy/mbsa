/*
 * MaintenanceAction.java
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
package com.mg.framework.api.ui;

/**
 * Интерактивные действия поддержки сервиса бизнес-компонента
 *
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceAction.java,v 1.2 2006/10/26 13:13:07 safonov Exp $
 */
public enum MaintenanceAction {
  /**
   * создание
   */
  ADD,

  /**
   * изменение
   */
  EDIT,

  /**
   * копирование
   */
  CLONE,

  /**
   * просмотр
   */
  VIEW
}
