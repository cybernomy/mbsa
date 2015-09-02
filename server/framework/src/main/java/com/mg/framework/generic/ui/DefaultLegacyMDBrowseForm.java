/*
 * DefaultLegacyMDBrowseForm.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.ui.MaintenanceBrowseForm;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;

/**
 * @author Oleg V. Safonov
 * @version $Id: DefaultLegacyMDBrowseForm.java,v 1.4 2006/09/22 08:56:13 safonov Exp $
 */
@Deprecated
public class DefaultLegacyMDBrowseForm extends AbstractForm implements MaintenanceBrowseForm {
	protected MaintenanceTableController master;
	protected MaintenanceTableController detail;
	protected BrowseCond masterRest = new BrowseCond(null, "", new LocalDataTransferObject(), false, DataBusinessObjectService.INTERNAL_LEGACY_FORMAT);
	protected BrowseCond detailRest = new BrowseCond(null, "", new LocalDataTransferObject(), false, DataBusinessObjectService.INTERNAL_LEGACY_FORMAT);
	protected AttributeMap masterUIProperties = new LocalDataTransferObject();
	protected AttributeMap detailUIProperties = new LocalDataTransferObject();
	protected DataBusinessObjectService masterService;
	protected DataBusinessObjectService detailService;
	
	public DefaultLegacyMDBrowseForm() {
		this.master = new MaintenanceTableController(masterRest, masterUIProperties);
		this.detail = new MaintenanceTableController(detailRest, detailUIProperties);
		this.master.addMasterModelListener(detail);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MaintenanceBrowseForm#setService(com.mg.framework.api.DataBusinessObjectService)
	 */
	public void setService(DataBusinessObjectService service) throws ApplicationException {
		this.masterService = service;
		//view.setTitle(service.loadMetadata().name);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if (masterService == null || detailService == null)
			throw new IllegalStateException("Service cann't be null");
		
		master.initController(masterService);
		detail.initController(detailService);
		super.doOnRun();
	}
}
