/*
 * FinAccountSearchHelp.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: FinAccountSearchHelp.java,v 1.4 2006/10/30 13:50:29 leonova Exp $
 */
public class FinAccountSearchHelp extends DefaultLegacySearchHelp {

	@Override
	protected String getServiceName() {
		return "merp/finance/Account";
	}


}
