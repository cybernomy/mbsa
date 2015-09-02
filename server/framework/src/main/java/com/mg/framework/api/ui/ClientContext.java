/*
 * ClientContext.java
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
package com.mg.framework.api.ui;

/**
 * ����� ����������������� ����������, ���� ������ � ��������� �� ������� ���������� �����������
 * ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ClientContext.java,v 1.4 2008/05/29 13:41:01 safonov Exp $
 */
public interface ClientContext {
	
	/**
	 * �������� �������� ����������� ������� �������� ����������, �������� ���� ���������� ��������
	 * �������� <code>http://localhost:8080/jmx-console</code>, �� ���������� �������� ���� <code>jmx-console</code>
	 * 
	 * @param urlString	���� � ��������� �� ���������� ����� �������� �������
	 */
	void showLocalDocument(String urlString);
	
	/**
	 * �������� ��������
	 * 
	 * @param urlString	������ ���� � ���������, �������� <code>"http://www.m-g.ru"</code>
	 */
	void showDocument(String urlString);

	/**
	 * �������� �������� ������������� �������� ����������
	 * 
	 * @param urlString	���� � ��������� �� ���������� ����� �������� ����������
	 */
	void showApplicationDocument(String urlString);

	/**
	 * �������� ����� �� ������ ���������� �� ������� ������� 
	 * 
	 * @param handler	���������� ��������
	 */
	void loadFile(FileLoadHandler handler);
	
	/**
	 * �������� ����� �� ������ ���������� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ��������
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	void loadFile(FileLoadHandler handler, long maximumFileSize);

	/**
	 * �������� ����� �� ������ ���������� �� ������� �������
	 * 
	 * @param handler	���������� ��������
	 * @param fileChooserConfig	����������� ������� ��������
	 */
	void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig);
	
	/**
	 * �������� ����� �� ������ ���������� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ��������
	 * @param fileChooserConfig	����������� ������� ��������
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize);

	/**
	 * ���������� ����� �� ������� �������
	 * 
	 * @param handler	���������� ����������
	 * @param fileName	��� �����, ����� ���� <code>null</code>
	 */
	void storeFile(FileStoreHandler handler, String fileName);

	/**
	 * ���������� ����� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ����������
	 * @param fileName	��� �����, ����� ���� <code>null</code>
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	void storeFile(FileStoreHandler handler, String fileName, long maximumFileSize);

	/**
	 * ���������� ����� �� ������� �������
	 * 
	 * @param handler	���������� ����������
	 * @param fileChooserConfig	����������� ������� ����������
	 */
	void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig);

	/**
	 * ���������� ����� �� ������� ������� � ������������ ������� �����
	 * 
	 * @param handler	���������� ����������
	 * @param fileChooserConfig	����������� ������� ����������
	 * @param maximumFileSize	������������ ������ ����� � ������
	 */
	void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize);

	/**
	 * Returns the raw IP address string "%d&#46%d&#46%d&#46%d".
	 * 
	 * @return	the raw IP address string "%d.%d.%d.%d"
	 */
	String getAddress();
	
	/**
	 * Returns the host name.
	 * 
	 * @return	the host name
	 */
	String getHost();

	/**
	 * Emits an audio beep.
	 */
	void beep();

	/**
	 * ����� ������������� ������ ��� ���������� ������
	 * 
	 * @param fileChooseHandler	���������� ������
	 * @param fileChooserConfig	����������� ������� ������
	 */
	void chooseFile(FileChooseHandler fileChooseHandler, FileChooserConfig fileChooserConfig);
	
}
