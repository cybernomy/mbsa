/*
 * InternalFrame.java
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
import com.mg.framework.api.ui.Window;

/**
 * ���������� �����, ������������ ��� MDI ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: InternalFrame.java,v 1.4 2006/11/21 15:34:06 safonov Exp $
 */
public interface InternalFrame extends Widget, Window {

	/**
	 * ��������� �� ���������
	 */
	final static String DEFAULT_TITLE = "InternalFrame";
	
	/**
	 * ��������� �������� ����
	 * 
	 * @param width		������ (� ��������)
	 * @param height	������ (� ��������)
	 */
	void setSize(int width, int height);
}
