/*
 * UIProfileManager.java
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
 * �������� ������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfileManager.java,v 1.1 2007/03/13 13:24:27 safonov Exp $
 */
public interface UIProfileManager {

	/**
	 * ��������� �������
	 * 
	 * @param name	��� �������
	 * @return	�������
	 */
	UIProfile load(String name);

	/**
	 * ��������� �������
	 * 
	 * @param profile	�������
	 */
	void store(UIProfile profile);

}
