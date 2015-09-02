/*
 * Shuttle.java
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

import com.mg.framework.api.ui.ControllableWidget;
import com.mg.framework.api.ui.Widget;

/**
 * Пользовательский элемент "Shuttle", предназначен для перемещения значений
 * между двумя списками
 * 
 * @author Oleg V. Safonov
 * @version $Id: Shuttle.java,v 1.1 2006/08/31 08:35:21 safonov Exp $
 */
public interface Shuttle extends Widget, ControllableWidget {

	/**
	 * атрибут заголовка списка источника
	 */
	static final String LEADING_HEADER = "leadingHeader";
	
	/**
	 * атрибут заголовка списка источника
	 */
	static final String TRAILING_HEADER = "trailingHeader";

}
