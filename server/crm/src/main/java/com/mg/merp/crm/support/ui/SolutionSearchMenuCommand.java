/*
 * SolutionSearchMenuCommand.java
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
package com.mg.merp.crm.support.ui;

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.support.ui.ShowComponentBrowse;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.SecurityUtils;

/**
 * Контроллер комманды меню "Поиск решения"
 * 
 * @author Artem V. Sharapov
 * @version $Id: SolutionSearchMenuCommand.java,v 1.3 2008/08/15 14:15:23 sharapov Exp $
 */
public class SolutionSearchMenuCommand extends ShowComponentBrowse {
	
	private SolutionSearchDlg dialog;
	
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#execute()
	 */
	@Override
	public void execute() throws Exception {
		dialog = (SolutionSearchDlg) UIProducer.produceForm("com/mg/merp/crm/resources/SolutionSearchDlg.mfd.xml"); //$NON-NLS-1$
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
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/crm/Solution", BusinessMethodPermission.BROWSE_METHOD)); //$NON-NLS-1$
	}

}