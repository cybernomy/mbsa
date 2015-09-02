/*
 * UIProfile.java
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

/**
 * ������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfile.java,v 1.1 2007/03/13 13:22:21 safonov Exp $
 */
public interface UIProfile {

	/**
	 * ��� �������
	 * 
	 * @return	���
	 */
	String getName();
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @return	�������� �������� ��� <code>null</code> ���� �� �������
	 */
	String getProperty(String name);

	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	String getProperty(String name, String defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	int getProperty(String name, int defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	boolean getProperty(String name, boolean defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	double getProperty(String name, double defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	float getProperty(String name, float defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	long getProperty(String name, long defaultValue);
	
	/**
	 * �������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� <code>defaultValue</code> ���� �� �������
	 */
	short getProperty(String name, short defaultValue);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, String value);

	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, int value);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, boolean value);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, double value);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, float value);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, long value);
	
	/**
	 * ���������� �������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setProperty(String name, short value);

}
