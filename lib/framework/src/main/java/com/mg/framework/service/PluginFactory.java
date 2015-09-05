/*
 * PluginFactory.java
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
package com.mg.framework.service;

import java.util.Properties;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: PluginFactory.java,v 1.4 2006/09/22 11:59:55 safonov Exp $
 */
@Deprecated
public class PluginFactory {
	private static Properties props = new Properties();
	
	static {
		// TODO load from config file
		//props.setProperty(PersistentManager.class.getName(), PersistentManagerEJBImpl.class.getName());
		props.setProperty(PersistentManager.class.getName(), /*PersistentManagerLegacyImpl.class.getName()*/null);
	}
	
	public static Object getPlugin(Class iface) {
		String implName = props.getProperty(iface.getName());
		if (implName == null) {
			throw new RuntimeException("Implementation not specified for " + iface.getName() + " in PluginFactory properties.");
		}
		try {
			return ServerUtils.loadClass(implName).newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException("Factory unable to construct instance of " + iface.getName());
		}
	}
}
