/**
 * CheckBoxMenuItem.java
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

/**
 * ������� "������� ���� � �������������� CheckBox"
 * 
 * @author Oleg V. Safonov
 * @version $Id: CheckBoxMenuItem.java,v 1.1 2008/06/07 09:11:49 safonov Exp $
 */
public interface CheckBoxMenuItem extends MenuItem {

	/**
	 * �������� �������� �������������
	 * 
	 * @return	�������� �������������, <code>true</code> ���� ������������� ����������
	 */
	boolean isSelected();

	/**
	 * ���������� �������� �������������
	 * 
	 * @param selected	�������� �������������, <code>true</code> ��� ��������� �������������
	 */
	void setSelected(boolean selected);

}
