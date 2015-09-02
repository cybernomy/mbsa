/**
 * CustomActionExecutionContext.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.BusinessObjectService;

/**
 * �������� ������� ������������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionExecutionContext.java,v 1.1 2007/11/15 08:35:11 safonov Exp $
 */
public interface CustomActionExecutionContext {

	/**
	 * ��� ��������
	 * 
	 * @return	���
	 */
	String getAction();
	
	/**
	 * ������-��������� ��� �������� ���� ������� ��������
	 * 
	 * @return	������-���������
	 */
	BusinessObjectService getService();
	
	/**
	 * ������ ��������������� ���������� ���������
	 * 
	 * @return	������ ���������������
	 */
	Serializable[] getSelectedIdentifiers();
	
	/**
	 * ������ ���������� �������������� ��������
	 * 
	 * @return	������ ����������
	 */
	CustomActionListener[] getListeners();
	
}
