/*
 * ModuleLicenseSurcharge.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
 * @version $Id: ModuleLicenseSurcharge.java,v 1.2 2007/04/13 14:02:03 safonov Exp $
 */
public class ModuleLicenseSurcharge extends LicenseException {
  private static final long serialVersionUID = -2641016019317012600L;

  private String subsystemName;

  public ModuleLicenseSurcharge(String subsystemName) {
    super();
    this.subsystemName = subsystemName;
  }

  public String getSubsystemName() {
    return subsystemName;
  }
}
