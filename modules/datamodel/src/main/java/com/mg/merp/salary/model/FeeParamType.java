/*
 * FeeParamType.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип параметра начисления/удержания
 *
 * @author leonova
 * @version $Id: FeeParamType.java,v 1.1 2006/03/30 11:31:37 safonov Exp $
 */
@DataItemName("Salary.FeeParam.FeeParamType")
public enum FeeParamType {
  /**
   * Число
   */
  @EnumConstantText("resource://com.mg.merp.salary.resources.dataitemlabels#FeeParamType.Number")
  NUMBER,

  /**
   * Дата
   */
  @EnumConstantText("resource://com.mg.merp.salary.resources.dataitemlabels#FeeParamType.Date")
  DATE,

  /**
   * Строка
   */
  @EnumConstantText("resource://com.mg.merp.salary.resources.dataitemlabels#FeeParamType.String")
  STRING
}
