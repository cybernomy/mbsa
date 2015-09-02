/*
 * ConfirmExecutionByDocDocFlowPluginFactory.java
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
import com.mg.merp.paymentcontrol.PaymentcontrolProcessorServiceLocal;

/**
 * Реализация фабрики реализации этапа ДО "Подтверждение исполнения обязательства"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ConfirmExecutionByDocDocFlowPluginFactory.java,v 1.1 2007/06/01 07:15:12 sharapov Exp $
 */
public class ConfirmExecutionByDocDocFlowPluginFactory extends AbstractDocFlowPluginFactory {

	public final static int FACTORY_IDENTIFIER = 13101;

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
				getProcessorService().confirmExecutionByDocument(getParams());
				complete();
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
			 */
			@Override
			protected void doRoolback() throws Exception {
				getProcessorService().rollBackConfirmExecutionByDocument(getParams());
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
		return "Confirm execution by document"; //$NON-NLS-1$
	}

	private PaymentcontrolProcessorServiceLocal getProcessorService() {
		return (PaymentcontrolProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentcontrolProcessorServiceLocal.LOCAL_SERVICE_NAME);
	}

}
