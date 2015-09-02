/*
 * Filler.java
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
 * ������� "�����������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Filler.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface Filler extends Widget {

	/**
	 * ������� ��� �����������, ����� ����� ��������� ��������
	 * </br>glue
	 * </br>horizontalGlue
	 * </br>verticalGlue
	 * </br>verticalStrut
	 * </br>horizontalStrut
	 * </br>rigidArea
	 * 
	 * @see #GLUE
	 * @see #HORIZONTAL_GLUE
	 * @see #VERTICAL_GLUE
	 * @see #VERTICAL_STRUT
	 * @see #HORIZONTAL_STRUT
	 * @see #RIGID_AREA
	 */
	final static String FILLER_TYPE = "type";
	
	/**
	 * ������������� �� ��������� � ����������� ������� ��� ��������� ������������
	 */
	final static String GLUE = "glue";
	
	/**
	 * ������������� �� ����������� ������� ��� ��������� ������������
	 */
	final static String HORIZONTAL_GLUE = "horizontalGlue";
	
	/**
	 * ������������� �� ��������� ������� ��� ��������� ������������
	 */
	final static String VERTICAL_GLUE = "verticalGlue";
	
	/**
	 * ������������ ��������, ������ �������� ��������������� ����� ������� {@link #HEIGHT}
	 */
	final static String VERTICAL_STRUT = "verticalStrut";
	
	/**
	 * �������������� ��������, ������ �������� ��������������� ����� ������� {@link #WIDTH}
	 */
	final static String HORIZONTAL_STRUT = "horizontalStrut";
	
	/**
	 * �������� �� ����������� � ���������, ������ �������� ��������������� ����� �������� {@link #HEIGHT} �
	 * {@link #WIDTH}
	 */
	final static String RIGID_AREA = "rigidArea";
	
	/**
	 * ������� ������� �������� �� �����������, ����� ��� <code>Integer</code>
	 */
	final static String WIDTH = "width";
	
	/**
	 * ������� ������� �������� �� ���������, ����� ��� <code>Integer</code>
	 */
	final static String HEIGHT = "height";
}
