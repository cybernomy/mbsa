/*
 * BPMManagerLocator.java
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
package com.mg.merp.bpm.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.bpm.BPMManager;

/**
 * @author Oleg V. Safonov
 * @version $Id: BPMManagerLocator.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class BPMManagerLocator {
  private static volatile BPMManager instance = null;

  /**
   * поиск сервиса
   *
   * @return сервис
   */
  public static BPMManager locate() {
    if (instance == null)
      try {
        instance = (BPMManager) ServerUtils.createMBeanProxy(BPMManager.class, "merp:service=BPMManagerServiceService"); //$NON-NLS-1$
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }

}
