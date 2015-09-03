/*
 * CarryForwardMenuCommand.java
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

import java.util.Map;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.ShowComponentBrowse;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.finance.TurnAccFlatServiceLocal;

/**
 * Контроллер комманды меню "Перенос остатков" фин.учета
 * @author Artem V. Sharapov
 * @version $Id: CarryForwardMenuCommand.java,v 1.2 2008/07/09 11:51:37 safonov Exp $
 */
public class CarryForwardMenuCommand extends ShowComponentBrowse {
	private FinCarryForwardDialog dialog;
	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.ShowComponentBrowse#execute()
	 */
	@Override
	public void execute() throws Exception {
		dialog = (FinCarryForwardDialog) UIProducer.produceForm("com/mg/merp/finance/resources/FinCarryForwardDialog.mfd.xml"); //$NON-NLS-1$
		dialog.addOkActionListener(new FormActionListener() {
			public void actionPerformed(FormEvent event) {																					
				TurnAccFlatServiceLocal turnAccService = (TurnAccFlatServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/TurnAccFlat"); //$NON-NLS-1$
				turnAccService.carryForward(dialog.getFinPeriodFrom(), dialog.getFinPeriodTill(), dialog.isAllAcc(), dialog.getAccounts());
			}			
		});	
		//dialog.setAllAcc(true);
		dialog.execute();
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
		return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/finance/TurnAccFlat", "carryForward"));
	}

}
