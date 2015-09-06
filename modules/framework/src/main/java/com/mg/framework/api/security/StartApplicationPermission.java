/*
 * StartApplicationPermission.java
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

package com.mg.framework.api.security;

import java.security.Permission;

/**
 * Разрешение на старт приложения
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class StartApplicationPermission extends Permission {

  public StartApplicationPermission(String name) {
    super(name);
  }

  /* (non-Javadoc)
   * @see java.security.Permission#implies(java.security.Permission)
   */
  @Override
  public boolean implies(Permission permission) {
    if (!(permission instanceof StartApplicationPermission))
      return false;

    StartApplicationPermission that = (StartApplicationPermission) permission;

    return getName().equals(that.getName());
  }

  /* (non-Javadoc)
   * @see java.security.Permission#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof StartApplicationPermission))
      return false;

    StartApplicationPermission that = (StartApplicationPermission) obj;

    return getName().equals(that.getName());
  }

  /* (non-Javadoc)
   * @see java.security.Permission#hashCode()
   */
  @Override
  public int hashCode() {
    return getName().hashCode();
  }

  /* (non-Javadoc)
   * @see java.security.Permission#getActions()
   */
  @Override
  public String getActions() {
    return "";
  }

}
