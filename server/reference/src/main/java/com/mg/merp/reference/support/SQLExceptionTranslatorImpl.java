/*
 * SQLExceptionTranslatorImpl.java
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
package com.mg.merp.reference.support;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.generic.AbstractSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Транслятор SQL ИС модуля справочники
 *
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorImpl.java,v 1.2 2007/11/08 12:07:50 sharapov Exp $
 */
public class SQLExceptionTranslatorImpl extends AbstractSQLExceptionTranslator {
  private final static String DBMS_CATALOG_TO_FOLDER = "CATALOG_TO_FOLDER"; //$NON-NLS-1$
  private final static String DBMS_CATALOGPRICE_INACTION_CUR = "IDX_CATALOGPRICE_INACTION_CUR"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractSQLExceptionTranslator#translate(java.sql.SQLException)
   */
  @Override
  public DataAccessException translate(SQLException sqlException) {
    String msg = null;
    String sqlMsg = sqlException.getMessage().toUpperCase();
    if (sqlMsg.indexOf(DBMS_CATALOG_TO_FOLDER) > 0)
      msg = Messages.getInstance().getMessage(Messages.DBMS_CATALOG_TO_FOLDER);
    if (sqlMsg.indexOf(DBMS_CATALOGPRICE_INACTION_CUR) > 0)
      msg = Messages.getInstance().getMessage(Messages.DBMS_CATALOGPRICE_INACTION_CUR);

    if (msg != null)
      return new DataAccessException(msg, sqlException);
    else
      return null;
  }

}
