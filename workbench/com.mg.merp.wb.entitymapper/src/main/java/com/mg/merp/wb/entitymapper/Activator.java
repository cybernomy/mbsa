/* Activator.java
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
package com.mg.merp.wb.entitymapper;

import java.util.ResourceBundle;

import org.osgi.framework.BundleContext;

import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;
import com.mg.merp.wb.entitymapper.ui.EntityMapperView;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: Activator.java,v 1.1 2007/05/07 13:08:35 poroxnenko Exp $
 */
public class Activator extends BusinessObjectPlugin<EntityTransformerMapping> {

	private static final String RESOURCE_NAME = "com.mg.merp.wb.entitymapper.messages";

	private static final String MAPPER_SERVICE_NAME = "merp:service=EntityMapperWorkbenchService";

	public static final String CHECK_SERVER_MESSAGE = "server.check.message";

	// The plug-in ID
	public static final String ID = "com.mg.merp.wb.entitymapper";

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
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
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
	public static Activator getDefault() {
		return plugin;
	}

	public EntityTransformerMapping addBusinessObject(EntityTransformerMapping bo) throws Exception {
		return (EntityTransformerMapping) invoke(MAPPER_SERVICE_NAME,
				"addEntityMapper", new Object[] {bo}, new String[] {EntityTransformerMapping.class.getName()}); //$NON-NLS-1$
	}

	public EntityTransformerMapping editBusinessObject(EntityTransformerMapping bo) throws Exception {
		return (EntityTransformerMapping) invoke(MAPPER_SERVICE_NAME,
				"editEntityMapper", new Object[] {bo}, new String[] {EntityTransformerMapping.class.getName()}); //$NON-NLS-1$
	}

	public EntityTransformerMapping[] synchronize(String query) throws Exception {
		return (EntityTransformerMapping[]) invoke(MAPPER_SERVICE_NAME,
				"getEntityMappers", new Object[] {query}, new String[] {String.class.getName()}); //$NON-NLS-1$
	}

	public void deleteBusinessObjectsList(Integer[] ids) throws Exception {
		invoke(MAPPER_SERVICE_NAME,
				"deleteEntityMappersList", new Object[] {ids}, new String[] {Integer[].class.getName()}); //$NON-NLS-1$
	}

	@Override
	public String getViewId() {
		return EntityMapperView.ID;
	}
}
