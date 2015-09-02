/*
 * PaymentSearchHelp.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Записи журнала платежа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentSearchHelp.java,v 1.1 2007/05/31 14:13:30 sharapov Exp $
 */
public class PaymentSearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PaymentSearchForm form = (PaymentSearchForm) UIProducer.produceForm("com/mg/merp/paymentalloc/resources/PaymentSearchForm.mfd.xml"); //$NON-NLS-1$
		form.addSearchHelpListener(this);
		form.run(UIUtils.isModalMode());
	}

}
