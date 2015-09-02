/*
 * DataItemName.java
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

import java.util.Locale;

import com.mg.framework.api.ui.SearchHelp;

/**
 * ������� ������ ������������ ��� �������� �������� ������������ � �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataItem.java,v 1.4 2007/01/25 14:52:09 safonov Exp $
 */
public interface DataItem {
	
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
     * ��� �������� ������
     * 
     * @return
     */
    DataItemKind getKind();
    
    /**
     * ����� ������������ ��� �������� ������� �������� ������
     * 
     * @return
     */
    Domain getDomain();
    
    /**
     * ����������� �������� ������� ������������ �� ������ �������, ������������
     * ������ ���������� ����, ������������ ������������� �������������� Java naming conventions
     * 
     * @return
     */
    String getDefaultComponentName();
    
    /**
     * ���������� ������� �����, ������������ ������� locale, ������ {@link #getShortLabel(Locale)}
     * 
     * @return
     */
    String getShortLabel();
    
    /**
     * ���������� ������� �����, ������������ ��� ����� ��� ��������� �����������������
     * ����������
     * 
     * @param locale
     * @return
     */
    String getShortLabel(Locale locale);
    
    int getShortLabelMaxLength();

    /**
     * ���������� ������� �����, ������������ ������� locale, ������ {@link #getMediumLabel(Locale)}
     * 
     * @return
     */    
    String getMediumLabel();
    
    /**
     * ���������� ������� �����, ������������ ��� ����� ��� ��������� �����������������
     * ����������, ���� �� �����������, �� ������������ ������� �����
     * 
     * @param locale
     * @return
     */
    String getMediumLabel(Locale locale);
    
    int getMediumLabelMaxLength();
    
    /**
     * ���������� ������� �����, ������������ ������� locale, ������ {@link #getLongLabel(Locale)}
     * 
     * @return
     */    
    String getLongLabel();
    
    /**
     * ���������� ������� �����, ������������ ��� ����� ��� ��������� �����������������
     * ����������, ���� �� �����������, �� ������������ ������� �����
     * 
     * @param locale
     * @return
     */
    String getLongLabel(Locale locale);
    
    int getLongLabelMaxLength();
    
    /**
     * ���������� ���������, ������������ ������� locale, ������ {@link #getHeader(Locale)}
     * 
     * @return
     */
    String getHeader();
    
    /**
     * ���������� ���������, ������������ � �������� ��������� ������� ������� �
     * ������
     * 
     * @param locale
     * @return
     */
    String getHeader(Locale locale);
    
    int getHeaderMaxLength();
    
    /**
     * ���������� ����� �������, ������������ ������� locale, ������ {@link #getReportLabel(Locale)}
     * 
     * @return
     */
    String getReportLabel();
    
    /**
     * ���������� ����� �������, ������������ � �������� ����� ��������� ���
     * ������ �������
     * 
     * @param locale
     * @return
     */
    String getReportLabel(Locale locale);
    
    int getReportLabelMaxLength();
    
    /**
     * ���������� ������������ ������� ��������
     * 
     * @return
     */
    DataItemDocumentation getDocumentation();
    
    /**
     * ���������� SearchHelp
     * 
     * @return
     */
    SearchHelp getSearchHelp();

    /**
     * ���������� ��� SearchHelp, ����������� {@link com.mg.framework.support.metadata.SearchHelpProcessor SearchHelpProcessor}
     * ��� �������� ���������� 
     * 
     * @return	��� ������ ����������
     */
    String getSearchHelpName();
    
    /**
     * ���������� ��������� ������������� ������� �������� � ����������������
     * ����������
     * 
     * @return
     */
    EntityText getEntityText();

    /**
     * ���������� ������ ������� �������� ��� �����������
     * 
     * @return
     */
    String getEntityPropertyText();
    
    /**
     * ���������� ������ ��� ����������� ��������
     * 
     * @return
     */
    String getEntityTextFormat();
    
    /**
     * ���������� �������� ��������� �������� ����� �������� ������, ��������
     * ����� ����� �� ������� �������� ������� ����������
     * 
     * @return
     */
    String getAssignParameterName();
    
    /**
     * ������� ����������� ��������� �������� ����, ���� ���������� � <code>true</code> �� ��������
     * �� ����� ���� ��������
     * 
     * @return
     */
    boolean isReadOnly();
}
