/*
 * BankAccountSearchHelp.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.finance.AnalyticsServiceLocal;
import com.mg.merp.finance.model.Account;

/**
 * Базовый класс для SearchHelp аналитических счетов финансового учета
 * 
 * @author leonova
 * @version $Id: FinAnlPlanSearchHelp.java,v 1.1 2006/10/30 13:50:17 leonova Exp $
 */
public abstract class FinAnlPlanSearchHelp extends AbstractSearchHelp {

	protected Account accPlanName;
	
	protected abstract short getAnalitikaLevel();

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */	
	@Override
	protected void doSearch() throws Exception {
		FinAnlPlanSearchForm form = (FinAnlPlanSearchForm) UIProducer.produceForm("com/mg/merp/finance/resources/FinAnlPlanSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.setSearchParams(getAccPlanName(), getAnalitikaLevel());
		form.run(UIUtils.isModalMode());
	}

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		AnalyticsServiceLocal service = (AnalyticsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Analytics");
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

	public void setAccPlanName(Account accPlanName) {
		this.accPlanName = accPlanName;
	}

	public Account getAccPlanName() {
		return accPlanName;
	}
}
