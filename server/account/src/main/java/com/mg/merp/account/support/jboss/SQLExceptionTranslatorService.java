/*
 * SQLExceptionTranslatorService.java
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
package com.mg.merp.account.support.jboss;

import com.mg.framework.api.jdbc.SQLExceptionTranslator;
import com.mg.framework.service.SQLExceptionTranslatorManagerLocator;
import com.mg.framework.service.jboss.DefaultSQLExceptionTranslatorServiceMBean;
import com.mg.merp.account.support.SQLExceptionTranslatorImpl;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса транслятора SQL ИС модуля бухгалтерия
 *
 * @author Artem V. Sharapov
 * @version $Id: SQLExceptionTranslatorService.java,v 1.1 2009/03/04 12:38:48 sharapov Exp $
 */
@Service(objectName = SQLExceptionTranslatorServiceMBean.SERVICE_NAME, name = "merp/account/SQLExceptionTranslatorService")
@Management(SQLExceptionTranslatorServiceMBean.class)
@Depends(DefaultSQLExceptionTranslatorServiceMBean.SERVICE_NAME)
public class SQLExceptionTranslatorService extends ServiceMBeanSupport implements SQLExceptionTranslatorServiceMBean {

  private SQLExceptionTranslator translator = new SQLExceptionTranslatorImpl();


  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#startService()
   */
  @Override
  protected void startService() throws Exception {
    super.startService();
    SQLExceptionTranslatorManagerLocator.locate().registerTranslator(translator);
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
    SQLExceptionTranslatorManagerLocator.locate().unregisterTranslator(translator);
    super.stopService();
  }

}