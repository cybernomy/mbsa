/*
 * MenuItem.java
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
 * Ёлемент "Ёлемент меню"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MenuItem.java,v 1.4 2008/06/07 09:16:13 safonov Exp $
 */
public interface MenuItem extends Widget {

	/**
	 * атрибут клавиш быстрого доступа, должен иметь следующий синтаксис:
	 * <pre> "&lt;modifiers&gt; &lt;key&gt;" modifiers := shift | control | meta | alt
	 * key := символ клавиши быстрого доступа. </pre>
	 */
	static final String KEY_STROKE = "keyStroke"; //$NON-NLS-1$
	
	/**
	 * атрибут тип переключател€, может иметь следующие значени€: {@link #TOGGLE_NONE},
	 * {@link #TOGGLE_CHECK_BOX}, {@link #TOGGLE_RADIO_BUTTON}
	 * 
	 * @since 4.0.6
	 */
	static final String TOGGLE = "toggle"; //$NON-NLS-1$
	
	/**
	 * пункт меню не €вл€етс€ переключателем, используетс€ по умолчанию
	 */
	static final String TOGGLE_NONE = "none"; //$NON-NLS-1$
	
	/**
	 * пункт меню имеет тип переключател€ CheckBox
	 */
	static final String TOGGLE_CHECK_BOX = "checkBox"; //$NON-NLS-1$
	
	/**
	 * пункт меню имеет тип переключател€ Radio button, не поддерживаетс€
	 * в текущей версии
	 */
	static final String TOGGLE_RADIO_BUTTON = "radioButton"; //$NON-NLS-1$
	
	/**
	 * тэг элемента меню с которым св€зано действие
	 */
	static final String MENU_ITEM = "jfd:menuItem"; //$NON-NLS-1$
	
	/**
	 * тэг элемента подменю
	 */
	static final String MENU = "jfd:menu"; //$NON-NLS-1$
	
	/**
	 * тэг элемента меню разделитель
	 */
	static final String SEPARATOR = "jfd:separator"; //$NON-NLS-1$
}
