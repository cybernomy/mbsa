/**
 * SetPropertyCommand.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

/**
 * ������� ��������� �������� �������� ������ � ���������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SetPropertyCommand.java,v 1.1 2008/10/08 11:41:20 safonov Exp $
 */
public class SetPropertyCommand implements Serializable {
    private final String propertyName;
    private final Object newValue;
    private Object oldValue;

    /**
     * �������� �������
     * 
     * @param newValue	����� ��������
     * @param oldValue	������ ��������
     * @param propertyName	��� �������� ������
     */
    public SetPropertyCommand(Object newValue, Object oldValue,
			String propertyName) {
		super();
		this.newValue = newValue;
		this.oldValue = oldValue;
		this.propertyName = propertyName;
	}

	/**
	 * �������� ��� ��������
	 * 
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * �������� ����� ��������
	 * 
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}

	/**
	 * �������� ������ ��������
	 * 
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}

}
