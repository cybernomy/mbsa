/*
 * ButtonImpl.java
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

import com.mg.framework.api.ui.Widget;

/**
 * ������� "������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Button.java,v 1.7 2008/07/24 15:14:01 safonov Exp $
 */
public interface Button extends Widget {
	
	/**
	 * ������� �������� ������ ����������� ������� �� ������ 
	 */
	static final String ACTION_LISTENER = "actionListener"; //$NON-NLS-1$

	/**
	 * ������� �������� ������� ��������, ����� ����������� ��� ����������� �������
	 * � �����������, ������� ����������� ��������� ���������������� ��������� 
	 */
	static final String ACTION_COMMAND = "actionCommand"; //$NON-NLS-1$

	/**
	 * ������� ��������� ������
	 * 
	 * @deprecated
	 */
	static final String CAPTION = "caption"; //$NON-NLS-1$

	/**
	 * ������� ��������� ������
	 */
	static final String TEXT = "text"; //$NON-NLS-1$

	/**
	 * ������� ������, �������� ��� ������� ��������� ����������� ������
	 */
	static final String ICON = "icon"; //$NON-NLS-1$
	
	/**
	 * ������� ����������� ���������� ����� ������� � ������ � �������� 
	 */
	static final String ICON_TEXT_GAP = "iconTextGap"; //$NON-NLS-1$
	
	/**
	 * ��������� ������
	 * 
	 * @param text	�����
	 */
	void setText(String text);
	
	/**
	 * ��������� ������
	 * 
	 * @return
	 */
	String getText();
}
