/*
 * ShuttleModel.java
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


/**
 * ������ �������� "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleModel.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleModel {

	/**
	 * �������� ������ ���������
	 * 
	 * @return	������ ��������
	 */
	Object[] getLeadingList();
	
	/**
	 * ���������� ������ ���������
	 * 
	 * @param contents	������ ��������
	 */
	void setLeadingList(Object[] contents);
	
	/**
	 * �������� ������ ���������
	 * 
	 * @return	������ ��������
	 */
	Object[] getTrailingList();
	
	/**
	 * ���������� ������ ���������
	 * 
	 * @param contents	������ ��������
	 */
	void setTrailingList(Object[] contents);
	
	/**
	 * �������� ��������� �� ������
	 * 
	 * @param listener	���������
	 */
	void addShuttleModelListener(ShuttleModelListener listener);
	
	/**
	 * ������� ��������� �� ������
	 * 
	 * @param listener	���������
	 */
	void removeShuttleModelListener(ShuttleModelListener listener);
}
