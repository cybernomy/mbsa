/*
 * FieldEditor.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import com.mg.framework.api.metadata.ui.FieldMetadata;

/**
 * �������� ���� ������ ��������� � ������������ �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: FieldEditor.java,v 1.3 2008/03/13 07:47:16 safonov Exp $
 */
public interface FieldEditor {
	
	/**
	 * ������� ������� ������� ��������� ����� ��� ���������, ����� �������� <code>Boolean</code>,
	 * �� ��������� ����� ������������
	 */
	final static String SHOW_LABEL = "showLabel";

	/**
	 * ������� ������ �� ������� ������, ������ ��������� ��� �������� ������, ��������� ��������������
	 * ������������� ������� ������ � ������
	 */
	final static String DATA_ITEM = "dataItem";
	
	/**
	 * ���������� �������� ���������
	 * 
	 * @param value	�������� ������
	 */
	public void setEditorValue(Object value);
	
	/**
	 * �������� �������� ���������
	 * 
	 * @return	�������� ���������
	 */
	public Object getEditorValue();
	
	/**
	 * ���������� ���������� ���������
	 * 
	 * @param metadata	����������
	 */
	public void setFieldMetadata(FieldMetadata metadata);
}
