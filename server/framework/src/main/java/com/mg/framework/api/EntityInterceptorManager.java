/*
 * EntityInterceptorManager.java
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

import com.mg.framework.api.orm.PersistentObject;

/**
 * �������� ������������� �������� ��� ��������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManager.java,v 1.4 2007/12/17 09:10:30 safonov Exp $
 */
public interface EntityInterceptorManager {
	
	/**
	 * ����������� ������������ � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� �����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPrePersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� �����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPrePersistInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostPersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostPersistInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ���������� � �������� ����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostCommitPersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ���������� � �������� ����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostCommitPersistInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPreUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPreUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ���������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ���������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ��������� � �������� ����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostCommitUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ��������� � �������� ����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostCommitUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ���������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPreRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ���������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPreRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ��������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ��������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� �������� � �������� ����������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostCommitRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� �������� � �������� ����������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostCommitRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * ����������� ������������ "����� ��������" � ���������
	 * 
	 * @param interceptor	�����������
	 */
    void registerPostLoadInterceptor(EntityInterceptor interceptor);
    
    /**
     * �������� ������������ "����� ��������" �� ���������
     * 
     * @param interceptor	�����������
     */
    void unregisterPostLoadInterceptor(EntityInterceptor interceptor);
    
    /**
     * ����� ������������ ��� �������� ����� ����������� � ���������
     * 
     * @param entity	��������
     */
    void invokeOnPrePersistInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� ���������� � ���������
     * 
     * @param entity	��������
     */
    void invokeOnPostPersistInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� ���������� � ��������� � �������� ����������
     * 
     * @param entity	��������
     */
    void invokeOnPostCommitPersistInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� ��������� �� ���������
     * 
     * @param entity	��������
     */
    void invokeOnPreRemoveInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� �������� �� ���������
     * 
     * @param entity	��������
     */
    void invokeOnPostRemoveInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� �������� �� ��������� � �������� ����������
     * 
     * @param entity	��������
     */
    void invokeOnPostCommitRemoveInterceptor(PersistentObject entity);
    
    /**
     * ����� ������������ ��� �������� ����� ���������� � ���������
     * 
     * @param entity	��������
     * @param oldState	���������� ��������� �������
     */
    void invokeOnPreUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * ����� ������������ ��� �������� ����� ��������� � ���������
     * 
     * @param entity	��������
     * @param oldState	���������� ��������� �������
     */
    void invokeOnPostUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * ����� ������������ ��� �������� ����� ��������� � ��������� � �������� ����������
     * 
     * @param entity	��������
     * @param oldState	���������� ��������� �������
     */
    void invokeOnPostCommitUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * ����� ������������ ��� �������� ����� �������� �� ���������
     * 
     * @param entity	��������
     */
    void invokeOnPostLoadInterceptor(PersistentObject entity);
    
}
