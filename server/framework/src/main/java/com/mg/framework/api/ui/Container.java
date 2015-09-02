/*
 * Container.java
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
package com.mg.framework.api.ui;

import org.dom4j.Element;

/**
 * ��������� ���������� ������ �������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Container.java,v 1.2 2006/11/21 15:31:23 safonov Exp $
 */
public interface Container extends Widget {
	
	/**
	 * ������� ���� ��������� ������������
	 */
	final static String LAYOUT = "layout";
	
	/**
	 * ������ ��������� ����������, ���������� ������� ����� ����������� ���������
	 *
	 */
	public void startContainer();
	
	/**
	 * ���������� ��������� ����������, ���������� ������� ����� ����������� ���� ���������
	 *
	 */
	public void endContainer();

	/**
	 * ������� �������� ����������������� ���������� � ���������
	 * 
	 * @param widget
	 * @param widgetLabel
	 * @param element
	 */
	public void add(Widget widget, Widget widgetLabel, Element element);
}
