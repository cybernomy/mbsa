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
 * ������� "�������� � ��������� ��������� ������-����������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTree.java,v 1.4 2007/08/16 13:48:49 safonov Exp $
 */
public interface MaintenanceTree extends Tree {
	/**
	 * ������������ ������ "�������" ������������ ����
	 */
	final static String ADD_MENU_ITEM = "addMenuItem";

	/**
	 * ������������ ������ "��������" ������������ ����
	 */
	final static String EDIT_MENU_ITEM = "editMenuItem";
	
	/**
	 * ������������ ������ "�������" ������������ ����
	 */
	final static String ERASE_MENU_ITEM = "eraseMenuItem";
	
	/**
	 * ������������ ������ "�����������" ������������ ����
	 */
	final static String VIEW_MENU_ITEM = "viewMenuItem";
	
	/**
	 * ������������ ������ "��������" ������������ ����
	 */
	final static String REFRESH_MENU_ITEM = "refreshMenuItem";
	
	/**
	 * ������������ ������ "��������" ������������ ����
	 */
	final static String PERMISSION_MENU_ITEM = "permissionMenuItem";

}
