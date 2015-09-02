/*
 * ScheduleHeadServiceLocal.java
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
package com.mg.merp.table;

import java.util.Date;
import java.util.List;

import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;

/**
 * ������-��������� "������� ����� � ��������� �����"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleHeadServiceLocal.java,v 1.2 2008/08/12 14:00:54 sharapov Exp $
 */
public interface ScheduleHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ScheduleHead, Integer> {

	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/table/ScheduleHead"; //$NON-NLS-1$

	/**
	 * ��� ������ "������� ������ �� �������"
	 */
	static final String CREATE_BY_PATTERN_METHOD_NAME = "createByPattern"; //$NON-NLS-1$
	
	/**
	 * ������� ������ �� �������
	 * @param scheduleHead - ��������� ������� � ��������� �����
	 * @param patternHead - ��������� �������
	 * @param initialShift - ��������(��� ������� ��������)
	 * @param beginDate - ���� �
	 * @param endDate - ���� ��
	 * @return ������ ������� ������������ ������� � ��������� �����
	 */
	List<ScheduleSpec> createByPattern(ScheduleHead scheduleHead, PatternHead patternHead, Integer initialShift, Date beginDate, Date endDate);

}
