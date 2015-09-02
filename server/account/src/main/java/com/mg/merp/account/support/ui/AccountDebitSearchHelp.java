/*
 * AccountDebitSearchHelp.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: AccountDebitSearchHelp.java,v 1.1 2008/05/22 13:29:21 alikaev Exp $
 */
public class AccountDebitSearchHelp extends AccountSearchHelp {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {"AnlDb1", "AnlDb2", "AnlDb3", "AnlDb4", "AnlDb5"};
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		setExportContextValue("AnlDb1", null);
		setExportContextValue("AnlDb2", null);
		setExportContextValue("AnlDb3", null);
		setExportContextValue("AnlDb4", null);
		setExportContextValue("AnlDb5", null);
	}

}
