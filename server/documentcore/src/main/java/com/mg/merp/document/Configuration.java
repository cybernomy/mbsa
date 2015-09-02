/*
 * Configuration.java
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
package com.mg.merp.document;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * ������������ ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Configuration.java,v 1.1 2006/12/12 14:32:43 safonov Exp $
 */
public interface Configuration {

	/**
	 * ������� ������, ������������ �� ��������� � �������� ������ ��������� ��� ��������
	 * 
	 * @return	������� ������
	 */
	Currency getBaseCurrency();
	
	/**
	 * ��������� ������ (������������ �������� ������), ������������ ��� �������� �������� ����������
	 * � �������� ������� �������� �� ���������
	 * 
	 * @return	��������� ������
	 */
	Currency getLocalCurrency();
	
	/**
	 * �������� �������� �������� ������� ����������
	 * 
	 * @return	�������� (���������� ������ ����� �������)
	 */
	int getCurrencyScale();
	
	/**
	 * ������� ��� ����� ������, ������������ �� ��������� ��� �������� ���������
	 * 
	 * @return	��� ����� ������
	 */
	CurrencyRateType getCurrencyRateType();
	
	/**
	 * ������� �������� ����� ������, ������������ �� ��������� ��� �������� ���������
	 * 
	 * @return	�������� ����� ������
	 */
	CurrencyRateAuthority getCurrencyRateAuthority();

}
