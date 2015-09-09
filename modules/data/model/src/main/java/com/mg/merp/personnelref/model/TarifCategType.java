/*
 * TarifCategType.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип категории тарификации должности
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: TarifCategType.java,v 1.1 2006/04/13 10:23:53 safonov Exp $
 */
@DataItemName("PersonnelRef.TariffingCategory.Type")
public enum TarifCategType {
  /**
   * Оклад
   */
  @EnumConstantText("resource://com.mg.merp.personnelref.resources.dataitemlabels#TarifCategType.Rate")
  RATE,

  /**
   * Ставка по тарифной сетке
   */
  @EnumConstantText("resource://com.mg.merp.personnelref.resources.dataitemlabels#TarifCategType.Tariff")
  TARIFF,

  /**
   * Процент надбавки
   */
  @EnumConstantText("resource://com.mg.merp.personnelref.resources.dataitemlabels#TarifCategType.Rise")
  RISE,

  /**
   * Количество минимальных окладов
   */
  @EnumConstantText("resource://com.mg.merp.personnelref.resources.dataitemlabels#TarifCategType.Min")
  MIN

}
