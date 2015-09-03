/*
 * PmcMenuCommand.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.support.ui.ShowComponentBrowse;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;

/**
 * Контроллер комманды меню "Управление платежами"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcMenuCommand.java,v 1.2 2008/08/18 07:14:24 sharapov Exp $
 */
public class PmcMenuCommand extends ShowComponentBrowse {

	private PmcDialog dialog;

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#execute()
	 */
	@Override
	public void execute() throws Exception {
		dialog = (PmcDialog) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcDialog.mfd.xml"); //$NON-NLS-1$
		dialog.run();
	}


	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#init(java.util.Map)
	 */
	@Override
	public void init(Map<String, String> arg0) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#isPermitted()
	 */
	@Override
	public boolean isPermitted() {
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(ResourceServiceLocal.LOCAL_SERVICE_NAME, "resourceManagment")); //$NON-NLS-1$
	}

}
