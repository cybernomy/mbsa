/*
 * BusinessObjectFactory.java
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
package com.mg.framework.utils;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;

import org.jboss.cache.TreeCache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.naming.NamingException;

/**
 * @author Oleg V. Safonov
 * @version $Id: BusinessObjectFactory.java,v 1.8 2007/04/02 13:43:12 safonov Exp $
 * @deprecated
 */
@Deprecated
public class BusinessObjectFactory {
  private static BusinessObjectFactory instance = null;
  private TreeCache localServiceCache;
  private boolean supportCache = false;

  BusinessObjectFactory() {
    try {
      if (getSupportCache()) {
        localServiceCache = new TreeCache();
        localServiceCache.createService();
        localServiceCache.setCacheMode(TreeCache.LOCAL);
        localServiceCache.startService();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized static BusinessObjectFactory getInstance() {
    if (instance == null)
      instance = new BusinessObjectFactory();
    return instance;
  }

  private boolean getSupportCache() {
    return supportCache;
  }

  public BusinessObjectService getLocalService(String name) {
    BusinessObjectService result = null;
    try {
      if (getSupportCache()) {
        LocalServiceCacheItem item = (LocalServiceCacheItem) localServiceCache.get("/merp/localservice", name);
        if (item == null) {
          item = new LocalServiceCacheItem();
          String contextName;
          if (name.startsWith("ejb/"))
            contextName = "java:comp/env/" + name;
          else
            contextName = name;
          item.home = (javax.ejb.EJBLocalHome) ServerUtils.getContext().lookup(contextName);
          item.ejbCreateMethod = item.home.getClass().getMethod("create", new Class[0]);
          localServiceCache.put("/merp/localservice", name, item);
        }
        result = (BusinessObjectService) item.ejbCreateMethod.invoke(item.home, new Object[0]);
      } else {
        String contextName;
        if (name.startsWith("ejb/"))
          contextName = "java:comp/env/" + name;
        else
          contextName = name;
//				javax.ejb.EJBLocalHome home = (javax.ejb.EJBLocalHome) ServerUtils.getContext().lookup(contextName);
//				Method ejbCreateMethod = home.getClass().getMethod("create", new Class[0]);
//				result = (BusinessObjectService) ejbCreateMethod.invoke(home, new Object[0]);
        result = (BusinessObjectService) ServerUtils.getContext().lookup(contextName);
      }
    } catch (NamingException e) {
      //TODO i18n
      throw new ApplicationException(String.format(ServerUtils.getUserLocale(), "Сервис %s не поддерживается", name), e);
    } catch (InvocationTargetException e) {
      throw new ApplicationException(e.getTargetException());
    } catch (Exception e) {
      throw new ApplicationException(e);
    }
    return result;
  }

  private class LocalServiceCacheItem {
    javax.ejb.EJBLocalHome home;
    Method ejbCreateMethod;
  }
}
