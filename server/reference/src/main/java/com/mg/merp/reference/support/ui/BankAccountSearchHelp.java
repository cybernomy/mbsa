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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.BankAccountServiceLocal;
import com.mg.merp.reference.model.Contractor;

/**
 * Базовый класс для SearchHelp банковских счетов
 * 
 * @author leonova
 * @version $Id: BankAccountSearchHelp.java,v 1.2 2007/08/17 07:28:25 safonov Exp $
 */
public abstract class BankAccountSearchHelp extends AbstractSearchHelp {

	protected abstract String getContractorContextName();
		
	@Override
	protected String[] defineImportContext() {
		return new String[] {getContractorContextName()};
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		Contractor сontractor = (Contractor) getImportContextValue(getContractorContextName());
		//без контрагента невозможен поиск банковских счетов
		if (сontractor == null)
			return;
		
		BankAccountSearchForm form = (BankAccountSearchForm) UIProducer.produceForm("com/mg/merp/reference/resources/BankAccountSearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.setContractor(сontractor);
		
		form.run(UIUtils.isModalMode());
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		BankAccountServiceLocal service = (BankAccountServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/BankAccount");
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}
}
