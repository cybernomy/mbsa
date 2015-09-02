/*
 * MaintenanceTable.java
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
 * “аблица с возможностью поддержкой бизнес-компонента
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTable.java,v 1.5 2008/12/23 09:35:42 safonov Exp $
 */
public interface MaintenanceTable extends Table {

	/**
	 * наименование пункта "—оздать" контекстного меню
	 */
	final static String ADD_MENU_ITEM = "addMenuItem";

	/**
	 * наименование пункта "»зменить" контекстного меню
	 */
	final static String EDIT_MENU_ITEM = "editMenuItem";
	
	/**
	 * наименование пункта "”далить" контекстного меню
	 */
	final static String ERASE_MENU_ITEM = "eraseMenuItem";
	
	/**
	 * наименование пункта "ѕросмотреть" контекстного меню
	 */
	final static String VIEW_MENU_ITEM = "viewMenuItem";
	
	/**
	 * наименование пункта "копировать частично" контекстного меню
	 */
	final static String CLONE_MENU_ITEM = "cloneMenuItem";

	/**
	 * наименование пункта "копировать полностью" контекстного меню
	 */
	final static String DEEP_CLONE_MENU_ITEM = "deepCloneMenuItem";

	/**
	 * наименование пункта "ќбновить" контекстного меню
	 */
	final static String REFRESH_MENU_ITEM = "refreshMenuItem";
	
	/**
	 * наименование пункта "”слови€ отбора" контекстного меню
	 */
	final static String RESTRICTION_MENU_ITEM = "restrictionMenuItem";
	
	/**
	 * наименование пункта "ѕечать" контекстного меню
	 */
	final static String PRINT_MENU_ITEM = "printMenuItem";

	/**
	 * наименование пункта "Ќастройка таблицы" контекстного меню
	 */
	final static String SETUP_TABLE_MENU_ITEM = "setupTableMenuItem";

}
