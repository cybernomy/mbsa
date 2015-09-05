/*
 * Controller.java
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
package com.mg.framework.api.ui;

import java.lang.reflect.InvocationTargetException;

import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.ui.FieldMetadata;

/**
 * Базовый интерфейс всех контроллеров интерактивных форм. Используется для
 * взаимодействия с видом.
 * 
 * @author Oleg V. Safonov
 * @version $Id: Controller.java,v 1.4 2008/10/08 11:41:20 safonov Exp $
 */
public interface Controller {
	
	/**
	 * Получить значение поля контроллера. Используется видом для заполнения
	 * элементов формы.
	 * 
	 * @param name	имя поля
	 * @return	значение поля
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	Object getFieldValue(String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 * Установить значение поля контроллера. Используется видом для заполнения
	 * поля значением из элемента формы.
	 * 
	 * @param name	имя поля
	 * @param value	устанавливаемое значение поля
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	void setFieldValue(String name, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 * Получить метаданные поля контроллера. Используется видом для идентификации
	 * необходимой метаинформации используемой при создании элементов формы.
	 * 
	 * @param name	имя поля
	 * @return	метаданные связанные с полем
	 */
	FieldMetadata getFieldMetadata(String name);
	
	/**
	 * Получить тип поля контроллера.
	 * 
	 * @param name	имя поля
	 * @return	тип поля
	 */
	Class<?> getFieldType(String name);

	/**
	 * Вызов обработчика контроллера. Используется видом для вызова обработчика
	 * установленного у элемента формы.
	 * 
	 * @param handlerName	имя обработчика
	 * @param args	аргументы вызова обработчика
	 * @return	результат
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	Object invokeHandler(String handlerName, Object ... args) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	/**
	 * Установка вида
	 * 
	 * @param view	вид
	 */
	void setView(View view);
	
	/**
	 * получить <code>Logger</code>
	 * 
	 * @return	<code>Logger</code>
	 */
	Logger getLogger();

	/**
	 * отменить последнее изменение модели
	 */
	void undo();

	/**
	 * отменить все изменения модели
	 */
	void undoAll();

	/**
	 * сбросить все изменения модели
	 */
	void resetUndo();

	/**
	 * получить признак изменения модели
	 * 
	 * @return	<code>true</code> если модель имела изменения
	 */
	boolean hasModelChanges();

}
