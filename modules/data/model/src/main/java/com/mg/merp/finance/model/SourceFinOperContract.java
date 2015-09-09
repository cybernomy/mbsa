/*
 * SourceFinOperContract.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: SourceFinOperContract.java,v 1.1 2006/03/30 11:26:56 safonov Exp $
 */
public enum SourceFinOperContract {
  /**
   * <>
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.None")
  NONE,

  /**
   * Поставщик
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.Provider")
  PROVIDER,

  /**
   * Покупатель
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.Customer")
  CUSTOMER,

  /**
   * Грузоотправитель
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.Shipper")
  SHIPPER,

  /**
   * Грузополучатель
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.Consignee")
  CONSIDNEE,

  /**
   * Склад источник
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.StockSrc")
  STOCKSRC,

  /**
   * Склад приемник
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.StockDest")
  STOCKDEST,

  /**
   * МОЛ источник
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.MOLSrc")
  MOLSRC,

  /**
   * МОЛ приемник
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.MOLDest")
  MOLDEST,

  /**
   * Через кого
   */
  @EnumConstantText("resource://com.mg.merp.finance.resources.dataitemlabels#FinOperModel.Source.Through")
  THROUGH
}
