/**
 * CustomActionManager.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.BusinessObjectService;

/**
 * Менеджер настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManager.java,v 1.2 2007/11/15 14:30:40 safonov Exp $
 */
public interface CustomActionManager {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:service=CustomActionManagerService";
	/**
	 * название формлета используемого в описателях форм для создания доступа
	 * к настраиваемым действиям
	 */
	final static String CUSTOM_ACTIONS_AREA_FORMLET = "com.mg.jet.ui.CustomActionsFormlet";
	/**
	 * название обработчика настраеваемых действий
	 */
	final static String CUSTOM_ACTION_LISTENER_NAME = "ExecuteCustomUserAction";
	/**
	 * название подменю контекстного меню содержащего пункты вызова настраиваемых действий
	 */
	final static String CUSTOM_ACTION_MENU_NAME = "customUserActionMenu";
	
	/**
	 * генерация формлета для создания доступа к настраиваемым действиям
	 * 
	 * @param service	бизнес-компонент
	 * @return	формлет
	 */
	String generateActionsArea(BusinessObjectService service);

	/**
	 * выполнить действие
	 * 
	 * @param context	контекст выполнения
	 */
	void executeAction(CustomActionExecutionContext context);
	
	/**
	 * получить список действий для бизнес-компонента
	 * 
	 * @param service	бизнес-компонент
	 * @return	список действие
	 */
	CustomActionDescriptor[] getCustomActions(BusinessObjectService service);
	
}
