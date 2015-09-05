/*
 * ConversionProcessor.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.api.metadata.ConversionRoutine;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: ConversionProcessor.java,v 1.2 2008/03/03 13:13:18 safonov Exp $
 */
public class ConversionProcessor {
	private static Logger logger = ServerUtils.getLogger(ConversionProcessor.class);
	
	public static ConversionRoutine<?, ?> createConversion(String name) {
		if (name == null)
			return null;
		try {
			return (ConversionRoutine<?, ?>) ServerUtils.loadClass(name).newInstance();
		} catch (Throwable e) {
			logger.error("ConversionRoutine instantiation error", e);
			return null;
		}
	}

}
