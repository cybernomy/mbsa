/*
 * RegisterInSaleBookDocFlowPluginFactory.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.factura.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.factura.FacturaProcessorServiceLocal;

/**
 * Реализация фабрики реализаций этапа ДО "Регистрация в книге продаж"
 * 
 * @author Artem V. Sharapov
 * @version $Id: RegisterInSaleBookDocFlowPluginFactory.java,v 1.1 2009/03/16 14:30:34 sharapov Exp $
 */
public class RegisterInSaleBookDocFlowPluginFactory extends AbstractDocFlowPluginFactory {
	
	public final static int FACTORY_IDENTIFIER = 18;
	
	
	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin() {
			
			private FacturaProcessorServiceLocal facturaProcessorService = (FacturaProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FacturaProcessorServiceLocal.SERVICE_NAME);
			
			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
			 */
			@Override
			protected void doExecute() throws Exception {
				facturaProcessorService.registerInSaleBook(getParams());
				complete();
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
			 */
			@Override
			protected void doRoolback() throws Exception {
				facturaProcessorService.rollBackRegisterInSaleBook(getParams());
				complete();
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
		return "Register in sale book"; //$NON-NLS-1$
	}

}
