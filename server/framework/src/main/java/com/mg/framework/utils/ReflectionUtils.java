/*
 * ReflectionUtils.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.SearchHelpName;
import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.orm.PersistentObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: ReflectionUtils.java,v 1.10 2007/12/13 13:31:37 safonov Exp $
 */
public class ReflectionUtils {

	/**
	 * получить метаданные поля класса
	 * 
	 * @param clazz		класс
	 * @param fieldName	имя поля
	 * @return			метаданные
	 */
	public static ReflectionMetadata getFieldReflectionMetadata(Class<?> clazz, String fieldName) {
		return getFieldReflectionMetadata(findDeclaredField(clazz, fieldName));
	}
	
	/**
	 * получить метаданные поля класса
	 * 
	 * @param field	поле
	 * @return		метаданные
	 */
	public static ReflectionMetadata getFieldReflectionMetadata(Field field) {
		ReflectionMetadata result = new ReflectionMetadata();
		if (field != null) {
			Class<?> fieldClazz = field.getType();
			DataItemName dataItemName = field.getAnnotation(DataItemName.class);
        	//если у свойства нет типа данных то проверка наличия ссылки на элемент данных у типа
        	if (dataItemName == null)
        		dataItemName = getDataItemByClass(fieldClazz);
        		
        	result.setDataItemName(dataItemName);
        	result.setPropertyType(fieldClazz);
		}
		return result;
	}
	
	/**
	 * получить метаданные класса
	 * 
	 * @param clazz	класс
	 * @return		метаданные
	 */
	public static ReflectionMetadata getClassReflectionMetadata(Class<?> clazz) {
		ReflectionMetadata result = new ReflectionMetadata();
		result.setDataItemName(getDataItemByClass(clazz));
		result.setPropertyType(clazz);
		return result;
	}
	
	/**
	 * получить метаданные атрибута класса-сущности
	 * 
	 * @param entityClazz	класс-сущность
	 * @param propertyName	имя атрибута
	 * @return				метаданные
	 */
	public static ReflectionMetadata getPropertyReflectionMetadata(Class<? extends PersistentObject> entityClazz, String propertyName) {
		if (propertyName == null)
			throw new NullPointerException("attribute name is null");
        if ("".equals(propertyName))
            throw new IllegalArgumentException("attribute name is empty");

        List<String> propList = StringUtils.split(propertyName, ".");
        if (propList.size() == 0)
            throw new IllegalArgumentException("attribute list is empty: ".concat(propertyName));

        Method m = findGetter(entityClazz, propList.get(0));
        if (m == null)
            throw new IllegalArgumentException("attribute not found: ".concat(propList.get(0)));

        if (propList.size() > 1) {
            Class<?> clazz = m.getReturnType();
        	propList.remove(0);
        	try {
            	return getPropertyReflectionMetadata(clazz.asSubclass(PersistentObject.class), StringUtils.join(propList, "."));        		
        	} catch (ClassCastException e) {
        		throw new IllegalStateException("property is not PersistentObject: ".concat(propertyName));
        	}
        }
        else {
        	DataItemName dataItemAnot;
        	Class<?> clazz = m.getReturnType();
        	//проверим элемент данных у свойства
        	dataItemAnot = m.getAnnotation(DataItemName.class);
        	//если у свойства нет типа данных то проверка наличия ссылки на элемент данных у типа
        	if (dataItemAnot == null)
        		dataItemAnot = getDataItemByClass(clazz);
        	//проверим SearchHelp
        	SearchHelpName searchHelpName = m.getAnnotation(SearchHelpName.class);
        		
        	ReflectionMetadata result = new ReflectionMetadata();
        	result.setDataItemName(dataItemAnot);
        	result.setPropertyType(clazz);
        	if (searchHelpName != null)
        		result.setSearchHelpName(searchHelpName);
        	return result;
        }
	}
	
	/**
	 * получить класс атрибута класса-сущности
	 * 
	 * @param entityClazz	класс-сущность
	 * @param propertyName	имя атрибута
	 * @return				метаданные
	 */
	public static Class<?> getPropertyType(Class<? extends PersistentObject> entityClazz, String propertyName) {
		if (propertyName == null)
			throw new NullPointerException("attribute name is null");
        if ("".equals(propertyName))
            throw new IllegalArgumentException("attribute name is empty");

        List<String> propList = StringUtils.split(propertyName, ".");
        if (propList.size() == 0)
            throw new IllegalArgumentException("attribute list is empty: ".concat(propertyName));

        Method m = findGetter(entityClazz, propList.get(0));
        if (m == null)
            throw new IllegalArgumentException("attribute not found: ".concat(propList.get(0)));

        if (propList.size() > 1) {
            Class<?> clazz = m.getReturnType();
        	propList.remove(0);
        	try {
            	return getPropertyType(clazz.asSubclass(PersistentObject.class), StringUtils.join(propList, "."));        		
        	} catch (ClassCastException e) {
        		throw new IllegalStateException("property is not PersistentObject: ".concat(propertyName), e);
        	}
        }
        else {
        	return m.getReturnType();
        }
	}
	
	private static DataItemName getDataItemByClass(Class<?> clazz) {
    	//поддерживается тип enum и entity
    	if ((Enum.class.isAssignableFrom(clazz) ||
    			PersistentObject.class.isAssignableFrom(clazz)))
    		return clazz.getAnnotation(DataItemName.class);
    	else
    		return null;
	}
	
	/**
	 * получает родовой тип класса по индексу 
	 * 
	 * @param <T>	тип
	 * @param clazz	класс
	 * @param index	индекс
	 * @return		родовой тип
	 * @throws		IndexOutOfBoundsException в случае если индекс превышает количество родовых типов
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericClass(Class<?> clazz, int index) {
		Type[] actualTypeArguments = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
		if (actualTypeArguments.length < index)
			throw new IndexOutOfBoundsException();
		return (Class<T>) actualTypeArguments[index];
	}
	
	/**
	 * возвращает класс объекта-сущности
	 * 
	 * @param entity	объект-сущность
	 * @return			класс объекта-сущности
	 */
	@SuppressWarnings("unchecked")
	public static <T extends PersistentObject> Class<T> getEntityClass(T entity) {
		Class<T> result = (Class<T>) entity.getClass();
		//если в названии класса присутствует javassist, то класс создан с помощью javassist
		//на основании реального класса сущности, реальный класс является предком
		if (result.getName().indexOf("javassist") != -1)
			result = (Class<T>) result.getSuperclass();
		return result;
	}
	
    /**
     * Finds a specific method of an object. Returns the method or null if not
     * found
     */
    public static Method findMethod(Class<?> classObj, String name,
            Class<?> parameterTypes[]) {
        Method method = null;
        try {
            method = classObj.getMethod(name, parameterTypes);
        } catch (Exception e) {
            // OK: will return null.
        }

        return method;
    }

    /**
     * Finds a specific method of an object given the number of parameters.
     * Returns the method or null if not found
     */
    public static Method findMethod(Class<?> classObj, String name, int paramCount) {

        Method method = null;
        try {
            Method[] methods = classObj.getMethods();
            int i = 0;
            boolean found = false;
            while ((i < methods.length) && !found) {
                found = methods[i].getName().equals(name);
                if (found) { // Now check if the number of parameters
                    found = (methods[i].getParameterTypes().length == paramCount);
                }
                i++;
            }
            if (found) {
                method = methods[i - 1]; // Note i-1 !
            }
        } catch (Exception e) {
            // OK: will return null;
        }
        return method;
    }

    /**
     * Finds the getter of a specific attribute in an object. Returns the method
     * for accessing the attributes, null otherwise
     */
    public static Method findGetter(Class<?> classObj, String attribute) {
        // Methods called "is" or "get" tout court are not getters
        if (attribute.length() == 0)
            return null;

        // Look for a method T getX(), where T is not void

        Method m = findMethod(classObj, "get" + attribute, null);
        if (m != null && m.getReturnType() != void.class)
            return m;

        // Look for a method boolean isX()
        // must not be any other type than "boolean", including not "Boolean"

        m = findMethod(classObj, "is" + attribute, null);
        if (m != null && m.getReturnType() == boolean.class)
            return m;

        return null;
    }

    /**
     * Finds the setter of a specific attribute without knowing its type.
     * Returns the method for accessing the attribute, null otherwise
     */
    public static Method findSetter(Class<?> classObj, String attribute) {
        return findMethod(classObj, "set" + attribute, 1);
    }

    /**
	 * Find a method with the given method name and the given parameter types,
	 * declared on the given class or one of its superclasses. Will return a public,
	 * protected, package access, or private method.
	 * <p>Checks <code>Class.getDeclaredMethod</code>, cascading upwards to all superclasses.
	 * @param clazz the class to check
	 * @param methodName the name of the method to find
	 * @param paramTypes the parameter types of the method to find
	 * @return the method object, or null if not found
	 * @see java.lang.Class#getDeclaredMethod
	 */
    public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException ex) {
			if (clazz.getSuperclass() != null) {
				return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
			}
			return null;
		}
	}

    /**
     * Find a field with the given field name declared on the given class or one of its superclasses.
     * 
     * @param clazz
     * @param name
     * @return
     */
    public static Field findDeclaredField(Class<?> clazz, String name) {
    	try {
    		Field fld = clazz.getDeclaredField(name);
    		fld.setAccessible(true);
    		return fld;
    	}
    	catch (NoSuchFieldException ex) {
    		if (clazz.getSuperclass() != null)
    			return findDeclaredField(clazz.getSuperclass(), name);
    		return null;
    	}
    }
    
}
