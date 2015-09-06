/*
 * SQLExceptionTranslatorManagerService.java
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.jdbc.SQLExceptionTranslator;
import com.mg.framework.api.jdbc.SQLExceptionTranslatorManager;
import com.mg.framework.service.SQLExceptionTranslatorManagerImpl;

import org.jboss.system.ServiceMBeanSupport;

import java.sql.SQLException;

/**
 * Реализация сервиса менеджера трансляторов SQL ИС в пользовательские исключения
 *
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManagerService.java,v 1.1 2006/11/17 14:31:44 safonov Exp $
 */
public class SQLExceptionTranslatorManagerService extends ServiceMBeanSupport
    implements SQLExceptionTranslatorManagerServiceMBean {
  private SQLExceptionTranslatorManager delegate = new SQLExceptionTranslatorManagerImpl();

  /* (non-Javadoc)
   * @see com.mg.framework.api.SQLExceptionTranslatorManager#registerTranslator(com.mg.framework.api.SQLExceptionTranslator)
   */
  public void registerTranslator(SQLExceptionTranslator translator) {
    delegate.registerTranslator(translator);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.SQLExceptionTranslatorManager#unregisterTranslator(com.mg.framework.api.SQLExceptionTranslator)
   */
  public void unregisterTranslator(SQLExceptionTranslator translator) {
    delegate.unregisterTranslator(translator);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.SQLExceptionTranslatorManager#translate(java.sql.SQLException)
   */
  public DataAccessException translate(SQLException sqlException) {
    return delegate.translate(sqlException);
  }

}
