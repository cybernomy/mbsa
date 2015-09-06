/*
 * OrderHeadStatus.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Статус заказа
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderStatus.java,v 1.2 2007/07/27 12:00:23 safonov Exp $
 */
@DataItemName("Warehouse.OrderHead.Status")
public enum OrderStatus {
  /**
   * Запланировано
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#OrderStatus.Planned")
  PLANNED,

  /**
   * Заказано
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#OrderStatus.Ordered")
  ORDERED,

  /**
   * Выполнено
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#OrderStatus.Completed")
  COMPLETED
}
