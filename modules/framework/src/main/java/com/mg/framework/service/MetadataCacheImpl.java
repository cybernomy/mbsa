/*
 * MetadataCacheImpl.java
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

import org.jboss.cache.CacheException;
import org.jboss.cache.TreeCache;

/**
 * @author Oleg V. Safonov
 * @version $Id: MetadataCacheImpl.java,v 1.2 2006/01/24 14:00:55 safonov Exp $
 */
public class MetadataCacheImpl implements MetadataCache {
  private String CACHE_FQN = "merp/metadata";
  private TreeCache cache;

  public MetadataCacheImpl() {
    super();
  }

  public void create() throws Exception {
    cache = new TreeCache();
    cache.createService();
    cache.setCacheMode(TreeCache.LOCAL);
  }

  public void destroy() {
    cache.destroyService();
    cache = null;
  }

  public void start() throws Exception {
    cache.startService();
  }

  public void stop() throws Exception {
    cache.startService();
  }

  public void invalidate(int classId) throws ApplicationException {
    try {
      cache.put(CACHE_FQN, new Integer(classId), new Long(System.currentTimeMillis()));
    } catch (CacheException e) {
      throw new ApplicationException(e);
    }
  }

  public boolean isInvalidated(int classId, long timeStamp) throws ApplicationException {
    boolean result = false;
    try {
      Long ts = (Long) cache.get(CACHE_FQN, new Integer(classId));
      result = (ts != null) && (timeStamp < ts.longValue());
    } catch (CacheException e) {
      throw new ApplicationException(e);
    }
    return result;
  }
    
    /*private class CacheItem {
        public boolean invalidate;
        public long timeStamp;
        
        CacheItem(boolean ) {
            
        }
    }*/
}
