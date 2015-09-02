/*
 * CreateLiabilityDocFlowPluginFactory.java
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
package com.mg.merp.paymentcontrol.support;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.paymentcontrol.PaymentControlProcessorListener;
import com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal;

/**
 * Реализация фабрики реализации этапа ДО "Создание записи реестра обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CreateLiabilityDocFlowPluginFactory.java,v 1.1 2007/05/14 05:18:50 sharapov Exp $
 */
public class CreateLiabilityDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

	public final static int FACTORY_IDENTIFIER = 13100;

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory#doCreatePlugin()
	 */
	@Override
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin() {

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
			 */
			@Override
			protected void doExecute() throws Exception {
				getProcessorService().createLiability(getParams(), new PaymentControlProcessorListener() {

					/* (non-Javadoc)
					 * @see com.mg.merp.paymentcontrol.PaymentControlProcessorListener#cancel()
					 */
					public void canceled() {
						cancel();
					}

					/* (non-Javadoc)
					 * @see com.mg.merp.paymentcontrol.PaymentControlProcessorListener#complete()
					 */
					public void completed() {
						complete();
					}
				});
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
			 */
			@Override
			protected void doRoolback() throws Exception {
				getProcessorService().rollBackCreateLiability(getParams());
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
		return "Create liability by document"; //$NON-NLS-1$
	}

	private PaymentcontrolProcessorServiceLocal getProcessorService() {
		return (PaymentcontrolProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentcontrolProcessorServiceLocal.LOCAL_SERVICE_NAME);
	}

}
