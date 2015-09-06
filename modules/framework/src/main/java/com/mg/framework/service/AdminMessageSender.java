/**
 * AdminMessageSender.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.service;

import com.mg.framework.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * Реализация сервиса отправки сообщений администратора системы пользователям. Используется
 * простейшая реализация, не поддерживается кластерная установка.
 *
 * @author Oleg V. Safonov
 * @version $Id: AdminMessageSender.java,v 1.1 2008/07/14 14:11:27 safonov Exp $
 */
public class AdminMessageSender extends Observable {
  private static AdminMessageSender instance = new AdminMessageSender();

  /**
   * получить экземпляр сервиса
   *
   * @return экземпляр сервиса
   */
  public static AdminMessageSender getInstance() {
    return instance;
  }

  /**
   * отправить сообщение пользователям
   *
   * @param sessionIds список сессий пользователей
   * @param message    сообщение
   */
  public void sendMessage(String[] sessionIds, String message) {
    setChanged();
    notifyObservers(new AdminMessage(sessionIds, message));
  }

  /**
   * Класс сообщение администратора
   */
  public class AdminMessage implements Serializable {
    private String[] sessionIds;
    private String message;
    private Date messageTime;

    public AdminMessage(String[] sessionIds, String message) {
      super();
      this.sessionIds = sessionIds;
      this.message = message;
      this.messageTime = DateTimeUtils.nowDate();
    }

    /**
     * список сессий
     *
     * @return the sessionIds
     */
    public String[] getSessionIds() {
      return sessionIds;
    }

    /**
     * сообщение
     *
     * @return the message
     */
    public String getMessage() {
      return message;
    }

    /**
     * время создания сообщения
     *
     * @return the messageTime
     */
    public Date getMessageTime() {
      return messageTime;
    }

  }

}
