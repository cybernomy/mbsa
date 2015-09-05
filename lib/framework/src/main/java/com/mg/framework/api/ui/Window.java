/*
 * Window.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.ui.widget.Button;

/**
 * Окно, основной элемент-контейнер содержащий другие элементы
 * 
 * @author Oleg V. Safonov
 * @version $Id: Window.java,v 1.4 2008/01/10 08:40:14 safonov Exp $
 */
public interface Window {

	/**
	 * атрибут заголовка окна
	 */
	final static String TITLE = "title";
	
	/**
	 * атрибут указывающий ширину окна в пикселах
	 */
	final static String WIDTH = "width";
	
	/**
	 * атрибут указывающий высоту окна в пикселах
	 */
	final static String HEIGHT = "height";
	
	/**
	 * добавляет в окно элемент
	 * 
	 * @param widget	элемент
	 */
	void add(Widget widget);
	
	/**
	 * установка заголовка
	 * 
	 * @param title	заголовок
	 */
	void setTitle(String title);
	
	/**
	 * показать окно
	 *
	 */
	void show();

	/**
	 * закрыть окно
	 *
	 */
	void close();

	/**
	 * разрушить окно
	 *
	 */
	void dispose();
	
	/**
	 * "упаковывает" имеющиеся в окне компоненты
	 *
	 */
	void pack();

	/**
	 * установить элемент по умолчанию для данной формы, при нажатии <code>ENTER</code>
	 * на форме будет вызван обработчик элемента
	 * 
	 * @param button	элемент по умолчанию или <code>null</code> для сброса
	 */
	void setDefaultButton(Button button);
	
	/**
	 * получить элемент по умолчанию данной формы
	 * 
	 * @return	элемент или <code>null</code> если не установлен
	 */
	Button getDefaultButton();

}
