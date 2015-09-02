/*
 * SplitPane.java
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

import com.mg.framework.api.ui.Container;

/**
 * ������� "������ �����������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SplitPane.java,v 1.4 2008/06/06 13:28:02 safonov Exp $
 */
public interface SplitPane extends Container {
	/**
	 * ������� ����������, ����� ����� �������� "hor" ��� "ver"
	 */
	final static String ORIENTATION = "orientation"; //$NON-NLS-1$
	
	/**
	 * ������������ ����������
	 */
	final static String VERTICAL_ORIENTATION = "ver"; //$NON-NLS-1$
	
	/**
	 * �������������� ����������
	 */
	final static String HORIZONTAL_ORIENTATION = "hor"; //$NON-NLS-1$

	/**
	 * ������� ��������� ��������, �������� � ���������, ��������� ������� ������
	 * ����� ����� (��� �������) ������� ���������� ������ ���������
	 */
	final static String DIVIDER_LOCATION = "dividerLocation"; //$NON-NLS-1$

	/**
	 * ������� ��������� �������� ����������/�������, ��� boolean
	 */
	final static String ONE_TOUCH_EXPANDABLE = "oneTouchExpandable";

	/**
	 * ������� ��������� ������� ��������, �������� � ��������
	 */
	final static String DIVIDER_SIZE = "dividerSize";

	/**
	 * ��������� ��������� ��������, �������� � ���������
	 * 
	 * @see #DIVIDER_LOCATION
	 * 
	 * @param dividerLocation	��������� ��������
	 */
	void setDividerLocation(double dividerLocation);
	
	/**
	 * �������� ��������� ��������
	 * 
	 * @see #DIVIDER_LOCATION
	 * 
	 * @return	��������� ��������
	 */
	double getDividerLocation();
	
}
