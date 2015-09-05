/*
 * PersistentObject.java
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
package com.mg.framework.api.orm;

import java.util.Collection;

import com.mg.framework.api.*;

/**
 * Объект сущность. Обеспечивает доступ к атрибутам по правилам JavaBean.
 * 
 * @author Oleg V. Safonov
 * $Id: PersistentObject.java,v 1.5 2007/08/16 13:26:40 safonov Exp $
 */
public interface PersistentObject {
	
	/**
	 * получить первичный ключ
	 * 
	 * @return	первичный ключ
	 */
	Object getPrimaryKey();
	
	/**
	 * получить наименование сущности
	 * 
	 * @return
	 */
	String getEntityName();
	
	/**
	 * взять значение атрибута сущности
	 * 
	 * @param name	наименование атрибута
	 * @return		значение атрибута
	 */
	Object getAttribute(String name);
	
	/**
	 * установить значение атрибута сущности
	 * 
	 * @param name	наименование атрибута
	 * @param value	значение атрибута
	 */
	void setAttribute(String name, Object value);
	
	/**
	 * взять значения атрибутов сущности
	 * 
	 * @param keyOfAttributes	список наименований атрибутов
	 * @return	карта наименований/значений атрибутов
	 */
	AttributeMap getAttributes(Collection<String> keyOfAttributes);
	
	/**
	 * взять значения всех атрибутов сущности
	 * 
	 * @return	карта наименований/значений атрибутов
	 */
	AttributeMap getAllAttributes();
	
	/**
	 * установить значения атрибута сущности
	 * 
	 * @param values	карта наименований/значений атрибутов
	 */
	void setAttributes(AttributeMap values);

	/**
	 * копирование объекта сущности, если <code>attributes</code> содержит значения, то будут установлены
	 * соответсвующие атрибуты созданной сущности
	 * 
	 * @param attributes	карта наименований свойств объекта и значений, может быть <code>null</code>
	 * @return	копия объекта сущности или <code>null</code> если копирование не удалось или не реализовано
	 */
	PersistentObject cloneEntity(AttributeMap attributes);
	
	/**
	 * определение наличия атрибута у сущности
	 * 
	 * @param name	имя атрибута
	 * @return		<code>true</code> если сущность имеет атрибут с таким именем
	 */
	boolean hasAttribute(String name);

}
