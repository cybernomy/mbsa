/*
 * LBScheduleManager.java
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
package com.mg.merp.document;

import com.mg.merp.document.model.DocHead;

/**
 * �������� ���������� ��������� ���������� ������������
 * 
 * @author Artem V. Sharapov
 * @version $Id: LBScheduleManager.java,v 1.1 2007/04/21 11:46:07 sharapov Exp $
 */
public interface LBScheduleManager {

	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=LBScheduleManagerService"; //$NON-NLS-1$
	
	/**
	 * ������� ������ ���������� ������������
	 * @param docHead - ��������, ��������� � ��������
	 */
	void createLBSchedule(DocHead docHead);
	
	/**
	 * ������� ������ ���������� ������������
	 * @param docHead - ��������, ��������� � ��������
	 */
	void openLBSchedule(DocHead docHead);
	
	/**
	 * ������� ������ ���������� ������������
	 * @param docHead - ��������, ��������� � ��������
	 */
	void removeLBSchedule(DocHead docHead);
	
}
