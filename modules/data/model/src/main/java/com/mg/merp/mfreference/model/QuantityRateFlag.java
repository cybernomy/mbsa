/*
 * QuantityRateFlag.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Метод измерения расхода материалов (для Материала операции)
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: QuantityRateFlag.java,v 1.3 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("Manufacture.QuantityRateFlag")
public enum QuantityRateFlag {
  /**
   * На один час операции
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#QuanRateFlag.Time")
  TIME,

  /**
   * На единицу готовой продукции
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#QuanRateFlag.Unit")
  UNIT,

  /**
   * Фиксированное количество на партию
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#QuanRateFlag.Fixed")
  FIXED
}
