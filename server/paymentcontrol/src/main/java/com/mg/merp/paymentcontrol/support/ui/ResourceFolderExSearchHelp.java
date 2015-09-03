/*
 * ResourceFolderExSearchHelp.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentcontrol.model.PmcConfig;

/**
 * Поисковик сущностей "Папки средств платежа"
 * Специализирован для диалога "Внутреннее перемещение средств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ResourceFolderExSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class ResourceFolderExSearchHelp extends ResourceFolderSearchHelp {
	
	private final String CURRENCY_EXPORT = "incomeCur"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
	 */
	@Override
	protected String[] defineExportContext() {
		return new String[] {CURRENCY_EXPORT};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	@Override
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		setExportContextValue(CURRENCY_EXPORT, getModuleConfiguration().getCurrency());
	}

	private PmcConfig getModuleConfiguration() {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		return ServerUtils.getPersistentManager().find(PmcConfig.class, sysClient.getId());
	}

}
