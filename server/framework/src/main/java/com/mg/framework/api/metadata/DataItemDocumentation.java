/*
 * DataItemDocumentation.java
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

import com.mg.framework.api.ApplicationException;

/**
 * @author Oleg V. Safonov
 * @version $Id: DataItemDocumentation.java,v 1.1 2006/01/24 13:45:28 safonov Exp $
 */
public interface DataItemDocumentation {
    public enum Status {
    	
    	/**
    	 * �����������, ������������ ���������� ��� ����� �������
    	 */
    	DOCUMENTED,
    	
    	/**
    	 * ������ �� ������������ � ���������������� ����������, ������������
    	 * �� ������� � �� ���������
    	 */
    	NOT_USED_IN_SCREEN,
    	
    	/**
    	 * ������� ����� � ����������� ������� ��������� ������, ������������
    	 * �� ������� � �� ���������
    	 */
    	EXPLAINED_BY_SHORT,
    	
    	/**
    	 * ����� ���� ������������ ��� �������� �������� ������ ������� �� ���������
    	 * �����������, ������������ �� ����������
    	 */
    	POSTPONED
    }
    
    public Status getStatus() throws ApplicationException;
    
    public String getShort() throws ApplicationException;
    public String getShort(Locale locale) throws ApplicationException;
    public String getDefenition() throws ApplicationException;
    public String getDefenition(Locale locale) throws ApplicationException;
}
