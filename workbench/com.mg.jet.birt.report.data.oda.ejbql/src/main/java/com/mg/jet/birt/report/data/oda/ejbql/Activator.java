/**
 * Activator.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.jet.birt.report.data.oda.ejbql;

import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Oleg V. Safonov
 * @version $Id: Activator.java,v 1.1 2007/10/29 08:33:24 safonov Exp $
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.mg.jet.birt.report.data.oda.ejbql";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		//Thread thread = Thread.currentThread();
		//ClassLoader oldloader = thread.getContextClassLoader();

		// context.getBundle().loadClass("org.hibernate.proxy.HibernateProxy");

		HibernateUtil.refreshURLs();
		HibernateUtil.pluginLoader = HibernateUtil.class.getClassLoader();
		// ClassLoader changeLoader = new URLClassLoader( (URL
		// [])URLList.toArray(new URL[0]),HibernateUtil.class.getClassLoader());
		// ClassLoader changeLoader = new URLClassLoader( (URL
		// [])URLList.toArray(new URL[0]),thread.getContextClassLoader());
		// ClassLoader changeLoader = new URLClassLoader( (URL
		// [])HibernateUtil.URLList.toArray(new URL[0]),
		// HibernateUtil.class.getClassLoader());

		ClassLoader changeLoader = new URLClassLoader(HibernateUtil.URLList.toArray(new URL[0]));
		//thread.setContextClassLoader(changeLoader);
		HibernateUtil.changeLoader = changeLoader;

		// HibernateUtil.constructSessionFactory("", "");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		HibernateUtil.closeSession();
		HibernateUtil.closeFactory();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
