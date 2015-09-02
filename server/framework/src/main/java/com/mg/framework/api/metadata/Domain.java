/*
 * Domain.java
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

import java.util.List;


/**
 * ����� ��������� ���������� � ����, ��������� ��������� ����� �����������
 * �� ������ �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: Domain.java,v 1.4 2008/03/03 13:11:02 safonov Exp $
 */
public interface Domain {
	
	/**
	 * ������������
	 * 
	 * @return
	 */
    String getName();
    
    /**
     * ��������
     * 
     * @return
     */
    String getDescription();
    
    /**
     * ���
     * 
     * @return
     */
    BuiltInType getBuiltInType();
    
    /**
     * ����� � �������� (��� ���������� ����)
     * 
     * @return
     */
    int getLength();
    
    /**
     * ���������� ����
     * 
     * @return
     */
    int getNumberOfPlaces();
    
    /**
     * ���������� ���� ����� �������
     * 
     * @return
     */
    int getNumberOfDecimalPlaces();
    
    /**
     * ������ ������������� ��������
     * 
     * @return
     */
    List<FixedValue<?>> getFixedValues();
    
    /**
     * �������� �� ���������
     * 
     * @return
     */
    Object getDefaultValue();
    
    /**
     * ����������� ������������
     * 
     * @return
     */
    String getDocumentation();
    
    /**
     * ������� �������� �������� � ������� ��������, ���� ���������� � <code>false</code>
     * �� ��������� ������� ����� �������������� � ������� �������
     * 
     * @return
     */
    boolean isLowercase();
    
    /**
     * ������� ������� ��������, ���� ���������� � <code>true</code> �� ��������
     * �� ����� ���� <code>null</code>
     * 
     * @return
     */
    boolean isMandatory();
    
    /**
     * ��������� ����������� �� �������� ����������������� ���������� � ��������
     * �������������� �������� � �������
     * 
     * @return ��������� �����������
     * 
     * @see ConversionRoutine
     */
    ConversionRoutine<?, ?> getConversion();
    
    /**
     * ������� �������������� ��������, ���� ���������� � <code>false</code> ��
     * �������� ����� ���� ������ �������������
     * 
     * @return
     */
    boolean isSign();
}
