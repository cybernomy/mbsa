/*
 * PeriodServiceLocal.java
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
package com.mg.merp.account;

import java.io.Serializable;
import java.util.Date;

import com.mg.merp.account.model.Period;

/**
 * ������-��������� "������� ���.�����"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Oleg V. Safonov
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/01/16 11:39:21 safonov Exp $
 */
public interface PeriodServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Period, Integer>
{
	/**
	 * ������� ������� ���.�����
	 * @param periods - ����� ��������
	 */
	void openPeriod(Serializable[] periods);

	/**
	 * ������� ������� ���.�����
	 * @param periods - ����� ��������
	 */
	void closePeriod(Serializable[] periods);

	/**
	 * ����� ������ �� ����, ���� ������ �� ������ �� ����� ������������� ��
	 * 
	 * @param date	����
	 * @return	������
	 */
	Period findByDate(Date date);
	
	/**
	 * �������� ������� �� ����������� ���������
	 * 
	 * @param date	���� ���������
	 */
	void checkPeriod(Date date);
	
	/**
	 * �������� ������� �� ����������� ���������
	 * 
	 * @param periodId	������������� �������
	 */
	void checkPeriod(Integer periodId);
	
}
