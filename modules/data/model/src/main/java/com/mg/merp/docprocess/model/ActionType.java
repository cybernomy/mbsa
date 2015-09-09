/*
 * ActionType.java
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип действия над документом
 *
 * @author Oleg V. Safonov
 * @version $Id: ActionType.java,v 1.2 2006/10/21 11:37:32 safonov Exp $
 */
@DataItemName("DocFlow.DocAction.Type")
public enum ActionType {
  /**
   * Выполнен этап ДО
   */
  @EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocAction.Type.Stage")
  STAGE,

  /**
   * Изменение
   */
  @EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocAction.Type.Update")
  UPDATE,

  /**
   * Удаление
   */
  @EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocAction.Type.Delete")
  DELETE
}
