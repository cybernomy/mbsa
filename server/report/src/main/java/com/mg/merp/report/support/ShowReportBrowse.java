/*
 * ShowReportBrowse.java
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
package com.mg.merp.report.support;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.ServerUtils;

/**
 * @author leonova
 * @version $Id: ShowReportBrowse.java,v 1.3 2008/08/15 15:55:49 sharapov Exp $
 */
public class ShowReportBrowse implements MenuCommand, Serializable {

	private static final String REPORT_FORM = "com/mg/merp/report/resources/Report.mfd.xml";
	
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
		return ServerUtils.getUserProfile().getGroups().length != 0;
	}

	public void init(Map<String, String> params) {
		
	}

}