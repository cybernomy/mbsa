/*
 * ButtonImpl.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Кнопка"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Button.java,v 1.7 2008/07/24 15:14:01 safonov Exp $
 */
public interface Button extends Widget {
	
	/**
	 * атрибут названия метода обработчика нажатия на кнопку 
	 */
	static final String ACTION_LISTENER = "actionListener"; //$NON-NLS-1$

	/**
	 * атрибут названия команды действия, может применяться для распознания команды
	 * в обработчике, который обслуживает несколько пользовательских элементов 
	 */
	static final String ACTION_COMMAND = "actionCommand"; //$NON-NLS-1$

	/**
	 * атрибут заголовка кнопки
	 * 
	 * @deprecated
	 */
	static final String CAPTION = "caption"; //$NON-NLS-1$

	/**
	 * атрибут заголовка кнопки
	 */
	static final String TEXT = "text"; //$NON-NLS-1$

	/**
	 * атрибут иконки, содержит имя ресурса хранящего изображение иконки
	 */
	static final String ICON = "icon"; //$NON-NLS-1$
	
	/**
	 * атрибут указывающий расстояние между иконкой и тектом в пикселах 
	 */
	static final String ICON_TEXT_GAP = "iconTextGap"; //$NON-NLS-1$
	
	/**
	 * установка текста
	 * 
	 * @param text	текст
	 */
	void setText(String text);
	
	/**
	 * получение текста
	 * 
	 * @return
	 */
	String getText();
}
