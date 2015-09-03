/*
 * TimeRange.java
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
 * Интервал времени в тиках
 * 
 * @author Oleg V. Safonov
 * @version $Id: TimeRange.java,v 1.1 2007/07/30 10:25:31 safonov Exp $
 */
public class TimeRange {
	private long startDateTime;
	private long endDateTime;

	public TimeRange(long startDateTime, long endDateTime) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	/**
	 * @return the endDateTime
	 */
	public long getEndDateTime() {
		return endDateTime;
	}
	/**
	 * @return the startDateTime
	 */
	public long getStartDateTime() {
		return startDateTime;
	}

}
