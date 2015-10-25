/*
 * ConstantDataType.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Типы данных
 *
 * @author leonova
 * @version $Id: ConstantDataType.java,v 1.1 2007/08/17 09:19:21 alikaev Exp $
 */
@DataItemName("BAi.Const.ConstantDataType") //$NON-NLS-1$
public enum ConstantDataType {
  /**
   * Строка
   */
  @EnumConstantText("resource://com.mg.merp.baiengine.resources.dataitemlabels#ConstantDataType.String") //$NON-NLS-1$
      STRING,

  /**
   * Число целое
   */
  @EnumConstantText("resource://com.mg.merp.baiengine.resources.dataitemlabels#ConstantDataType.Integer") //$NON-NLS-1$
      INTEGER,

  /**
   * Число вещественное
   */
  @EnumConstantText("resource://com.mg.merp.baiengine.resources.dataitemlabels#ConstantDataType.Float") //$NON-NLS-1$
      FLOAT,

  /**
   * Дата
   */
  @EnumConstantText("resource://com.mg.merp.baiengine.resources.dataitemlabels#ConstantDataType.Date") //$NON-NLS-1$
      DATE
}

