/*
 * ContractorSource.java
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: ContractorSource.java,v 1.1 2006/03/30 11:29:12 safonov Exp $
 */
public enum ContractorSource {
  /**
   * <>
   */
  @EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#ContractorSource.Type.None")
  NONE,

  /**
   * От кого
   */
  @EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#ContractorSource.Type.From")
  FROM,

  /**
   * Кому
   */
  @EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#ContractorSource.Type.To")
  TO,

  /**
   * Через кого
   */
  @EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#ContractorSource.Type.Through")
  THROUGH
}