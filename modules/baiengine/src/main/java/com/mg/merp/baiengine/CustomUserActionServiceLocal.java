/*
 * CustomUserActionServiceLocal.java
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
package com.mg.merp.baiengine;

import com.mg.merp.baiengine.model.CustomUserAction;
import com.mg.merp.baiengine.model.CustomUserActionPermiss;

/**
 * Бизнес-компонент "Настраиваемые действия"
 *
 * @author Oleg V. Safonov
 * @version $Id: CustomUserActionServiceLocal.java,v 1.1 2007/11/15 09:04:38 safonov Exp $
 */
public interface CustomUserActionServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CustomUserAction, Integer> {
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/baiengine/CustomUserAction";

  /**
   * установить права на действие
   *
   * @param actionId идентификатор действия
   * @param roleId   идентификатор роли
   */
  void grantPermission(int actionId, int roleId);

  /**
   * удалить права действие
   *
   * @param perms права
   */
  void revokePermission(CustomUserActionPermiss[] perms);

}
