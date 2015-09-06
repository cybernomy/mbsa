/*
 * DayTimes.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference;

/**
 * Время дня в тиках
 *
 * @author Oleg V. Safonov
 * @version $Id: DayTimes.java,v 1.1 2007/07/30 07:14:30 safonov Exp $
 */
public class DayTimes {
  private long startDayTicks;
  private long endDayTicks;
  private long startTicks;
  private long endTicks;

  public DayTimes(long startDayTicks, long endDayTicks, long startTicks, long endTicks) {
    super();
    this.startDayTicks = startDayTicks;
    this.endDayTicks = endDayTicks;
    this.startTicks = startTicks;
    this.endTicks = endTicks;
  }

  /**
   * @return the aEndTicks
   */
  public long getEndTicks() {
    return endTicks;
  }

  /**
   * @return the endDayTicks
   */
  public long getEndDayTicks() {
    return endDayTicks;
  }

  /**
   * @return the startDayTicks
   */
  public long getStartDayTicks() {
    return startDayTicks;
  }

  /**
   * @return the startTicks
   */
  public long getStartTicks() {
    return startTicks;
  }

  public boolean isDayEmpty() {
    return endDayTicks == 0 && endTicks == 0 && startDayTicks == 0 && startTicks == 0;
  }

}
