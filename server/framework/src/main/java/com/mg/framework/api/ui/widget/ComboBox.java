/*
 * ComboBox.java
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

import com.mg.framework.api.ui.FieldEditor;

/**
 * ComboBox widget
 * 
 * @author Oleg V. Safonov
 * @version $Id: ComboBox.java,v 1.3 2007/01/25 14:33:53 safonov Exp $
 */
public interface ComboBox extends FieldEditor {

	/**
	 * ������� �������� ������� ������
	 */
	final static String SELECTED_INDEX = "selectedIndex";

	/**
	 * ������� ������������� ������� ��� �������� ������ ��� ������� ���������
	 */
	final static String USE_INDEX = "useIndex";

	/**
	 * ��� ���������� ������ ���������
	 */
	final static String ITEMS = "jfd:items";
	
	/**
	 * ��� ����������� �������, ������������ ������ ���� <code>ITEMS</code>
	 */
	final static String ITEM = "jfd:item";
	
	/**
	 * ������� �������� ��������, ������������ ������ ���� <code>ITEM</code>
	 */
	final static String ITEM_VALUE = "value";
	
	/**
	 * ��������� ��������� � ������
	 * 
	 * @param items	������ ���������
	 */
	void setItems(Object[] items);
}
