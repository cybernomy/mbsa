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
 * �������� ������������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManager.java,v 1.2 2007/11/15 14:30:40 safonov Exp $
 */
public interface CustomActionManager {
	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=CustomActionManagerService";
	/**
	 * �������� �������� ������������� � ���������� ���� ��� �������� �������
	 * � ������������� ���������
	 */
	final static String CUSTOM_ACTIONS_AREA_FORMLET = "com.mg.jet.ui.CustomActionsFormlet";
	/**
	 * �������� ����������� ������������� ��������
	 */
	final static String CUSTOM_ACTION_LISTENER_NAME = "ExecuteCustomUserAction";
	/**
	 * �������� ������� ������������ ���� ����������� ������ ������ ������������� ��������
	 */
	final static String CUSTOM_ACTION_MENU_NAME = "customUserActionMenu";
	
	/**
	 * ��������� �������� ��� �������� ������� � ������������� ���������
	 * 
	 * @param service	������-���������
	 * @return	�������
	 */
	String generateActionsArea(BusinessObjectService service);

	/**
	 * ��������� ��������
	 * 
	 * @param context	�������� ����������
	 */
	void executeAction(CustomActionExecutionContext context);
	
	/**
	 * �������� ������ �������� ��� ������-����������
	 * 
	 * @param service	������-���������
	 * @return	������ ��������
	 */
	CustomActionDescriptor[] getCustomActions(BusinessObjectService service);
	
}
