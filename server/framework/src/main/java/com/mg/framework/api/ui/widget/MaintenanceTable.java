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
 * ������� � ������������ ���������� ������-����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTable.java,v 1.5 2008/12/23 09:35:42 safonov Exp $
 */
public interface MaintenanceTable extends Table {

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
	 * ������������ ������ "���������� ��������" ������������ ����
	 */
	final static String CLONE_MENU_ITEM = "cloneMenuItem";

	/**
	 * ������������ ������ "���������� ���������" ������������ ����
	 */
	final static String DEEP_CLONE_MENU_ITEM = "deepCloneMenuItem";

	/**
	 * ������������ ������ "��������" ������������ ����
	 */
	final static String REFRESH_MENU_ITEM = "refreshMenuItem";
	
	/**
	 * ������������ ������ "������� ������" ������������ ����
	 */
	final static String RESTRICTION_MENU_ITEM = "restrictionMenuItem";
	
	/**
	 * ������������ ������ "������" ������������ ����
	 */
	final static String PRINT_MENU_ITEM = "printMenuItem";

	/**
	 * ������������ ������ "��������� �������" ������������ ����
	 */
	final static String SETUP_TABLE_MENU_ITEM = "setupTableMenuItem";

}
