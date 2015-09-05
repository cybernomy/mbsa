/*
 * EntityField.java
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

import com.mg.framework.api.ui.FieldEditor;

/**
 * Элемент "Редактор ссылки на сущность"
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityField.java,v 1.4 2007/03/27 08:40:10 safonov Exp $
 */
public interface EntityField extends FieldEditor {
	
	/**
	 * атрибут названия реализации SearchHelp, если установлен, то заменяет используемый в описании типа сущности
	 */
	final static String SEARCH_HELP = "searchHelp";

	/**
	 * атрибут списка свойств для отображения
	 */
	final static String ENTITY_PROPERTY_TEXT = "entityPropertyText";

	/**
	 * атрибут формата отображения
	 */
	final static String ENTITY_FORMAT = "entityFormat";

}
