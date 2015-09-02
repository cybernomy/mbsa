/*
 * PeriodServiceLocal.java
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
 */
package com.mg.merp.paymentcontrol;

import java.util.List;

import com.mg.merp.paymentcontrol.model.PmcPeriod;

/**
 * ������ ������-���������� "������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface PeriodServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PmcPeriod, Integer> {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Period"; //$NON-NLS-1$

	/**
	 * ������� ������� ������������ (� �������� ����������)
	 * @param isPmcYear - ������� ��������
	 * @param isPmcHalfYear - ����-������� ��������
	 * @param isPmcQuarter - ����������� ��������
	 * @param isPmcMonth - �������� ��������
	 * @param isPmcTenDays - �������� ������
	 * @param isPmcWeek - ��������� ��������
	 * @param isPmcDay - ������� ��������
	 * @param beginDate - ���� ������ ������������
	 * @param upLevelQuantity - ���������� �������� (�������� ������)
	 * @param Parent - ������������ ����
	 */
	void createPeriods(boolean isPmcYear, boolean isPmcHalfYear, boolean isPmcQuarter, boolean isPmcMonth, boolean isPmcTenDays, boolean isPmcWeek, boolean isPmcDay, java.util.Date beginDate, java.lang.Integer upLevelQuantity, com.mg.merp.paymentcontrol.model.PmcPeriod Parent);
	
	/**
	 * �������� ������ ��������� �������� ������������ (�� ����������)
	 * @param parentPeriod - ��� �������
	 * @return ������ �������� ��������
	 */
	List<PmcPeriod> getNestedPeriods(PmcPeriod parentPeriod);

}
