/*
 * DocumentDocFlowPluginService.java
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
package com.mg.merp.document.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.document.support.BAiCreateDocumentDocFlowPluginFactory;
import com.mg.merp.document.support.CreateDocOnComponentsDocFlowPluginFactory;
import com.mg.merp.document.support.CreateDocumentDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Документы"
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentDocFlowPluginService.java,v 1.3 2007/10/23 13:55:29 alikaev Exp $
 */
@Service(objectName = DocumentDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(DocumentDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class DocumentDocFlowPluginService extends ServiceMBeanSupport implements
    DocumentDocFlowPluginServiceMBean {
  private DocFlowPluginFactory createDocumentDocFlowPluginFactory;
  private DocFlowPluginFactory createDocumentBAiDocFlowPluginFactory;
  private DocFlowPluginFactory createDocOnComponentsDocFlowPluginFactory;

  protected void createService() throws Exception {
    createDocumentDocFlowPluginFactory = new CreateDocumentDocFlowPluginFactory();
    createDocumentBAiDocFlowPluginFactory = new BAiCreateDocumentDocFlowPluginFactory();
    createDocOnComponentsDocFlowPluginFactory = new CreateDocOnComponentsDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createDocumentBAiDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(createDocOnComponentsDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createDocumentDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createDocumentBAiDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(createDocOnComponentsDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    createDocumentDocFlowPluginFactory = null;
    createDocumentBAiDocFlowPluginFactory = null;
    createDocOnComponentsDocFlowPluginFactory = null;
  }

}
