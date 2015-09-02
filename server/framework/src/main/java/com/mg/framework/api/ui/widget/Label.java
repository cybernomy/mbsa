/*
 * Label.java
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
 * Элемент "Текстовая метка"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Label.java,v 1.3 2008/07/24 15:14:43 safonov Exp $
 */
public interface Label extends Widget {
	
	/**
	 * атрибут текста метки
	 */
	final static String TEXT = "text";
	
	/**
	 * атрибут признак видимости текста, имеет тип <code>boolean</code>
	 */
	final static String TEXT_VISIBLE = "textVisible";
	
	/**
	 * атрибут вертикального выравнивания, может иметь значения:
	 * </br>bottom
	 * </br>center
	 * </br>top
	 */
	final static String VERTICAL_ALIGNMENT  = "verticalAlignment";

	/**
	 * атрибут горизонтального выравнивания, может иметь значения:
	 * </br>left
	 * </br>center
	 * </br>right
	 * </br>leading
	 * </br>trailing
	 */
	final static String HORIZONTAL_ALIGNMENT  = "horizontalAlignment";

	/**
	 * установка связанного с меткой элемента
	 * 
	 * @param widget	элемент
	 */
	void setLabelFor(Widget widget);
	
	/**
	 * установка текстового значения метки
	 * 
	 * @param text	тесктовое значение
	 */
	void setText(String text);
}
