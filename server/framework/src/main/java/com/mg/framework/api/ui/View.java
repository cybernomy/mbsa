/*
 * View.java
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


import com.mg.framework.api.Logger;

/**
 * Вид
 * 
 * @author Oleg V. Safonov
 * @version $Id: View.java,v 1.10 2008/01/10 08:41:53 safonov Exp $
 */
public interface View {
	
	/**
	 * имя формы пользовательского интерфейса
	 * 
	 * @return	имя
	 */
	String getName();
	
	/**
	 * показать форму вида
	 * 
	 * @param modal	если <code>true</code>, то форма будет модальной
	 */
	void show(boolean modal);
	
	/**
	 * закрыть форму вида
	 *
	 */
	void close();
	
	/**
	 * признак видимости формы
	 * 
	 * @return	<code>true</code> если форма доступна в интерфейсе
	 */
	boolean isVisible();
	
	/**
	 * сброс значений редакторов полей в модель
	 *
	 */
	void flushForm();
	
	/**
	 * заполнение значений редакторов полей из модели
	 *
	 */
	void flushModel();

	/**
	 * установить заголовок формы вида
	 * 
	 * @param title	заголовок
	 */
	void setTitle(String title);
	
	/**
	 * получить заголовок формы вида
	 * 
	 * @return	заголок
	 */
	String getTitle();
	
	/**
	 * получить по имени интерфейсный элемент формы вида
	 * 
	 * @param name	имя элемента
	 * @return	интерфейсный элемент
	 */
	Widget getWidget(String name);
	
	/**
	 * получить элемент "окно" данного вида
	 * 
	 * @return	окно
	 */
	Window getWindow();
	
	/**
	 * получить список всех интерфейсных элементов формы вида
	 * 
	 * @return	список интерфейсных элементов
	 */
	Widget[] getWidgets();
	
	/**
	 * получить контроллер данного вида
	 * 
	 * @return	котроллер
	 */
	Controller getController();
	
	/**
	 * вызвать обработчик события, используется для внутренних целей, не вызывать
	 * в прикладном коде
	 * 
	 * @param handlerName	имя обработчика
	 * @param args			параметры вызова
	 * @return				результат выполнения обработчика, обычно <code>null</code>
	 * @throws Throwable	в случае любой ИС
	 */
	Object invokeHandler(String handlerName, Object ... args) throws Throwable;
	
	/**
	 * получить <code>Logger</code>
	 * 
	 * @return
	 */
	Logger getLogger();
	
	/**
	 * разрушение вида
	 *
	 */
	void dispose();
	
	/**
	 * "упаковывает" имеющиеся в окне компоненты
	 *
	 */
	void pack();

	/**
	 * получить тематический раздел системы помощи для вида
	 * 
	 * @return	тематический раздел или <code>null</code> если не установлен
	 */
	String getHelpTopic();

	/**
	 * получить профиль пользовательского интерфейса формы данного вида
	 * 
	 * @return	профиль, может быть <code>null</code> если невозможно создание профиля
	 */
	UIProfile getUIProfile();

}
