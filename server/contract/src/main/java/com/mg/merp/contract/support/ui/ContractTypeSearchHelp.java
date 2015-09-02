/*
 * ContractTypeSearchHelp.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.contract.ContractTypeServiceLocal;

/**
 * SearchHelp бизнес-компонента "Вид договора"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ContractTypeSearchHelp.java,v 1.1 2007/09/17 12:29:23 alikaev Exp $
 */
public class ContractTypeSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/contract/resources/ContractTypeSearchForm.mfd.xml"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		ContractTypeSearchForm searchForm = (ContractTypeSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		ContractTypeServiceLocal service = (ContractTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ContractTypeServiceLocal.LOCAL_SERVICE_NAME);
		MaintenanceHelper.view(service, (Integer) entity.getPrimaryKey(), null, null);		
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}
	
}
