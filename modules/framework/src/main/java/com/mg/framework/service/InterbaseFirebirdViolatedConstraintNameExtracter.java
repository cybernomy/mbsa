/*
 * InterbaseFirebirdViolatedConstraintNameExtracter.java
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
package com.mg.framework.service;

import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.exception.TemplatedViolatedConstraintNameExtracter;

import java.sql.SQLException;

/**
 * Класс извлечения имен конструкций из специфичных ИС СУБД Interbase и Firebird
 *
 * @author Oleg V. Safonov
 * @version $Id: InterbaseFirebirdViolatedConstraintNameExtracter.java,v 1.1 2006/12/15 10:02:51
 *          safonov Exp $
 */
public class InterbaseFirebirdViolatedConstraintNameExtracter extends
    TemplatedViolatedConstraintNameExtracter {

  /* (non-Javadoc)
   * @see org.hibernate.exception.ViolatedConstraintNameExtracter#extractConstraintName(java.sql.SQLException)
   */
  public String extractConstraintName(SQLException sqle) {
    int errorCode = JDBCExceptionHelper.extractErrorCode(sqle);
    if (errorCode == 335544665)
      return extractUsingTemplate("violation of PRIMARY or UNIQUE KEY constraint: ", "\n", sqle.getMessage());
    else if (errorCode == 335544466)
      return extractUsingTemplate("violation of FOREIGN KEY constraint: ", "\n", sqle.getMessage());
    else {
      return null;
    }
  }

}
