/*
 * MetadataCache.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.ApplicationException;

/**
 * @author Oleg V. Safonov
 */
public interface MetadataCache {
  public void invalidate(int classId) throws ApplicationException;

  public boolean isInvalidated(int classId, long timeStamp) throws ApplicationException;
}
