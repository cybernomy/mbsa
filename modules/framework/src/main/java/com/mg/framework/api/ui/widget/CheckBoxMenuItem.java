/**
 * CheckBoxMenuItem.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

/**
 * Элемент "Элемент меню с переключателем CheckBox"
 * 
 * @author Oleg V. Safonov
 * @version $Id: CheckBoxMenuItem.java,v 1.1 2008/06/07 09:11:49 safonov Exp $
 */
public interface CheckBoxMenuItem extends MenuItem {

	/**
	 * получить значение переключателя
	 * 
	 * @return	значение переключателя, <code>true</code> если переключатель установлен
	 */
	boolean isSelected();

	/**
	 * установить значение переключателя
	 * 
	 * @param selected	значение переключателя, <code>true</code> для установки переключателя
	 */
	void setSelected(boolean selected);

}
