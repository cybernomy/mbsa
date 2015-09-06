/*
 * MetadataCacheLocator.java
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
import com.mg.framework.api.metadata.MetadataCache;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: MetadataCacheLocator.java,v 1.2 2006/01/24 14:00:55 safonov Exp $
 */
public class MetadataCacheLocator {
  private static volatile MetadataCache instance = null;

  public static MetadataCache locate() throws ApplicationException {
    if (instance == null)
      try {
        instance = (MetadataCache) ServerUtils.createMBeanProxy(MetadataCache.class, "merp:service=MetadataCacheService");
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }
}
