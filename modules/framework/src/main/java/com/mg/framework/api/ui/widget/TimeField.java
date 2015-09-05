/*
 * TimeField.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ui.FieldEditor;

/**
 * Элемент "Редактор времени"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TimeField.java,v 1.1 2007/06/20 10:52:48 safonov Exp $
 */
public interface TimeField extends FieldEditor {
	
	/**
	 * атрибут формат времени, может иметь следующие значения:
	 * </br>medium
	 * </br>short
	 * 
	 * @see #TIME_MEDIUM
	 * @see #TIME_SHORT
	 */
	final static String TIME_FORMAT = "timeFormat";
	
	/**
	 * средний формат, поведение совпадает с форматом {@link java.text.DateFormat#MEDIUM}
	 */
	final static String TIME_MEDIUM = "medium";
	
	/**
	 * короткий формат, поведение совпадает с форматом {@link java.text.DateFormat#SHORT}
	 */
	final static String TIME_SHORT = "short";

}
