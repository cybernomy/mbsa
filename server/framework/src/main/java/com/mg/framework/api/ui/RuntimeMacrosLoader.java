/*
 * RuntimeMacrosLoader.java
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

/**
 * ��������� ������������ ��������, ������������ ��� �������� ���� UI, ���������� ������ ���������
 * ������ ��������������� ����������� ����������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: RuntimeMacrosLoader.java,v 1.1 2007/01/25 14:38:28 safonov Exp $
 */
public interface RuntimeMacrosLoader {

	/**
	 * ��������� ������
	 * 
	 * @param name	��� �������
	 * @return	���� �������
	 */
	String loadMacros(String name);
	
}
