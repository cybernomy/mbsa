/*
 * HolidayAccountKind.java
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
package com.mg.merp.table.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип учета праздничных дней
 *
 * @author leonova
 * @version $Id: HolidayAccountKind.java,v 1.1 2006/03/30 11:32:49 safonov Exp $
 */
@DataItemName("Table.Schedule.HolidayAccountKind")
public enum HolidayAccountKind {
  /**
   * Не учитывать
   */
  @EnumConstantText("resource://com.mg.merp.table.resources.dataitemlabels#HolidayAccountKind.DontAccount")
  DontAccount,

  /**
   * Учитывать как выходной
   */
  @EnumConstantText("resource://com.mg.merp.table.resources.dataitemlabels#HolidayAccountKind.AsHoliday")
  AsHoliday,

  /**
   * Учитывать другим типом времени
   */
  @EnumConstantText("resource://com.mg.merp.table.resources.dataitemlabels#HolidayAccountKind.AnotherTimeKind")
  AnotherTimeKind
}


