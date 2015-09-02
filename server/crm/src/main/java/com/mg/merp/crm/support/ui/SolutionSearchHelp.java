/*
 * SolutionSearchHelp.java
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
 * Поисковик бизнес-компонента "Решение"
 *  
 * @author Artem V. Sharapov
 * @version $Id: SolutionSearchHelp.java,v 1.1 2007/02/07 07:02:41 sharapov Exp $
 */
public class SolutionSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return "merp/crm/Solution"; //$NON-NLS-1$
	}

}
