/*
 * InvalidUserNameOrPassword.java
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
package com.mg.framework.api.security;

import com.mg.framework.api.ApplicationException;

/**
 * Invalid user name or password exception
 *
 * @author Oleg V. Safonov
 * @version $Id: InvalidUserNameOrPassword.java,v 1.2 2006/06/26 12:02:09 safonov Exp $
 */
@javax.ejb.ApplicationException
public class InvalidUserNameOrPassword extends ApplicationException {
  public InvalidUserNameOrPassword() {
    super();
  }
}
