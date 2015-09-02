/**
 * FinOperModelSearchHelp.java 
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
 */
package com.mg.merp.finance.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Поисковик бизнес-компонента "Образцы Фин. операций"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FinOperModelSearchHelp.java,v 1.1 2007/02/16 14:33:39 sharapov Exp $
 */
public class FinOperModelSearchHelp extends DefaultLegacySearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
	 */
	@Override
	protected String getServiceName() {
		return "merp/finance/OperationModel"; //$NON-NLS-1$
	}

}
