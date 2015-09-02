/*
 * AnlPlanAccDbSearchHelp.java
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
 * Ѕазовый класс дл€ SearchHelp аналитических счетов по дебету
 * 
 * @author leonova
 * @version $Id: AnlPlanAccDbSearchHelp.java,v 1.1 2006/10/04 06:19:16 leonova Exp $
 */
public abstract class AnlPlanAccDbSearchHelp extends AnlPlanSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.AnlPlanSearchHelp#getAccPlanContextName()
	 */
	@Override
	protected String getAccPlanContextName() {		
		return "AccDb";
	}


}
