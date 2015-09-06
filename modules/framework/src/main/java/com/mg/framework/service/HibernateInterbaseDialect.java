/*
 * HibernateInterbaseDialect.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.service;

import org.hibernate.dialect.InterbaseDialect;
import org.hibernate.exception.ViolatedConstraintNameExtracter;

/**
 * Диалект СУБД Interbase
 *
 * @author Oleg V. Safonov
 * @version $Id: HibernateInterbaseDialect.java,v 1.2 2006/12/15 10:02:51 safonov Exp $
 */
public class HibernateInterbaseDialect extends InterbaseDialect {
  private static ViolatedConstraintNameExtracter EXTRACTER = new InterbaseFirebirdViolatedConstraintNameExtracter();

  public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
    return EXTRACTER;
  }

  public String getForUpdateString() {
    return " for update"; //fix unsupported SQL
  }

}
