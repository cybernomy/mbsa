/*
 * AbstractMaintenanceTableModel.java
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
import java.util.List;

import com.mg.framework.api.DataBusinessObjectService;

/**
 * ������ ������� ��������� ������-����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableModel.java,v 1.4 2009/02/09 14:10:24 safonov Exp $
 */
public interface MaintenanceTableModel extends TableModel {

	/**
	 * Return primary key of current row
	 * 
	 * @return	primary key
	 */
	Serializable[] getSelectedPrimaryKeys();

	/**
	 * �������� �������� ������� � ������
	 * 
	 * @param masterKey
	 */
	void setCurrentMaster(Serializable masterKey);

	/**
	 * ���������� ������-��������� ������������� ������ ������
	 * 
	 * @param service	������-���������
	 */
	void setService(DataBusinessObjectService<?, ?> service);

	/**
	 * �������� ���������� � �������� �������
	 * 
	 * @return	��������� ���������� � ��������
	 */
	List<TableColumnInfo> getColumnInfos();

	/**
	 * ���������� ������ ������� �������� �������
	 * 
	 * @param visibleColumns	�������� �������� �������� ��� �����������
	 */
	void setVisibleColumns(List<String> visibleColumns);

	/**
	 * �������� �������� ���������� ����� �� �������
	 * 
	 * @param row	���������� ����� �������
	 * @return	�������� ���������� �����
	 */
	Serializable getPrimaryKey(int row);

}
