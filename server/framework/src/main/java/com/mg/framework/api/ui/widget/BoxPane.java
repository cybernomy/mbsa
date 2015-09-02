/*
 * BoxPane.java
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
 * ������� "������� �������� ������������ BoxLayout"
 * 
 * @author Oleg V. Safonov
 * @version $Id: BoxPane.java,v 1.4 2008/07/14 15:46:20 safonov Exp $
 */
public interface BoxPane extends Container {

	/**
	 * ������� ������������
	 */
	public static final String ALIGNMENT = "alignment"; //$NON-NLS-1$

	/**
	 * ����������� �� ���������
	 */
	public static final String DEFAULT_ALIGNMENT = "left_top"; //$NON-NLS-1$
	
	/**
	 * ������� ����������
	 */
	public final static String ORIENTATION = "orientation"; //$NON-NLS-1$
	
	/**
	 * ������������ ����������
	 */
	public final static String VERTICAL_ORIENTATION = "ver"; //$NON-NLS-1$
	
	/**
	 * �������������� ����������
	 */
	public final static String HORIZONTAL_ORIENTATION = "hor"; //$NON-NLS-1$
	
	/**
	 * ���������� �������
	 */
	public final static String COLUMNS = "columns"; //$NON-NLS-1$
	
	/**
	 * ���������� �����
	 */
	public final static String ROWS = "rows"; //$NON-NLS-1$
	
	/**
	 * ����� ����� ������
	 */
	public final static String VERTICAL_GAP = "verticalGap"; //$NON-NLS-1$
	
	/**
	 * ����� ����� ���������
	 */
	public final static String HORIZONTAL_GAP = "horizontalGap"; //$NON-NLS-1$
	
	/**
	 * ���������� ����� �� ����������� ���������� ���������
	 */
	public final static String HORIZONTAL_SPAN = "horizontalSpan"; //$NON-NLS-1$
	
	/**
	 * ���������� ����� �� ��������� ���������� ���������
	 */
	public final static String VERTICAL_SPAN = "verticalSpan"; //$NON-NLS-1$

	/**
	 * ���������� ����� ������� ����� ��������� ����� ����������� ��������
	 */
	public final static String SKIP = "skip"; //$NON-NLS-1$

	/**
	 * ��������� ���� ������������
	 * 
	 * @param vertical	���� <code>true</code> �� ������������, ����� ��������������
	 */
	void setVertical(boolean vertical);
	
	/**
	 * ��������� ������ ����� ������
	 * 
	 * @param verticalGap	����� ����� ������
	 */
	void setVerticalGap(int verticalGap);
	
	/**
	 * ��������� ������ ����� ���������
	 * 
	 * @param horizontalGap	����� ����� ���������
	 */
	void setHorizontalGap(int horizontalGap);
	
}
