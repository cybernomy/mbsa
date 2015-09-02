/*
 * ScheduleSpecServiceLocal.java
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

import java.util.List;

import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;

/**
 * ������-��������� "������������ ������� ����� � ��������� �����"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleSpecServiceLocal.java,v 1.3 2008/08/12 14:08:13 sharapov Exp $
 */
public interface ScheduleSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ScheduleSpec, Integer> {

	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/table/ScheduleSpec"; //$NON-NLS-1$

	/**
	 * ��������� ������������
	 * @param scheduleHead - ���������
	 * @return ������������
	 */
	List<ScheduleSpec> loadSpecs(ScheduleHead scheduleHead);

}
