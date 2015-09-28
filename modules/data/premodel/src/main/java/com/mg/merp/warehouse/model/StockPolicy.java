/*
 * StockPolicy.java
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
 * Политика списания со склада
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: StockPolicy.java,v 1.1 2006/04/13 10:28:32 safonov Exp $
 */
@DataItemName("Warehouse.Warehouse.StockPolicy")
public enum StockPolicy {
  /**
   * FIFO
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#STPolicy.Fifo")
  SPFIFO,

  /**
   * LIFO
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#STPolicy.Lifo")
  SPLIFO,

  /**
   * Партионный учёт
   */
  @EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#STPolicy.Batch")
  SPBATCH

}

