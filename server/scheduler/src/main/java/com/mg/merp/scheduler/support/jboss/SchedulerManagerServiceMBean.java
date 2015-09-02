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
 * JMX ������ ��������� ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SchedulerManagerServiceMBean.java,v 1.2 2008/08/28 13:40:11 safonov Exp $
 */
public interface SchedulerManagerServiceMBean extends ListenerServiceMBean {
	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=SchedulerManagerService"; //$NON-NLS-1$

	/**
	 * ���������� �������� ������������
	 * 
	 * @param properties	��������
	 */
	void setProperties(String properties);
	
	/**
	 * �������� �������� ������������
	 * 
	 * @return	��������
	 */
	String getProperties();

	/**
	 * ����������� ������ � ������������
	 * 
	 * @param sysClientId	�������
	 * @param taskCode	��� ������
	 */
	void registerTask(Integer sysClientId, String taskCode);
	
	/**
	 * �������� ������ �� ������������
	 * 
	 * @param sysClientId	�������
	 * @param taskCode	��� ������
	 */
	void unregisterTask(Integer sysClientId, String taskCode);
	
	/**
	 * �������� �����������
	 * 
	 * @return	�����������
	 */
	Scheduler getScheduler();

	/**
	 * ���������� ��� ������� ������ ��� ���������� �������
	 * 
	 * @param login	��� ������� ������
	 */
	void setSchedulerUserName(String login);

	/**
	 * �������� ��� ������� ������ ��� ���������� �������
	 * 
	 * @return	��� ������� ������
	 */
	String getSchedulerUserName();

	/**
	 * ���������� ������ ������� ������ ��� ���������� �������
	 * 
	 * @param login	������ ������� ������
	 */
	void setSchedulerPassword(String login);

	/**
	 * �������� ������ ������� ������ ��� ���������� �������
	 * 
	 * @return	������ ������� ������
	 */
	String getSchedulerPassword();

}
