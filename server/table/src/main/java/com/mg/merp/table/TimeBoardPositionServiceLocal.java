/*
 * TimeBoardPositionServiceLocal.java
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

import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.table.model.TimeBoardPosition;

/**
 * ������-��������� "������ ����������� � ������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardPositionServiceLocal.java,v 1.2 2008/08/12 14:08:13 sharapov Exp $
 */
public interface TimeBoardPositionServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TimeBoardPosition, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/table/TimeBoardPosition"; //$NON-NLS-1$
	
	/**
	 * �������� ���������� � ������
	 * @param timeBoardHeadId - ������������� ��������� ������
	 * @param positionFills - ������ ���������� ���������� ������������
	 */
	void addTimeBoardPositions(Integer timeBoardHeadId, PositionFill[] positionFills);
	
}
