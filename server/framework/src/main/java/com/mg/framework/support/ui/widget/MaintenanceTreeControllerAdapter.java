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
 * ������� ����������� �������� ������ � ������������ ��������� �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTreeControllerAdapter.java,v 1.4 2007/11/15 08:49:27 safonov Exp $
 */
public interface MaintenanceTreeControllerAdapter extends TreeControllerAdapter {
	
	/**
	 * ���������� ��������
	 *
	 */
	void add();
	
	/**
	 * �������������� ��������
	 *
	 */
	void edit();
	
	/**
	 * �������� ��������
	 *
	 */
	void view();
	
	/**
	 * ���������� ������ ������
	 *
	 */
	void refresh();
	
	/**
	 * �������� ��������
	 *
	 */
	void erase();
	
	/**
	 * ��������� ���� ������� �� ������� ������
	 *
	 */
	void setupPermissions();
	
	/**
	 * �������� ������ ������������� �������� ��������� ��� ������� ������
	 * 
	 * @return	������ ������������� ��������
	 */
	CustomActionDescriptor[] getCustomUserActions();
	
	/**
	 * �������� ������������� ������� ��������
	 * 
	 * @return	�������������
	 */
	Serializable getCurrentNodeId();
	
	/**
	 * �������� ������-��������� ��� ������� ������
	 * 
	 * @return	������-���������
	 */
	BusinessObjectService getService();
	
}
