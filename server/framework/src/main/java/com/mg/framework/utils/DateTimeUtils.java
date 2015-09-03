/*
 * DateTimeUtils.java
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
package com.mg.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class for handling java.util.Date, the java.sql data/time classes and
 * related
 * 
 * @author Oleg V. Safonov
 * @version $Id: DateTimeUtils.java,v 1.7 2008/08/12 13:57:09 sharapov Exp $
 */
public class DateTimeUtils {
	
	/**
	 * нулевая дата применяемая в Delphi
	 * 
	 * @return дата 30.12.1899 0:00:00
	 */
	public static final Date LEGACY_ZERO_DATE = new Date(-2209176000000L);

	/**
	 * нулевая дата
	 * 
	 * @return дата 1.1.1970 0:00:00 GMT
	 */
	public static final Date ZERO_DATE = new Date(0L);

	/**
	 * максимальная дата - 01.01.2100
	 * 
	 * @return дата 01.01.2100 0:00:00 GMT
	 */
	public static final Date MAX_DATE = new Date(4102430400000L);
	
	/**
	 * количество миллисекунд в сутках
	 */
	public static final long MSECS_IN_DAY = 3600000L * 24L;
	
	/**
	 * количество миллисекунд в часе
	 */
	public static final long MSECS_IN_HOUR = 3600000L;
	
	/**
	 * Return a Timestamp for right now
	 * 
	 * @return Timestamp for right now
	 */
	public static java.sql.Timestamp nowTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * Return a string formatted as yyyyMMddHHmmss
	 * 
	 * @return String formatted for right now
	 */
	public static String nowDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}

	/**
	 * Return a Date for right now
	 * 
	 * @return Date for right now
	 */
	public static java.util.Date nowDate() {
		return new java.util.Date();
	}

	public static java.sql.Timestamp getDayStart(Date stamp) {
		return getDayStart(stamp, 0);
	}

	public static java.sql.Timestamp getDayStart(Date stamp,
			int daysLater) {
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(stamp);
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
				tempCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		java.sql.Timestamp retStamp = new java.sql.Timestamp(tempCal.getTime()
				.getTime());
		retStamp.setNanos(0);
		return retStamp;
	}

	public static java.sql.Timestamp getNextDayStart(Date stamp) {
		return getDayStart(stamp, 1);
	}

	public static java.sql.Timestamp getDayEnd(Date stamp) {
		return getDayEnd(stamp, 0);
	}

	public static java.sql.Timestamp getDayEnd(Date stamp,
			int daysLater) {
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(stamp);
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
				tempCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		java.sql.Timestamp retStamp = new java.sql.Timestamp(tempCal.getTime().getTime());
		retStamp.setNanos(999999999);
		return retStamp;
	}

	/**
	 * Return the date for the first day of the month
	 * 
	 * @param stamp
	 * @return
	 */
	public static java.sql.Timestamp getMonthStart(Date stamp) {
		return getMonthStart(stamp, 0);
	}

	public static java.sql.Timestamp getMonthStart(Date stamp,
			int daysLater) {
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(stamp);
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), 1,
				0, 0, 0);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		java.sql.Timestamp retStamp = new java.sql.Timestamp(tempCal.getTime()
				.getTime());
		retStamp.setNanos(0);
		return retStamp;
	}

	/**
	 * Return the date for the first day of the week
	 * 
	 * @param stamp
	 * @return
	 */
	public static java.sql.Timestamp getWeekStart(Date stamp) {
		return getWeekStart(stamp, 0);
	}

	public static java.sql.Timestamp getWeekStart(Date stamp,
			int daysLater) {
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(stamp);
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH),
				tempCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		tempCal.set(Calendar.DAY_OF_WEEK, tempCal.getFirstDayOfWeek());
		java.sql.Timestamp retStamp = new java.sql.Timestamp(tempCal.getTime()
				.getTime());
		retStamp.setNanos(0);
		return retStamp;
	}

	/**
	 * Converts a date String into a java.sql.Date
	 * 
	 * @param date
	 *            The date String: MM/DD/YYYY
	 * @return A java.sql.Date made from the date String
	 */
	public static java.sql.Date toSqlDate(String date) {
		java.util.Date newDate = toDate(date, "00:00:00");

		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a java.sql.Date from separate Strings for month, day, year
	 * 
	 * @param monthStr
	 *            The month String
	 * @param dayStr
	 *            The day String
	 * @param yearStr
	 *            The year String
	 * @return A java.sql.Date made from separate Strings for month, day, year
	 */
	public static java.sql.Date toSqlDate(String monthStr, String dayStr,
			String yearStr) {
		java.util.Date newDate = toDate(monthStr, dayStr, yearStr, "0", "0",
				"0");

		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a java.sql.Date from separate ints for month, day, year
	 * 
	 * @param month
	 *            The month int
	 * @param day
	 *            The day int
	 * @param year
	 *            The year int
	 * @return A java.sql.Date made from separate ints for month, day, year
	 */
	public static java.sql.Date toSqlDate(int month, int day, int year) {
		java.util.Date newDate = toDate(month, day, year, 0, 0, 0);

		if (newDate != null)
			return new java.sql.Date(newDate.getTime());
		else
			return null;
	}

	/**
	 * Converts a time String into a java.sql.Time
	 * 
	 * @param time
	 *            The time String: either HH:MM or HH:MM:SS
	 * @return A java.sql.Time made from the time String
	 */
	public static java.sql.Time toSqlTime(String time) {
		java.util.Date newDate = toDate("1/1/1970", time);

		if (newDate != null)
			return new java.sql.Time(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a java.sql.Time from separate Strings for hour, minute, and second.
	 * 
	 * @param hourStr
	 *            The hour String
	 * @param minuteStr
	 *            The minute String
	 * @param secondStr
	 *            The second String
	 * @return A java.sql.Time made from separate Strings for hour, minute, and
	 *         second.
	 */
	public static java.sql.Time toSqlTime(String hourStr, String minuteStr,
			String secondStr) {
		java.util.Date newDate = toDate("0", "0", "0", hourStr, minuteStr,
				secondStr);

		if (newDate != null)
			return new java.sql.Time(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a java.sql.Time from separate ints for hour, minute, and second.
	 * 
	 * @param hour
	 *            The hour int
	 * @param minute
	 *            The minute int
	 * @param second
	 *            The second int
	 * @return A java.sql.Time made from separate ints for hour, minute, and
	 *         second.
	 */
	public static java.sql.Time toSqlTime(int hour, int minute, int second) {
		java.util.Date newDate = toDate(0, 0, 0, hour, minute, second);

		if (newDate != null)
			return new java.sql.Time(newDate.getTime());
		else
			return null;
	}

	/**
	 * Converts a date and time String into a Timestamp
	 * 
	 * @param dateTime
	 *            A combined data and time string in the format "MM/DD/YYYY
	 *            HH:MM:SS", the seconds are optional
	 * @return The corresponding Timestamp
	 */
	public static java.sql.Timestamp toTimestamp(String dateTime) {
		java.util.Date newDate = toDate(dateTime);

		if (newDate != null)
			return new java.sql.Timestamp(newDate.getTime());
		else
			return null;
	}

	/**
	 * Converts a date String and a time String into a Timestamp
	 * 
	 * @param date
	 *            The date String: MM/DD/YYYY
	 * @param time
	 *            The time String: either HH:MM or HH:MM:SS
	 * @return A Timestamp made from the date and time Strings
	 */
	public static java.sql.Timestamp toTimestamp(String date, String time) {
		java.util.Date newDate = toDate(date, time);

		if (newDate != null)
			return new java.sql.Timestamp(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a Timestamp from separate Strings for month, day, year, hour,
	 * minute, and second.
	 * 
	 * @param monthStr
	 *            The month String
	 * @param dayStr
	 *            The day String
	 * @param yearStr
	 *            The year String
	 * @param hourStr
	 *            The hour String
	 * @param minuteStr
	 *            The minute String
	 * @param secondStr
	 *            The second String
	 * @return A Timestamp made from separate Strings for month, day, year,
	 *         hour, minute, and second.
	 */
	public static java.sql.Timestamp toTimestamp(String monthStr,
			String dayStr, String yearStr, String hourStr, String minuteStr,
			String secondStr) {
		java.util.Date newDate = toDate(monthStr, dayStr, yearStr, hourStr,
				minuteStr, secondStr);

		if (newDate != null)
			return new java.sql.Timestamp(newDate.getTime());
		else
			return null;
	}

	/**
	 * Makes a Timestamp from separate ints for month, day, year, hour, minute,
	 * and second.
	 * 
	 * @param month
	 *            The month int
	 * @param day
	 *            The day int
	 * @param year
	 *            The year int
	 * @param hour
	 *            The hour int
	 * @param minute
	 *            The minute int
	 * @param second
	 *            The second int
	 * @return A Timestamp made from separate ints for month, day, year, hour,
	 *         minute, and second.
	 */
	public static java.sql.Timestamp toTimestamp(int month, int day, int year,
			int hour, int minute, int second) {
		java.util.Date newDate = toDate(month, day, year, hour, minute, second);

		if (newDate != null)
			return new java.sql.Timestamp(newDate.getTime());
		else
			return null;
	}

	/**
	 * Converts a date and time String into a Date
	 * 
	 * @param dateTime
	 *            A combined data and time string in the format "MM/DD/YYYY
	 *            HH:MM:SS", the seconds are optional
	 * @return The corresponding Date
	 */
	public static java.util.Date toDate(String dateTime) {
		// dateTime must have one space between the date and time...
		String date = dateTime.substring(0, dateTime.indexOf(" "));
		String time = dateTime.substring(dateTime.indexOf(" ") + 1);

		return toDate(date, time);
	}

	/**
	 * Converts a date String and a time String into a Date
	 * 
	 * @param date
	 *            The date String: MM/DD/YYYY
	 * @param time
	 *            The time String: either HH:MM or HH:MM:SS
	 * @return A Date made from the date and time Strings
	 */
	public static java.util.Date toDate(String date, String time) {
		if (date == null || time == null)
			return null;
		String month;
		String day;
		String year;
		String hour;
		String minute;
		String second;

		int dateSlash1 = date.indexOf("/");
		int dateSlash2 = date.lastIndexOf("/");

		if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
			return null;
		int timeColon1 = time.indexOf(":");
		int timeColon2 = time.lastIndexOf(":");

		if (timeColon1 <= 0)
			return null;
		month = date.substring(0, dateSlash1);
		day = date.substring(dateSlash1 + 1, dateSlash2);
		year = date.substring(dateSlash2 + 1);
		hour = time.substring(0, timeColon1);

		if (timeColon1 == timeColon2) {
			minute = time.substring(timeColon1 + 1);
			second = "0";
		} else {
			minute = time.substring(timeColon1 + 1, timeColon2);
			second = time.substring(timeColon2 + 1);
		}

		return toDate(month, day, year, hour, minute, second);
	}

	/**
	 * Makes a Date from separate Strings for month, day, year, hour, minute,
	 * and second.
	 * 
	 * @param monthStr
	 *            The month String
	 * @param dayStr
	 *            The day String
	 * @param yearStr
	 *            The year String
	 * @param hourStr
	 *            The hour String
	 * @param minuteStr
	 *            The minute String
	 * @param secondStr
	 *            The second String
	 * @return A Date made from separate Strings for month, day, year, hour,
	 *         minute, and second.
	 */
	public static java.util.Date toDate(String monthStr, String dayStr,
			String yearStr, String hourStr, String minuteStr, String secondStr) {
		int month, day, year, hour, minute, second;

		try {
			month = Integer.parseInt(monthStr);
			day = Integer.parseInt(dayStr);
			year = Integer.parseInt(yearStr);
			hour = Integer.parseInt(hourStr);
			minute = Integer.parseInt(minuteStr);
			second = Integer.parseInt(secondStr);
		} catch (Exception e) {
			return null;
		}
		return toDate(month, day, year, hour, minute, second);
	}

	/**
	 * Makes a Date from separate ints for month, day, year, hour, minute, and
	 * second.
	 * 
	 * @param month
	 *            The month int
	 * @param day
	 *            The day int
	 * @param year
	 *            The year int
	 * @param hour
	 *            The hour int
	 * @param minute
	 *            The minute int
	 * @param second
	 *            The second int
	 * @return A Date made from separate ints for month, day, year, hour,
	 *         minute, and second.
	 */
	public static java.util.Date toDate(int month, int day, int year, int hour,
			int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		try {
			calendar.set(year, month - 1, day, hour, minute, second);
		} catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * Makes a date String in the format MM/DD/YYYY from a Date
	 * 
	 * @param date
	 *            The Date
	 * @return A date String in the format MM/DD/YYYY
	 */
	public static String toDateString(java.util.Date date) {
		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		String monthStr;
		String dayStr;
		String yearStr;

		if (month < 10) {
			monthStr = "0" + month;
		} else {
			monthStr = "" + month;
		}
		if (day < 10) {
			dayStr = "0" + day;
		} else {
			dayStr = "" + day;
		}
		yearStr = "" + year;
		return monthStr + "/" + dayStr + "/" + yearStr;
	}

	/**
	 * Makes a time String in the format HH:MM:SS from a Date. If the seconds
	 * are 0, then the output is in HH:MM.
	 * 
	 * @param date
	 *            The Date
	 * @return A time String in the format HH:MM:SS or HH:MM
	 */
	public static String toTimeString(java.util.Date date) {
		if (date == null)
			return "";
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		return (toTimeString(calendar.get(Calendar.HOUR_OF_DAY), calendar
				.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));
	}

	/**
	 * Makes a time String in the format HH:MM:SS from a separate ints for hour,
	 * minute, and second. If the seconds are 0, then the output is in HH:MM.
	 * 
	 * @param hour
	 *            The hour int
	 * @param minute
	 *            The minute int
	 * @param second
	 *            The second int
	 * @return A time String in the format HH:MM:SS or HH:MM
	 */
	public static String toTimeString(int hour, int minute, int second) {
		String hourStr;
		String minuteStr;
		String secondStr;

		if (hour < 10) {
			hourStr = "0" + hour;
		} else {
			hourStr = "" + hour;
		}
		if (minute < 10) {
			minuteStr = "0" + minute;
		} else {
			minuteStr = "" + minute;
		}
		if (second < 10) {
			secondStr = "0" + second;
		} else {
			secondStr = "" + second;
		}
		if (second == 0)
			return hourStr + ":" + minuteStr;
		else
			return hourStr + ":" + minuteStr + ":" + secondStr;
	}

	/**
	 * Makes a combined data and time string in the format "MM/DD/YYYY HH:MM:SS"
	 * from a Date. If the seconds are 0 they are left off.
	 * 
	 * @param date
	 *            The Date
	 * @return A combined data and time string in the format "MM/DD/YYYY
	 *         HH:MM:SS" where the seconds are left off if they are 0.
	 */
	public static String toDateTimeString(java.util.Date date) {
		if (date == null)
			return "";
		String dateString = toDateString(date);
		String timeString = toTimeString(date);

		if (dateString != null && timeString != null)
			return dateString + " " + timeString;
		else
			return "";
	}

	/**
	 * Makes a Timestamp for the beginning of the month
	 * 
	 * @return A Timestamp of the beginning of the month
	 */
	public static java.sql.Timestamp monthBegin() {
		Calendar mth = Calendar.getInstance();

		mth.set(Calendar.DAY_OF_MONTH, 1);
		mth.set(Calendar.HOUR_OF_DAY, 0);
		mth.set(Calendar.MINUTE, 0);
		mth.set(Calendar.SECOND, 0);
		mth.set(Calendar.AM_PM, Calendar.AM);
		return new java.sql.Timestamp(mth.getTime().getTime());
	}

	public static Date convertToTolerance(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// ограничим мартом
		if (c.get(Calendar.MONTH) > 2) {
			c.set(Calendar.MONTH, 2);
		}
		return c.getTime();
	}

	/**
	 * Makes a Timestamp from Date
	 * 
	 * @param date	date
	 * @return	A Timestamp made from the date
	 */
	public static java.sql.Timestamp toTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	private static int getField(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(field);
	}
	
	/**
	 * получить год
	 * 
	 * @param date	дата
	 * @return	год
	 */
	public static int getYear(Date date) {
		return getField(date, Calendar.YEAR);
	}

	/**
	 * получить месяц, первый месяц года имеет порядковый номер 1
	 * 
	 * @param date	дата
	 * @return	месяц
	 */
	public static int getMonth(Date date) {
		return getField(date, Calendar.MONTH) + 1;
	}

	/**
	 * получить день месяца, первый день месяца имеет порядковый номер 1
	 * 
	 * @param date
	 * @return	день месяца
	 */
	public static int getDayOfMonth(Date date) {
		return getField(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * получить день недели
	 * 
	 * @param date
	 * @return	день недели
	 */
	public static int getDayOfWeek(Date date) {
		return getField(date, Calendar.DAY_OF_WEEK);
	}

	/**
	 * получить день года, первый день года имеет порядковый номер 1
	 * 
	 * @param date
	 * @return	день года
	 */	
	public static int getDayOfYear(Date date) {
		return getField(date, Calendar.DAY_OF_YEAR);
	}

	/**
	 * получить час
	 * 
	 * @param date	дата
	 * @return	час
	 */
	public static int getHour(Date date) {
		return getField(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * получить минута
	 * 
	 * @param date	дата
	 * @return	минута
	 */
	public static int getMinute(Date date) {
		return getField(date, Calendar.MINUTE);
	}

	/**
	 * получить секунду
	 * 
	 * @param date	дата
	 * @return	секунда
	 */
	public static int getSecond(Date date) {
		return getField(date, Calendar.SECOND);
	}

	/**
	 * получить миллисекунду
	 * 
	 * @param date	дата
	 * @return	миллисекунда
	 */
	public static int getMillisecond(Date date) {
		return getField(date, Calendar.MILLISECOND);
	}

	private static Date incField(Date date, int field, int number, Locale locale) {
		Calendar c = Calendar.getInstance(locale);
		c.setTime(date);
		c.set(field, c.get(field) + number);
		return c.getTime();
	}
	
	/**
	 * добавить дней
	 * 
	 * @param date	дата
	 * @param numberOfDays	количество дней
	 * @return	дата
	 */
	public static Date incDay(Date date, int numberOfDays) {
		return incDay(date, numberOfDays, ServerUtils.getUserLocale());
	}

	/**
	 * добавить дней
	 * 
	 * @param date	дата
	 * @param numberOfDays	количество дней
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incDay(Date date, int numberOfDays, Locale locale) {
		return incField(date, Calendar.DAY_OF_YEAR, numberOfDays, locale);
	}

	/**
	 * добавить месяцы
	 * 
	 * @param date	дата
	 * @param numberOfMonths	количество месяцев
	 * @return	дата
	 */
	public static Date incMonth(Date date, int numberOfMonths) {
		return incMonth(date, numberOfMonths, ServerUtils.getUserLocale());
	}

	/**
	 * добавить месяцы
	 * 
	 * @param date	дата
	 * @param numberOfMonths	количество месяцев
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incMonth(Date date, int numberOfMonths, Locale locale) {
		return incField(date, Calendar.MONTH, numberOfMonths, locale);
	}

	/**
	 * добавить годы
	 * 
	 * @param date	дата
	 * @param numberOfYears	количество лет
	 * @return	дата
	 */
	public static Date incYear(Date date, int numberOfYears) {
		return incYear(date, numberOfYears, ServerUtils.getUserLocale());
	}

	/**
	 * добавить годы
	 * 
	 * @param date	дата
	 * @param numberOfYears	количество лет
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incYear(Date date, int numberOfYears, Locale locale) {
		return incField(date, Calendar.YEAR, numberOfYears, locale);
	}

	/**
	 * добавить часы
	 * 
	 * @param date	дата
	 * @param numberOfHours	количество часов
	 * @return	дата
	 */
	public static Date incHour(Date date, int numberOfHours) {
		return incHour(date, numberOfHours, ServerUtils.getUserLocale());
	}

	/**
	 * добавить часы
	 * 
	 * @param date	дата
	 * @param numberOfHours	количество часов
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incHour(Date date, int numberOfHours, Locale locale) {
		return incField(date, Calendar.HOUR_OF_DAY, numberOfHours, locale);
	}

	/**
	 * добавить минуты
	 * 
	 * @param date	дата
	 * @param numberOfMinutes	количество минут
	 * @return	дата
	 */
	public static Date incMinute(Date date, int numberOfMinutes) {
		return incMinute(date, numberOfMinutes, ServerUtils.getUserLocale());
	}

	/**
	 * добавить минуты
	 * 
	 * @param date	дата
	 * @param numberOfMinutes	количество минут
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incMinute(Date date, int numberOfMinutes, Locale locale) {
		return incField(date, Calendar.MINUTE, numberOfMinutes, locale);
	}

	/**
	 * добавить секунды
	 * 
	 * @param date	дата
	 * @param numberOfSeconds	количество секунд
	 * @return	дата
	 */
	public static Date incSecond(Date date, int numberOfSeconds) {
		return incSecond(date, numberOfSeconds, ServerUtils.getUserLocale());
	}

	/**
	 * добавить секунды
	 * 
	 * @param date	дата
	 * @param numberOfSeconds	количество секунд
	 * @param locale	локаль
	 * @return	дата
	 */
	public static Date incSecond(Date date, int numberOfSeconds, Locale locale) {
		return incField(date, Calendar.SECOND, numberOfSeconds, locale);
	}

	/**
	 * добавить миллисекунды
	 * 
	 * @param date	дата
	 * @param numberOfMilliseconds	количество миллисекунд
	 * @return	дата
	 */
	public static Date incMillisecond(Date date, int numberOfMilliseconds) {
		return incMillisecond(date, numberOfMilliseconds, ServerUtils.getUserLocale());
	}

	/**
	 * добавить миллисекунды
	 * 
	 * @param date	дата
	 * @param numberOfMilliseconds	количество миллисекунд
	 * @return	дата
	 */
	public static Date incMillisecond(Date date, int numberOfMilliseconds, Locale locale) {
		return incField(date, Calendar.MILLISECOND, numberOfMilliseconds, locale);
	}

	/**
	 * возвращает число дней между двумя датами
	 * 
	 * @param now	1я дата
	 * @param then	2я дата
	 * @return	количество дней
	 */
	public static long getDaysBetween(Date now, Date then) {
		long thenTime = then.getTime();
		long nowTime = now.getTime();
		if (nowTime < thenTime)
			return (thenTime - nowTime + MSECS_IN_HOUR) / MSECS_IN_DAY;
		else
			return (nowTime - thenTime + MSECS_IN_HOUR) / MSECS_IN_DAY;
	}

	/**
	 * Определяет начало полугодия
	 * 
	 * @param date
	 * @return дата начала полугодия
	 */
	public static Date beginOfTheHalfYear(Date date) {
		return beginOfTheHalfYear(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет начало полугодия
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return дата начала полугодия
	 */
	public static Date beginOfTheHalfYear(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		int month = Calendar.JULY;
		if(calendar.get(Calendar.MONTH) < 6)
			month = Calendar.JANUARY;
		
		calendar.set(calendar.get(Calendar.YEAR), month, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Определяет начало квартала
	 * 
	 * @param date
	 * @return дата начала квартала
	 */
	public static Date beginOfTheQuarter(Date date) {
		return beginOfTheQuarter(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет начало квартала
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return дата начала квартала
	 */
	public static Date beginOfTheQuarter(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		int quarter = ((calendar.get(Calendar.MONTH)) / 3) + 1;
		calendar.set(calendar.get(Calendar.YEAR), quarter * 3 - 3, 1, 0, 0, 0);//начало квартала
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Определяет конец месяца 
	 * @param date
	 * @return дата установленная в конец месяца (последнее число месяца XX.XX.XX 23:59:59)
	 */
	public static Date endOfTheMonth(Date date) {
		return endOfTheMonth(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет конец месяца 
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return дата установленная в конец месяца (последнее число месяца XX.XX.XX 23:59:59)
	 */
	public static Date endOfTheMonth(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Определяет конец недели 
	 * @param date
	 * @return дата установленная в конец недели (воскресение 23:59:59)
	 */
	public static Date endOfTheWeek(Date date) {
		return endOfTheWeek(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет конец недели 
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return дата установленная в конец недели (воскресение 23:59:59)
	 */
	public static Date endOfTheWeek(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Определяет конец года
	 * @param date
	 * @return конец года (31.12.XX 23:59:59)
	 */
	public static Date endOfTheYear(Date date) {
		return endOfTheYear(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет конец года
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return конец года (31.12.XX 23:59:59)
	 */
	public static Date endOfTheYear(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59); //конец года
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Определяет полугодие
	 * @param date
	 * @return полугодие (первое или второе)
	 */
	public static int halfYearOf(Date date) {
		return halfYearOf(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет полугодие
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return полугодие (первое или второе)
	 */
	public static int halfYearOf(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) < 6 ? 1 : 2;
	}

	/**
	 * Определяет квартал
	 * @param date
	 * @return квартал
	 */
	public static int qurterOf(Date date) {
		return qurterOf(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет квартал
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return квартал
	 */
	public static int qurterOf(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * Определяет дату начала года 
	 * @param date 
	 * @return начало года
	 */
	public static Date startOfTheYear(Date date) {
		return startOfTheYear(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет дату начала года 
	 * @param date 
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return начало года
	 */
	public static Date startOfTheYear(Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);//начало года
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Определяет номер недели года
	 * @param date
	 * @return номер недели года
	 */
	public static int weekOfYear(java.util.Date date) {
		return weekOfYear(date, ServerUtils.getUserLocale());
	}

	/**
	 * Определяет номер недели года
	 * @param date
	 * @param locale - локаль временных(даты, времени) параметров
	 * @return номер недели года
	 */
	public static int weekOfYear(java.util.Date date, Locale locale) {
		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * определяет имеет ли дата граничные значения
	 * 
	 * @see #LEGACY_ZERO_DATE
	 * @see #MAX_DATE
	 * @see #ZERO_DATE
	 * 
	 * @param value	дата, не может быть <code>null</code>
	 * @return	<code>true</code> если имеет граничные значения
	 */
	public static boolean isBoundDateValue(Date value) {
		return value.compareTo(DateTimeUtils.LEGACY_ZERO_DATE) <= 0
				|| value.compareTo(DateTimeUtils.MAX_DATE) >= 0
				|| value.compareTo(DateTimeUtils.ZERO_DATE) <= 0;
	}
	
	/**
	 * Получить максимальную дату из списка дат
	 * @param dates - список дат
	 * @return максимальная дата
	 */
	public static Date getMaxDate(Date ... dates) {
		return getMinOrMaxDate(true, dates);
	}
	
	/**
	 * Получить минимальную дату из списка дат
	 * @param dates - список дат
	 * @return минимальная дата
	 */
	public static Date getMinDate(Date ... dates) {
		return getMinOrMaxDate(false, dates);
	}
	
	private static Date getMinOrMaxDate(boolean isMaxDateTarget, Date ... dates) {
		Date targetDate = null;
		if(dates != null && dates.length > 0) {
			targetDate = dates[0];
			for(int i = 1; i < dates.length; i++) {
				if(dates[i] == null)
					continue;
				if(targetDate == null && dates[i] != null)
					targetDate = dates[i];
				
				if(isMaxDateTarget) {
					if(dates[i].compareTo(targetDate) > 0)
						targetDate = dates[i];
				} else {
					if(dates[i].compareTo(targetDate) < 0)
						targetDate = dates[i];
				}
			}
		}
		return targetDate;
	}
	
	/**
	 * Выполнить проверку даты <code>date</code> на принадлежность интервалу c <code>dateFrom<code> по <code>dateTill</code>
	 * @param date - проверяемая дата
	 * @param dateFrom - дата с
	 * @param dateTill - дата по
	 * @return true если дата находится в интервале включая границы;<br> false во всех остальных случаях
	 */
	public static boolean isDateBetweenNulable(Date date, Date dateFrom, Date dateTill) {
		if(date == null || dateFrom == null || dateTill == null)
			return false;
		else 
			return (date.compareTo(dateFrom) >= 0 && date.compareTo(dateTill) <= 0);
	}
	
}
