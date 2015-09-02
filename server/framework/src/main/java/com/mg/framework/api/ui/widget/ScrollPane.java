/*
 * ScrollPane.java
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
import com.mg.framework.api.ui.Widget;

/**
 * ������� "������ ���������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ScrollPane.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface ScrollPane extends Container {

	/**
	 * ������� ������� ������� ���������, ����� ��� <code>Boolean</code>
	 */
	final static String SCROLL = "scroll";
	
	/**
	 * ��������� �������� � �������� ����� �������� ���������
	 * 
	 * @param viewPortView	�������
	 */
	public void setViewPortView(Widget viewPortView);
	
}
