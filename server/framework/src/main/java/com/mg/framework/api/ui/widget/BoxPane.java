/*
 * BoxPane.java
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

import com.mg.framework.api.ui.Container;

/**
 * Элемент "Блочный менеджер расположения BoxLayout"
 * 
 * @author Oleg V. Safonov
 * @version $Id: BoxPane.java,v 1.4 2008/07/14 15:46:20 safonov Exp $
 */
public interface BoxPane extends Container {

	/**
	 * атрибут выравнивания
	 */
	public static final String ALIGNMENT = "alignment"; //$NON-NLS-1$

	/**
	 * выранивание по умолчанию
	 */
	public static final String DEFAULT_ALIGNMENT = "left_top"; //$NON-NLS-1$
	
	/**
	 * атрибут ориентации
	 */
	public final static String ORIENTATION = "orientation"; //$NON-NLS-1$
	
	/**
	 * вертикальная ориентация
	 */
	public final static String VERTICAL_ORIENTATION = "ver"; //$NON-NLS-1$
	
	/**
	 * горизонтальная ориентация
	 */
	public final static String HORIZONTAL_ORIENTATION = "hor"; //$NON-NLS-1$
	
	/**
	 * количество колонок
	 */
	public final static String COLUMNS = "columns"; //$NON-NLS-1$
	
	/**
	 * количество рядов
	 */
	public final static String ROWS = "rows"; //$NON-NLS-1$
	
	/**
	 * зазор между рядами
	 */
	public final static String VERTICAL_GAP = "verticalGap"; //$NON-NLS-1$
	
	/**
	 * зазор между столбцами
	 */
	public final static String HORIZONTAL_GAP = "horizontalGap"; //$NON-NLS-1$
	
	/**
	 * количество ячеек по горизонтали занимаемое элементом
	 */
	public final static String HORIZONTAL_SPAN = "horizontalSpan"; //$NON-NLS-1$
	
	/**
	 * количество ячеек по вертикали занимаемое элементом
	 */
	public final static String VERTICAL_SPAN = "verticalSpan"; //$NON-NLS-1$

	/**
	 * количество ячеек которое бодет пропущено перед добавлением элемента
	 */
	public final static String SKIP = "skip"; //$NON-NLS-1$

	/**
	 * установка типа расположения
	 * 
	 * @param vertical	если <code>true</code> то вертикальный, иначе горизонтальный
	 */
	void setVertical(boolean vertical);
	
	/**
	 * установка зазора между рядами
	 * 
	 * @param verticalGap	зазор между рядами
	 */
	void setVerticalGap(int verticalGap);
	
	/**
	 * установка зазора между столбцами
	 * 
	 * @param horizontalGap	зазор между столбцами
	 */
	void setHorizontalGap(int horizontalGap);
	
}
