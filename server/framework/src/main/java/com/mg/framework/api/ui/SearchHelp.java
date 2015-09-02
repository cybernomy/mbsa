/*
 * SearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.orm.PersistentObject;

/**
 * ��������� ��������� ������ �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelp.java,v 1.4 2008/05/23 14:18:45 safonov Exp $
 */
public interface SearchHelp extends Serializable {
	
	/**
	 * ���������� ��������� �� ������� ������
	 * 
	 * @param listener	���������
	 */
	void addSearchHelpListener(SearchHelpListener listener);
	
	/**
	 * ������� ��������� �� ������� ������
	 * 
	 * @param listener	���������
	 */
	void removeSearchHelpListener(SearchHelpListener listener);
	
	/**
	 * ������ ��������� ������
	 *
	 */
	void search();

	/**
	 * ������ ��������� ������, ������ ����� ����� ������������ ���� ����������� ��������
	 * ������ ��������� �� ���������, �������� ����������� ����� ������ � ������� ����������
	 * ���������������� �� ������ ������������ ���������� ��������
	 * 
	 * @param entity	��������
	 */
	void search(PersistentObject entity);
	
	/**
	 * ������� ��������� �������������� ��������� ��������
	 * 
	 * @return	<code>true</code> ���� �������������� ��������
	 */
	boolean isSupportView();
	
	/**
	 * ������ ��������� ��������� ��������
	 * 
	 * @param entity	��������
	 */
	void view(PersistentObject entity);
	
	/**
	 * �������� �������� ��������� �������
	 * 
	 * @return	������ ������������ ��������� ��� ������� �� ������
	 * 
	 * @see #setImportContext(Map)
	 */
	String[] getImportContextDefinition();
	
	/**
	 * �������� �������� ��������� ��������
	 * 
	 * @return	������ ������������ ��������� ��� �������� � ������
	 * 
	 * @see #getExportContext()
	 */
	String[] getExportContextDefinition();

	/**
	 * ������������� �������� �������, �������� ������������� �� ������ �����������
	 * ����� ��������� ������ �����
	 * 
	 * @param context	��������
	 */
	void setImportContext(Map<String, Object> context);
	
	/**
	 * ������������� �������� ��������, �������� �������������� � ������ �����������
	 * ����� ��������� ������ �����
	 * 
	 * @return
	 */
	Map<String, Object> getExportContext();
}
