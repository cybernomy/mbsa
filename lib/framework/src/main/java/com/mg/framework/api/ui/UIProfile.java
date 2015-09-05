/*
 * UIProfile.java
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
package com.mg.framework.api.ui;

/**
 * Профиль пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfile.java,v 1.1 2007/03/13 13:22:21 safonov Exp $
 */
public interface UIProfile {

	/**
	 * имя профиля
	 * 
	 * @return	имя
	 */
	String getName();
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @return	значений свойство или <code>null</code> если не найдено
	 */
	String getProperty(String name);

	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	String getProperty(String name, String defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	int getProperty(String name, int defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	boolean getProperty(String name, boolean defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	double getProperty(String name, double defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	float getProperty(String name, float defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	long getProperty(String name, long defaultValue);
	
	/**
	 * получить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param defaultValue	значений по умолчанию
	 * @return	значений свойство или <code>defaultValue</code> если не найдено
	 */
	short getProperty(String name, short defaultValue);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, String value);

	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, int value);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, boolean value);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, double value);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, float value);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, long value);
	
	/**
	 * установить значение свойства
	 * 
	 * @param name	имя свойства
	 * @param value	значений
	 */
	void setProperty(String name, short value);

}
