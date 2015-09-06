/*
 * ContractDocFlowPluginService.java
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
package com.mg.merp.contract.support.jboss;

import com.mg.merp.contract.support.CreateFactContractClausesDocFlowPluginFactory;
import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Контракты"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractDocFlowPluginService.java,v 1.1 2007/03/07 12:26:05 sharapov Exp $
 */
@Service(objectName = ContractDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(ContractDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class ContractDocFlowPluginService extends ServiceMBeanSupport implements
    ContractDocFlowPluginServiceMBean {

  private DocFlowPluginFactory createFactContractClausesDocFlowPluginFactory;

  protected void createService() throws Exception {
    createFactContractClausesDocFlowPluginFactory = new CreateFactContractClausesDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createFactContractClausesDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createFactContractClausesDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    createFactContractClausesDocFlowPluginFactory = null;
  }

}
