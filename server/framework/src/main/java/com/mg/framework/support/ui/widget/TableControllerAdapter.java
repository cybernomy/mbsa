/*
 * TableControllerAdapter.java
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

import java.io.Serializable;

import com.mg.framework.api.ui.widget.Table;

/**
 * ������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableControllerAdapter.java,v 1.6 2009/02/09 13:43:19 safonov Exp $
 */
public interface TableControllerAdapter extends Serializable {
	
	/**
	 * ���������� ������ �������
	 * 
	 * @return	������
	 */
	TableModel getModel();

	/**
	 * ��������� ���������� �����
	 * 
	 * @param rows ���������� ����
	 */
	void setSelectedRows(int[] rows);
	
	/**
	 * ��������� ������ �������
	 * 
	 * @param columns ������ �������
	 */
	void setColumns(TableColumnModel[] columns);

	/**
	 * ��������� �������� ����������������� ���������� "�������" ���������� � ������ ������������
	 * 
	 * @param table	������� UI "�������"
	 */
	void setTable(Table table);

}
