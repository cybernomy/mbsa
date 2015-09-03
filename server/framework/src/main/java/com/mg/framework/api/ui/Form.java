/*
 * Form.java
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
package com.mg.framework.api.ui;

/**
 * форма пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: Form.java,v 1.5 2008/01/10 08:41:53 safonov Exp $
 */
public interface Form {
	/**
	 * стандартное название формы поддержки по умолчанию
	 */
	final static String DEFAULT_MAINTENANCE_NAME = "defaultMaintenance";
	
	/**
	 * стандартное название формы списка по умолчанию
	 */
	final static String DEFAULT_BROWSE_NAME = "defaultBrowse";
	
	/**
	 * префикс обработчиков событий от элементов пользовательского интерфейса
	 */
	final static String ACTION_HANDLER_PREFIX = "onAction";
	
	/**
	 * название обработчика на событие закрытия формы
	 */
	final static String CLOSE_HANDLER = "close";
	
	/**
	 * запуск формы (показ)
	 *
	 */
	void run();
	
	/**
	 * запуск формы (показ) с параметром модальности
	 * 
	 * @param modal	модальность, если <code>true</code> то форма будет показана как модальный диалог
	 */
	void run(boolean modal);
	
	/**
	 * закрыть форму
	 *
	 */
	void close();
	
	/**
	 * установить заголовок формы
	 * 
	 * @param title	заголовок
	 */
	void setTitle(String title);
	
	/**
	 * получить заголовок формы
	 * 
	 * @return заголовок
	 */
	String getTitle();
	
	/**
	 * зарегистрировать слушатель на событие закрытия формы
	 * 
	 * @param listener	слушатель
	 */
	void addCloseActionListener(FormActionListener listener);
	
	/**
	 * удалить слушатель на событие закрытия формы
	 * 
	 * @param listener	слушатель
	 */
	void removeCloseActionListener(FormActionListener listener);
	
	/**
	 * разрушение формы, обычно формы разрушаются автоматически при закрытие
	 *
	 */
	void dispose();
}
