/*
 * DocFlowPluginService.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.docflow.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.BAiDocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.FinishDocFlowPluginFactory;
import com.mg.merp.docflow.support.SignatureDocFlowPluginFactory;

import org.jboss.system.ServiceMBeanSupport;

/**
 * Сервис регистрации фабрик этапов ДО реализованных в данном модуле
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginService.java,v 1.4 2008/07/31 14:22:28 safonov Exp $
 */
public class DocFlowPluginService extends ServiceMBeanSupport implements DocFlowPluginServiceMBean {
  private DocFlowPluginFactory signaturePluginFactory;
  private DocFlowPluginFactory baiPluginFactory;
  private DocFlowPluginFactory finishPluginFactory;

  protected void createService() throws Exception {
    signaturePluginFactory = new SignatureDocFlowPluginFactory();
    baiPluginFactory = new BAiDocFlowPluginFactory();
    finishPluginFactory = new FinishDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(signaturePluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(baiPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(finishPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(signaturePluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(baiPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(finishPluginFactory);
  }

  protected void destroyService() throws Exception {
    signaturePluginFactory = null;
    baiPluginFactory = null;
    finishPluginFactory = null;
  }

}
