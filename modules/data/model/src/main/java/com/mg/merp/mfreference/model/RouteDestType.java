/*
 * RouteDestType.java
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
 * Тип приёмника товара
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: RouteDestType.java,v 1.2 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName("MfReference.RouteDestType")
public enum RouteDestType {
  /**
   * Продажа
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#SrcType.Sale")
  SALE,

  /**
   * Производство
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#SrcType.Manufacture")
  MANUFACTURE,

  /**
   * Перемещение
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#SrcType.Transfer")
  TRANSFER
}
