/**
 * ComboMenuBar.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
 * ������� "����������� ����"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ComboMenuBar.java,v 1.1 2008/05/19 14:30:35 safonov Exp $
 */
public interface ComboMenuBar extends Widget {

	/**
	 * �������� ������� ���� �� ������������
	 * 
	 * @param id	��� �������� ����
	 * @return	������� ���� ��� <code>null</code> ���� �� ������
	 */
	MenuItem getMenuItem(String id);

	/**
	 * ��������� ���������
	 * 
	 * @param text	���������
	 */
	void setText(String text);
	
}
