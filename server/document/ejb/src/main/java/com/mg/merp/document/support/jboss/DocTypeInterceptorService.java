/*
 * DocTypeInterceptorService.java
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

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.framework.service.jboss.EntityInterceptorManagerServiceMBean;
import com.mg.merp.document.support.DocTypeInterceptorImpl;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса перехватчика для обработки типов документов
 *
 * @author Oleg V. Safonov
 * @version $Id: DocTypeInterceptorService.java,v 1.2 2006/10/19 14:12:21 safonov Exp $
 */
@Service(objectName = DocTypeInterceptorServiceMBean.SERVICE_NAME)
@Management(DocTypeInterceptorServiceMBean.class)
@Depends(EntityInterceptorManagerServiceMBean.SERVICE_NAME)
public class DocTypeInterceptorService extends ServiceMBeanSupport implements
    DocTypeInterceptorServiceMBean {
  private EntityInterceptor interceptor = null;

  @Override
  protected void createService() throws Exception {
    interceptor = new DocTypeInterceptorImpl();
  }

  @Override
  protected void startService() throws Exception {
    EntityInterceptorManagerLocator.locate().registerPostPersistInterceptor(interceptor);
  }

  @Override
  protected void stopService() throws Exception {
    EntityInterceptorManagerLocator.locate().unregisterPostPersistInterceptor(interceptor);
  }

  @Override
  protected void destroyService() throws Exception {
    interceptor = null;
  }

}
