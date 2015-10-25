/*
 * DateOffSetKind.java
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: DateOffSetKind.java,v 1.1 2006/03/30 11:27:50 safonov Exp $
 */
@DataItemName("LbSchedule.Item.DateOffSetKind")
public enum DateOffSetKind {
  /**
   * рабочих дней
   */
  @EnumConstantText("resource://com.mg.merp.lbschedule.resources.dataitemlabels#DateOffSetKind.Workday")
  WORKDAY,

  /**
   * календарных дней
   */
  @EnumConstantText("resource://com.mg.merp.lbschedule.resources.dataitemlabels#DateOffSetKind.Day")
  DAY,

  /**
   * месяцев
   */
  @EnumConstantText("resource://com.mg.merp.lbschedule.resources.dataitemlabels#DateOffSetKind.Month")
  MONTH,

  /**
   * лет
   */
  @EnumConstantText("resource://com.mg.merp.lbschedule.resources.dataitemlabels#DateOffSetKind.Year")
  YEAR
}