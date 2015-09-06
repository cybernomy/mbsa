/*
 * AccountEntityInterceptorService.java
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

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.framework.service.jboss.EntityInterceptorManagerServiceMBean;
import com.mg.merp.account.support.EconomicOperEntityInterceptorImpl;
import com.mg.merp.account.support.EconomicSpecEntityInterceptorImpl;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса регистрации перехватчиков на сущности подсистемы "Бухгалтерия"
 *
 * @author Oleg V. Safonov
 * @version $Id: AccountEntityInterceptorService.java,v 1.1 2007/01/16 11:46:37 safonov Exp $
 */
@Service(objectName = AccountEntityInterceptorServiceMBean.SERVICE_NAME, name = "merp/account/AccountEntityInterceptorService")
@Management(AccountEntityInterceptorServiceMBean.class)
@Depends(EntityInterceptorManagerServiceMBean.SERVICE_NAME)
public class AccountEntityInterceptorService extends ServiceMBeanSupport
    implements AccountEntityInterceptorServiceMBean {
  private EntityInterceptor economicOperInterceptor = null;
  private EntityInterceptor economicSpecInterceptor = null;

  @Override
  protected void createService() throws Exception {
    economicOperInterceptor = new EconomicOperEntityInterceptorImpl();
    economicSpecInterceptor = new EconomicSpecEntityInterceptorImpl();
  }

  @Override
  protected void startService() throws Exception {
    EntityInterceptorManagerLocator.locate().registerPreUpdateInterceptor(economicOperInterceptor);
    EntityInterceptorManagerLocator.locate().registerPreRemoveInterceptor(economicOperInterceptor);
    EntityInterceptorManagerLocator.locate().registerPostPersistInterceptor(economicSpecInterceptor);
    EntityInterceptorManagerLocator.locate().registerPostUpdateInterceptor(economicSpecInterceptor);
    EntityInterceptorManagerLocator.locate().registerPostRemoveInterceptor(economicSpecInterceptor);
  }

  @Override
  protected void stopService() throws Exception {
    EntityInterceptorManagerLocator.locate().unregisterPreUpdateInterceptor(economicOperInterceptor);
    EntityInterceptorManagerLocator.locate().unregisterPreRemoveInterceptor(economicOperInterceptor);
    EntityInterceptorManagerLocator.locate().unregisterPostPersistInterceptor(economicSpecInterceptor);
    EntityInterceptorManagerLocator.locate().unregisterPostUpdateInterceptor(economicSpecInterceptor);
    EntityInterceptorManagerLocator.locate().unregisterPostRemoveInterceptor(economicSpecInterceptor);
  }

  @Override
  protected void destroyService() throws Exception {
    economicOperInterceptor = null;
    economicSpecInterceptor = null;
  }

}
