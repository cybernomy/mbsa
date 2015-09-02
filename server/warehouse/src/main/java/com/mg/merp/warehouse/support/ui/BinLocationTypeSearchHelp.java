/*
 * BinLocationTypeSearchHelp.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BinLocationTypeSearchHelp.java,v 1.2 2006/07/10 11:55:59 leonova Exp $
 */
public class BinLocationTypeSearchHelp extends DefaultLegacySearchHelp{

	@Override
	protected String getServiceName() {		
		return "merp/warehouse/BinLocationType";
	}
}
