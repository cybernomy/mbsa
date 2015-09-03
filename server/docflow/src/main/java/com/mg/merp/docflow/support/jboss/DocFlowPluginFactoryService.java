/*
 * DocFlowPluginFactoryService.java
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
package com.mg.merp.docflow.support.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.DocFlowPluginFactoryManager;
import com.mg.merp.docflow.PluginNotImplementedException;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerImpl;

/**
 * Реализация сервиса менеджера фабрик реализаций этапов ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryService.java,v 1.2 2007/01/29 14:10:02 safonov Exp $
 */
public class DocFlowPluginFactoryService extends ServiceMBeanSupport implements
		DocFlowPluginFactoryServiceMBean {
	private DocFlowPluginFactoryManager delegate;

    protected void createService() throws Exception {
    	delegate = new DocFlowPluginFactoryManagerImpl();
    }
    
    protected void destroyService() throws Exception {
    	delegate = null;
    }

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#registerPluginFactory(com.mg.merp.docflow.DocFlowPluginFactory)
	 */
	public void registerPluginFactory(DocFlowPluginFactory pluginFactory) {
		delegate.registerPluginFactory(pluginFactory);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#unregisterPluginFactory(com.mg.merp.docflow.DocFlowPluginFactory)
	 */
	public void unregisterPluginFactory(DocFlowPluginFactory pluginFactory) {
		delegate.unregisterPluginFactory(pluginFactory);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactoryManager#findPluginFactory(int)
	 */
	public DocFlowPluginFactory findPluginFactory(int identifier)
			throws PluginNotImplementedException {
		return delegate.findPluginFactory(identifier);
	}

}
