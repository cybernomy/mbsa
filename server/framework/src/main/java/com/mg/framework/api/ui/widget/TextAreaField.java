/*
 * TextAreaField.java
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
 * ������������ ������� "��������� �������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TextAreaField.java,v 1.1 2006/10/27 07:58:36 safonov Exp $
 */
public interface TextAreaField extends FieldEditor {

	/**
	 * ������� ����������� ���������� �������
	 */
	final static String COLUMNS = "columns";
	
	/**
	 * ������� ����������� ���������� �����
	 */
	final static String ROWS = "rows";
	
	/**
	 * ������� ������� ��������������� �������� �����
	 */
	final static String LINE_WRAP = "lineWrap";
	
	/**
	 * ������� ������� ��������������� �������� ����� �� ������
	 */
	final static String WORD_WRAP = "wordWrap";
	
	/**
	 * ���������� ����� �� ���������
	 */
	final static int DEFAULT_ROWS = 5;
	
}
