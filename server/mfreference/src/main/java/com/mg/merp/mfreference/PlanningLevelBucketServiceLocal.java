/*
 * PlanningLevelBucketServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.mfreference;

import java.util.Date;

import com.mg.merp.mfreference.model.PlanningLevelBucket;

/**
 * ������-��������� "������ ������� ������������"
 * 
 * @author leonova
 * @version $Id: PlanningLevelBucketServiceLocal.java,v 1.2 2007/07/30 10:25:31 safonov Exp $
 */
public interface PlanningLevelBucketServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<PlanningLevelBucket, Integer>
{
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/mfreference/PlanningLevelBucket";
	
	/**
	 * �������� ��������� ���� ������ ������ � ����������� ����
	 * 
	 * @param planningLevelId	������������� ������ ������������
	 * @param planningDate	���� ������������
	 * @return	��������� ���� ������ ������ ��� <code>null</code> ���� ����� �� ������
	 */
	Date nearestBucketStartDate(int planningLevelId, Date planningDate);

	/**
	 * ����������� �������� ������
	 * 
	 * @param planningLevelId	������� ������������
	 * @param offsetDate		����
	 * @return	�������� ������
	 */
	short determineOffset(int planningLevelId, Date offsetDate);
	
	/**
	 * ����������� ��������� ������
	 * 
	 * @param planningLevelId	������� ������������
	 * @param bucketOffset		�������� ������
	 * @return	�������� ������
	 */
	BucketRange determineRange(int planningLevelId, short bucketOffset);

}
