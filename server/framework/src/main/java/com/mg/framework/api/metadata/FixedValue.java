/*
 * FixedValue.java
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


/**
 * ������������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FixedValue.java,v 1.2 2006/09/30 11:41:00 safonov Exp $
 */
public interface FixedValue<T> {
	/**
	 * ��� ��������
	 */
    public enum Kind {
    	/**
    	 * �������� ��������
    	 */
    	INTERVAL,
    	/**
    	 * ��������� ��������
    	 */
    	SINGLE
    }
    
    /**
     * �������� ��� �������������� ��������
     * 
     * @return	���
     */
    Kind getKind();
    
    /**
     * �������� ���������
     * 
     * @return
     */
    String getExplanatory();
    
    /**
     * �������� ����������� ��������, ���� ��� {@link Kind#INTERVAL INTERVAL}
     * 
     * @return	����������� ��������
     */
    T getMinValue();
    
    /**
     * �������� ������������ ��������, ���� ��� {@link Kind#INTERVAL INTERVAL}
     * 
     * @return	������������ ��������
     */
    T getMaxValue();
    
    /**
     * �������� ��������, ���� ��� {@link Kind#SINGLE SINGLE}
     * 
     * @return	��������
     */
    T getValue();
}
