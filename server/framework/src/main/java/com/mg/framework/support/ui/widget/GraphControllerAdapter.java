/*
 * GraphControllerAdapter.java
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

import java.util.List;

import com.mg.framework.support.ui.widget.graph.GraphElement;

/**
 * ������� ����������� �������� ����
 * 
 * @author Oleg V. Safonov
 * @version $Id: GraphControllerAdapter.java,v 1.2 2006/11/21 15:39:15 safonov Exp $
 */
public interface GraphControllerAdapter {
	/**
	 * �������� ������ ��������� �����
	 * 
	 * @return	������ ���������
	 */
	List<? extends GraphElement> getElements();
	
	/**
	 * ���������� ��������� ������ �����
	 * 
	 * @param listener	���������
	 */
	void addGraphModelListener(GraphModelListener listener);
	
	/**
	 * �������� �������� �����
	 * 
	 * @param cells	�������� �����
	 * @param areNewRoots	�������� ����� ���������
	 */
	void selectCells(GraphElement[] cells, boolean[] areNewRoots);
	
	/**
	 * �������� ��������� �� ��������� �������� �����
	 * 
	 * @param cell	������� �����
	 */
	void cellChanged(GraphElement cell);
}
