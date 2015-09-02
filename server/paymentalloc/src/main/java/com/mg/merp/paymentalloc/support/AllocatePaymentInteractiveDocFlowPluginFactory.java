/* AllocatePaymentInteractiveDocFlowPluginFactory.java
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
package com.mg.merp.paymentalloc.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.paymentalloc.PaymentallocProcessorListener;
import com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal;

/**
 * Реализация фабрики реализации этапа ДО "Отработка в журнале платежей интерактивная"
 * 
 * @author Artem V. Sharapov
 * @version $Id: AllocatePaymentInteractiveDocFlowPluginFactory.java,v 1.1 2007/05/31 14:11:43 sharapov Exp $
 */
public class AllocatePaymentInteractiveDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

	public final static int FACTORY_IDENTIFIER = 13002;

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin(){

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
			 */
			@Override
			protected void doExecute() throws Exception {
				PaymentallocProcessorServiceLocal service = (PaymentallocProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentallocProcessorServiceLocal.LOCAL_SERVICE_NAME);
				service.allocatePaymentIteractive(getParams(), new PaymentallocProcessorListener() {

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
				PaymentallocProcessorServiceLocal service = (PaymentallocProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentallocProcessorServiceLocal.LOCAL_SERVICE_NAME);
				service.rollBackAllocatePaymentIteractive(getParams());
				complete();
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getIdentifier()
	 */
	@Override
	public int getIdentifier() {
		return FACTORY_IDENTIFIER;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#getName()
	 */
	@Override
	public String getName() {
		return "Payment allocation iteractive"; //$NON-NLS-1$
	}

}
