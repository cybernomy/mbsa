/*
 * EntityInterceptor.java
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
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;

/**
 * ����������� �������� ��� ��������� ����������, ���������� ������� ���������� ������������
 * ��� ��������� ������� ������������ � ��������� ���������� (��������, �����������, ��������, ��������).
 * ���������� ������� ������������ ����� ������������ ���������������� �������������� ��������,
 * �������� ������ �������� JNDI, JDBC, JMS, EJB.
 * <strong>��������, � ����������� ������� ������� ���������� ��������� ��������� �������� � UI, ��������� ��������� �������,
 * �������� �������� ��������� ������������� ��������� ��� �������������� ��������� ��������-���������</strong>
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptor.java,v 1.6 2007/12/17 09:10:30 safonov Exp $
 */
public interface EntityInterceptor {
	
	/**
	 * ������������ ��� �������� ��������� ���� ��������� �������������
	 */
	final static String HANDLED_ALL_ENTITIES = "*";
	
	/**
	 * �������� ��� ������������
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * ���������� ������ ���� �������������� ���������
	 * 
	 * @return
	 */
	String[] getHandledEntities();
	
    /**
     * �������� ��� ��������� ����� ����������� � ���������
     * 
     * @param entity	������-��������
     */
    void onPrePersist(PersistentObject entity);

    /**
     * �������� ��� ��������� ����� ���������� � ���������
     * 
     * @param entity	������-��������
     */
    void onPostPersist(PersistentObject entity);

    /**
     * �������� ��� ��������� ����� ���������� � ��������� � �������� ����������
     * 
     * @param entity	������-��������
     */
    void onPostCommitPersist(PersistentObject entity);
    
    /**
     * �������� ��� ��������� ����� ��������� �� ���������
     * 
     * @param entity	������-��������
     */
    void onPreRemove(PersistentObject entity);

    /**
     * �������� ��� ��������� ����� �������� �� ���������
     * 
     * @param entity	������-��������
     */
    void onPostRemove(PersistentObject entity);
    
    /**
     * �������� ��� ��������� ����� �������� �� ��������� � �������� ����������
     * 
     * @param entity	������-��������
     */
    void onPostCommitRemove(PersistentObject entity);

    /**
     * �������� ��� ��������� ����� ���������� � ���������
     * 
     * @param entity	������-��������
     * @param oldState	���������� ��������� �������
     */
    void onPreUpdate(PersistentObject entity, AttributeMap oldState);
    
    /**
     * �������� ��� ��������� ����� ��������� � ���������
     * 
     * @param entity	������-��������
     * @param oldState	���������� ��������� �������
     */
    void onPostUpdate(PersistentObject entity, AttributeMap oldState);
    
    /**
     * �������� ��� ��������� ����� ��������� � ��������� � �������� ����������
     * 
     * @param entity	������-��������
     * @param oldState	���������� ��������� �������
     */
    void onPostCommitUpdate(PersistentObject entity, AttributeMap oldState);

    /**
     * �������� ��� ��������� ����� �������� �� ���������
     * 
     * @param entity	������-��������
     */
    void onPostLoad(PersistentObject entity);
}
