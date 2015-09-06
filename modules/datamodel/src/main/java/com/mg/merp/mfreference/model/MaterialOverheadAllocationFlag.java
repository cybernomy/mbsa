/*
 * MaterialOverheadAllocationFlag.java
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
 * Метод начисления накладных расходов на материал
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: MaterialOverheadAllocationFlag.java,v 1.2 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("Manufacture.MtlOhAllocationFlag")
public enum MaterialOverheadAllocationFlag {
  /**
   * На единицу материала
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MtlOhAllocFlag.Unit")
  UNIT,

  /**
   * Коэффициент от стоимости ресурса
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MtlOhAllocFlag.Cost")
  COST,

  /**
   * Фиксированная стоимость на партию
   */
  @EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MtlOhAllocFlag.Fixed")
  FIXED

}
