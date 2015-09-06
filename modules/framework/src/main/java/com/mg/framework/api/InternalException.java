/*
 * InternalException.java
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
package com.mg.framework.api;

/**
 * @author Oleg V. Safonov $Id: InternalException.java,v 1.2 2006/09/28 12:24:12 safonov Exp $
 */
@javax.ejb.ApplicationException
public class InternalException extends ApplicationException {
  public InternalException(String s, Throwable cause) {
    super(s, cause);
  }

  public InternalException(String s) {
    super(s);
  }

  public InternalException() {
    super();
  }

  public InternalException(Throwable cause) {
    super(cause);
  }
}
