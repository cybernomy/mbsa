/*
 * HibernateTreeCacheProvider.java
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
package com.mg.framework.service;

import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.jboss.cache.TreeCache;
import org.jboss.cache.TreeCacheMBean;
import org.jboss.logging.Logger;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.mx.util.MBeanServerLocator;

import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * A Hibernate CacheProvider implementation which knows how to obtain a deployed JBossCache via its
 * JMX ObjectName.
 *
 * @author Oleg V. Safonov
 * @version $Id: HibernateTreeCacheProvider.java,v 1.1 2006/12/27 13:39:34 safonov Exp $
 */
public class HibernateTreeCacheProvider implements CacheProvider {
  public static final String OBJECT_NAME_PROP = "hibernate.treecache.objectName";
  public static final String DEFAULT_OBJECT_NAME = "jboss.cache:service=HibernateTreeCache";
  private static final Logger log = Logger.getLogger(HibernateTreeCacheProvider.class);
  private TreeCache deployedTreeCache;

  public void start(Properties properties) throws CacheException {
    // Determine the TreeCache MBean ObjectName.
    String configObjectName = properties.getProperty(OBJECT_NAME_PROP, DEFAULT_OBJECT_NAME);
    ObjectName objectName;
    try {
      objectName = new ObjectName(configObjectName);
    } catch (Throwable t) {
      throw new CacheException("Malformed TreeCache ObjectName");
    }

    TreeCacheMBean mbean;
    try {
      MBeanServer server = MBeanServerLocator.locateJBoss();
      mbean = (TreeCacheMBean) MBeanProxyExt.create(TreeCacheMBean.class, objectName, server);
    } catch (Throwable t) {
      log.warn("Unable to locate TreeCache MBean under object name [" + configObjectName + "]", t);
      throw new CacheException("Unable to locate TreeCache MBean under object name [" + configObjectName + "]");
    }

    deployedTreeCache = mbean.getInstance();
  }

  public void stop() {
    deployedTreeCache = null;
  }

  public boolean isMinimalPutsEnabledByDefault() {
    return true;
  }

  /**
   * Called by Hibernate in order to build the given named cache "region".
   *
   * @param name       The cache "region" name.
   * @param properties The configuration properties.
   * @return The constructed Cache wrapper around the jndi-deployed TreeCache.
   * @throws CacheException Generally indicates a problem locating the TreeCache.
   */
  public Cache buildCache(String name, Properties properties) throws CacheException {
    return new org.hibernate.cache.OptimisticTreeCache(deployedTreeCache, name);
  }

  public long nextTimestamp() {
    return System.currentTimeMillis() / 100;
  }

}
