/*
 * WarehouseTypeSearchHelp.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.warehouse.WarehouseTypeServiceLocal;

/**
 * SearchHelp бизнес-компонента "Тип склада"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTypeSearchHelp.java,v 1.1 2007/09/17 12:55:25 alikaev Exp $
 */
public class WarehouseTypeSearchHelp extends AbstractSearchHelp {

	private final String FORM_NAME = "com/mg/merp/warehouse/resources/WarehouseTypeSearchForm.mfd.xml"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
	 */
	@Override
	protected void doSearch() throws Exception {
		WarehouseTypeSearchForm searchForm = (WarehouseTypeSearchForm) UIProducer.produceForm(FORM_NAME);
		searchForm.addSearchHelpListener(this);
		searchForm.run(UIUtils.isModalMode());
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doView(PersistentObject entity) {
		WarehouseTypeServiceLocal service = (WarehouseTypeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseTypeServiceLocal.LOCAL_SERVICE_NAME);
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
