/*
 * FinanceDocFlowPluginService.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.finance.support.CreateOperationDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Финансы"
 *
 * @author Oleg V. Safonov
 * @version $Id: FinanceDocFlowPluginService.java,v 1.1 2006/10/21 10:59:21 safonov Exp $
 */
@Service(objectName = FinanceDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(FinanceDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class FinanceDocFlowPluginService extends ServiceMBeanSupport implements
    FinanceDocFlowPluginServiceMBean {
  private DocFlowPluginFactory createOperationDocFlowPluginFactory;

  protected void createService() throws Exception {
    createOperationDocFlowPluginFactory = new CreateOperationDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createOperationDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createOperationDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    createOperationDocFlowPluginFactory = null;
  }

}
