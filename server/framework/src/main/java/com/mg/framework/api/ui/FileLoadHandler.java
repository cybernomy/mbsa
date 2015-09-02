/*
 * FileLoadHandler.java
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

import java.io.InputStream;
import java.io.Serializable;

/**
 * ���������� �������� ����� �� ������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileLoadHandler.java,v 1.2 2008/02/29 10:16:15 safonov Exp $
 */
public interface FileLoadHandler extends Serializable {
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
	 * �������� ������� ���������
	 * 
	 * @param ins		������ �����
	 * @param filePaths	������ ��������� ���� � ����������� ������, ��� <code>null</code> ���� ����� ������� �� ������������� ���������� � �����
	 * @param fileNames	����� ����������� ������, ��� <code>null</code> ���� ����� ������� �� ������������� ���������� �� ������
	 */
	void onSuccess(InputStream[] ins, String[] filePaths, String[] fileNames);

	/**
	 * �������� ��������
	 * 
	 * @param reason		������� ����������, ����� ��������� ��������� ��������: <ul> <li>{@link #CANCELLED}: ������������ ������� �������� <li>{@link #FAILED}: �������� ��������� �������� <li>{@link #FILE_SIZE_EXCEEDED}: �������� ���������� ������ ����� </ul>
	 * @param description	��������
	 */
	void onFailure(int reason, String description);
	
}
