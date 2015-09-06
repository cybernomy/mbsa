/**
 * TaskListenerBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.scheduler.support;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Очередь событий действий над сущностью "Задачи планировщика"
 *
 * @author Oleg V. Safonov
 * @version $Id: TaskListenerBean.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/com/mg/jet/schedulertask")
})
public class TaskListenerBean implements MessageListener {
  public final static String SYS_CLIENT_PARAM = "SYS_CLIENT_PARAM";
  public final static String NEW_CODE_PARAM = "NEW_CODE_PARAM";
  public final static String OLD_CODE_PARAM = "OLD_CODE_PARAM";
  private Logger logger = ServerUtils.getLogger(TaskListenerBean.class);

  /* (non-Javadoc)
   * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
   */
  public void onMessage(Message message) {
    try {
      MapMessage msg = (MapMessage) message;
      String newCode = msg.getString(NEW_CODE_PARAM);
      String oldCode = msg.getString(OLD_CODE_PARAM);
      Integer sysClientId = msg.getInt(SYS_CLIENT_PARAM);
      SchedulerManagerServiceMBean service = SchedulerManagerServiceLocator.locate();
      if (!StringUtils.stringNullOrEmpty(oldCode))
        service.unregisterTask(sysClientId, oldCode);
      if (!StringUtils.stringNullOrEmpty(newCode))
        service.registerTask(sysClientId, newCode);
    } catch (Throwable th) {
      logger.error("task handling failure", th);
    }
  }

}
