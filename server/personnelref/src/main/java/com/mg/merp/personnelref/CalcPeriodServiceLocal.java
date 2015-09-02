/*
 * CalcPeriodServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.CalcPeriod;

/**
 * ������ ������-���������� "��������� �������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcPeriodServiceLocal.java,v 1.2 2007/07/09 07:40:01 sharapov Exp $
 */
public interface CalcPeriodServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcPeriod, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/personnelref/CalcPeriod"; //$NON-NLS-1$
	
	/**
	 * ��� ������� ������������ ��� �������� �������� ���������� ������� 
	 */
	static final String PROFILE_NAME = "PersonnelRef"; //$NON-NLS-1$
	
	/**
	 * ��� �������� �������� ���������� ������� � ������� ������������
	 */
	static final String PROFILE_PROPERTY = "CurrentCalcPeriodId"; //$NON-NLS-1$
		
	/**
	 * �������� ������� ��������� ������
	 * @return ������� ��������� ������
	 */
	CalcPeriod getCurrentCalcPeriod();
	
	/**
	 * ���������� ������� ��������� ������
	 * @param calcPeriodId - ������������� ���������� �������
	 */
	void setCurrentCalcPeriod(Integer calcPeriodId);
	
}
