/*
 * JobRouteDetectSearchHelp.java
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
package com.mg.merp.manufacture.support.ui;

/**
 * Механизм поиска операций ЗНП для документов на списание потерь
 * 
 * @author Oleg V. Safonov
 * @version $Id: JobRouteDetectSearchHelp.java,v 1.1 2007/07/31 06:31:06 safonov Exp $
 */
public class JobRouteDetectSearchHelp extends JobRouteSearchHelp {
	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.support.ui.JobRouteSearchHelp#getJobPropertyName()
	 */
	@Override
	protected String getJobPropertyName() {
		return "DetectJob";
	}

}
