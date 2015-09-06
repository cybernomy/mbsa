/*
 * ManufactureDocFlowPluginService.java
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
package com.mg.merp.manufacture.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.manufacture.support.PerformBackflushDocFlowPluginFactory;
import com.mg.merp.manufacture.support.PerformScrapDocFlowPluginFactory;
import com.mg.merp.manufacture.support.PostToWorkInProgressDocFlowPluginFactory;
import com.mg.merp.manufacture.support.UpdateJobFromOutputDocumentDocFlowPluginFactory;
import com.mg.merp.manufacture.support.UpdateJobFromScrapDocumentDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * @author Oleg V. Safonov
 * @version $Id: ManufactureDocFlowPluginService.java,v 1.1 2007/08/06 12:44:11 safonov Exp $
 */
@Service(objectName = ManufactureDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(ManufactureDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class ManufactureDocFlowPluginService extends ServiceMBeanSupport
    implements ManufactureDocFlowPluginServiceMBean {
  private DocFlowPluginFactory updateJobFromOutputDocumentDocFlowPluginFactory;
  private DocFlowPluginFactory updateJobFromScrapDocumentDocFlowPluginFactory;
  private DocFlowPluginFactory performBackflushDocFlowPluginFactory;
  private DocFlowPluginFactory postToWorkInProgressDocFlowPluginFactory;
  private DocFlowPluginFactory performScrapDocFlowPluginFactory;

  @Override
  protected void createService() throws Exception {
    updateJobFromOutputDocumentDocFlowPluginFactory = new UpdateJobFromOutputDocumentDocFlowPluginFactory();
    updateJobFromScrapDocumentDocFlowPluginFactory = new UpdateJobFromScrapDocumentDocFlowPluginFactory();
    performBackflushDocFlowPluginFactory = new PerformBackflushDocFlowPluginFactory();
    postToWorkInProgressDocFlowPluginFactory = new PostToWorkInProgressDocFlowPluginFactory();
    performScrapDocFlowPluginFactory = new PerformScrapDocFlowPluginFactory();
  }

  @Override
  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(updateJobFromOutputDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(performBackflushDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(postToWorkInProgressDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(updateJobFromScrapDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(performScrapDocFlowPluginFactory);
  }

  @Override
  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(updateJobFromOutputDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(performBackflushDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(postToWorkInProgressDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(updateJobFromScrapDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(performScrapDocFlowPluginFactory);
  }

  @Override
  protected void destroyService() throws Exception {
    updateJobFromOutputDocumentDocFlowPluginFactory = null;
    performBackflushDocFlowPluginFactory = null;
    postToWorkInProgressDocFlowPluginFactory = null;
    updateJobFromScrapDocumentDocFlowPluginFactory = null;
    performScrapDocFlowPluginFactory = null;
  }

}
