/*
 * Principal.java
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
package com.mg.merp.security;

import net.sf.jguard.core.principals.RolePrincipal;

/**
 * Принципал представляющий понятие роль
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class Principal extends RolePrincipal {

  public Principal() {
    super();
  }

  public Principal(String name) {
    super(name);
  }

  public Principal(String name, String applicationName) {
    super(name, applicationName);
  }

}
