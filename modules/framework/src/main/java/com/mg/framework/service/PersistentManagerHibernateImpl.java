/*
 * PersistentManagerHibernateImpl.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.CreateException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FinderException;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.RemoveException;
import com.mg.framework.support.orm.CriteriaHibernateImpl;
import com.mg.framework.utils.ContextUtils;
import com.mg.framework.utils.ServerUtils;

import org.hibernate.CallbackException;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TypeMismatchException;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import java.io.Serializable;

import javax.naming.NamingException;

/**
 * Реализация менеджера перманентного хранилища на базе Hibernate
 *
 * @author Oleg V. Safonov
 * @version $Id: PersistentManagerHibernateImpl.java,v 1.11 2008/12/08 06:10:47 safonov Exp $
 */
public class PersistentManagerHibernateImpl implements PersistentManager, Serializable {
  /**
   * JNDI имя фабрики сессий
   */
  public static final String SESSION_FACTORY_NAME = "java:/hibernate/MERPSessionFactory";
  private static Logger log = ServerUtils.getLogger(PersistentManagerHibernateImpl.class);
  /**
   * экземпляр менеджера перманентного хранилища
   */
  private static volatile PersistentManager persistentManager = null;

  /**
   * получить экземпляр менеджера перманентного хранилища
   *
   * @return менеджер перманентного хранилища
   */
  public static PersistentManager getInstance() {
    if (persistentManager == null)
      persistentManager = new PersistentManagerHibernateImpl();
    return persistentManager;
  }

  /**
   * получить фабрику сессий библиотеки Hibernate
   *
   * @return фабрика сессий
   */
  public static SessionFactoryImplementor getFactory() {
    try {
      return ContextUtils.lookup(PersistentManagerHibernateImpl.SESSION_FACTORY_NAME, SessionFactoryImplementor.class);
    } catch (NamingException e) {
      throw new RuntimeException("Unable to locate SessionFactory in JNDI under name [" + PersistentManagerHibernateImpl.SESSION_FACTORY_NAME + "]", e);
    }
  }

  private ApplicationException convertCallbackExceptionToCreateException(CallbackException e) {
    Throwable cause = e.getCause();
    if (cause instanceof ApplicationException)
      return (ApplicationException) cause;
    else
      return new CreateException(e);
  }

  private ApplicationException convertCallbackExceptionToDatabaseException(CallbackException e) {
    Throwable cause = e.getCause();
    if (cause instanceof ApplicationException)
      return (ApplicationException) cause;
    else
      return new DataAccessException(e);
  }

  private ApplicationException convertCallbackExceptionToFinderException(CallbackException e) {
    Throwable cause = e.getCause();
    if (cause instanceof ApplicationException)
      return (ApplicationException) cause;
    else
      return new FinderException(e);
  }

  private ApplicationException convertCallbackExceptionToRemoveException(CallbackException e) {
    Throwable cause = e.getCause();
    if (cause instanceof ApplicationException)
      return (ApplicationException) cause;
    else
      return new RemoveException(e);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#make(java.lang.String, com.mg.framework.api.AttributeMap)
   */
  public PersistentObject make(String name, AttributeMap attributes) {
    try {
      PersistentObject result = (PersistentObject) ServerUtils.loadClass(name).newInstance();
      if (attributes != null)
        result.setAttributes(attributes);
      return result;
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new CreateException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#persist(com.mg.framework.api.orm.PersistentObject)
   */
  public void persist(PersistentObject object) {
    try {
      getCurrentSession().persist(object);
    } catch (PersistentObjectException e) {
      merge(object);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToCreateException(e);
    } catch (HibernateException e) {
      throw new CreateException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#merge(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public <T> T merge(T entity) {
    try {
      return (T) getCurrentSession().merge(entity);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToDatabaseException(e);
    } catch (HibernateException e) {
      throw new DataAccessException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#create(com.mg.framework.api.orm.PersistentObject)
   */
  public PersistentObject create(PersistentObject object) {
    try {
      Session session = getCurrentSession();
      //session.save(object);
      session.persist(object);
      return object;
    } catch (CallbackException e) {
      throw convertCallbackExceptionToCreateException(e);
    } catch (HibernateException e) {
      throw new CreateException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#find(java.lang.String, java.lang.Object)
   */
  public PersistentObject find(String name, Object primaryKey)
      throws FinderException {
    try {
      return (PersistentObject) getCurrentSession().get(ServerUtils.loadClass(name), (Serializable) primaryKey);
    } catch (TypeMismatchException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToFinderException(e);
    } catch (HibernateException e) {
      throw new FinderException(e);
    } catch (ClassNotFoundException e) {
      throw new FinderException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#store(com.mg.framework.api.orm.PersistentObject)
   */
  public PersistentObject store(PersistentObject object) {
    try {
      //session.update(object);
      return (PersistentObject) getCurrentSession().merge(object);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToDatabaseException(e);
    } catch (HibernateException e) {
      throw new DataAccessException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#remove(java.lang.String, java.lang.Object)
   */
  public void remove(String name, Object primaryKey) {
    try {
      Session session = getCurrentSession();
      session.delete(session.load(ServerUtils.loadClass(name), (Serializable) primaryKey));
    } catch (CallbackException e) {
      throw convertCallbackExceptionToRemoveException(e);
    } catch (HibernateException e) {
      throw new RemoveException(e);
    } catch (ClassNotFoundException e) {
      throw new RemoveException(e);
    }
  }

  /*
   *  (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#remove(com.mg.framework.api.orm.PersistentObject)
   */
  public void remove(PersistentObject entity) {
    try {
      getCurrentSession().delete(entity);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToRemoveException(e);
    } catch (HibernateException e) {
      throw new RemoveException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#flush()
   */
  public void flush() {
    Session s = getCurrentSession();
    try {
      s.flush();
    } catch (JDBCException e) {
      s.clear();
      throw SQLExceptionTranslatorManagerLocator.locate().translate(e.getSQLException());
    } catch (CallbackException e) {
      throw convertCallbackExceptionToDatabaseException(e);
    } catch (Exception e) {
      try {
        ServerUtils.getCurrentTransaction().setRollbackOnly();
      } catch (Exception ee) {
        //we do not want the subsequent exception to swallow the original one
        log.error("Unable to mark for rollback on Exception: ", ee);
      }
      if (e instanceof ApplicationException)
        throw (ApplicationException) e;
      else
        throw new DataAccessException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#clear()
   */
  public void clear() {
    getCurrentSession().clear();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#contains(java.lang.Object)
   */
  public boolean contains(Object entity) {
    try {
      return getCurrentSession().contains(entity);
    } catch (HibernateException e) {
      throw new DataAccessException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#find(java.lang.Class, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public <T> T find(Class<T> entityClass, Object primaryKey) {
    try {
      return (T) getCurrentSession().get(entityClass, (Serializable) primaryKey);
    } catch (TypeMismatchException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToFinderException(e);
    } catch (HibernateException e) {
      throw new FinderException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#getReference(java.lang.Class, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public <T> T getReference(Class<T> entityClass, Object primaryKey) {
    try {
      return (T) getCurrentSession().load(entityClass, (Serializable) primaryKey);
    } catch (TypeMismatchException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (ClassCastException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToFinderException(e);
    } catch (HibernateException e) {
      throw new FinderException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#refresh(com.mg.framework.api.orm.PersistentObject)
   */
  public void refresh(PersistentObject entity) {
    try {
      //FIXME не проверяем присутствие в сессии, иногда необходимо обновлять detach объекты, однако это не рекомендуется авторами Hibernate, что делать не совсем понятно
//			Session s = getCurrentSession();
//			if (!s.contains(entity))
//				throw new IllegalArgumentException("Entity not managed");
      getCurrentSession().refresh(entity);
    } catch (CallbackException e) {
      throw convertCallbackExceptionToDatabaseException(e);
    } catch (HibernateException e) {
      throw new DataAccessException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#createCriteria(java.lang.String)
   */
  public Criteria createCriteria(String entityName) {
    return new CriteriaHibernateImpl(getFactory().getCurrentSession().createCriteria(entityName));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.PersistentManager#getDelegate()
   */
  public Object getDelegate() {
    return getCurrentSession();
  }

  /**
   * получить текущую сессию
   *
   * @return текущая сессия
   * @throws IllegalStateException если текущая сессия не установлена
   */
  private Session getCurrentSession() {
    Session result = getFactory().getCurrentSession();
    if (result == null)
      throw new IllegalStateException("Unable to get current session");
    return result;
  }

}
