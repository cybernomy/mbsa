/**
 * DatabaseAuditServiceMBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.jboss.system.ServiceMBean;

/**
 * ������ ������ ��������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditServiceMBean.java,v 1.2 2007/11/27 14:48:36 safonov Exp $
 */
public interface DatabaseAuditServiceMBean extends ServiceMBean {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp:service=DatabaseAuditService";

	/**
	 * ��������� ������� ������ ��������
	 * 
	 * @param createEvent	������� ��������
	 */
	void auditCreate(PostInsertEvent createEvent);
	
	/**
	 *  ��������� ������� ������ ���������
	 * 
	 * @param modifyEvent	������� ���������
	 */
	void auditModify(PostUpdateEvent modifyEvent);
	
	/**
	 *  ��������� ������� ������ ��������
	 * 
	 * @param removeEvent	������� ��������
	 */
	void auditRemove(PostDeleteEvent removeEvent);
	
	/**
	 * ��������� ��������� ������ � ������� ������ ������� ����������
	 */
	void applyAuditSetup();

	/**
	 * ������� ��������� ������
	 * 
	 * @return	�������
	 */
	Boolean isAuditActivated();
	
	/**
	 * ��������� �������� ��������� ������
	 * 
	 * @param value	��������
	 */
	void setAuditActivated(Boolean value);
	
	/**
	 * �������� ��� ��������� ��������� ������
	 * 
	 * @return	��� ���������
	 */
	public String getJmsDestinationName();
	
	/**
	 * ���������� ��� ��������� ��������� ������
	 * 
	 * @param name	��� ���������
	 */
	public void setJmsDestinationName(String name);

}
