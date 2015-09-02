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
package com.mg.merp.finance;

import java.io.Serializable;
import java.util.Date;

import com.mg.merp.finance.model.FinPeriod;

/**
 * ������-��������� "������� ����������� �����"
 * 
 * @author leonova
 * @author Artem V. Sharapov 
 * @version $Id: PeriodServiceLocal.java,v 1.3 2007/01/16 14:35:00 safonov Exp $
 */
public interface PeriodServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<FinPeriod, Integer>
{

	/**
	 * ������� ������
	 * 
	 * @param periodIds	- �������������� �������� ���. �����	
	 */
	void closePeriod(Serializable[] periodIds);

	/**
	 * ������� ������
	 * 
	 * @param periodIds - �������������� �������� ���. �����
	 */
	void openPeriod(Serializable[] periodIds);

	/**
	 * ����� �� ����
	 * 
	 * @param date	����
	 * @return ������ ��� ������������ �� ���� ������ �� ������
	 */
	FinPeriod findByDate(Date date);
	
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
