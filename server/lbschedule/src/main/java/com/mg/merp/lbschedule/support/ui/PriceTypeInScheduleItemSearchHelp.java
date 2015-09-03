/*
 * PriceTypeInScheduleItemSearchHelp.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.merp.reference.support.ui.PriceTypeBoundedSearchHelp;

/**
 * Поисковик "типов цен для прайс-листа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PriceTypeInScheduleItemSearchHelp.java,v 1.2 2007/05/10 07:32:15 safonov Exp $
 */
public class PriceTypeInScheduleItemSearchHelp extends PriceTypeBoundedSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.support.ui.PriceTypeBoundedSearchHelp#getPriceHeadProperty()
	 */
	@Override
	protected String getPriceHeadProperty() {
		return "PriceListHead"; //$NON-NLS-1$
	}


}
