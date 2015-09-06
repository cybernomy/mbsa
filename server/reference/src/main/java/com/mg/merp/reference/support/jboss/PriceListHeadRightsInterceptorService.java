/*
 * PriceListHeadRightsInterceptorService.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.jboss;

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.framework.service.jboss.EntityInterceptorManagerServiceMBean;
import com.mg.merp.reference.support.PriceListHeadInterceptorImpl;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса перехватчика для обработки прайс-листов
 *
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListHeadRightsInterceptorService.java,v 1.1 2008/05/13 09:54:17 alikaev Exp $
 */
@Service(objectName = PriceListHeadRightsInterceptorServiceMBean.SERVICE_NAME)
@Management(PriceListHeadRightsInterceptorServiceMBean.class)
@Depends(EntityInterceptorManagerServiceMBean.SERVICE_NAME)
public class PriceListHeadRightsInterceptorService extends ServiceMBeanSupport implements PriceListHeadRightsInterceptorServiceMBean {

  private EntityInterceptor interceptor = null;

  /*
   * (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#createService()
   */
  @Override
  protected void createService() throws Exception {
    interceptor = new PriceListHeadInterceptorImpl();
  }

  /*
   * (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#destroyService()
   */
  @Override
  protected void destroyService() throws Exception {
    interceptor = null;
  }

  /*
   * (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#startService()
   */
  @Override
  protected void startService() throws Exception {
    EntityInterceptorManagerLocator.locate().registerPostPersistInterceptor(interceptor);
  }

  /*
   * (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
    EntityInterceptorManagerLocator.locate().unregisterPostPersistInterceptor(interceptor);
  }

}
