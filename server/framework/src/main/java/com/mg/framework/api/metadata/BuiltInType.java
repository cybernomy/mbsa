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
 * Встроенный тип данных поддерживаемый приложением
 * 
 * @author Oleg V. Safonov
 * @version $Id: BuiltInType.java,v 1.5 2008/07/28 14:07:18 safonov Exp $
 */
@DataItemName ("Core.BuiltInType")
public enum BuiltInType {
	/**
	 * Байт, 8-bit знаковое, имеет значение от -128 до 127 включительно. Соответствует Java типу {@link java.lang.Byte Byte}.
	 * <br>Порядковый номер 0.
	 */
	BYTE,

	/**
	 * Короткое целое, 16-bit знаковое, имеет значение от -32768 до 32767 включительно. Соответствует Java типу {@link java.lang.Short Short}.
	 * <br>Порядковый номер 1.
	 */
	SMALLINTEGER,

	/**
	 * Целое, 32-bit знаковое, имеет значение от –2147483648 до 2147483647 включительно. Соответствует Java типу {@link java.lang.Integer Integer}.
	 * <br>Порядковый номер 2.
	 */
	INTEGER,

	/**
	 * Длинное целое, 64-bit знаковое, имеет значение от –9223372036854775808 до 9223372036854775807 включительно. Соответствует Java типу {@link java.lang.Long Long}.
	 * <br>Порядковый номер 3.
	 */
	BIGINTEGER,

	/**
	 * Знаковый числовой тип с указанной точностью. Соответствует Java типу {@link java.math.BigDecimal BigDecimal}.
	 * <br>Порядковый номер 4.
	 */
	NUMBER,

	/**
	 * Одинарный тип с плавающей точкой, 32-bit, значения и операции описаны в IEEE Standard for Binary Floating-Point Arithmetic,
	 * ANSI/IEEE Standard 754-1985 (IEEE, New York). Соответствует Java типу {@link java.lang.Float Float}.
	 * <br>Порядковый номер 5.
	 */
	FLOAT,

	/**
	 * Тип с плавающей точкой двойной точности, 64-bit, значения и операции описаны в IEEE Standard for Binary Floating-Point Arithmetic,
	 * ANSI/IEEE Standard 754-1985 (IEEE, New York). Соответствует Java типу {@link java.lang.Double Double}.
	 * <br>Порядковый номер 6.
	 */
	DOUBLE,

	/**
	 * Логический тип, имеет значение <code>true</code>-истина и <code>false</code>-ложь. Соответствует Java типу {@link java.lang.Boolean Boolean}.
	 * <br>Порядковый номер 7.
	 */
	BOOLEAN,

	/**
	 * Тип дата/время, предназначен для хранения величины дата/время. Соответствует Java типу {@link java.util.Date Date}.
	 * <br>Порядковый номер 8.
	 */
	DATETIME,

	/**
	 * Тип дата, предназначен для хранения величины даты. Соответствует Java типу {@link java.util.Date Date}.
	 * <br>Порядковый номер 9.
	 */
	DATE,

	/**
	 * Тип время, предназначен для хранения величины времени. Соответствует Java типу {@link java.util.Date Date}.
	 * <br>Порядковый номер 10.
	 */
	TIME,

	/**
	 * Символ, 16-bit беззнаковое целое представляющее код UTF-16, имеет значение от '\u0000' до '\uffff' (0 to 65535) включительно. Соответствует Java типу {@link java.lang.Character Character}.
	 * <br>Порядковый номер 11.
	 */
	CHAR,

	/**
	 * Строка, представляет собой последовательность символов Юникод. Соответствует Java типу {@link java.lang.String String}.
	 * <br>Порядковый номер 12.
	 */
	STRING,

	/**
	 * Сериализованный крупный объект, представляет собой последовательность байт. Соответствует Java типу {@link java.lang.Serializable Serializable}.
	 * Применяется для описания атрибутов связанных с полями СУБД имеющих тип BLOB.
	 * <br>Порядковый номер 13.
	 */
	BLOB,

	/**
	 * Строка, представляет собой последовательность символов Юникод. Соответствует Java типу {@link java.lang.String String}.
	 * Применяется для описания атрибутов связанных с полями СУБД имеющих тип CLOB.
	 * <br>Порядковый номер 14.
	 */
	CLOB,

	/**
	 * Знаковый числовой тип с указанной точностью предназначенный для хранения величины денежной суммы. Соответствует Java типу {@link java.math.BigDecimal BigDecimal}.
	 * <br>Порядковый номер 15.
	 */
	MONETARY_AMOUNT,

	/**
	 * Знаковый числовой тип с указанной точностью предназначенный для хранения количественных величин. Соответствует Java типу {@link java.math.BigDecimal BigDecimal}.
	 * <br>Порядковый номер 16.
	 */
	QUANTITY,

	/**
	 * Перечислимый тип. Соответствует Java типу {@link java.lang.Enum Enum}.
	 * <br>Порядковый номер 17.
	 */
	ENUM,

	/**
	 * Тип сущность. Предназначени для хранения объектов-сущностей. Соответствует Java типу {@link com.mg.framework.api.orm.PersistentObject PersistentObject}.
	 * <br>Порядковый номер 18.
	 */
	ENTITY;
	
	/**
	 * получить Java тип на основании встроенного типа
	 * 
	 * @return	Java тип
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
	 * получить встроенный тип приложения на основании Java типа
	 * 
	 * @param clazz	класс Java типа
	 * @return	встроенный тип или <code>null</code> если нет соответствующего типа
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
