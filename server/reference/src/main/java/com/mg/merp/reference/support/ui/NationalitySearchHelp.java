/*
 * NationalitySearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: NationalitySearchHelp.java,v 1.1 2006/07/11 06:51:34 leonova Exp $ 
 */
public class NationalitySearchHelp extends DefaultLegacySearchHelp{

	@Override
	protected String getServiceName() {
		return "merp/reference/Nationality";
	}

}
