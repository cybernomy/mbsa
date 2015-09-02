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
 * Математические утилиты
 * 
 * @author Oleg V. Safonov
 * @version $Id: MathUtils.java,v 1.9 2008/06/04 09:35:45 sharapov Exp $
 */
public class MathUtils {

	/**
	 * константа 100
	 */
	public final static BigDecimal HUNDRED = new BigDecimal(BigInteger.valueOf(100), 0);
	/**
	 * константа 1000
	 */
	public final static BigDecimal THOUSAND = new BigDecimal(BigInteger.valueOf(1000), 0);
	
	/**
	 * сравнение с нулем
	 * 
	 * @param value	значение
	 * @return	-1 если <code>value</code> меньше нуля, 0 если <code>value</code> равно нулю, 1 если <code>value</code> больше нуля
	 */
	public static int compareToZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO);
	}
	
	/**
	 * сравнение с нулем/null
	 * 
	 * @param value	значение
	 * @return	-1 если <code>value</code> меньше нуля, 0 если <code>value</code> равно нулю или <code>null</code>, 1 если <code>value</code> больше нуля
	 */
	public static int compareToZeroOrNull(BigDecimal value) {
		if(value == null)
			return 0;
		else
			return compareToZero(value);
	}
	
	/**
	 * округление значения
	 * 
	 * @param value	значение
	 * @param rc	контекст {@link com.mg.framework.api.math.RoundContext RoundContext}
	 * @return	округленное значение
	 */
	public static BigDecimal round(BigDecimal value, RoundContext rc) {
		//return value.round(new MathContext(value.precision() == 1 ? rc.getScale() : value.precision() - value.scale() + rc.getScale(), rc.getRoundingMode()));
		return value.setScale(rc.getScale(), rc.getRoundingMode());
	}

	/**
	 * округление значения
	 * 
	 * @param value			значение
	 * @param scale			количество знаков после точки
	 * @param roundingMode	режим округления {@link java.math.RoundingMode RoundingMode}
	 * @return	округленное значение
	 */
	public static BigDecimal round(BigDecimal value, int scale, RoundingMode roundingMode) {
		return value.setScale(scale, roundingMode);
	}

	/**
	 * умножение с округлением
	 * 
	 * @param multiplicand	множимое
	 * @param multiplier	множитель
	 * @param rc			контекст округления
	 * @return				произведение, или <code>null</code> если <code>multiplicand == null</code> или <code>multiplier == null</code>
	 */
	public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier, RoundContext rc) {
		if (multiplicand == null || multiplier == null)
			return null;
		else
			return round(multiplicand.multiply(multiplier), rc);
	}
	
	/**
	 * сложение с округлением
	 * 
	 * @param augend	первое слагаемое
	 * @param addend	второе слагаемое
	 * @param rc		контекст округления
	 * @return	сумма, или <code>null</code> если <code>augend == null</code> или <code>addend == null</code>
	 */
	public static BigDecimal add(BigDecimal augend, BigDecimal addend, RoundContext rc) {
		if (augend == null || addend == null)
			return null;
		return round(addend.add(augend), rc);
	}
	
	/**
	 * деление с округлением
	 * 
	 * @param dividend	делимое
	 * @param divisor	делитель
	 * @param rc		контекст округления
	 * @return	частное, или <code>null</code> если <code>dividend == null</code> или <code>divisor == null</code>
	 */
	public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, RoundContext rc) {
		if (dividend == null || divisor == null)
			return null;
		return dividend.divide(divisor, rc.getScale(), rc.getRoundingMode());
	}
	
	/**
	 * вычитание с округлением
	 * 
	 * @param minuend		уменьшаемое
	 * @param subtrahend	вычитаемое	
	 * @param rc			контекст округления
	 * @return	разность, или <code>null</code> если <code>minuend == null</code> или <code>subtrahend == null</code>
	 */
	public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend, RoundContext rc) {
		if (minuend == null || subtrahend == null)
			return null;
		return round(minuend.subtract(subtrahend), rc);
	}
	
	/**
	 * сложение с округлением
	 * 
	 * @param augend	первое слагаемое
	 * @param addend	второе слагаемое
	 * @param rc		контекст округления
	 * @return	сумма, или <code>addend</code> если <code>augend == null</code>, или <code>augend</code> если <code>addend == null</code>
	 */
	public static BigDecimal addNullable(BigDecimal augend, BigDecimal addend, RoundContext rc) {
		if (augend == null)
			return addend;
		return round(addend == null ? augend : addend.add(augend), rc);
	}

	/**
	 * вычитание с округлением
	 * 
	 * @param minuend		уменьшаемое
	 * @param subtrahend	вычитаемое	
	 * @param rc			контекст округления
	 * @return	разность, или <code>subtrahend</code> если <code>minuend == null</code>, <code>minuend</code> или <code>subtrahend == null</code>
	 */
	public static BigDecimal subtractNullable(BigDecimal minuend, BigDecimal subtrahend, RoundContext rc) {
		if (minuend == null)
			return subtrahend;
		return round(subtrahend == null ? minuend : minuend.subtract(subtrahend), rc);
	}

	/**
	 * возвращает значение <code>-value</code>
	 * 
	 * @param value	значение
	 * @return	<code>-value</code>
	 */
	public static BigDecimal negate(BigDecimal value) {
		return value == null ? null : value.negate();
	}

	/**
	 * возвращает значение <code>-value</code>
	 * 
	 * @param value	значение
	 * @param rc	контекст
	 * @return	<code>-value</code>
	 */
	public static BigDecimal negate(BigDecimal value, RoundContext rc) {
		return value == null ? null : round(value.negate(), rc);
	}

}
