/*
 * RptBIRTDeployer.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.report.service;

import com.mg.merp.report.RptMainTransfer;

/**
 * ��������� ����� ������� ���������� � ��������� ������� � ����� ����������
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptBIRTDeployer.java,v 1.7 2007/08/30 14:29:18 safonov Exp $
 */
public interface RptBIRTDeployer {

	/**
	 * ��������� ������ ������� ��� ������������� ������
	 * 
	 * @param rmt
	 * @return NULL, ���� ������ � ��������������� ����� ��� � ����, �����
	 *         ���������� ��������� ������ � ����� ��������� ������. ���� �����
	 *         ��� ��� ������, �� ������������ ����� ���������
	 *         {@link RptMainTransfer}
	 * @throws Exception
	 */
	RptMainTransfer persistTemplate(RptMainTransfer rmt) throws Exception;

	/**
	 * ���������� ��������� ������, ��� ������� ������������� �����
	 * 
	 * @param likeSentence
	 *            ����� ����� �������. ������ ���������� ��� ����������� "like"
	 *            � EJBQL(������� '*' ������������� ���������� �� '%', � '?' ��
	 *            '_')
	 * @return ������ �������, ��������������� �����
	 * @throws Exception
	 */
	RptMainTransfer[] getReports(String likeSentence) throws Exception;

	/**
	 * �������� ������ ������
	 * 
	 * @param rmt
	 * @return ���������� ��������� ������ {@link RptMainTransfer}, �
	 *         �������������� ����������� id � sysVersion.
	 */
	RptMainTransfer addReport(RptMainTransfer rmt) throws Exception;

	/**
	 * �������� ������ �������
	 * 
	 * @param ids
	 *            ������ � ���������������� �������, ���������� ��������
	 * @throws Exception
	 */
	public void deleteReportList(Integer[] ids) throws Exception;

	/**
	 * ��������� ������
	 * 
	 * @param rmt
	 *            ����� �������� �����
	 * @return ��������� �����, � ����� ���������� sysVersion ��� NULL, �
	 *         ������ ������ ����������
	 * @throws Exception
	 */
	RptMainTransfer editReport(RptMainTransfer rmt) throws Exception;
}
