/*
 * ApplicationServerServiceMBean.java
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.ApplicationServer;

import org.jboss.system.ServiceMBean;

/**
 * JMX сервис управления прикладными функциями сервера приложения
 *
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServerServiceMBean.java,v 1.4 2008/12/08 06:07:51 safonov Exp $
 */
public interface ApplicationServerServiceMBean extends ApplicationServer, ServiceMBean {
  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:service=ApplicationServerService";

  /**
   * конвертация базы данных
   */
  void convertDatabase() throws Exception;

  /**
   * остановка пула соединений с базой данных
   */
  void stopDatabasePool(String connectionPoolName) throws Exception;

  /**
   * запуск пула соединений с базой данных
   */
  void startDatabasePool(String connectionPoolName) throws Exception;

  /**
   * конфигурация соединения
   *
   * @return конфигурация
   */
  org.w3c.dom.Element getDatabaseConfiguration();

  /**
   * установить конфигурацию соединения, должен иметь следующию структуру:
   *
   * <pre>
   * &lt;attribute name="DatabaseConfiguration"&gt;
   *   &lt;configuration&gt;
   *     &lt;property name="database.systempath" value="D:/opt/Firebird_2_0/bin"/&gt;
   *     &lt;property name="database.jdbcdriverclass" value="org.firebirdsql.jdbc.FBDriver"/&gt;
   *     &lt;property name="database.url" value="rd01:D:/projects/merp40/db/MERP4.FDB"/&gt;
   *     &lt;property name="database.scriptpath" value="D:/projects/merp40/db/scripts/updatesql"/&gt;
   *     &lt;property name="database.username" value="SYSDBA"/&gt;
   *     &lt;property name="database.password" value="masterkey"/&gt;
   *     &lt;property name="database.characterset" value="WIN1251"/&gt;
   *   &lt;/configuration&gt;
   * &lt;/attribute&gt;
   * </pre>
   *
   * @param element конфигурация
   */
  void setDatabaseConfiguration(org.w3c.dom.Element element);

  /**
   * наименование сервера базы данных
   *
   * @return наименование сервера базы данных
   */
  String getDatabaseName();

  /**
   * установка наименования сервера базы данных, может иметь следующие значения: <p>Firebird
   * <p>Interbase <p>Oracle <p>PostgreSQL
   *
   * @param databaseName наименование сервера базы данных
   */
  void setDatabaseName(String databaseName);

  /**
   * получить имя реализации менеджера хранилища данных
   *
   * @return имя реализации
   */
  String getPersistentManagerImpl();

  /**
   * установить имя реализации менеджера хранилища данных
   *
   * @param implName имя реализации
   */
  void setPersistentManagerImpl(String implName);

}
