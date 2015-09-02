/*
 * PositionFillInStaffListSearchHelp.java
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
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;

/**
 * Поисковик сущностей "Занимаемые должности в ШР"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PositionFillInStaffListSearchHelp.java,v 1.1 2007/07/09 08:33:47 sharapov Exp $
 */
public class PositionFillInStaffListSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/salary/resources/PositionFillInStaffListSearchForm.mfd.xml"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		PositionFillInStaffListSearchForm searchForm = (PositionFillInStaffListSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.setStaffListId(getCalcPeriodService().getCurrentCalcPeriod().getStaffList().getId());
		searchForm.run(UIUtils.isModalMode());
	}

	private CalcPeriodServiceLocal getCalcPeriodService() {
		return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
	}

}
