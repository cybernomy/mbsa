/*
 * AbstractWidgetFactory.java
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
package com.mg.framework.support.ui;

import org.dom4j.Element;

import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.ShowExceptionDialog;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetFactory;

/**
 * ����������� ���������� ������� ��������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractWidgetFactory.java,v 1.5 2006/11/21 15:37:40 safonov Exp $
 */
public abstract class AbstractWidgetFactory implements WidgetFactory {

	/**
	 * ���������� �������� �������� ����������������� ����������
	 * 
	 * @param type		��� ����
	 * @param name		��� ��������
	 * @param element	���������
	 * @param view		���
	 * @return	�������
	 */
	protected abstract Widget doCreateWidget(String type, String name, Element element, View view);
	
	/**
	 * ���������� �������� ������� Alert
	 * 
	 * @return	������
	 */
	protected abstract Alert doCreateAlert();
	
	/**
	 * ���������� �������� ������� ��������� �� ��
	 * 
	 * @return	������
	 */
	protected abstract ShowExceptionDialog doCreateExceptionDialog();
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WidgetFactory#createWidget(java.lang.String, java.lang.String, org.dom4j.Element)
	 */
	public final Widget createWidget(String type, String name, Element element, View view) {
		return doCreateWidget(type, name, element, view);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WidgetFactory#createAlert()
	 */
	public Alert createAlert() {
		return doCreateAlert();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WidgetFactory#createExceptionDialog()
	 */
	public ShowExceptionDialog createExceptionDialog() {
		return doCreateExceptionDialog();
	}

}
