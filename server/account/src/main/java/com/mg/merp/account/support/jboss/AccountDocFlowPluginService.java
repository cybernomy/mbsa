/*
 * AccountDocFlowPluginService.java
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
package com.mg.merp.account.support.jboss;

import com.mg.merp.account.support.CreateOperationDocFlowPluginFactory;
import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Бухгалтерия"
 *
 * @author Oleg V. Safonov
 * @version $Id: AccountDocFlowPluginService.java,v 1.1 2006/10/21 11:07:04 safonov Exp $
 */
@Service(objectName = AccountDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(AccountDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class AccountDocFlowPluginService extends ServiceMBeanSupport implements
    AccountDocFlowPluginServiceMBean {
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
