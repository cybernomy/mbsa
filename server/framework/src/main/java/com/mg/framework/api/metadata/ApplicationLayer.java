/*
 * ApplicationLayer.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.annotations.DataItemName;

/**
 * ������ ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationLayer.java,v 1.2 2008/03/03 13:11:02 safonov Exp $
 */
@DataItemName ("Core.ApplicationLayer")
public enum ApplicationLayer {
	
	/**
	 * System ����� ������ �� �������� �����, ������������� �������������
	 * �������������� �������� ������������� Millennium Business Suite Anywhere
	 * 
	 */
	SYSTEM,
	
	/**
	 * Global solutions ��������������� ��������� ������������� ��� ����������
	 * ���������� �������
	 * 
	 */
	GLOBAL,
	
	/**
	 * Distributor ����������� ��������� ���������������� ������ �����������������,
	 * ���������� ����������� ������ ������� � ������������ � ������������
	 * ���������������� � ���������� ������� ����� ���� ������
	 * 
	 */
	DISTRIBUTOR,
	
	/**
	 * Local solutions ��������������� ��������� ������������� ��� ����������
	 * ��������� �������
	 * 
	 */
	LOCAL,
	
	/**
	 * Business ������������ ��� ���������� "��������������" (��������������, ��� ����������) �
	 * "������������" (�� ���� �� ��������� �� ���������� ��������) ���������� 
	 * 
	 */
	BUSINESS,
	
	/**
	 * Value added resellers ������������ ��� ��������� ���������������� �
	 * ������������ � ������������ ����������� �������, ������ {@link #BUS}
	 * 
	 */
	VAR,
	
	/**
	 * Customer ����� �������������� �������� ��� �������� ����������� ��������������
	 * �������, � ������ ���� �������� ����� ��������� ������� ����������� ���
	 * ���� �����������
	 * 
	 */
	CUSTOMER,
	
	/**
	 * User ����������� ������� �� ������ �������������� ������������, ��������� �
	 * ���������� ��� ������ �������� ���������� ���������, � ������ ���� �����
	 * ��������� ������� ����������� ��� ��������� �������� �����������
	 * 
	 */
	USER
}
