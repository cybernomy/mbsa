/*
 * ApplicationException.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.framework.support.Messages;

/**
 * @author Oleg V. Safonov
 * @version $Id: ApplicationException.java,v 1.6 2007/04/13 13:36:33 safonov Exp $
 */
@javax.ejb.ApplicationException
public class ApplicationException extends UnrecoverableException {
  private static final long serialVersionUID = -4467597673119425945L;

  public ApplicationException(String s, Throwable cause) {
    super(s, cause);
  }

  public ApplicationException(String s) {
    super(s);
  }

  public ApplicationException() {
    super();
  }

  public ApplicationException(ApplicationException cause) {
    super(cause);
  }

  public ApplicationException(Throwable cause) {
    super(Messages.getInstance().getMessage(Messages.INTERNAL_SOFTWARE_EXCEPTION, "Internal software exception"), cause); //$NON-NLS-1$
  }
}
