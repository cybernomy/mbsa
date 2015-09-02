/**
 * TableCellRenderParameters.java
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

import com.mg.framework.api.ui.Color;
import com.mg.framework.api.ui.Font;
import com.mg.framework.api.ui.HorizontalAlignment;
import com.mg.framework.api.ui.Icon;
import com.mg.framework.api.ui.VerticalAlignment;

/**
 * ��������� ��������� ������ �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableCellRenderParameters.java,v 1.1 2008/07/24 15:16:05 safonov Exp $
 */
public interface TableCellRenderParameters {
	/**
	 * ���������� ���� ����
	 * 
	 * @param color	���� ����
	 */
	void setBackground(Color color);

	/**
	 * ���������� ���� ������
	 * 
	 * @param color	���� ������
	 */
	void setForeground(Color color);

	/**
	 * ���������� ������� ������������
	 * 
	 * @param opaque	���� <code>true</code> �� �� ����������
	 */
	void setOpaque(boolean opaque);

	/**
	 * ���������� �����
	 * 
	 * @param font	�����
	 */
	void setFont(Font font);

	/**
	 * ���������� ������� ����������� ������
	 * 
	 * @param textVisible	���� <code>true</code> �� ����� ������������
	 */
	void setTextVisible(boolean textVisible);

	/**
	 * ���������� �������� ����� ������� � �������
	 * 
	 * @param iconTextGap	�������� � ��������
	 */
	void setIconTextGap(int iconTextGap);

	/**
	 * ���������� �������������� ������������
	 * 
	 * @param alignment	�������������� ������������
	 */
	void setHorizontalAlignment(HorizontalAlignment alignment);

	/**
	 * ���������� ������������ ������������
	 * 
	 * @param alignment	������������ ������������
	 */
	void setVerticalAlignment(VerticalAlignment alignment);

	/**
	 * ���������� ������ ��� �����������
	 * 
	 * @param icon	������
	 */
	void setIcon(Icon icon);
}
