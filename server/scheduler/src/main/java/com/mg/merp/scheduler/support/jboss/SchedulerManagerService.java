/**
 * SchedulerManagerService.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.scheduler.support.jboss;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.scheduler.support.SchedulerManagerServiceImpl;

import org.jboss.system.ListenerServiceMBeanSupport;
import org.jboss.system.server.Server;
import org.quartz.Scheduler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.management.Notification;

/**
 * Рализация JMX сервиса менеджера планировщика
 *
 * @author Oleg V. Safonov
 * @version $Id: SchedulerManagerService.java,v 1.2 2008/08/28 13:40:11 safonov Exp $
 */
public class SchedulerManagerService extends ListenerServiceMBeanSupport
    implements SchedulerManagerServiceMBean {
  private Logger logger = ServerUtils.getLogger(SchedulerManagerService.class);
  private SchedulerManagerServiceImpl delegate;
  private Properties properties = null;
  private String schedulerUserName;
  private String schedulerPassword;

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#createService()
   */
  @Override
  protected void createService() throws Exception {
    delegate = new SchedulerManagerServiceImpl();
    super.createService();
    super.subscribe(true); // subscribe listeners
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#destroyService()
   */
  @Override
  protected void destroyService() throws Exception {
    delegate = null;
    super.unsubscribe();
    super.destroyService();
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#startService()
   */
  @Override
  protected void startService() throws Exception {
    super.startService();
    delegate.setSchedulerUserName(schedulerUserName);
    delegate.setSchedulerPassword(schedulerPassword);
    delegate.start(properties);
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
    delegate.stop();
    super.stopService();
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ListenerServiceMBeanSupport#handleNotification2(javax.management.Notification, java.lang.Object)
   */
  @Override
  public void handleNotification2(Notification notification, Object handback) {
    String type = notification.getType().intern();
    //после запуска сервера загружаем задачи для щедулера
    if (type == Server.START_NOTIFICATION_TYPE) {
      delegate.scheduleTasks();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#getProperties()
   */
  public String getProperties() {
    if (this.properties == null)
      return "";

    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      this.properties.store(baos, "");

      return new String(baos.toByteArray());
    } catch (IOException ioe) {
      // should not happen
      return "";
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#setProperties(java.lang.String)
   */
  public void setProperties(String properties) {
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(properties.getBytes());
      this.properties = new Properties();
      this.properties.load(bais);
    } catch (IOException ioe) {
      // should not happen
      logger.error("load properties failure", ioe);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#registerTask(java.lang.Integer, java.lang.String)
   */
  public void registerTask(Integer sysClientId, String taskCode) {
    delegate.registerTask(sysClientId, taskCode);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#unregisterTask(java.lang.Integer, java.lang.String)
   */
  public void unregisterTask(Integer sysClientId, String taskCode) {
    delegate.unregisterTask(sysClientId, taskCode);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#getScheduler()
   */
  public Scheduler getScheduler() {
    return delegate.getScheduler();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#getSchedulerUserName()
   */
  public String getSchedulerUserName() {
    return schedulerUserName;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#setSchedulerUserName(java.lang.String)
   */
  public void setSchedulerUserName(String schedulerUserName) {
    this.schedulerUserName = schedulerUserName;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#getSchedulerPassword()
   */
  public String getSchedulerPassword() {
    return schedulerPassword;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean#setSchedulerPassword(java.lang.String)
   */
  public void setSchedulerPassword(String schedulerPassword) {
    this.schedulerPassword = schedulerPassword;
  }

}
