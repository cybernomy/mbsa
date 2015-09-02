/*
 * ScheduleServiceLocal.java
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
package com.mg.merp.lbschedule;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.lbschedule.model.Schedule;

/**
 * ������ ������-���������� "������ ���������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleServiceLocal.java,v 1.5 2007/09/10 13:26:50 sharapov Exp $
 */
public interface ScheduleServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Schedule, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/Schedule"; //$NON-NLS-1$
	
	/**
	 * ��� ����� ��� �������� ���������� ������������
	 */
	final static short FOLDER_PART = 13600;

	/**
	 * �������� ���������� �������� �������
	 */
	static final int SCHEDULE_SUM_PREC = 2;
	
	/**
	 * �������� �������� �� ��������� �������� ������ ���
	 * @param scheduleId - ������������� ������� ���������� �������������
	 * @return �������� �� ��������� �������� ������ ��� ��� <code>null</code>, ���� �� ������
	 */
	DocHead getDocHead(Integer scheduleId);

}
