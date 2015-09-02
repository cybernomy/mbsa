/*
 * AnlPlanAnlLevel1SearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

/**
 * @author leonova
 * @version $Id: AnlPlanAnlLevel1SearchHelp.java,v 1.1 2006/10/18 06:13:19 leonova Exp $
 */
public class AnlPlanAnlLevel1SearchHelp extends AnlPlanRemnSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.AnlPlanSearchHelp#getAnalitikaLevel()
	 */
	@Override
	protected short getAnalitikaLevel() {
		return 1;
	}

}
