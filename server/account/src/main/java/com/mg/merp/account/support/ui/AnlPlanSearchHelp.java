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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.account.AnlPlanServiceLocal;
import com.mg.merp.account.model.AccPlan;

/**
 * Базовый класс для SearchHelp аналитических счетов
 * 
 * @author leonova
 * @version $Id: AnlPlanSearchHelp.java,v 1.7 2009/02/25 15:09:59 safonov Exp $
 */
public abstract class AnlPlanSearchHelp extends AbstractSearchHelp {

	protected AccPlan accPlan;
	
	protected abstract String getAccPlanContextName();
	
	protected abstract short getAnalitikaLevel();
	
	@Override
	protected String[] defineImportContext() {
		return new String[] {getAccPlanContextName()};
	}

	private AccPlan getAccPlan() {
		return getAccPlanContextName() == null ? accPlan : (AccPlan) getImportContextValue(getAccPlanContextName());
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		AnlPlanSearchForm form = (AnlPlanSearchForm) UIProducer.produceForm("com/mg/merp/account/resources/AnlPlanSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.setSearchParams(getAccPlan(), getAnalitikaLevel());		
		form.run(UIUtils.isModalMode());
	}

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		AnlPlanServiceLocal service = (AnlPlanServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AnlPlan");
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

	public void setAccPlan(AccPlan accPlan) {
		this.accPlan = accPlan;
	}
}
