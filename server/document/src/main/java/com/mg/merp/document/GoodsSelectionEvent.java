/*
 * GoodsSelectionEvent.java
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
package com.mg.merp.document;

import java.util.EventObject;

/**
 * ������� � ������ ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: GoodsSelectionEvent.java,v 1.1 2006/12/02 12:35:32 safonov Exp $
 */
public class GoodsSelectionEvent extends EventObject {
	private CreateSpecificationInfo[] specInfo;

	/**
	 * ������� �������
	 * 
	 * @param source	��������
	 * @param specInfo	���������� � ����������� ������������
	 */
	public GoodsSelectionEvent(Object source, CreateSpecificationInfo[] specInfo) {
		super(source);
		this.specInfo = specInfo;
	}

	/**
	 * �������� ���������� � ����������� ������������
	 * 
	 * @return the specInfo
	 */
	public CreateSpecificationInfo[] getSpecInfo() {
		return specInfo;
	}

}
