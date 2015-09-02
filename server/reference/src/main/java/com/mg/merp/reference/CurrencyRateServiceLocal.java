/*
 * CurrencyRateServiceLocal.java
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
package com.mg.merp.reference;

import java.math.BigDecimal;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRate;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * ������-��������� "����� �����"
 * 
 * @author leonova
 * @version $Id: CurrencyRateServiceLocal.java,v 1.4 2007/03/27 12:59:36 safonov Exp $
 */
public interface CurrencyRateServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<CurrencyRate, Integer>
{

	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/reference/CurrencyRate"; //$NON-NLS-1$

	/**
	 * �������� ����� �����
	 */
	static final int DEFAULT_RATE_SCALE = 6;
	
	/**
	 * �������� ������ ���� �����
	 * 
	 * @param currencyTo		������ ��� ������� ������� ����
	 * @param currencyFrom		������ �� ��������� � ������� ������� ����
	 * @param rateAuthority	�������� �����
	 * @param rateType		��� �����
	 * @param effectiveDate	���� �����, ���� <code>null</code>, �� ����� ������������ ������� ����
	 * @return	���� ������
	 * @throws CurrencyRateNotFoundException	���� ���� �� ������
	 */
	BigDecimal getCurrencyRate(Currency currencyTo, Currency currencyFrom, 
			CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate) throws CurrencyRateNotFoundException;

	/**
	 * �������� �������� ���� �����
	 * 
	 * @param currencyTo		������ ��� ������� ������� ����
	 * @param currencyFrom		������ �� ��������� � ������� ������� ����
	 * @param rateAuthority	�������� �����
	 * @param rateType		��� �����
	 * @param effectiveDate	���� �����, ���� <code>null</code>, �� ����� ������������ ������� ����
	 * @return	���� ������
	 * @throws CurrencyRateNotFoundException	���� ���� �� ������
	 */
	BigDecimal getIndirectCurrencyRate(Currency currencyTo, Currency currencyFrom, 
			CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate) throws CurrencyRateNotFoundException;

}
