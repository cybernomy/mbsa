/*
 * BusinessAddinRepositorySearchHelp.java
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
package com.mg.merp.baiengine.support.ui;

import java.io.Serializable;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.baiengine.RepositoryServiceLocal;

/**
 * Механизм поиска сущности BAi
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: BusinessAddinRepositorySearchHelp.java,v 1.2 2009/02/27 13:14:05 sharapov Exp $
 */
public class BusinessAddinRepositorySearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		SearchHelpForm form = (SearchHelpForm) UIProducer.produceForm("com/mg/merp/baiengine/resources/BusinessAddinRepositorySearchForm.mfd.xml");
		form.addSearchHelpListener(this);
		form.run(UIUtils.isModalMode());
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#isSupportView()
	 */
	@Override
	public boolean isSupportView() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		RepositoryServiceLocal repositoryService = (RepositoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(RepositoryServiceLocal.SERVICE_NAME);
		MaintenanceHelper.view(repositoryService, (Serializable) entity.getPrimaryKey(), null, null);
	}

}