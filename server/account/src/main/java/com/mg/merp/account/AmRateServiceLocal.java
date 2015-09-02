/*
 * AmRateServiceLocal.java
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
package com.mg.merp.account;

import java.math.BigDecimal;

import com.mg.merp.account.model.AmCode;
import com.mg.merp.account.model.AmRate;

/**
 * ������-��������� "����� �����������" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: AmRateServiceLocal.java,v 1.2 2008/04/28 10:09:51 alikaev Exp $
 */
public interface AmRateServiceLocal extends com.mg.framework.api.DataBusinessObjectService<AmRate, Integer> {
	
	/**
	 * ���������� ����� ����������� � ���������
	 * 
	 * @param amCode
	 * 			- ���� ����� �����������
	 * @param aMonth
	 * 				- ����� ���������� ����������� � ���������� ���������� (���*12 + �����)
	 * @return
	 */
	BigDecimal getAmortRate(AmCode amCode, Short aMonth);
	
	/**
	 * ��������� �������������� ����� ���������
	 * 
	 * @param amCode
	 * 			- ���� ����� �����������
	 * @param aMonth
	 * 				- ����� ���������� ����������� � ���������� ���������� (���*12 + �����)
	 * @return
	 */
	BigDecimal getVolumeProd(AmCode amCode, Short aMonth);
	
}
