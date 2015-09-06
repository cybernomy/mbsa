/*
 * PaymentcontrolDocFlowPluginService.java
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
package com.mg.merp.paymentcontrol.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.paymentcontrol.support.ConfirmExecutionByDocDocFlowPluginFactory;
import com.mg.merp.paymentcontrol.support.CreateLiabilityDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Платежный календарь"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentcontrolDocFlowPluginService.java,v 1.2 2007/06/01 07:15:44 sharapov Exp $
 */
@Service(objectName = PaymentcontrolDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(PaymentcontrolDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class PaymentcontrolDocFlowPluginService extends ServiceMBeanSupport
    implements PaymentcontrolDocFlowPluginServiceMBean {

  private DocFlowPluginFactory createLiabilityDocFlowPluginFactory;
  private DocFlowPluginFactory confirmExecutionByDocDocFlowPluginFactory;

  protected void createService() throws Exception {
    createLiabilityDocFlowPluginFactory = new CreateLiabilityDocFlowPluginFactory();
    confirmExecutionByDocDocFlowPluginFactory = new ConfirmExecutionByDocDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createLiabilityDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(confirmExecutionByDocDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createLiabilityDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(confirmExecutionByDocDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    createLiabilityDocFlowPluginFactory = null;
    confirmExecutionByDocDocFlowPluginFactory = null;
  }

}
