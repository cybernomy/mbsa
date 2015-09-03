/*
 * PmcPeriodSearchHelp.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Период планирования"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcPeriodSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcPeriodSearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PmcPeriodSearchForm form =  (PmcPeriodSearchForm) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcPeriodSearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		form.run(UIUtils.isModalMode());
	}

}
