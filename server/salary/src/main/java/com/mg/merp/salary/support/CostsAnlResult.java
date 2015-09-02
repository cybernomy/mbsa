/*
 * CostsAnlResult.java
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
package com.mg.merp.salary.support;

import com.mg.merp.personnelref.model.CostsAnl;

/**
 * �����-��������� ������ ��������� ������� ������
 * 
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlResult.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public class CostsAnlResult {
	
	private CostsAnl costsAnl1;
	private CostsAnl costsAnl2;
	private CostsAnl costsAnl3;
	private CostsAnl costsAnl4;
	private CostsAnl costsAnl5;
	
	
	public CostsAnlResult() {
	}
	
	/**
	 * @param costsAnl1 - ��������� ������� ������ 1-�� ������
	 * @param costsAnl2 - ��������� ������� ������ 2-�� ������
	 * @param costsAnl3 - ��������� ������� ������ 3-�� ������
	 * @param costsAnl4 - ��������� ������� ������ 4-�� ������
	 * @param costsAnl5 - ��������� ������� ������ 5-�� ������
	 */
	public CostsAnlResult(CostsAnl costsAnl1, CostsAnl costsAnl2, CostsAnl costsAnl3, CostsAnl costsAnl4, CostsAnl costsAnl5) {
		this.costsAnl1 = costsAnl1;
		this.costsAnl2 = costsAnl2;
		this.costsAnl3 = costsAnl3;
		this.costsAnl4 = costsAnl4;
		this.costsAnl5 = costsAnl5;
	}

	
	/**
	 * �������� ��������� ������� ������ 1-�� ������
	 * @return ��������� ������� ������ 1-�� ������
	 */
	public CostsAnl getCostsAnl1() {
		return this.costsAnl1;
	}

	/**
	 * �������� ��������� ������� ������ 2-�� ������
	 * @return ��������� ������� ������ 2-�� ������
	 */
	public CostsAnl getCostsAnl2() {
		return this.costsAnl2;
	}

	/**
	 * �������� ��������� ������� ������ 3-�� ������
	 * @return ��������� ������� ������ 3-�� ������
	 */
	public CostsAnl getCostsAnl3() {
		return this.costsAnl3;
	}

	/**
	 * �������� ��������� ������� ������ 4-�� ������
	 * @return ��������� ������� ������ 4-�� ������
	 */
	public CostsAnl getCostsAnl4() {
		return this.costsAnl4;
	}

	/**
	 * �������� ��������� ������� ������ 5-�� ������
	 * @return ��������� ������� ������ 5-�� ������
	 */
	public CostsAnl getCostsAnl5() {
		return this.costsAnl5;
	}
	
}
