/*
 * ShuttleControllerAdapter.java
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
 * ������� ������� "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleControllerAdapter.java,v 1.1 2006/08/31 08:36:41 safonov Exp $
 */
public interface ShuttleControllerAdapter {

	/**
	 * �������� ������
	 * 
	 * @return	������
	 */
	ShuttleModel getModel();
	
	/**
	 * �������� ��������� �� ������� �������
	 * 
	 * @param listener	���������
	 */
	void addShuttleListener(ShuttleListener listener);
	
	/**
	 * ������� ��������� �� ������� ��������
	 * 
	 * @param listener	���������
	 */
	void removeShuttleListener(ShuttleListener listener);
	
	/**
	 * ����������� ������ �� ������ ��������� � ������ ��������
	 * 
	 * @param contents	����������
	 */
	void move(Object[] contents);
	
	/**
	 * ����������� ������ �� ������ ��������� � ������ ��������
	 * 
	 * @param contents	����������
	 */
	void remove(Object[] contents);

}
