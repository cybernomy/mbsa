/*
 * CreateDocOnComponentsDocFlowPluginFactory.java
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
package com.mg.merp.document.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.document.DocumentProcessorServiceLocal;
import com.mg.merp.document.generic.AbstractCreateDocumentDocFlowPlugin;

/**
 * Реализация фабрики реализаций этапа ДО "Создать документ на комплектующие"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponentsDocFlowPluginFactory.java,v 1.2 2009/02/04 09:35:26 safonov Exp $
 */
public class CreateDocOnComponentsDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

	public final static int FACTORY_IDENTIFIER = 29;

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractCreateDocumentDocFlowPlugin() {

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
			 */
			@Override
			protected void doExecute() throws Exception {
				DocumentProcessorServiceLocal service = (DocumentProcessorServiceLocal) ApplicationDictionaryLocator.locate()
					.getBusinessService(DocumentProcessorServiceLocal.LOCAL_SERVICE_NAME);
				service.processCreateDocOnComponents(getParams(), new CreateDocumentDocFlowListener() {

					public void canceled() {
						cancel();
					}

					public void completed() {
						complete();
					}
				});
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
			 */
			@Override
			protected void doRoolback() throws Exception {
				DocumentProcessorServiceLocal service = (DocumentProcessorServiceLocal) ApplicationDictionaryLocator.locate()
					.getBusinessService(DocumentProcessorServiceLocal.LOCAL_SERVICE_NAME);
				service.rollbackCreateDocOnComponents(getParams(), new CreateDocumentDocFlowListener() {

					public void canceled() {
						cancel();
					}

					public void completed() {
						complete();
					}
				});
			}

		};
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
	 */
	@Override
	public int getIdentifier() {
		return FACTORY_IDENTIFIER;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
	 */
	@Override
	public String getName() {
		return "Create document on components";
	}

}
