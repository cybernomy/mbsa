/*
 * SQLExceptionTranslatorImpl.java
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
package com.mg.merp.account.support;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.generic.AbstractSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Транслятор SQL ИС модуля бухгалтерия
 *
 * @author Artem V. Sharapov
 * @version $Id: SQLExceptionTranslatorImpl.java,v 1.1 2009/03/04 12:38:14 sharapov Exp $
 */
public class SQLExceptionTranslatorImpl extends AbstractSQLExceptionTranslator {

  private final static String DBMS_CONTRACTOR_FROM_EMPTY = "E_CONTRACTOR_FROM_EMPTY"; //$NON-NLS-1$
  private final static String DBMS_CONTRACTOR_TO_EMPTY = "E_CONTRACTOR_TO_EMPTY"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractSQLExceptionTranslator#translate(java.sql.SQLException)
   */
  @Override
  public DataAccessException translate(SQLException sqlException) {
    String msg = null;
    String sqlMsg = sqlException.getMessage().toUpperCase();
    if (sqlMsg.indexOf(DBMS_CONTRACTOR_FROM_EMPTY) > 0)
      msg = Messages.getInstance().getMessage(Messages.CONTRACTOR_FROM_EMPTY);
    if (sqlMsg.indexOf(DBMS_CONTRACTOR_TO_EMPTY) > 0)
      msg = Messages.getInstance().getMessage(Messages.CONTRACTOR_TO_EMPTY);

    if (msg != null)
      return new DataAccessException(msg, sqlException);
    else
      return null;
  }

}