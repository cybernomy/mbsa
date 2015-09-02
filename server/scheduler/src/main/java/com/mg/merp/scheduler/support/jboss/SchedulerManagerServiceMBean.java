/**
 * SchedulerManagerServiceMBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.scheduler.support.jboss;

import org.jboss.system.ListenerServiceMBean;
import org.quartz.Scheduler;

/**
 * JMX сервис менеджера планировщика
 * 
 * @author Oleg V. Safonov
 * @version $Id: SchedulerManagerServiceMBean.java,v 1.2 2008/08/28 13:40:11 safonov Exp $
 */
public interface SchedulerManagerServiceMBean extends ListenerServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:service=SchedulerManagerService"; //$NON-NLS-1$

	/**
	 * установить свойства планировщика
	 * 
	 * @param properties	свойства
	 */
	void setProperties(String properties);
	
	/**
	 * получить свойства планировщика
	 * 
	 * @return	свойства
	 */
	String getProperties();

	/**
	 * регистрация задачи в планировщике
	 * 
	 * @param sysClientId	мандант
	 * @param taskCode	код задачи
	 */
	void registerTask(Integer sysClientId, String taskCode);
	
	/**
	 * удаление задачи из планировщика
	 * 
	 * @param sysClientId	мандант
	 * @param taskCode	код задачи
	 */
	void unregisterTask(Integer sysClientId, String taskCode);
	
	/**
	 * получить планировщик
	 * 
	 * @return	планировщик
	 */
	Scheduler getScheduler();

	/**
	 * установить имя учетной записи для выполнения заданий
	 * 
	 * @param login	имя учетной записи
	 */
	void setSchedulerUserName(String login);

	/**
	 * получить имя учетной записи для выполнения заданий
	 * 
	 * @return	имя учетной записи
	 */
	String getSchedulerUserName();

	/**
	 * установить пароль учетной записи для выполнения заданий
	 * 
	 * @param login	пароль учетной записи
	 */
	void setSchedulerPassword(String login);

	/**
	 * получить пароль учетной записи для выполнения заданий
	 * 
	 * @return	пароль учетной записи
	 */
	String getSchedulerPassword();

}
