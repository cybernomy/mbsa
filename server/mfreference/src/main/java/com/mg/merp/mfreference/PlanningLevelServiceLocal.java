/*
 * PlanningLevelServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference;

import com.mg.merp.mfreference.model.PlanningLevel;

/**
 * ������ ������-���������� "������ ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PlanningLevelServiceLocal.java,v 1.2 2007/02/19 12:55:14 sharapov Exp $
 */
public interface PlanningLevelServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PlanningLevel, Integer> {

	/**
	 * ������� ������� ������ ������������
	 * @param planningLevel - ������� ������������
	 * @param bucketLength - ����� ������� � ����
	 * @param bucketNumber - ���������� ��������
	 */
	void generateBuckets(PlanningLevel planningLevel, Integer bucketLength, Integer bucketNumber);

}
