/*
 * AccountKreditSearchHelp.java
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
 * @version $Id: AccountKreditSearchHelp.java,v 1.1 2008/05/22 13:29:21 alikaev Exp $
 */
public class AccountKreditSearchHelp extends AccountSearchHelp {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {"AnlKt1", "AnlKt2", "AnlKt3", "AnlKt4", "AnlKt5"};
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		setExportContextValue("AnlKt1", null);
		setExportContextValue("AnlKt2", null);
		setExportContextValue("AnlKt3", null);
		setExportContextValue("AnlKt4", null);
		setExportContextValue("AnlKt5", null);
	}

}
