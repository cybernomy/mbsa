/*
 * IncomeKindSearchHelp.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поисковик сущностей "Вид дохода"
 * 
 * @author Artem V. Sharapov
 * @version $Id: IncomeKindSearchHelp.java,v 1.1 2007/07/18 08:04:40 sharapov Exp $
 */
public class IncomeKindSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/salary/resources/IncomeKindSearchForm.mfd.xml"; //$NON-NLS-1$
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		IncomeKindSearchForm searchForm = (IncomeKindSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
	}

}
