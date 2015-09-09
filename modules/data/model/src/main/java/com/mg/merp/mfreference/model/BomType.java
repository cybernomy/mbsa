/*
 * BomType.java
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
 * Тип для состава изделия
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BomType.java,v 1.1 2006/04/13 10:20:42 safonov Exp $
 */
@DataItemName("MfReference.BomType")
public enum BomType {
  /**
   * Подготовительный
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#BomType.Preparatory")
  PREPARATORY,

  /**
   * Текущий
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#BomType.Current")
  CURRENT,

  /**
   * Стандартный
   */
  @EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#BomType.Standart")
  STANDART

}
