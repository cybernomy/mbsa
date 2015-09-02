/*
 * AmCodeSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.account.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.account.AmCodeServiceLocal;

/**
 * Механизм поиска сущностей бизнес-компонента "Шифры амортизации"
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: AmCodeSearchHelp.java,v 1.4 2008/07/15 08:15:09 safonov Exp $
 */
public class AmCodeSearchHelp extends AbstractSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		AmCodeSearchForm form = (AmCodeSearchForm) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.AmCodeSearchForm");
		form.addSearchHelpListener(this);
		form.run(UIUtils.isModalMode());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		if (entity == null)
			throw new IllegalArgumentException("Entity is null");
		AmCodeServiceLocal service = (AmCodeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AmCode");
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
