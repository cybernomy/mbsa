/*
 * OdaUiPlugin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.report.birt.data.oda.badi.ui;

import java.util.ResourceBundle;

import org.osgi.framework.BundleContext;

import com.mg.merp.wb.core.ui.plugin.MerpUIPlugin;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: OdaUiPlugin.java,v 1.3 2007/01/23 08:31:39 poroxnenko Exp $
 */
public class OdaUiPlugin extends MerpUIPlugin {

	private static final String RESOURCE_NAME = "com.mg.merp.wb.report.birt.data.oda.badi.ui.messages";
	// The plug-in ID
	public static final String PLUGIN_ID = "com.mg.merp.wb.report.birt.data.oda.badi.ui";

	// The shared instance
	private static OdaUiPlugin plugin;
	
	/**
	 * The constructor
	 */
	public OdaUiPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static OdaUiPlugin getDefault() {
		return plugin;
	}

}
