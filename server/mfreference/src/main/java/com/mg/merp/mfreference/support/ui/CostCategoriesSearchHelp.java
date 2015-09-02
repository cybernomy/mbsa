/*
 * CostCategoriesSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * @author leonova
 * @version $Id: CostCategoriesSearchHelp.java,v 1.3 2006/07/12 09:17:18 leonova Exp $ 
 */
public class CostCategoriesSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return "merp/mfreference/CostCategories";
	}

}
