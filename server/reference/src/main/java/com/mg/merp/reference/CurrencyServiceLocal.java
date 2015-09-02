/*
 * CurrencyServiceLocal.java
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
package com.mg.merp.reference;

import java.math.BigDecimal;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * ������-��������� "������"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: CurrencyServiceLocal.java,v 1.8 2008/04/25 13:48:20 safonov Exp $
 */
public interface CurrencyServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<Currency, Integer>{

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/reference/Currency";
	
	/**
	 * ����������� �����
	 * 
	 * @param currencyTo		������ � ������� ������������
	 * @param currencyFrom		������ �� ������� ������������
	 * @param rateAuthority	�������� ����� ������
	 * @param rateType		��� ����� ������
	 * @param effectiveDate	���� �����������, ���� <code>null</code>, �� ����� ������������ ������� ����
	 * @param currencyFromAmount	����� ��� �����������
	 * @return	���������������� �����
	 * @throws CurrencyRateNotFoundException ���� �� ������ �� ������ �� �������� ���� �����
	 */
	BigDecimal conversion(Currency currencyTo, Currency currencyFrom, 
			CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException;

	/**
	 * ����������� ����������� �����, ���������� �������������� ��������� �� ������� ���� �����������
	 * �����������
	 * 
	 * @param currencyTo		������ � ������� ������������
	 * @param currencyFrom		������ �� ������� ������������
	 * @param rateAuthority	�������� ����� ������
	 * @param rateType		��� ����� ������
	 * @param effectiveDate	���� �����������, ���� <code>null</code>, �� ����� ������������ ������� ����
	 * @param currencyFromAmount	����� ��� �����������
	 * @return	���������������� ����� � ��������� �����������
	 * @throws CurrencyRateNotFoundException ���� �� ������ �� ������ �� �������� ���� �����
	 */
	CurrencyConversionResult conversionEx(Currency currencyTo, Currency currencyFrom, 
			CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException;
	
	/**
	 * ����� ������ �� ����
	 * 
	 * @param code	��� ������
	 * @return	������ ��� <code>null</code> ���� �� �������
	 */
	Currency findByCode(String code);
	
}
