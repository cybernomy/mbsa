/*
 * FacturaDocFlowPluginService.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.factura.support.jboss;

import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docflow.support.jboss.DocFlowPluginFactoryServiceMBean;
import com.mg.merp.factura.support.RegisterFacturaDocFlowPluginFactory;
import com.mg.merp.factura.support.RegisterInBuyBookDocFlowPluginFactory;
import com.mg.merp.factura.support.RegisterInSaleBookDocFlowPluginFactory;
import com.mg.merp.factura.support.RegisterPayDateDocFlowPluginFactory;
import com.mg.merp.factura.support.RegisterStockDateDocFlowPluginFactory;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса дополнительных модулей подсистемы "Счета-фактуры"
 *
 * @author Artem V. Sharapov
 * @version $Id: FacturaDocFlowPluginService.java,v 1.1 2009/03/16 14:30:34 sharapov Exp $
 */
@Service(objectName = FacturaDocFlowPluginServiceMBean.SERVICE_NAME)
@Management(FacturaDocFlowPluginServiceMBean.class)
@Depends(DocFlowPluginFactoryServiceMBean.SERVICE_NAME)
public class FacturaDocFlowPluginService extends ServiceMBeanSupport implements FacturaDocFlowPluginServiceMBean {

  private DocFlowPluginFactory registerFacturaDocFlowPluginFactory;
  private DocFlowPluginFactory registerStockDateDocFlowPluginFactory;
  private DocFlowPluginFactory registerPayDateDocFlowPluginFactory;
  private DocFlowPluginFactory registerInBuyBookDocFlowPluginFactory;
  private DocFlowPluginFactory registerInSaleBookDocFlowPluginFactory;


  protected void createService() throws Exception {
    registerFacturaDocFlowPluginFactory = new RegisterFacturaDocFlowPluginFactory();
    registerStockDateDocFlowPluginFactory = new RegisterStockDateDocFlowPluginFactory();
    registerPayDateDocFlowPluginFactory = new RegisterPayDateDocFlowPluginFactory();
    registerInBuyBookDocFlowPluginFactory = new RegisterInBuyBookDocFlowPluginFactory();
    registerInSaleBookDocFlowPluginFactory = new RegisterInSaleBookDocFlowPluginFactory();
  }

  protected void startService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(registerFacturaDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(registerStockDateDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(registerPayDateDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(registerInBuyBookDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().registerPluginFactory(registerInSaleBookDocFlowPluginFactory);
  }

  protected void stopService() throws Exception {
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(registerFacturaDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(registerStockDateDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(registerPayDateDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(registerInBuyBookDocFlowPluginFactory);
    DocFlowPluginFactoryManagerServiceLocator.locate().unregisterPluginFactory(registerInSaleBookDocFlowPluginFactory);
  }

  protected void destroyService() throws Exception {
    registerFacturaDocFlowPluginFactory = null;
    registerStockDateDocFlowPluginFactory = null;
    registerPayDateDocFlowPluginFactory = null;
    registerInBuyBookDocFlowPluginFactory = null;
    registerInSaleBookDocFlowPluginFactory = null;
  }

}
