/*
 * TaxType.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

@DataItemName("Reference.Tax.Type")
public enum TaxType {
  /**
   * Процентный
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.Type.Percent")
  PERCENT,

  /**
   * Суммовой
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.Type.Sum")
  SUM
}