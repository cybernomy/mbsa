/*
 * ChooseCalcPeriodMenuCommand.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.personnelref.support.ui;

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.ShowComponentBrowse;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.CalcPeriod;

/**
 * Контроллер комманды меню "Установить рассчетный период" управление персонала
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ChooseCalcPeriodMenuCommand.java,v 1.1 2008/03/25 09:40:02 alikaev Exp $
 */
public class ChooseCalcPeriodMenuCommand extends ShowComponentBrowse {

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#execute()
	 */
	@Override
	public void execute() throws Exception {
		SearchHelp orderHeadSearchHelp = SearchHelpProcessor.createSearch("com.mg.merp.personnelref.support.ui.CalcPeriodSearchHelp"); //$NON-NLS-1$
		orderHeadSearchHelp.addSearchHelpListener(new SearchHelpListener() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
			 */
			public void searchCanceled(SearchHelpEvent event) {
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
			 */
			public void searchPerformed(SearchHelpEvent event) {
				CalcPeriodServiceLocal service = (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
				service.setCurrentCalcPeriod(((CalcPeriod) event.getItems()[0]).getId());
			}
		});
		try {
			orderHeadSearchHelp.search();
		} catch (Exception e) {
			ServerUtils.getLogger(ChooseCalcPeriodMenuCommand.class).error("Error find entity"); //$NON-NLS-1$
		}		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#init(java.util.Map)
	 */
	@Override
	public void init(Map<String, String> params) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#isPermitted()
	 */
	@Override
	public boolean isPermitted() {
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME, "setCurrentCalcPeriod")); //$NON-NLS-1$
	}	
	
}
