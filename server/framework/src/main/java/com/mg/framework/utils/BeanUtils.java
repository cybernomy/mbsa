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
 * ������� ������� � �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: BeanUtils.java,v 1.4 2009/02/09 13:06:02 safonov Exp $
 */
public class BeanUtils {
	
	public final static char NESTED_DELIM = '.';

	/**
	 * �������� �������� ��������
	 * 
	 * @param bean	������
	 * @param name	��� ��������
	 * @return	�������� ��������
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
	 * �������� �������� �������������� ��������, ������ ����� ��������� �������� ������������� ��������
	 * ������� �� ����������� ������� ������ � ������� �� ������ {@link com.mg.framework.api.orm.PersistentObject#getPrimaryKey()}
	 * ������� ��������� �������� ������������� ������ ��� ��������� ������������� ������� ������
	 * 
	 * @param bean	��������
	 * @return	�������� ��������������
	 */
	public static Object getIdentifierProperty(final Object bean) {
		if (bean == null)
			throw new IllegalArgumentException("No bean specified");
		String identifierPropertyName = PersistentManagerHibernateImpl.getFactory().getClassMetadata(ReflectionUtils.getEntityClass((PersistentObject) bean)).getIdentifierPropertyName();
		return getProperty(bean, identifierPropertyName);
	}

	/**
	 * ���������� �������� ��������
	 * 
	 * @param bean	������
	 * @param name	��� ��������
	 * @param value	�������� ��������
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
	 * ��������� �������� �������� ��������
	 * 
	 * @param oldValue	������ ��������
	 * @param newValue	����� ��������
	 * @return	<code>true</code> ���� �������� ��������
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
