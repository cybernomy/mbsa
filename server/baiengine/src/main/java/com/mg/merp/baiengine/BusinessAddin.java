/*
 * BusinessAddin.java
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
package com.mg.merp.baiengine;

import java.util.Map;

import com.mg.framework.api.BusinessException;

/**
 * BAi (Business Add-in) ������ ���������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddin.java,v 1.2 2006/10/12 12:02:05 safonov Exp $
 */
public interface BusinessAddin<T> {
	/**
	 * ����������� ��������� ���������� BAi
	 * 
	 * @see BusinessAddinListener
	 * 
	 * @param listener
	 */
	void registerListener(BusinessAddinListener<T> listener);
	
	/**
	 * ������ ���������� BAi
	 * 
	 * @param params		����� ���������� ������������ � BAi �� ����� ������
	 * @throws Exception	��� ������������� ����� �� ������������ ��
	 * @throws BusinessException ��� ������������� ���������� ��, ��� ������� ������������ ��� ���������
	 */
	void perform(Map<String, ? extends Object> params) throws Exception, BusinessException;
}
