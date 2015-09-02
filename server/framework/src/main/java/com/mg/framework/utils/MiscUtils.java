/*
 * MiscUtils.java
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

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.mg.framework.api.Logger;
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.support.Messages;
import com.mg.framework.support.ui.UIUtils;

/**
 * Misc Utility Functions
 * 
 * @author Oleg V. Safonov
 * @version $Id: MiscUtils.java,v 1.13 2008/12/23 09:06:45 safonov Exp $
 */
public class MiscUtils {
	private static Logger logger = ServerUtils.getLogger(MiscUtils.class);

	/**
	 * конвертация списка без родового типа в список с родовым типом
	 * 
	 * @param <T>		родовой тип
	 * @param itemClass	класс родового типа
	 * @param list		список без родового типа
	 * @return	список с родовым типом
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public static <T> List<T> convertUncheckedList(Class<T> itemClass, List list) {
		return new ArrayList<T>(list);
	}

	/**
	 * получить текстовое представление атрибута объекта-сущности, используется локаль подключенного пользователя
	 * 
	 * @param value
	 * @param metadata
	 * @return
	 */
	public static Object getPropertyTextRepresentation(Object value, FieldMetadata metadata) {
		return getPropertyTextRepresentation(value, metadata, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление атрибута объекта-сущности
	 * 
	 * @param value		значение атрибута
	 * @param metadata	метаданные
	 * @param locale	локаль
	 * @return	текстовое представление или <code>value</code> если тип атрибута не имеет специфичных методов представления в UI
	 */
	@SuppressWarnings("unchecked")
	public static Object getPropertyTextRepresentation(Object value, FieldMetadata metadata, Locale locale) {
		if (metadata != null && metadata.getConversionRoutine() != null) {
			//TODO добавить установку контекста импорта, вполне возможно что в данном случае установить его невозможно
			return metadata.getConversionRoutine().outputConverse(value);
		}
		else if (value == null)
			return null;
		else if (value instanceof String)
			return org.apache.commons.lang.StringUtils.stripEnd((String) value, null);
		else if (value instanceof Date) 
			return MiscUtils.getDateTextRepresentation((Date) value, metadata, locale);
		else if (value instanceof Enum)
			return MiscUtils.getEnumTextRepresentation((Enum) value, locale);
		else if (value instanceof Boolean)
			return MiscUtils.getBooleanTextRepresentation((Boolean) value, locale);
		else if (value instanceof PersistentObject)
			return MiscUtils.getPersistentObjectTextRepresentation((PersistentObject) value, metadata, locale);
		else
			return value;
	}

	/**
	 * получить текстовое представление массива пользовательских полей
	 * 
	 * @param value	массив значений пользовательских полей
	 * @param metadata	метаданные
	 * @return	текстовое представление
	 */
	public static String getArrayCustomFieldTextRepresentation(Object[] value, FieldMetadata metadata) {
		return getArrayCustomFieldTextRepresentation(value, metadata, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление массива пользовательских полей
	 * 
	 * @param value	массив значений пользовательских полей
	 * @param metadata	метаданные
	 * @param locale	локаль
	 * @return	текстовое представление
	 */
	public static String getArrayCustomFieldTextRepresentation(Object[] value, FieldMetadata metadata, Locale locale) {
		if (metadata == null)
			return value == null ? null : Arrays.toString(value);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length; i++) {
			Object element = MiscUtils.getPropertyTextRepresentation(value[i], metadata, locale);
			if (element != null) {
				if (i > 0)
					sb.append(", ");
				sb.append(element);
			}			
		}
		return sb.toString();
	}

	/**
	 * получить текстовое представление константы перечислимого типа, используется локаль подключенного пользователя
	 * 
	 * @param value	константа перечислимого типа
	 * @return	текстовое представление
	 * 
	 * @throws NullPointerException если <code>value == null</code>
	 * 
	 * @see #getEnumTextRepresentation(Enum, Locale)
	 */
	public static String getEnumTextRepresentation(Enum<?> value) {
		return getEnumTextRepresentation(value, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление константы перечислимого типа
	 * 
	 * @param value	константа перечислимого типа
	 * @param locale локаль
	 * @return	текстовое представление
	 * 
	 * @throws NullPointerException если <code>value == null</code>
	 */
	public static String getEnumTextRepresentation(Enum<?> value, Locale locale) {
		if (value == null)
			throw new NullPointerException();

		String result;
		EnumConstantText textAnnot = null;
		Field fld = ReflectionUtils.findDeclaredField(value.getClass(), value.name());
		textAnnot = fld.getAnnotation(EnumConstantText.class);
		if (textAnnot != null)
			result = UIUtils.loadL10nText(locale, textAnnot.value());
		else
			result = value.name();
		return result;
	}

	/**
	 * получить текстовое представление объекта логического типа, используется локаль подключенного пользователя
	 * 
	 * @param value	значение
	 * @return	текстовое представление
	 * 
	 * @throws NullPointerException если <code>value == null</code>
	 */
	public static String getBooleanTextRepresentation(Boolean value) {
		return getBooleanTextRepresentation(value, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление объекта логического типа
	 * 
	 * @param value		значение
	 * @param locale	локаль
	 * @return			текстовое представление
	 * 
	 * @throws NullPointerException если <code>value == null</code>
	 */
	public static String getBooleanTextRepresentation(Boolean value, Locale locale) {
		if (value == null)
			throw new NullPointerException();

		return value ? Messages.getInstance().getMessage(Messages.TRUE_TEXT, locale) : Messages.getInstance().getMessage(Messages.FALSE_TEXT, locale);
	}

	/**
	 * генерация стандартного текстового представления объекта сущности, атрибуты сущности перечисляются
	 * через символ <пробел>
	 * 
	 * @param values	значения атрибутов сущности
	 * @return	текстовое представление
	 */
	private static String generateDefaultText(Object[] values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++)
			sb.append(StringUtils.BLANK_STRING).append(values[i]);
		return sb.substring(1);
	}

	/**
	 * получить текстовое представление объекта сущности, используется локаль подключенного пользователя
	 * 
	 * @param persistentObject	сущность
	 * @param metadata			метаданные
	 * @return	текстовое представление
	 * 
	 * @throws NullPointerException если <code>persistentObject == null</code> или <code>metadata == null</code>
	 * 
	 * @see #getPersistentObjectTextRepresentation(PersistentObject, FieldMetadata, Locale)
	 */
	public static String getPersistentObjectTextRepresentation(PersistentObject persistentObject, FieldMetadata metadata) {
		return getPersistentObjectTextRepresentation(persistentObject, metadata, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление объекта сущности
	 * 
	 * @param persistentObject	сущность
	 * @param metadata			метаданные
	 * @param locale			локаль
	 * @return	текстовое представление
	 */
	public static String getPersistentObjectTextRepresentation(PersistentObject persistentObject, FieldMetadata metadata, Locale locale) {
		if (metadata == null)
			throw new NullPointerException("The metadata must not be null");

		return getPersistentObjectTextRepresentation(persistentObject, metadata.getEntityPropertyText(), metadata.getEntityPropertyFormatText(), locale);
	}

	/**
	 * получить текстовое представление объекта сущности
	 * 
	 * @param persistentObject			сущность
	 * @param entityPropertyText		свойства для отображения
	 * @param entityPropertyFormatText	формат отображения (должен быть совместим с форматом метода {@link String#format(String, Object[])})
	 * @return	текстовое представление
	 */
	public static String getPersistentObjectTextRepresentation(PersistentObject persistentObject, String[] entityPropertyText, String entityPropertyFormatText) {
		return getPersistentObjectTextRepresentation(persistentObject, entityPropertyText, entityPropertyFormatText, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление объекта сущности
	 * 
	 * @param persistentObject			сущность
	 * @param entityPropertyText		свойства для отображения
	 * @param entityPropertyFormatText	формат отображения (должен быть совместим с форматом метода {@link String#format(String, Object[])})
	 * @param locale					локаль
	 * @return	текстовое представление
	 */
	public static String getPersistentObjectTextRepresentation(PersistentObject persistentObject, String[] entityPropertyText, String entityPropertyFormatText, Locale locale) {
		if (persistentObject == null)
			throw new NullPointerException("The persistentObject must not be null");

		String result;
		try {
			if (entityPropertyText != null) {
				Object[] values = new Object[entityPropertyText.length];
				for (int i = 0; i < entityPropertyText.length; i++) {
					Object attrValue = persistentObject.getAttribute(entityPropertyText[i]);
					//в строках удалим незначащие пробелы
					if (attrValue instanceof String)
						values[i] = ((String) attrValue).trim();
					else
						values[i] = attrValue;
				}
				if (entityPropertyFormatText != null) {
					try {
						result = String.format(locale, entityPropertyFormatText, values);
					} catch (IllegalFormatException e) {
						logger.error("Illegal format of entity text representation", e);
						result = generateDefaultText(values);
					}
				} else {
					result = generateDefaultText(values);
				}
			} else {
				//legacy implementation
				try {
					result = (String) persistentObject.getAttribute("Code");
				} catch (Exception e) {
					try {
						result = (String) persistentObject.getAttribute("Name");
					} catch (Exception ee) {
						logger.error("The entityPropertyText must be explicit set (Code or Name property is not found)", ee);
						result = "<unknown>";
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error during entity text generation", e);
			result = "<invalid text>";
		}
		return result;
	}

	/**
	 * получить текстовое представление атрибута сущности с типом <code>DATE, DATETIME, TIME</code>. Используется локаль подключенного пользователя
	 * 
	 * @param value		значение атрибута
	 * @param metadata	метаданные
	 * @return	текстовое представление или пустую строку если <code>value == null</code>
	 * 
	 * @see #getDateTextRepresentation(Date, FieldMetadata, Locale)
	 * 
	 * @throws NullPointerException если <code>metadata == null</code>
	 */
	public static String getDateTextRepresentation(Date value, FieldMetadata metadata) {
		return getDateTextRepresentation(value, metadata, ServerUtils.getUserLocale());
	}

	/**
	 * получить текстовое представление атрибута сущности с типом <code>DATE, DATETIME, TIME</code>
	 * 
	 * @param value		значение атрибута
	 * @param metadata	метаданные
	 * @param locale	локаль
	 * @return	текстовое представление или пустую строку если <code>value == null</code>
	 * 
	 * @throws NullPointerException если <code>metadata == null</code>
	 */
	public static String getDateTextRepresentation(Date value, FieldMetadata metadata, Locale locale) {
		if (value == null || DateTimeUtils.isBoundDateValue(value))
			return StringUtils.EMPTY_STRING;
		if (metadata == null)
			return value.toString();

		DateFormat df;
		switch (metadata.getBuiltInType()) {
		case DATE:
			df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
			break;
		case DATETIME:
			df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
			break;
		case TIME:
			df = DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
			break;
		default:
			return value.toString();	
		}
		return df.format(value);
	}

	public static String[] generateEntityPropertyText(String entityPropertyText) {
		List<String> properties = StringUtils.split(entityPropertyText, ",");
		if (properties != null)
			return properties.toArray(new String[properties.size()]);
		else
			return null;
	}

	/**
	 * получить встроенный тип приложения на основании Java типа
	 * 
	 * @param clazz	класс Java типа
	 * @return	встроенный тип
	 */
	public static BuiltInType javaTypeToBuiltInType(Class<?> clazz) {
		return BuiltInType.fromJavaClass(clazz);
	}

	/**
	 * Get an iterator from a collection, returning null if collection is null
	 * @param col The collection to be turned in to an iterator
	 * @return The resulting Iterator
	 */
	public static <E> Iterator<E> toIterator(Collection<E> col) {
		if (col == null)
			return null;
		else
			return col.iterator();
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1) {
		return new MiscUtils.SimpleMap(name1, value1);

		/* Map fields = new HashMap();
         fields.put(name1, value1);
         return fields;*/
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2) {
		return new MiscUtils.SimpleMap(name1, value1, name2, value2);

		/* Map fields = new HashMap();
         fields.put(name1, value1);
         fields.put(name2, value2);
         return fields;*/
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
		return new MiscUtils.SimpleMap(name1, value1, name2, value2, name3, value3);

		/* Map fields = new HashMap();
         fields.put(name1, value1);
         fields.put(name2, value2);
         fields.put(name3, value3);
         return fields;*/
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3,
			Object value3, String name4, Object value4) {
		return new MiscUtils.SimpleMap(name1, value1, name2, value2, name3, value3, name4, value4);

		/* Map fields = new HashMap();
         fields.put(name1, value1);
         fields.put(name2, value2);
         fields.put(name3, value3);
         fields.put(name4, value4);
         return fields;*/
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
			String name4, Object value4, String name5, Object value5) {
		Map<String, Object> fields = new HashMap<String, Object>();

		fields.put(name1, value1);
		fields.put(name2, value2);
		fields.put(name3, value3);
		fields.put(name4, value4);
		fields.put(name5, value5);
		return fields;
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
			String name4, Object value4, String name5, Object value5, String name6, Object value6) {
		Map<String, Object> fields = new HashMap<String, Object>();

		fields.put(name1, value1);
		fields.put(name2, value2);
		fields.put(name3, value3);
		fields.put(name4, value4);
		fields.put(name5, value5);
		fields.put(name6, value6);
		return fields;
	}

	/**
	 * Create a map from passed nameX, valueX parameters
	 * @return The resulting Map
	 */
	public static Map<String, Object> toMap(Object[] data) {
		if (data == null) {
			return null;
		}
		if (data.length % 2 == 1) {
			throw new IllegalArgumentException("You must pass an even sized array to the toMap method");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < data.length; ) {
			map.put((String) data[i++], data[i++]);
		}
		return map;
	}

	/**
	 * Sort a List of Maps by specified consistent keys.
	 * @param listOfMaps List of Map objects to sort.
	 * @param sortKeys List of Map keys to sort by.
	 * @return a new List of sorted Maps.
	 */
	public static <K, V> List<Map<K,Comparable<V>>> sortMaps(List<Map<K,Comparable<V>>> listOfMaps, List<K> sortKeys) {
		if (listOfMaps == null || sortKeys == null)
			return null;
		List<Map<K,Comparable<V>>> toSort = new LinkedList<Map<K,Comparable<V>>>(listOfMaps);
		try {
			MapComparator<K, V> mc = new MapComparator<K, V>(sortKeys);
			Collections.sort(toSort, mc);
		} catch (Exception e) {
			//Debug.logError(e, "Problems sorting list of maps; returning null.", module);
			return null;
		}
		return toSort;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <T> List<T> toList(T obj1) {
		List<T> list = new ArrayList<T>(1);

		list.add(obj1);
		return list;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <E> List<E> toList(E obj1, E obj2) {
		List<E> list = new ArrayList<E>(2);

		list.add(obj1);
		list.add(obj2);
		return list;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <E> List<E> toList(E obj1, E obj2, E obj3) {
		List<E> list = new ArrayList<E>(3);

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		return list;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <E> List<E> toList(E obj1, E obj2, E obj3, E obj4) {
		List<E> list = new ArrayList<E>(4);

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		return list;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <E> List<E> toList(E obj1, E obj2, E obj3, E obj4, E obj5) {
		List<E> list = new ArrayList<E>(5);

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		return list;
	}

	/**
	 * Create a list from passed objX parameters
	 * @return The resulting List
	 */
	public static <E> List<E> toList(E obj1, E obj2, E obj3, E obj4, E obj5, E obj6) {
		List<E> list = new ArrayList<E>(6);

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		list.add(obj6);
		return list;
	}

	public static <E> List<E> toList(Collection<E> collection) {
		if (collection == null) return null;
		if (collection instanceof List) {
			return (List<E>) collection;
		} else {
			return new ArrayList<E>(collection);
		}
	}

	public static <E> List<E> toListArray(E[] data) {
		if (data == null) {
			return null;
		}
		List<E> list = new ArrayList<E>(data.length);
		for (int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
		return list;
	}

	/**
	 * Parse a locale string Locale object
	 * @param localeString The locale string (en_US)
	 * @return Locale The new Locale object or null if no valid locale can be interpreted
	 */
	public static Locale parseLocale(String localeString) {
		if (localeString == null || localeString.length() == 0) {
			return null;
		}

		Locale locale = null;
		if (localeString.length() == 2) {
			// two letter language code
			locale = new Locale(localeString);
		} else if (localeString.length() == 5) {
			// positions 0-1 language, 3-4 are country
			String language = localeString.substring(0, 2);
			String country = localeString.substring(3, 5);
			locale = new Locale(language, country);
		} else if (localeString.length() > 6) {
			// positions 0-1 language, 3-4 are country, 6 and on are special extensions
			String language = localeString.substring(0, 2);
			String country = localeString.substring(3, 5);
			String extension = localeString.substring(6);
			locale = new Locale(language, country, extension);
		} else {
			//Debug.logWarning("Do not know what to do with the localeString [" + localeString + "], should be length 2, 5, or greater than 6, returning null", module);
		}

		return locale;
	}

	/** The input can be a String, Locale, or even null and a valid Locale will always be returned; if nothing else works, returns the default locale.
	 * @param localeObject An Object representing the locale
	 */
	public static Locale ensureLocale(Object localeObject) {
		if (localeObject != null && localeObject instanceof String) {
			localeObject = MiscUtils.parseLocale((String) localeObject);
		}
		if (localeObject != null && localeObject instanceof Locale) {
			return (Locale) localeObject;
		} else {
			return Locale.getDefault();
		}
	}

	public static List<Locale> availableLocaleList = null;
	/** Returns a List of available locales sorted by display name */
	public static List<Locale> availableLocales() {
		if (availableLocaleList == null) {
			synchronized(MiscUtils.class) {
				if (availableLocaleList == null) {
					TreeMap<String, Locale> localeMap = new TreeMap<String, Locale>();
					Locale[] locales = Locale.getAvailableLocales();
					for (int i = 0; i < locales.length; i++) {
						localeMap.put(locales[i].getDisplayName(), locales[i]);
					}
					availableLocaleList = new LinkedList<Locale>(localeMap.values());
				}
			}
		}
		return availableLocaleList;
	}

	/** This is meant to be very quick to create and use for small sized maps, perfect for how we usually use UtilMisc.toMap */
	protected static class SimpleMap implements Map<String, Object>, java.io.Serializable {
		protected Map<String, Object> realMapIfNeeded = null;

		String[] names;
		Object[] values;

		public SimpleMap() {
			names = new String[0];
			values = new Object[0];
		}

		public SimpleMap(String name1, Object value1) {
			names = new String[1];
			values = new Object[1];
			this.names[0] = name1;
			this.values[0] = value1;
		}

		public SimpleMap(String name1, Object value1, String name2, Object value2) {
			names = new String[2];
			values = new Object[2];
			this.names[0] = name1;
			this.values[0] = value1;
			this.names[1] = name2;
			this.values[1] = value2;
		}

		public SimpleMap(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
			names = new String[3];
			values = new Object[3];
			this.names[0] = name1;
			this.values[0] = value1;
			this.names[1] = name2;
			this.values[1] = value2;
			this.names[2] = name3;
			this.values[2] = value3;
		}

		public SimpleMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
			names = new String[4];
			values = new Object[4];
			this.names[0] = name1;
			this.values[0] = value1;
			this.names[1] = name2;
			this.values[1] = value2;
			this.names[2] = name3;
			this.values[2] = value3;
			this.names[3] = name4;
			this.values[3] = value4;
		}

		protected void makeRealMap() {
			realMapIfNeeded = new HashMap<String, Object>();
			for (int i = 0; i < names.length; i++) {
				realMapIfNeeded.put(names[i], values[i]);
			}
			this.names = null;
			this.values = null;
		}

		public void clear() {
			if (realMapIfNeeded != null) {
				realMapIfNeeded.clear();
			} else {
				realMapIfNeeded = new HashMap<String, Object>();
				names = null;
				values = null;
			}
		}

		public boolean containsKey(Object obj) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.containsKey(obj);
			} else {
				for (int i = 0; i < names.length; i++) {
					if (obj == null && names[i] == null) return true;
					if (names[i] != null && names[i].equals(obj)) return true;
				}
				return false;
			}
		}

		public boolean containsValue(Object obj) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.containsValue(obj);
			} else {
				for (int i = 0; i < names.length; i++) {
					if (obj == null && values[i] == null) return true;
					if (values[i] != null && values[i].equals(obj)) return true;
				}
				return false;
			}
		}

		public java.util.Set<Map.Entry<String, Object>> entrySet() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.entrySet();
			} else {
				this.makeRealMap();
				return realMapIfNeeded.entrySet();
			}
		}

		public Object get(Object obj) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.get(obj);
			} else {
				for (int i = 0; i < names.length; i++) {
					if (obj == null && names[i] == null) return values[i];
					if (names[i] != null && names[i].equals(obj)) return values[i];
				}
				return null;
			}
		}

		public boolean isEmpty() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.isEmpty();
			} else {
				if (this.names.length == 0) return true;
				return false;
			}
		}

		public java.util.Set<String> keySet() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.keySet();
			} else {
				this.makeRealMap();
				return realMapIfNeeded.keySet();
			}
		}

		public Object put(String obj, Object obj1) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.put(obj, obj1);
			} else {
				this.makeRealMap();
				return realMapIfNeeded.put(obj, obj1);
			}
		}

		public void putAll(java.util.Map<? extends String, ? extends Object> map) {
			if (realMapIfNeeded != null) {
				realMapIfNeeded.putAll(map);
			} else {
				this.makeRealMap();
				realMapIfNeeded.putAll(map);
			}
		}

		public Object remove(Object obj) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.remove(obj);
			} else {
				this.makeRealMap();
				return realMapIfNeeded.remove(obj);
			}
		}

		public int size() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.size();
			} else {
				return this.names.length;
			}
		}

		public java.util.Collection<Object> values() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.values();
			} else {
				this.makeRealMap();
				return realMapIfNeeded.values();
			}
		}

		public String toString() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.toString();
			} else {
				StringBuffer outString = new StringBuffer("{");
				for (int i = 0; i < names.length; i++) {
					if (i > 0) outString.append(',');
					outString.append('{');
					outString.append(names[i]);
					outString.append(',');
					outString.append(values[i]);
					outString.append('}');
				}
				outString.append('}');
				return outString.toString();
			}
		}

		public int hashCode() {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.hashCode();
			} else {
				int hashCode = 0;
				for (int i = 0; i < names.length; i++) {
					//note that this calculation is done based on the calc specified in the Java java.util.Map interface
					int tempNum = (names[i] == null   ? 0 : names[i].hashCode()) ^
					(values[i] == null ? 0 : values[i].hashCode());
					hashCode += tempNum;
				}
				return hashCode;
			}
		}

		@SuppressWarnings("unchecked")
		public boolean equals(Object obj) {
			if (realMapIfNeeded != null) {
				return realMapIfNeeded.equals(obj);
			} else {
				Map<String, Object> mapObj = (Map<String, Object>) obj;

				//first check the size
				if (mapObj.size() != names.length) return false;

				//okay, same size, now check each entry
				for (int i = 0; i < names.length; i++) {
					//first check the name
					if (!mapObj.containsKey(names[i])) return false;

					//if that passes, check the value
					Object mapValue = mapObj.get(names[i]);
					if (mapValue == null) {
						if (values[i] != null) return false;
					} else {
						if (!mapValue.equals(values[i])) return false;
					}
				}

				return true;
			}
		}
	}

}
