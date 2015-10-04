/*
 * TimeKindDayliSearchHelp.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.table.support.ui;

/**
 * Механизм поиска сущностей "Тип времени" (с учетом по дням)
 *
 * @author Artem V. Sharapov
 * @version $Id: TimeKindDayliSearchHelp.java,v 1.1 2008/08/12 14:38:08 sharapov Exp $
 */
public class TimeKindDayliSearchHelp extends TimeKindSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.table.support.ui.TimeKindSearchHelp#isWholeDay()
   */
  @Override
  protected Boolean isWholeDay() {
    return true;
  }

}
