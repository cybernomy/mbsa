/**
 * Menu.java
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
package com.mg.framework.api.ui.widget;

/**
 * ������� "����"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Menu.java,v 1.1 2007/11/15 14:32:22 safonov Exp $
 */
public interface Menu extends MenuItem {

	/**
	 * �������� ������� ���� �� ������������
	 * 
	 * @param id	��� �������� ����
	 * @return	������� ���� ��� <code>null</code> ���� �� ������
	 */
	MenuItem getMenuItem(String id);

}
