/*
 * OfferStatusSearchHelp.java
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
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: OfferStatusSearchHelp.java,v 1.1 2006/07/10 12:18:03 leonova Exp $ 
 */
public class OfferStatusSearchHelp extends DefaultLegacySearchHelp {

	@Override
	protected String getServiceName() {
		return "merp/crm/OfferStatus";
	}

}
