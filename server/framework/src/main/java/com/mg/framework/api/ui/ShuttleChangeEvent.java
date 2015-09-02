/*
 * ShuttleChangeEvent.java
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
 * ������� �������� "Shuttle"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ShuttleChangeEvent.java,v 1.1 2006/08/31 08:34:03 safonov Exp $
 */
public class ShuttleChangeEvent extends UIEvent {
	private Object[] contents;

	/**
	 * ����������� �������
	 * 
	 * @param source	�������� �������
	 * @param contents	���������� ������
	 */
	public ShuttleChangeEvent(Object source, Object[] contents) {
		super(source);
		this.contents = contents;
	}
	
	/**
	 * ���������� ���������� ������
	 * 
	 * @return
	 */
	public Object[] getContents() {
		return contents;
	}
}
