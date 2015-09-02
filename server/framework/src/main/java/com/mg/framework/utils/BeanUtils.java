/**
 * BeanUtils.java
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
package com.mg.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.mg.framework.api.InternalException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.PersistentManagerHibernateImpl;

/**
 * Утилиты доступа к объектам сущностям
 * 
 * @author Oleg V. Safonov
 * @version $Id: BeanUtils.java,v 1.4 2009/02/09 13:06:02 safonov Exp $
 */
public class BeanUtils {
	
	public final static char NESTED_DELIM = '.';

	/**
	 * получить значение атрибута
	 * 
	 * @param bean	объект
	 * @param name	имя атрибута
	 * @return	значение атрибута
	 */
	public static Object getProperty(final Object bean, final String name) {
		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}
		
		Method getter = ReflectionUtils.findGetter(
				bean instanceof PersistentObject ? ReflectionUtils.getEntityClass((PersistentObject) bean) : bean.getClass(), name);
		if (getter == null)
			throw new InternalException("Unknown property '" + name + "'");
		try {
			return getter.invoke(bean);
		} catch (IllegalArgumentException e) {
			throw new InternalException("invalid get property: " + name, e);
		} catch (IllegalAccessException e) {
			throw new InternalException("invalid get property: " + name, e);
		} catch (InvocationTargetException e) {
			throw new InternalException("invalid get property: " + name, e);
		}
	}

	/**
	 * получить значение идентификатора сущности, данный метод позволяет получить идентификатор сущности
	 * которая не принадлежит текущей сессии в отличии от метода {@link com.mg.framework.api.orm.PersistentObject#getPrimaryKey()}
	 * который позволяет получить идентификатор только для сущностей принадлежащих текущей сессии
	 * 
	 * @param bean	сущность
	 * @return	значение идентификатора
	 */
	public static Object getIdentifierProperty(final Object bean) {
		if (bean == null)
			throw new IllegalArgumentException("No bean specified");
		String identifierPropertyName = PersistentManagerHibernateImpl.getFactory().getClassMetadata(ReflectionUtils.getEntityClass((PersistentObject) bean)).getIdentifierPropertyName();
		return getProperty(bean, identifierPropertyName);
	}

	/**
	 * установить значение атрибута
	 * 
	 * @param bean	объект
	 * @param name	имя атрибута
	 * @param value	значение атрибута
	 */
	public static void setProperty(final Object bean, final String name, final Object value) {
		if (bean == null) {
			throw new IllegalArgumentException("No bean specified");
		}
		if (name == null) {
			throw new IllegalArgumentException("No name specified");
		}
		
		Method setter = ReflectionUtils.findSetter(
				bean instanceof PersistentObject ? ReflectionUtils.getEntityClass((PersistentObject) bean) : bean.getClass(), name);
		if (setter == null)
			throw new InternalException("Unknown property '" + name + "'");
		try {
			setter.invoke(bean, value);
		} catch (IllegalArgumentException e) {
			throw new InternalException("invalid get property: " + name, e);
		} catch (IllegalAccessException e) {
			throw new InternalException("invalid get property: " + name, e);
		} catch (InvocationTargetException e) {
			throw new InternalException("invalid get property: " + name, e);
		}
	}

	/**
	 * сравнение значения свойства сущности
	 * 
	 * @param oldValue	старое значение
	 * @param newValue	новое значение
	 * @return	<code>true</code> если значения различны
	 */
	public static boolean isPropertyDifferent(Object oldValue, Object newValue) {
    	if (oldValue instanceof BigDecimal && newValue instanceof BigDecimal)
    		return ((BigDecimal) oldValue).compareTo((BigDecimal) newValue) != 0;
    	else if (oldValue instanceof Timestamp && newValue instanceof Date)
    		return ((Date) oldValue).compareTo((Date) newValue) != 0;
    	else if (oldValue instanceof Date && newValue instanceof Timestamp)
    		return ((Date) newValue).compareTo((Date) oldValue) != 0;
    	else
    		return ((oldValue == null) && (newValue != null)) || (oldValue != null && !oldValue.equals(newValue));
	}

}
