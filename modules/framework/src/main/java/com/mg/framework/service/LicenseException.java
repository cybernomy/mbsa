/*
 * LicenseException.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

/**
 * @author Oleg V. Safonov
 * @version $Id: LicenseException.java,v 1.2 2007/04/13 14:01:34 safonov Exp $
 */
@javax.ejb.ApplicationException
public class LicenseException extends com.mg.framework.api.SecurityException {
  private static final long serialVersionUID = 671729530601555258L;

  public LicenseException() {
    super();
  }

  public LicenseException(String s) {
    super(s);
  }

  public LicenseException(String s, Throwable cause) {
    super(s, cause);
  }

}
