/*
 * UserActionInterceptor.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.validator.ValidationContext;

/**
 * ����������� �������� ������������� ��������� (����������, ���������, �����������, ��������)
 * ������� ������-�����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptor.java,v 1.4 2007/02/24 13:39:11 safonov Exp $
 */
public interface UserActionInterceptor {

	/**
	 * �������� ��� ������������
	 * 
	 * @return	���
	 */
	String getName();

	/**
	 * �������� ������ �������������� �������� ������-�����������
	 * 
	 * @return	������ �������������� �������� ������-�����������
	 */
	String[] getHandledServices();

	/**
	 * ������� "����� �����", ���������� ����� ������ �������� ����� ���������
     * � ������
	 * 
	 * @param dialogSession		���������������� ������ �������� ���������
	 * @param validationContext	�������� �������� ������
	 * @param isSaveAction		���� <code>true</code> �� ������� "���������" ����� ���������
	 * @param isCloseAction		���� <code>true</code> �� ������� "�������" ����� ���������
	 */
	void afterInput(MaintenanceConversationSession dialogSession, ValidationContext validationContext, boolean isSaveAction, boolean isCloseAction);

	/**
	 * ������� "����� �������", ���������� ����� ������� ������ � ����� ���������
	 * 
	 * @param dialogSession		���������������� ������ �������� ���������
	 */
	void beforeOutput(MaintenanceConversationSession dialogSession);

}
