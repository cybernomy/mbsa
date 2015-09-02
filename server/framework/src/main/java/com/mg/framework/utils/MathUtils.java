/*
 * MathUtils.java
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
package com.mg.framework.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import com.mg.framework.api.math.RoundContext;

/**
 * �������������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MathUtils.java,v 1.9 2008/06/04 09:35:45 sharapov Exp $
 */
public class MathUtils {

	/**
	 * ��������� 100
	 */
	public final static BigDecimal HUNDRED = new BigDecimal(BigInteger.valueOf(100), 0);
	/**
	 * ��������� 1000
	 */
	public final static BigDecimal THOUSAND = new BigDecimal(BigInteger.valueOf(1000), 0);
	
	/**
	 * ��������� � �����
	 * 
	 * @param value	��������
	 * @return	-1 ���� <code>value</code> ������ ����, 0 ���� <code>value</code> ����� ����, 1 ���� <code>value</code> ������ ����
	 */
	public static int compareToZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO);
	}
	
	/**
	 * ��������� � �����/null
	 * 
	 * @param value	��������
	 * @return	-1 ���� <code>value</code> ������ ����, 0 ���� <code>value</code> ����� ���� ��� <code>null</code>, 1 ���� <code>value</code> ������ ����
	 */
	public static int compareToZeroOrNull(BigDecimal value) {
		if(value == null)
			return 0;
		else
			return compareToZero(value);
	}
	
	/**
	 * ���������� ��������
	 * 
	 * @param value	��������
	 * @param rc	�������� {@link com.mg.framework.api.math.RoundContext RoundContext}
	 * @return	����������� ��������
	 */
	public static BigDecimal round(BigDecimal value, RoundContext rc) {
		//return value.round(new MathContext(value.precision() == 1 ? rc.getScale() : value.precision() - value.scale() + rc.getScale(), rc.getRoundingMode()));
		return value.setScale(rc.getScale(), rc.getRoundingMode());
	}

	/**
	 * ���������� ��������
	 * 
	 * @param value			��������
	 * @param scale			���������� ������ ����� �����
	 * @param roundingMode	����� ���������� {@link java.math.RoundingMode RoundingMode}
	 * @return	����������� ��������
	 */
	public static BigDecimal round(BigDecimal value, int scale, RoundingMode roundingMode) {
		return value.setScale(scale, roundingMode);
	}

	/**
	 * ��������� � �����������
	 * 
	 * @param multiplicand	��������
	 * @param multiplier	���������
	 * @param rc			�������� ����������
	 * @return				������������, ��� <code>null</code> ���� <code>multiplicand == null</code> ��� <code>multiplier == null</code>
	 */
	public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier, RoundContext rc) {
		if (multiplicand == null || multiplier == null)
			return null;
		else
			return round(multiplicand.multiply(multiplier), rc);
	}
	
	/**
	 * �������� � �����������
	 * 
	 * @param augend	������ ���������
	 * @param addend	������ ���������
	 * @param rc		�������� ����������
	 * @return	�����, ��� <code>null</code> ���� <code>augend == null</code> ��� <code>addend == null</code>
	 */
	public static BigDecimal add(BigDecimal augend, BigDecimal addend, RoundContext rc) {
		if (augend == null || addend == null)
			return null;
		return round(addend.add(augend), rc);
	}
	
	/**
	 * ������� � �����������
	 * 
	 * @param dividend	�������
	 * @param divisor	��������
	 * @param rc		�������� ����������
	 * @return	�������, ��� <code>null</code> ���� <code>dividend == null</code> ��� <code>divisor == null</code>
	 */
	public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, RoundContext rc) {
		if (dividend == null || divisor == null)
			return null;
		return dividend.divide(divisor, rc.getScale(), rc.getRoundingMode());
	}
	
	/**
	 * ��������� � �����������
	 * 
	 * @param minuend		�����������
	 * @param subtrahend	����������	
	 * @param rc			�������� ����������
	 * @return	��������, ��� <code>null</code> ���� <code>minuend == null</code> ��� <code>subtrahend == null</code>
	 */
	public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend, RoundContext rc) {
		if (minuend == null || subtrahend == null)
			return null;
		return round(minuend.subtract(subtrahend), rc);
	}
	
	/**
	 * �������� � �����������
	 * 
	 * @param augend	������ ���������
	 * @param addend	������ ���������
	 * @param rc		�������� ����������
	 * @return	�����, ��� <code>addend</code> ���� <code>augend == null</code>, ��� <code>augend</code> ���� <code>addend == null</code>
	 */
	public static BigDecimal addNullable(BigDecimal augend, BigDecimal addend, RoundContext rc) {
		if (augend == null)
			return addend;
		return round(addend == null ? augend : addend.add(augend), rc);
	}

	/**
	 * ��������� � �����������
	 * 
	 * @param minuend		�����������
	 * @param subtrahend	����������	
	 * @param rc			�������� ����������
	 * @return	��������, ��� <code>subtrahend</code> ���� <code>minuend == null</code>, <code>minuend</code> ��� <code>subtrahend == null</code>
	 */
	public static BigDecimal subtractNullable(BigDecimal minuend, BigDecimal subtrahend, RoundContext rc) {
		if (minuend == null)
			return subtrahend;
		return round(subtrahend == null ? minuend : minuend.subtract(subtrahend), rc);
	}

	/**
	 * ���������� �������� <code>-value</code>
	 * 
	 * @param value	��������
	 * @return	<code>-value</code>
	 */
	public static BigDecimal negate(BigDecimal value) {
		return value == null ? null : value.negate();
	}

	/**
	 * ���������� �������� <code>-value</code>
	 * 
	 * @param value	��������
	 * @param rc	��������
	 * @return	<code>-value</code>
	 */
	public static BigDecimal negate(BigDecimal value, RoundContext rc) {
		return value == null ? null : round(value.negate(), rc);
	}

}
