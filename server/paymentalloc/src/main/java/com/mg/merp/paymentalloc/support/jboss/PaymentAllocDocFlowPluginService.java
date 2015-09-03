/*
 * PaymentAllocDocFlowPluginService.java
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
package com.mg.merp.paymentalloc.support.jboss;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.paymentalloc.support.AllocatePaymentDocFlowPluginFactory;
import com.mg.merp.paymentalloc.support.AllocatePaymentInteractiveDocFlowPluginFactory;
import com.mg.merp.paymentalloc.support.CreatePaymentTransactionDocFlowPluginFactory;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Журнал платежей"
 * 
 * @author Denis V. Arychkov
 * @author Artem V. Sharapov
 * @version $Id: PaymentAllocDocFlowPluginService.java,v 1.2 2007/05/31 14:12:18 sharapov Exp $
 */
@Service(objectName=PaymentAllocDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(PaymentAllocDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class PaymentAllocDocFlowPluginService extends ServiceMBeanSupport implements PaymentAllocDocFlowPluginServiceMBean {

	private DocFlowPluginFactory createPaymentTransactionDocFlowPluginFactory;
	private DocFlowPluginFactory allocatePaymentDocFlowPluginFactory;
	private DocFlowPluginFactory allocatePaymentInteractiveDocFlowPluginFactory;


	/*
	 * (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	protected void createService() throws Exception {
		createPaymentTransactionDocFlowPluginFactory = new CreatePaymentTransactionDocFlowPluginFactory();
		allocatePaymentDocFlowPluginFactory = new AllocatePaymentDocFlowPluginFactory();
		allocatePaymentInteractiveDocFlowPluginFactory = new AllocatePaymentInteractiveDocFlowPluginFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#startService()
	 */
	protected void startService() throws Exception {
		DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createPaymentTransactionDocFlowPluginFactory);
		DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(allocatePaymentDocFlowPluginFactory);
		DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(allocatePaymentInteractiveDocFlowPluginFactory);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#stopService()
	 */
	protected void stopService() throws Exception {
		DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createPaymentTransactionDocFlowPluginFactory);
		DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(allocatePaymentDocFlowPluginFactory);
		DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(allocatePaymentInteractiveDocFlowPluginFactory);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	protected void destroyService() throws Exception {
		createPaymentTransactionDocFlowPluginFactory = null;
		allocatePaymentDocFlowPluginFactory = null;
		allocatePaymentInteractiveDocFlowPluginFactory = null;
	}

}
