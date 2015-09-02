/*
 * FinCenterMt.java
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

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.finance.model.Center;
import com.mg.merp.reference.OrgUnitServiceLocal;
import com.mg.merp.reference.model.OrgUnit;

/**
 * @author leonova
 * @version $Id: FinCenterMt.java,v 1.1 2006/10/27 11:46:04 leonova Exp $
 */
public class FinCenterMt extends DefaultMaintenanceForm {

	public void onActionAddOrgUnit(WidgetEvent event) throws Exception {
		final OrgUnitServiceLocal service = (OrgUnitServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/OrgUnit");
		final SearchHelpForm searchHelp = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		
		searchHelp.addSearchHelpListener(new SearchHelpListener(){
			
			public void searchCanceled(SearchHelpEvent event) {
				
			}

			public void searchPerformed(SearchHelpEvent event) {
				final OrgUnit orgUnit = ((OrgUnit) event.getItems()[0]);
				Center center = (Center)getEntity();
				center.setCode(orgUnit.getCode());
				center.setName(orgUnit.getFullName());
				center.setUpCode(orgUnit.getUpCode());
				view.flushModel();
			}
			
		});
		searchHelp.run();
	}
	
}
