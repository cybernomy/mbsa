/*
 * MaintenanceTreeControllerAdapter.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.support.ui.widget;

import java.io.Serializable;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.ui.CustomActionDescriptor;

/**
 * адаптер визуального элемента дерево с возможностью поддержки объектов сущностей
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTreeControllerAdapter.java,v 1.4 2007/11/15 08:49:27 safonov Exp $
 */
public interface MaintenanceTreeControllerAdapter extends TreeControllerAdapter {
	
	/**
	 * добавление сущности
	 *
	 */
	void add();
	
	/**
	 * редактирование сущности
	 *
	 */
	void edit();
	
	/**
	 * просмотр сущности
	 *
	 */
	void view();
	
	/**
	 * обновление модели дерева
	 *
	 */
	void refresh();
	
	/**
	 * удаление сущности
	 *
	 */
	void erase();
	
	/**
	 * настройка прав доступа на элемент дерева
	 *
	 */
	void setupPermissions();
	
	/**
	 * получить список настраиваемых действий доступных для данного дерева
	 * 
	 * @return	список настраиваемых действий
	 */
	CustomActionDescriptor[] getCustomUserActions();
	
	/**
	 * получить идентификатор текущей сущности
	 * 
	 * @return	идентификатор
	 */
	Serializable getCurrentNodeId();
	
	/**
	 * получить бизнес-компонент для данного дерева
	 * 
	 * @return	бизнес-компонент
	 */
	BusinessObjectService getService();
	
}
