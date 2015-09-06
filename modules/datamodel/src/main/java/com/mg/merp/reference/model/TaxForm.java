/*
 * TaxForm.java
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

/**
 * Форма налога
 *
 * @author Oleg V. Safonov
 * @version $Id: TaxForm.java,v 1.1 2006/03/29 13:06:23 safonov Exp $
 */
@DataItemName("Reference.Tax.Form")
public enum TaxForm {
  /**
   * НДС
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.From.NDS")
  NDS,

  /**
   * ГСМ
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.From.GSM")
  GSM,

  /**
   * Акциз
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.From.Excise")
  EXCISE,

  /**
   * Прочие
   */
  @EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Tax.From.Other")
  OTHER
}