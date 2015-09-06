/**
 * ApplicationServer.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api;

import java.util.Set;

/**
 * Сервис управления прикладными функциями сервера приложения
 *
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServer.java,v 1.2 2008/12/08 06:05:43 safonov Exp $
 */
public interface ApplicationServer {

  /**
   * загрузить информацию о сессиях активных пользователей
   *
   * @return информация о сессиях
   */
  Set<UserSessionInfo> loadUserSessionInfos() throws Exception;

  /**
   * загрузить информацию о сессии
   *
   * @param httpSessionId идентификатор сессии
   * @return информация о сессии
   */
  UserSessionInfo loadUserSessionInfo(String httpSessionId) throws Exception;

  /**
   * отправить сообщение администратора в текущии сессии
   *
   * @param sessionIds идентификаторы сессий
   * @param message    сообщение
   */
  void sendAdminMessage(String[] sessionIds, String message) throws Exception;

  /**
   * отправить сообщение администратора в текущии сессии
   *
   * @param sessionIds идентификаторы сессий в виде одной строки, в качестве разделителя
   *                   используется символ <code>,</code>
   * @param message    сообщение
   */
  void sendAdminMessage(String sessionIds, String message) throws Exception;

  /**
   * завершить сессии
   *
   * @param sessionIds идентификаторы сессий
   */
  void invalidateUserSessions(String[] sessionIds) throws Exception;

}
