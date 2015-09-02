/*
 * WidgetEvent.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

/**
 * ������� �������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: WidgetEvent.java,v 1.2 2007/11/15 08:34:38 safonov Exp $
 */
public class WidgetEvent extends UIEvent {
	private String actionCommand = null;

	/**
	 * �����������
	 * 
	 * @param source	������� ����������������� ����������
	 */
	public WidgetEvent(Widget source) {
		super(source);
	}

	/**
	 * �����������
	 * 
	 * @param source	������� ����������������� ����������
	 * @param actionCommand	�������
	 */
	public WidgetEvent(Widget source, String actionCommand) {
		super(source);
		this.actionCommand = actionCommand;
	}

	/**
	 * �������� ������� ����������������� ���������� �������������� ������ �������
	 * 
	 * @return	������� ����������������� ����������
	 */
	public Widget getWidget() {
		return (Widget) getSource();
	}

	/**
	 * �������� �������, ��� ������� ������������ ��� ����������� ������� ����
	 * ������������ ���� ���������� ��� ���������� ���������
	 * 
	 * @return the actionCommand	�������
	 */
	public String getActionCommand() {
		return actionCommand;
	}

}
