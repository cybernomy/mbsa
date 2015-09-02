/*
 * SystemTenant.java
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
package com.mg.framework.api;

/**
 * A Client is a company or a legal entity. You cannot share data between Clients.
 * Tenant is a synonym for Client.
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemTenant.java,v 1.2 2009/02/18 12:18:19 safonov Exp $
 */
public interface SystemTenant {

	/**
	 * ������������� ��������
	 * 
	 * @return	�������������
	 */
	int getIdentifier();

	/**
	 * ��� ��������
	 * 
	 * @return	���
	 */
	String getCode();
	
	/**
	 * ��� ��������
	 * 
	 * @return	���
	 */
	String getName();
	
	/**
	 * �������� ��������
	 * 
	 * @return	��������
	 */
	String getDescription();
	
	/**
	 * �������� ���� ��������
	 * 
	 * @return	����
	 */
	String getLanguage();
	
}
