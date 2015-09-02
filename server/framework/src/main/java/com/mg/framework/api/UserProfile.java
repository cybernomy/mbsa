/*
 * UserProfile.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.api;

import java.net.URL;
import java.util.Locale;

/**
 * ������� ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserProfile.java,v 1.7 2007/04/13 14:09:57 safonov Exp $
 */
public interface UserProfile {
	
	/**
	 * ������ ������������
	 * 
	 * @return ������
	 */
	Locale getLocale();
	
	/**
	 * ������������� ������������
	 * 
	 * @return �������������
	 */
	int getIdentificator();
	
	/**
	 * ����� ������������, ��� � ������� ������������ �������� ���� � �������
	 * 
	 * @return �����
	 */
	String getUserName();
	
	/**
	 * �������� ������ ��������������� ����� � ������� ������ ������������
	 * 
	 * @return	�������������� �����
	 */
	int[] getGroups();
	
	/**
	 * �������� ������ ��������������� ����� � ������� ������ ������������ �������������� �������
	 * 
	 * @return	�������������� �����
	 */
	String getGroupsCommaText();
	
	/**
	 * �������� ������ ���������� ������� ������� ������������ �������������
	 * 
	 * @return	������ �������
	 */
	String[] getPermittableSubsystems();
	
	/**
	 * �������� ������� ���� ������������
	 * 
	 * @return	������� ����
	 */
	URL getGlobalMenu();
}
