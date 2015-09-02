/*
 * MenuItem.java
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
 * ������� "������� ����"
 * 
 * @author Oleg V. Safonov
 * @version $Id: MenuItem.java,v 1.4 2008/06/07 09:16:13 safonov Exp $
 */
public interface MenuItem extends Widget {

	/**
	 * ������� ������ �������� �������, ������ ����� ��������� ���������:
	 * <pre> "&lt;modifiers&gt; &lt;key&gt;" modifiers := shift | control | meta | alt
	 * key := ������ ������� �������� �������. </pre>
	 */
	static final String KEY_STROKE = "keyStroke"; //$NON-NLS-1$
	
	/**
	 * ������� ��� �������������, ����� ����� ��������� ��������: {@link #TOGGLE_NONE},
	 * {@link #TOGGLE_CHECK_BOX}, {@link #TOGGLE_RADIO_BUTTON}
	 * 
	 * @since 4.0.6
	 */
	static final String TOGGLE = "toggle"; //$NON-NLS-1$
	
	/**
	 * ����� ���� �� �������� ��������������, ������������ �� ���������
	 */
	static final String TOGGLE_NONE = "none"; //$NON-NLS-1$
	
	/**
	 * ����� ���� ����� ��� ������������� CheckBox
	 */
	static final String TOGGLE_CHECK_BOX = "checkBox"; //$NON-NLS-1$
	
	/**
	 * ����� ���� ����� ��� ������������� Radio button, �� ��������������
	 * � ������� ������
	 */
	static final String TOGGLE_RADIO_BUTTON = "radioButton"; //$NON-NLS-1$
	
	/**
	 * ��� �������� ���� � ������� ������� ��������
	 */
	static final String MENU_ITEM = "jfd:menuItem"; //$NON-NLS-1$
	
	/**
	 * ��� �������� �������
	 */
	static final String MENU = "jfd:menu"; //$NON-NLS-1$
	
	/**
	 * ��� �������� ���� �����������
	 */
	static final String SEPARATOR = "jfd:separator"; //$NON-NLS-1$
}
