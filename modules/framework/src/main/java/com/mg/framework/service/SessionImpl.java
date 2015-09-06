/*
 * SessionImpl.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Session;
import com.mg.framework.api.SystemTenant;
import com.mg.framework.api.WorkingConnection;
import com.mg.framework.utils.ServerUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация сессии
 *
 * @author Oleg V. Safonov
 * @version $Id: SessionImpl.java,v 1.9 2008/08/28 13:15:27 safonov Exp $
 */
public class SessionImpl
    implements Session, Serializable {
  private static int handleCount = 0;
  private int handle;
  @Deprecated
  private int nativeImpl = 0;
  private WorkingConnection connection;
  private Map<AttributeScopeType, Map<String, Object>> attrMap = new HashMap<AttributeScopeType, Map<String, Object>>();

  /**
   *
   */
  public SessionImpl(WorkingConnection connection) {
    super();
    //initialize attribute maps
    for (AttributeScopeType scopeType : AttributeScopeType.values())
      attrMap.put(scopeType, new HashMap<String, Object>());
    this.connection = connection;
    createHandle();
//		SessionControlImpl.getSingleton().registerSession(this);
  }

  public void destroy() {
  }

  public void ping() throws ApplicationException {
    ((WorkingConnectionImpl) connection).ping();
  }

  private synchronized void createHandle() {
    handle = ++handleCount;
  }

  public int handle() {
    return handle;
  }

  @Deprecated
  public int getNativeImpl() {
    return nativeImpl;
  }

  public int getHandle() {
    return handle;
  }

  public void beforeCompletion() {
  }

  public WorkingConnection getWorkingConnection() {
    return connection;
  }

  public void setWorkingConnection(WorkingConnection connection) {
    this.connection = connection;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#setAttribute(java.lang.String, java.lang.Object, com.mg.framework.api.Session.AttributeScopeType)
   */
  public void setAttribute(String key, Object value, AttributeScopeType scope) {
    attrMap.get(scope).put(key, value);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#setAttribute(java.lang.String, java.lang.Object)
   */
  public void setAttribute(String key, Object value) {
    attrMap.get(AttributeScopeType.APPLICATION).put(key, value);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#getAttribute(java.lang.String)
   */
  public Object getAttribute(String key) {
    //поиск по всем областям видимости
    for (Map<String, Object> value : attrMap.values()) {
      Object result = value.get(key);
      if (result != null)
        return result;
    }
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#removeAttribute(java.lang.String)
   */
  public void removeAttribute(String key) {
    //поиск по всем областям видимости
    for (Map<String, Object> value : attrMap.values())
      value.remove(key);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#getSystemTenant()
   */
  public SystemTenant getSystemTenant() {
    //TODO необходимо заполнять текущим мандантом в который вошел пользователь
    return (SystemTenant) ServerUtils.getPersistentManager().find("com.mg.merp.core.model.SysClient", 1);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#stopApplication()
   */
  public void stopApplication() {
    //do nothing
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.Session#isInteractive()
   */
  public boolean isInteractive() {
    return false;
  }

}
