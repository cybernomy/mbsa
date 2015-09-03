/*
 * MaintenanceTree.java
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


/**
 * Элемент "Деререво с функциями поддержки бизнес-компонента"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTree.java,v 1.4 2007/08/16 13:48:49 safonov Exp $
 */
public interface MaintenanceTree extends Tree {
	/**
	 * наименование пункта "Создать" контекстного меню
	 */
	final static String ADD_MENU_ITEM = "addMenuItem";

	/**
	 * наименование пункта "Изменить" контекстного меню
	 */
	final static String EDIT_MENU_ITEM = "editMenuItem";
	
	/**
	 * наименование пункта "Удалить" контекстного меню
	 */
	final static String ERASE_MENU_ITEM = "eraseMenuItem";
	
	/**
	 * наименование пункта "Просмотреть" контекстного меню
	 */
	final static String VIEW_MENU_ITEM = "viewMenuItem";
	
	/**
	 * наименование пункта "Обновить" контекстного меню
	 */
	final static String REFRESH_MENU_ITEM = "refreshMenuItem";
	
	/**
	 * наименование пункта "Обновить" контекстного меню
	 */
	final static String PERMISSION_MENU_ITEM = "permissionMenuItem";

}
