/**
 * DatabaseAuditServiceMBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.service.jboss;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.jboss.system.ServiceMBean;

/**
 * Сервис аудита хранилища данных
 *
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditServiceMBean.java,v 1.2 2007/11/27 14:48:36 safonov Exp $
 */
public interface DatabaseAuditServiceMBean extends ServiceMBean {
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp:service=DatabaseAuditService";

  /**
   * отправить событие аудита создания
   *
   * @param createEvent событие создания
   */
  void auditCreate(PostInsertEvent createEvent);

  /**
   * отправить событие аудита изменения
   *
   * @param modifyEvent событие изменения
   */
  void auditModify(PostUpdateEvent modifyEvent);

  /**
   * отправить событие аудита удаления
   *
   * @param removeEvent событие удаления
   */
  void auditRemove(PostDeleteEvent removeEvent);

  /**
   * применить настройки аудита в текущем сеансе сервера приложения
   */
  void applyAuditSetup();

  /**
   * признак активации аудита
   *
   * @return признак
   */
  Boolean isAuditActivated();

  /**
   * установка признака активации аудита
   *
   * @param value значение
   */
  void setAuditActivated(Boolean value);

  /**
   * получить имя приемника сообщений аудита
   *
   * @return имя приемника
   */
  public String getJmsDestinationName();

  /**
   * установить имя приемника сообщений аудита
   *
   * @param name имя приемника
   */
  public void setJmsDestinationName(String name);

}
