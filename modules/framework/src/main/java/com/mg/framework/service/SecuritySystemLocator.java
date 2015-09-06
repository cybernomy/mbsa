/*
 * SecuritySystemLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.security.SecuritySystem;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SecuritySystemLocator {
  private static volatile SecuritySystem instance = null;

  public static SecuritySystem locate() {
    if (instance == null) {
      try {
        instance = (SecuritySystem) ServerUtils.createMBeanProxy(SecuritySystem.class, "merp:service=SecuritySystemService");
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    }
    return instance;
  }
}
