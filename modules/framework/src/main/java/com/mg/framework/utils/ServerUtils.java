/*
 * ServerUtils.java
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
package com.mg.framework.utils;

import com.mg.framework.api.Logger;
import com.mg.framework.api.Session;
import com.mg.framework.api.SystemAuditEvent;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.service.SecuritySystemLocator;
import com.mg.framework.service.SessionControlImpl;
import com.mg.framework.service.WorkingConnectionImpl;

import org.jboss.ejb3.BaseSessionContext;
import org.jboss.ejb3.ProxyFactoryHelper;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.system.server.ServerConfigLocator;

import java.io.File;
import java.net.URL;
import java.util.Locale;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.management.MalformedObjectNameException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

/**
 * Серверные утилиты общего назначения
 *
 * @author Oleg V. Safonov
 * @version $Id: ServerUtils.java,v 1.29 2008/12/08 06:02:01 safonov Exp $
 */
public class ServerUtils {
  /**
   * Источник данных системы
   */
  public static String MBSA_DATASOURCE_NAME = "java:/MERPBackboneDS";
  /**
   * Расположение дополнительных ресурсов системы в среде сервера приложения
   */
  public static String MBSA_CUSTOM_LOCATION = "";
  private static Logger logger = getLogger(ServerUtils.class);

  /**
   * получить контекст именований JNDI
   *
   * @return контекст
   * @throws NamingException при ошибке создания
   */
  public static Context getContext() throws NamingException {
    return new InitialContext();
  }

  /**
   * получить JNDI имя EJB на основании контекста
   *
   * @param sessionContext контекст
   * @return имя EJB
   */
  public static String getJNDIName(SessionContext sessionContext) {
    return ProxyFactoryHelper.getLocalJndiName(((BaseSessionContext) sessionContext).getContainer());
  }

  /**
   * получить текущую сессию
   *
   * @return текущая сессия
   */
  public static com.mg.framework.api.Session getCurrentSession() {
    return SessionControlImpl.getSingleton().getCurrentSession();
  }

  /**
   * получить локаль текущего пользователя
   *
   * @return текущая локаль
   */
  public static Locale getUserLocale() {
    com.mg.framework.api.Session session = getCurrentSession();
    if (session == null)
      return Locale.getDefault();
    else
      return session.getWorkingConnection().getUserProfile().getLocale();
  }

  /**
   * получить текущее рабочее соединение
   *
   * @return рабочее соединение или <code>null</code> если нет текущей сессии
   */
  public static com.mg.framework.api.WorkingConnection getWorkingConnection() {
    //check current session, maybe already initialized
    com.mg.framework.api.Session session = ServerUtils.getCurrentSession();
    return session == null ? null : session.getWorkingConnection();
  }

  /**
   * получить профиль текущего пользователя
   *
   * @return профиль текущего пользователя или <code>null</code> если нет текущей сессии
   */
  public static com.mg.framework.api.UserProfile getUserProfile() {
    com.mg.framework.api.WorkingConnection wc = getWorkingConnection();
    return wc == null ? null : wc.getUserProfile();
  }

  /**
   * создать рабочее соединение
   *
   * @return рабочее соединение
   */
  public static com.mg.framework.api.WorkingConnection createWorkingConnection(String sessionImpl) throws Exception {
    return new WorkingConnectionImpl(sessionImpl);
  }

  /**
   * получить соединение с хранилицем данных
   *
   * @return соединение
   */
  public static java.sql.Connection getConnection() {
    try {
      javax.sql.DataSource ds;

      ds = ContextUtils.lookup(MBSA_DATASOURCE_NAME, javax.sql.DataSource.class);

      return ds.getConnection();
    } catch (NamingException e) {
      throw new EJBException("Can't find DataSource " + e);
    } catch (Exception e) {
      throw new EJBException("Can't get connection " + e);
    }
  }

  /**
   * Получить устаревший менеджер перманентного хранилища
   *
   * @deprecated
   */
  @Deprecated
  public static PersistentManager getLegacyPersistentManager() {
    return (PersistentManager) com.mg.framework.service.PluginFactory.getPlugin(PersistentManager.class);
  }

  /**
   * Получить менеджер перманентного хранилища
   *
   * @return менеджер перманентного хранилища
   */
  public static PersistentManager getPersistentManager() {
    try {
      return ContextUtils.lookup(PersistentManager.JNDI_NAME, PersistentManager.class);
    } catch (NamingException e) {
      throw new RuntimeException("Unable to locate PersistentManager in JNDI under name [" + PersistentManager.JNDI_NAME + "]", e);
    }
  }

  /**
   * получить имя класса реализующего Logger
   *
   * @return имя класса реализующего Logger
   */
  public static String getLoggerClassName() {
    //TODO get logger plugin name from config file
    return com.mg.framework.service.jboss.JBossLoggerPlugin.class.getName();
  }

  /**
   * получить объект Logger для класса
   *
   * @param clazz класс
   * @return объект Logger
   */
  public static com.mg.framework.api.Logger getLogger(Class<?> clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * получить объект Logger по имени класса
   *
   * @param className имя класса
   * @return объект Logger
   */
  public static com.mg.framework.api.Logger getLogger(String className) {
    return com.mg.framework.api.Logger.getLogger(className);
  }

  /**
   * получить сервис системы безопасности
   *
   * @return сервис системы безопасности
   */
  public static com.mg.framework.api.security.SecuritySystem getSecuritySystem() {
    return SecuritySystemLocator.locate();
  }

  /**
   * получить менеджер транзакций
   *
   * @return менеджер транзакций
   */
  public static TransactionManager getTransactionManager() {
    try {
      return ContextUtils.lookup("java:/TransactionManager", TransactionManager.class);
    } catch (Exception e) {
      throw new RuntimeException("java:/TransactionManager lookup failed", e);
    }
  }

  /**
   * получить текущую транзакцию
   *
   * @return текущая транзакция
   */
  public static Transaction getCurrentTransaction() {
    TransactionManager transactionManager;
    try {
      transactionManager = ContextUtils.lookup("java:/TransactionManager", TransactionManager.class);
    } catch (Exception e) {
      throw new RuntimeException("java:/TransactionManager lookup failed", e);
    }

    try {
      return transactionManager.getTransaction();
    } catch (SystemException e) {
      throw new RuntimeException("Failed to obtain current transaction: " + e.getMessage(), e);
    }
  }

  /**
   * создать MBean заглушку (proxy)
   *
   * @param intf интерфейс для которого будет реализована заглушка
   * @param name строка используемая при создании имя MBean сервиса
   * @return MBean заглушка
   * @throws MalformedObjectNameException при неверном имени сервиса
   */
  public static Object createMBeanProxy(final Class<?> intf, final String name) throws MalformedObjectNameException {
    return MBeanProxyExt.create(intf, name);
  }

  /**
   * загрузить класс по имени
   *
   * @param className имя класса
   * @return класс
   * @throws ClassNotFoundException если класс не найден
   */
  public static Class<?> loadClass(String className) throws ClassNotFoundException {
    return Thread.currentThread().getContextClassLoader().loadClass(className);
  }

  /**
   * загрузить ресурс по имени
   *
   * @param name имя ресурса
   * @return ресурс
   */
  public static URL loadResource(String name) {
    return Thread.currentThread().getContextClassLoader().getResource(name);
  }

  /**
   * получить временную папку сервера приложения
   *
   * @return временная папка
   */
  public static File getServerTempDir() {
    //TODO remove JBoss specific
    return ServerConfigLocator.locate().getServerTempDir();
  }

  /**
   * получить папку логов сервера приложения
   *
   * @return папка логов
   */
  public static File getServerLogDir() {
    //TODO remove JBoss specific
    return ServerConfigLocator.locate().getServerLogDir();
  }

  /**
   * пометить текущую транзакцию так, что возможен только откат данной транзакции
   */
  public static void setTransactionRollbackOnly() {
    try {
      ServerUtils.getTransactionManager().setRollbackOnly();
    } catch (Exception e) {
      logger.warn("Failed to set rollback only; ignoring", e);
    }
  }

  /**
   * установить таймаут текущей транзакции
   *
   * @param seconds величина таймаута в секундах, если значение параметра <code>0</code>, то
   *                значение таймаута будет сброшено в значение по умолчанию
   */
  public static void setTransactionTimeout(int seconds) {
    try {
      ServerUtils.getTransactionManager().setTransactionTimeout(seconds);
    } catch (Exception e) {
      logger.warn("Failed to set transaction timeout; ignoring", e);
    }
  }

  /**
   * получить идентификатор текущего манданта
   *
   * @return идентификатор
   */
  public static int getSystemTenantId() {
    //TODO реализовать хранение идентификатора в сессии, чтобы не требовалось обращение к хранилищу, т.о. избежим проблем при обращении вне транзакции
    return 1;
        /*Session session = ServerUtils.getCurrentSession();
      if (session != null)
    		return (Integer) ((PersistentObject) session.getSystemTenant()).getPrimaryKey();
    	else
    		return 1;*/
  }

  /**
   * создать событие системного аудита
   *
   * @param event событие
   */
  public static void addSystemAuditEvent(SystemAuditEvent event) {
    try {
      JmsUtils.sendObjectMessageToTopic("topic/com/mg/jet/systemaudit", event);
    } catch (Exception e) {
      logger.error("system audit failed", e);
    }
  }

  /**
   * создать событие системного аудита, в качестве отправителя будет использован текущий
   * пользователь
   *
   * @param beanName  объект источник
   * @param operation операция
   * @param details   описание события
   */
  public static void addSystemAuditEvent(String beanName, String operation, String details) {
    Session session = ServerUtils.getCurrentSession();
    String userName = "system";
    if (session != null)
      userName = session.getWorkingConnection().getUserProfile().getUserName();
    addSystemAuditEvent(new SystemAuditEvent(userName, beanName, operation, details));
  }

}
