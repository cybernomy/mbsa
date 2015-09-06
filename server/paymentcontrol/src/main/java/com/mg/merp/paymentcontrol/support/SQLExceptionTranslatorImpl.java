/*
 * SQLExceptionTranslatorImpl.java
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
package com.mg.merp.paymentcontrol.support;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.generic.AbstractSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Транслятор SQL ИС модуля платежный календарь
 *
 * @author Artem V. Sharapov
 * @version $Id: SQLExceptionTranslatorImpl.java,v 1.1 2007/05/23 06:02:59 sharapov Exp $
 */
public class SQLExceptionTranslatorImpl extends AbstractSQLExceptionTranslator {

  private final static String DBMS_UNIQUE_INDEX = "IDX_PMC_PERIOD_UN_DATE"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractSQLExceptionTranslator#translate(java.sql.SQLException)
   */
  @Override
  public DataAccessException translate(SQLException sqlException) {
    String msg = null;
    String sqlMsg = sqlException.getMessage().toUpperCase();
    if (sqlMsg.indexOf(DBMS_UNIQUE_INDEX) > 0)
      msg = Messages.getInstance().getMessage(Messages.PMCPERIOD_DBMS_CROSS_PERIOD);

    if (msg != null)
      return new DataAccessException(msg, sqlException);
    else
      return null;
  }

}
