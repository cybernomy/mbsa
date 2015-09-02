/*
 * UserActionInterceptorManager.java
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
 * �������� ������������� �������� ������������� ��������� (����������, ���������, �����������, ��������)
 * ������� ������-�����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManager.java,v 1.3 2006/10/26 13:14:33 safonov Exp $
 */
public interface UserActionInterceptorManager {
	
	/**
	 * ����������� ������������
	 * 
	 * @param interceptor	�����������
	 */
    void registerInterceptor(UserActionInterceptor interceptor);
    
    /**
     * �������� ������������
     * 
     * @param interceptor	�����������
     */
    void unregisterInterceptor(UserActionInterceptor interceptor);
    
    /**
     * ����� ������������ ��� ������� ������-���������� ����� ������� ������ � ����� ���������
     * 
     * @param dialogSession	���������������� ������ �������� ���������
     */
    void invokeBeforeOutputInterceptor(MaintenanceConversationSession dialogSession);
    
    /**
     * ����� ������������ ��� ������� ������-���������� ����� ������ �������� ����� ���������
     * � ������
     * 
     * @param dialogSession		���������������� ������ �������� ���������
     * @param validationContext	�������� �������� ������
     * @param isSaveAction		���� <code>true</code> �� ������� "���������" ����� ���������
     * @param isCloseAction		���� <code>true</code> �� ������� "�������" ����� ���������
     */
    void invokeAfterInputInterceptor(MaintenanceConversationSession dialogSession, ValidationContext validationContext,
    		boolean isSaveAction, boolean isCloseAction);

}
