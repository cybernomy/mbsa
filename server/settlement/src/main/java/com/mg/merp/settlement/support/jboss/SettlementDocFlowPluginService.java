/*
 * SettlementDocFlowPluginService.java
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
package com.mg.merp.settlement.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.settlement.support.DoPlanContractorCardDocFlowPluginFactory;
import com.mg.merp.settlement.support.ProcessContractorCardDocFlowPluginFactory;
import com.mg.merp.settlement.support.UndoPlanContractorCardDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Расчеты с партнерами"
 *
 * @author Artem V. Sharapov
 * @version $Id: SettlementDocFlowPluginService.java,v 1.1 2007/03/19 15:05:29 sharapov Exp $
 */
@Service(objectName = SettlementDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(SettlementDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class SettlementDocFlowPluginService extends ServiceMBeanSupport
    implements SettlementDocFlowPluginServiceMBean {

  private DocFlowPluginFactory doPlanContractorCardDocFlowPluginFactory;
  private DocFlowPluginFactory processContractorCardDocFlowPluginFactory;
  private DocFlowPluginFactory undoPlanContractorCardDocFlowPluginFactory;


  protected void createService() throws Exception {
    doPlanContractorCardDocFlowPluginFactory = new DoPlanContractorCardDocFlowPluginFactory();
    processContractorCardDocFlowPluginFactory = new ProcessContractorCardDocFlowPluginFactory();
    undoPlanContractorCardDocFlowPluginFactory = new UndoPlanContractorCardDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(doPlanContractorCardDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(processContractorCardDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(undoPlanContractorCardDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(doPlanContractorCardDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(processContractorCardDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(undoPlanContractorCardDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    doPlanContractorCardDocFlowPluginFactory = null;
    processContractorCardDocFlowPluginFactory = null;
    undoPlanContractorCardDocFlowPluginFactory = null;
  }

}
