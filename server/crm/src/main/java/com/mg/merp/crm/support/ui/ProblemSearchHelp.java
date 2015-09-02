/*
 * ProblemSearchHelp.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * SearchHelp для Проблем
 * 
 * @author leonova
 * @version $Id: ProblemSearchHelp.java,v 1.1 2006/10/16 11:02:50 leonova Exp $
 */
public class ProblemSearchHelp extends DefaultLegacySearchHelp {

	@Override
	protected String getServiceName() {
		return "merp/crm/Problem";
	}	
}
