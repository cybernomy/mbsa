/*
 * FileStoreHandler.java
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

import java.io.OutputStream;
import java.io.Serializable;

/**
 * ���������� ���������� ����� �� ������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileStoreHandler.java,v 1.3 2008/05/29 13:41:01 safonov Exp $
 */
public interface FileStoreHandler extends Serializable {
	/**
	 * ������������ ������� ��������
	 */
	final static int CANCELLED = 1;
	/**
	 * �������� ��������� ��������
	 */
	final static int FAILED = 2;
	/**
	 * �������� ���������� ������ �����
	 */
	final static int FILE_SIZE_EXCEEDED = 3;

	/**
	 * ���������� ����� � ��������
	 * 
	 * @param data
	 * @throws Exception
	 */
	void prepareFile(OutputStream data) throws Exception;

	/**
	 * ���������� ������� ���������
	 * 
	 * @param filePath	������ ��������� ���� � �����, ��� <code>null</code> ���� ����� ������� �� ������������� ���������� � �����
	 * @param fileName	��� �����, ��� <code>null</code> ���� ����� ������� �� ������������� ���������� �� ������
	 */
	void onSuccess(String filePath, String fileName);
	
	/**
	 * ���������� ��������
	 * 
	 * @param reason		������� ����������, ����� ��������� ��������� ��������: <ul> <li>{@link #CANCELLED}: ������������ ������� �������� <li>{@link #FAILED}: �������� ��������� �������� </ul>
	 * @param description	��������
	 */
	void onFailure(int reason, String description);
	
}
