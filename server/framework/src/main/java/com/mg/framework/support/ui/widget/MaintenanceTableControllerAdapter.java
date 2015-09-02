/*
 * MaintenanceTableControllerAdapter.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.ui.CustomActionDescriptor;


/**
 * ������� ����������� �������� ������� � ������������ ��������� �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableControllerAdapter.java,v 1.8 2008/12/23 09:19:54 safonov Exp $
 */
public interface MaintenanceTableControllerAdapter extends TableControllerAdapter {
	
	/**
	 * ���������� ������� ��������
	 *
	 */
	void add();
	
	/**
	 * ��������� ������� ��������
	 *
	 */
	void edit();
	
	/**
	 * �������� ������� ��������
	 *
	 */
	void erase();
	
	/**
	 * �������� ������� ��������
	 *
	 */
	void view();
	
	/**
	 * ����������� ��������
	 * 
	 * @param deepClone
	 */
	void clone(boolean deepClone);
	
	/**
	 * ���������� ������ �������
	 *
	 */
	void refresh();
	
	/**
	 * ��������� �����������
	 *
	 */
	void setRestriction();
	
	/**
	 * �������� ������ ������������� �������� ��������� ��� ������ �������
	 * 
	 * @return	������ ������������� ��������
	 */
	CustomActionDescriptor[] getCustomUserActions();

	/**
	 * ����� ������� ������
	 *
	 */
	void print();
	
	/**
	 * �������� ������-��������� ��� ������ �������
	 * 
	 * @return	������-���������
	 */
	BusinessObjectService getService();

	/**
	 * ��������� ������� ��� �������
	 */
	void setup();

}
