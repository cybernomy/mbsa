/*
 * MeasureControl.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Управление ЕИ
 *
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: MeasureControl.java,v 1.3 2006/12/12 14:22:15 safonov Exp $
 */
@DataItemName("Reference.Catalog.MControl.Type")
public enum MeasureControl {

  /**
   * номенклатура с одной ЕИ
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#MControl.Type.Single")
  SINGLE,

  /**
   * номенклатура с переменной эффективности (вторая единица измерения зависит от первой и
   * расчитывается)
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#MControl.Type.Potency")
  POTENCY,

  /**
   * номенклатура учетно-весовая (две единицы измерения не зависят друг от друга)
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#MControl.Type.Catchweight")
  CATCHWEIGHT
}

