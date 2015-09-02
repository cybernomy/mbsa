/*
 * BuiltInType.java
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
package com.mg.framework.api.metadata;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.PersistentObject;

/**
 * ���������� ��� ������ �������������� �����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: BuiltInType.java,v 1.5 2008/07/28 14:07:18 safonov Exp $
 */
@DataItemName ("Core.BuiltInType")
public enum BuiltInType {
	/**
	 * ����, 8-bit ��������, ����� �������� �� -128 �� 127 ������������. ������������� Java ���� {@link java.lang.Byte Byte}.
	 * <br>���������� ����� 0.
	 */
	BYTE,

	/**
	 * �������� �����, 16-bit ��������, ����� �������� �� -32768 �� 32767 ������������. ������������� Java ���� {@link java.lang.Short Short}.
	 * <br>���������� ����� 1.
	 */
	SMALLINTEGER,

	/**
	 * �����, 32-bit ��������, ����� �������� �� �2147483648 �� 2147483647 ������������. ������������� Java ���� {@link java.lang.Integer Integer}.
	 * <br>���������� ����� 2.
	 */
	INTEGER,

	/**
	 * ������� �����, 64-bit ��������, ����� �������� �� �9223372036854775808 �� 9223372036854775807 ������������. ������������� Java ���� {@link java.lang.Long Long}.
	 * <br>���������� ����� 3.
	 */
	BIGINTEGER,

	/**
	 * �������� �������� ��� � ��������� ���������. ������������� Java ���� {@link java.math.BigDecimal BigDecimal}.
	 * <br>���������� ����� 4.
	 */
	NUMBER,

	/**
	 * ��������� ��� � ��������� ������, 32-bit, �������� � �������� ������� � IEEE Standard for Binary Floating-Point Arithmetic,
	 * ANSI/IEEE Standard 754-1985 (IEEE, New York). ������������� Java ���� {@link java.lang.Float Float}.
	 * <br>���������� ����� 5.
	 */
	FLOAT,

	/**
	 * ��� � ��������� ������ ������� ��������, 64-bit, �������� � �������� ������� � IEEE Standard for Binary Floating-Point Arithmetic,
	 * ANSI/IEEE Standard 754-1985 (IEEE, New York). ������������� Java ���� {@link java.lang.Double Double}.
	 * <br>���������� ����� 6.
	 */
	DOUBLE,

	/**
	 * ���������� ���, ����� �������� <code>true</code>-������ � <code>false</code>-����. ������������� Java ���� {@link java.lang.Boolean Boolean}.
	 * <br>���������� ����� 7.
	 */
	BOOLEAN,

	/**
	 * ��� ����/�����, ������������ ��� �������� �������� ����/�����. ������������� Java ���� {@link java.util.Date Date}.
	 * <br>���������� ����� 8.
	 */
	DATETIME,

	/**
	 * ��� ����, ������������ ��� �������� �������� ����. ������������� Java ���� {@link java.util.Date Date}.
	 * <br>���������� ����� 9.
	 */
	DATE,

	/**
	 * ��� �����, ������������ ��� �������� �������� �������. ������������� Java ���� {@link java.util.Date Date}.
	 * <br>���������� ����� 10.
	 */
	TIME,

	/**
	 * ������, 16-bit ����������� ����� �������������� ��� UTF-16, ����� �������� �� '\u0000' �� '\uffff' (0 to 65535) ������������. ������������� Java ���� {@link java.lang.Character Character}.
	 * <br>���������� ����� 11.
	 */
	CHAR,

	/**
	 * ������, ������������ ����� ������������������ �������� ������. ������������� Java ���� {@link java.lang.String String}.
	 * <br>���������� ����� 12.
	 */
	STRING,

	/**
	 * ��������������� ������� ������, ������������ ����� ������������������ ����. ������������� Java ���� {@link java.lang.Serializable Serializable}.
	 * ����������� ��� �������� ��������� ��������� � ������ ���� ������� ��� BLOB.
	 * <br>���������� ����� 13.
	 */
	BLOB,

	/**
	 * ������, ������������ ����� ������������������ �������� ������. ������������� Java ���� {@link java.lang.String String}.
	 * ����������� ��� �������� ��������� ��������� � ������ ���� ������� ��� CLOB.
	 * <br>���������� ����� 14.
	 */
	CLOB,

	/**
	 * �������� �������� ��� � ��������� ��������� ��������������� ��� �������� �������� �������� �����. ������������� Java ���� {@link java.math.BigDecimal BigDecimal}.
	 * <br>���������� ����� 15.
	 */
	MONETARY_AMOUNT,

	/**
	 * �������� �������� ��� � ��������� ��������� ��������������� ��� �������� �������������� �������. ������������� Java ���� {@link java.math.BigDecimal BigDecimal}.
	 * <br>���������� ����� 16.
	 */
	QUANTITY,

	/**
	 * ������������ ���. ������������� Java ���� {@link java.lang.Enum Enum}.
	 * <br>���������� ����� 17.
	 */
	ENUM,

	/**
	 * ��� ��������. ������������� ��� �������� ��������-���������. ������������� Java ���� {@link com.mg.framework.api.orm.PersistentObject PersistentObject}.
	 * <br>���������� ����� 18.
	 */
	ENTITY;
	
	/**
	 * �������� Java ��� �� ��������� ����������� ����
	 * 
	 * @return	Java ���
	 */
	public Class<?> toJavaClass() {
		switch (this) {
		case STRING:
		case CLOB:
			return String.class;
		case CHAR:
			return Character.class;
		case INTEGER:
			return Integer.class;
		case BIGINTEGER:
			return Long.class;
		case SMALLINTEGER:
			return Short.class;
		case FLOAT:
			return Float.class;
		case DOUBLE:
			return Double.class;
		case TIME:
			return Time.class;
		case BOOLEAN:
			return Boolean.class;
		case DATETIME:
			return Timestamp.class;
		case DATE:
			return Date.class;
		case BLOB:
			return byte[].class;
		case BYTE:
			return Byte.class;
		case ENUM:
			return Enum.class;
		case ENTITY:
			return PersistentObject.class;
		case NUMBER:
		case MONETARY_AMOUNT:
		case QUANTITY:
			return BigDecimal.class;
		}
		return null; //not reachable
	}

	/**
	 * �������� ���������� ��� ���������� �� ��������� Java ����
	 * 
	 * @param clazz	����� Java ����
	 * @return	���������� ��� ��� <code>null</code> ���� ��� ���������������� ����
	 */
	public static BuiltInType fromJavaClass(Class<?> clazz) {
    	if (clazz == null)
    		throw new NullPointerException("The clazz must not be null");
    	
    	if (String.class.isAssignableFrom(clazz))
    		return BuiltInType.STRING;
    	else if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.equals(clazz))
    		return BuiltInType.INTEGER;
    	else if (Long.class.isAssignableFrom(clazz) || Long.TYPE.equals(clazz))
    		return BuiltInType.BIGINTEGER;
    	else if (Short.class.isAssignableFrom(clazz) || Short.TYPE.equals(clazz))
    		return BuiltInType.SMALLINTEGER;
    	else if (java.math.BigDecimal.class.isAssignableFrom(clazz))
    		return BuiltInType.NUMBER;
    	else if (Float.class.isAssignableFrom(clazz) || Float.TYPE.equals(clazz))
    		return BuiltInType.FLOAT;
    	else if (Double.class.isAssignableFrom(clazz) || Double.TYPE.equals(clazz))
    		return BuiltInType.DOUBLE;
    	else if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.equals(clazz))
    		return BuiltInType.BOOLEAN;
    	else if (Time.class.isAssignableFrom(clazz))
    		return BuiltInType.TIME;
    	else if (Timestamp.class.isAssignableFrom(clazz))
    		return BuiltInType.DATETIME;
    	else if (Date.class.isAssignableFrom(clazz))
    		return BuiltInType.DATE;
    	else if (byte[].class.isAssignableFrom(clazz))
    		return BuiltInType.BLOB;
    	else if (Enum.class.isAssignableFrom(clazz))
    		return BuiltInType.ENUM;
    	else if (Byte.class.isAssignableFrom(clazz) || Byte.TYPE.equals(clazz))
    		return BuiltInType.BYTE;
    	else if (PersistentObject.class.isAssignableFrom(clazz))
    		return BuiltInType.ENTITY;
    	else if (Character.class.isAssignableFrom(clazz) || Character.TYPE.equals(clazz))
    		return BuiltInType.CHAR;
    	return null;
	}
}
