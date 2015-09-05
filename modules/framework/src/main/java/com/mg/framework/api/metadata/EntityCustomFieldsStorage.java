/*
 * EntityCustomFieldsStorage.java
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

import java.util.Map;

/**
 * Репозитарий хранения пользовательских полей
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityCustomFieldsStorage.java,v 1.1 2007/01/25 15:17:13 safonov Exp $
 */
public interface EntityCustomFieldsStorage {

	/**
	 * получить значение
	 * 
	 * @param name	имя атрибута
	 * @return	значение
	 */
	Object getValue(String name);
	
	/**
	 * установить значение
	 * 
	 * @param name	имя атрибута
	 * @param value	значение
	 */
	void setValue(String name, Object value);
	
	/**
	 * получить значения всех атрибутов
	 * 
	 * @return	значения
	 */
	Map<String, Object> getValues();
	
	/**
	 * установить значения
	 * 
	 * @param values	значения
	 */
	void setValues(Map<String, Object> values);
	
}
