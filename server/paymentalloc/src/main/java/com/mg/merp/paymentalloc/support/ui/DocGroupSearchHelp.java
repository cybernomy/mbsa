/*
 * DocGroupSearchHelp.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Поисковик "Групп документов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocGroupSearchHelp.java,v 1.1 2007/01/13 13:26:39 sharapov Exp $
 */
public class DocGroupSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return "merp/paymentalloc/DocGroup"; //$NON-NLS-1$
	}

}
