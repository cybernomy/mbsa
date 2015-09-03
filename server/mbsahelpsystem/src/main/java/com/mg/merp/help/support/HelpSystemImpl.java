/*
 * HelpSystemImpl.java
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
package com.mg.merp.help.support;

import com.mg.framework.api.help.HelpSystem;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация сервиса системы помощи
 * 
 * @author Oleg V. Safonov
 * @version $Id: HelpSystemImpl.java,v 1.1 2006/11/14 15:29:39 safonov Exp $
 */
public class HelpSystemImpl implements HelpSystem {
	private final static String HELP_APP = "mbsahelp/help/"; //$NON-NLS-1$
	private final static String TUPIC_SUFFIX = "?topic="; //$NON-NLS-1$
	private final static String LOCALE_SUFFIX = "&locale="; //$NON-NLS-1$
	private final static String DELIMITER = "#"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.framework.api.help.HelpSystem#showContextHelp(java.lang.String)
	 */
	public void showContextHelp(String helpTopic) {
		UIUtils.showLocalDocument(new StringBuilder().append(HELP_APP).append(helpTopic.replaceFirst(DELIMITER, TUPIC_SUFFIX)).append(LOCALE_SUFFIX).append(ServerUtils.getUserLocale().getLanguage()).toString());
	}

}
