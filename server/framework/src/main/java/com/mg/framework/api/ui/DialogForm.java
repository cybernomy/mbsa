/*
 * DialogForm.java
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
 * ����������� ����� "������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DialogForm.java,v 1.1 2006/10/19 09:32:42 safonov Exp $
 */
public interface DialogForm extends Form {

	/**
	 * ���������� ������ ���������� �� ������� "ok"
	 * 
	 * @return	������ ����������
	 */
	FormActionListener[] getOkActionListenerList();
	
	/**
	 * ��������������� ��������� �� ������� "��"
	 * 
	 * @param listener	���������
	 */
	void addOkActionListener(FormActionListener listener);
	
	/**
	 * ������� ��������� �� ������� "��"
	 * 
	 * @param listener	���������
	 */
	void removeOkActionListener(FormActionListener listener);

	
	/**
	 * ���������� ������ ���������� �� ������� "������"
	 * 
	 * @return	������ ����������
	 */
	FormActionListener[] getCancelActionListenerList();
	
	/**
	 * ��������������� ��������� �� ������� "������"
	 * 
	 * @param listener	���������
	 */
	void addCancelActionListener(FormActionListener listener);
	
	/**
	 * ������� ��������� �� ������� "������"
	 * 
	 * @param listener	���������
	 */
	void removeCancelActionListener(FormActionListener listener);
	
	/**
	 * ������ ������� (�����)
	 *
	 */
	void execute();

}
