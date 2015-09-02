/*
 * ShowReportBrowse.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.support.ui.UIProducer;

/**
 * Обработчик главного меню показа списка бухгалтерских отчетов
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShowReportBrowse.java,v 1.1 2009/02/26 14:08:18 safonov Exp $
 */
public class ShowReportBrowse implements MenuCommand, Serializable {

	private static final String REPORT_FORM = "com/mg/merp/account/resources/Report.mfd.xml";
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#execute()
	 */
	public void execute() throws Exception {
		final AbstractForm form = (AbstractForm) UIProducer.produceForm(REPORT_FORM);
		form.run();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MenuCommand#isPermitted()
	 */
	public boolean isPermitted() {
		return true;
	}

	public void init(Map<String, String> params) {
		
	}

}