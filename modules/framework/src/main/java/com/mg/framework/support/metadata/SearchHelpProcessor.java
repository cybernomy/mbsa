/*
 * SearchHelpProcessor.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.support.metadata;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpProcessor.java,v 1.1 2006/01/24 14:11:35 safonov Exp $
 */
public class SearchHelpProcessor {
	private static Logger logger = ServerUtils.getLogger(SearchHelpProcessor.class);
	
	public static SearchHelp createSearch(String name) {
		if (name == null)
			return null;
		try {
			return (SearchHelp) ServerUtils.loadClass(name).newInstance();
		} catch (Throwable e) {
			logger.error("SearchHelp instantiation error", e);
			return null;
		}
	}
}
