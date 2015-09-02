/*
 * ChooseBrowseForm.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.orm.PersistentObject;


/**
 * ����� ������ ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpForm.java,v 1.3 2009/02/09 12:59:03 safonov Exp $
 */
public interface SearchHelpForm extends Form {
	/**
	 * ������������ �������� ������ ������ ���������
	 */
	static final String STANDART_CHOOSE_BUTTON = "�hooseButton";
	
	/**
	 * ���������������� ��������� �� ������� ������
	 * 
	 * @param listener	���������
	 */
	void addSearchHelpListener(SearchHelpListener listener);

	/**
	 * ������� ������������������ ��������� �� ������� ������
	 * 
	 * @param listener	���������
	 */
	void removeSearchHelpListener(SearchHelpListener listener);

	/**
	 * �������� ������ ������������������ ���������� �� ������� ������
	 * 
	 * @return	������ ����������, ���� ��� ������������������ ���������� �� ������ ������
	 */
	SearchHelpListener[] getSearchHelpListeners();

	/**
	 * ���������� �������� ��� ����������������, �������� ����� �������������� ����������
	 * ������ ��������� ��� �������� ��� �������������� �������� � ����� ������ ���
	 * ����������� ���������������� � UI
	 * 
	 * @param entity	��������, ����� ���� <code>null</code>
	 */
	void setTargetEntity(PersistentObject entity);

}
