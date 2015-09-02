/*
 * TaxSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: TaxSearchHelp.java,v 1.1 2006/10/24 10:31:19 leonova Exp $
 */
public class TaxSearchHelp extends DefaultLegacySearchHelp{

	@Override
	protected String getServiceName() {
		return "merp/reference/Tax";
	}

}
