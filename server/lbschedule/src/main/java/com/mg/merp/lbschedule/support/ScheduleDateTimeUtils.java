/*
 * ScheduleDateTimeUtils.java
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

package com.mg.merp.lbschedule.support;

import java.util.Calendar;
import java.util.Locale;

/**
 * ������� ��������� ��� (��� ������-���������� "������� ������������")
 * @author Artem V. Sharapov
 * @version $Id: ScheduleDateTimeUtils.java,v 1.1 2007/04/17 12:50:59 sharapov Exp $
 */
public class ScheduleDateTimeUtils {

	/**
	 * ���������� ���� ������ ���� 
	 * @param date 
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ������ ����
	 */
	public static java.util.Date startOfTheYear(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);//������ ����
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		return calendar.getTime();
	}

	/**
	 * ���������� ����� ����
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ����� ���� (31.12.XX 23:59:59)
	 */
	public static java.util.Date endOfTheYear(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);//����� ����
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * ���������� ����� ������ 
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ������������� � ����� ������ (��������� ����� ������ XX.XX.XX 23:59:59)
	 */
	public static java.util.Date endOfTheMonth(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * ���������� ����� ������ 
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ������������� � ����� ������ (����������� 23:59:59)
	 */
	public static java.util.Date endOfTheWeek(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * ���������� ������ ������
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ������������� � ������ ������ (�����������)
	 */
	public static java.util.Date startOfTheWeek(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * ����������� ������ �� ����� �������� ���������� amount
	 * @param date
	 * @param amount - ��� ����������
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ����������� �� ����� �������� ���������� amount
	 */
	public static java.util.Date incWeek(java.util.Date date, int amount, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * ���������� ������ ������
	 * @param date
	 * @param locale
	 * @return ���� ������������� � ������ ������ (01.XX.XX)
	 */
	public static java.util.Date startOfTheMonth(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * ����������� ��� �� ����� �������� ���������� amount
	 * @param date
	 * @param amount - ��� �������������
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� � ����������� �����
	 */
	public static java.util.Date incYear(java.util.Date date, int amount, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}

	/**
	 * ����������� ����� �� ����� �������� ���������� amount
	 * @param date
	 * @param amount - ��� �������������
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� � ����������� �������
	 */
	public static java.util.Date incMonth(java.util.Date date, int amount, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * ����������� ���� �� ����� �������� ���������� amount
	 * @param date
	 * @param amount - ��� �������������
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� � ����������� ����
	 */
	public static java.util.Date incDay(java.util.Date date, int amount, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
	}

	/**
	 * ���������� ���������
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ��������� (������ ��� ������)
	 */
	public static int halfYearOf(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) < 6 ? 1 : 2;
	}

	/**
	 * ���������� �������
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return �������
	 */
	public static int qurterOf(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * ���������� ����� ������ ����
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ����� ������ ����
	 */
	public static int weekOfYear(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * ���������� ������ ���������
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ������ ���������
	 */
	public static java.util.Date beginOfTheHalfYear(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		if(calendar.get(Calendar.MONTH) <= 6) {
			calendar.set(Calendar.MONTH, Calendar.JANUARY);//������ 1 ���������
			calendar.set(Calendar.DATE, 1);
			return calendar.getTime();
		}
		else {
			calendar.set(Calendar.MONTH, Calendar.JULY);//������ 2 ���������
			calendar.set(Calendar.DATE, 1);
			return calendar.getTime();
		}
	}

	/**
	 * ���������� ������ ��������
	 * @param date
	 * @param locale - ������ ���������(����, �������) ����������
	 * @return ���� ������ ��������
	 */
	public static java.util.Date beginOfTheQuarter(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		int quarter = ((calendar.get(Calendar.MONTH)) / 3) + 1;
		calendar.set(Calendar.MONTH, quarter * 3 - 3);//������ ��������
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
}
