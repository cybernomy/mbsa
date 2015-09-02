/*
 * FeeRefAlgorithmResult.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.salary;

import java.util.Date;

/**
 * ��������� ���������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class FeeRefAlgorithmResult {
	/**
	 * ����� �/�
	 */
	public double value;
	
	/**
	 * ��������� �
	 */
	public Date beginDate;
	
	/**
	 * ��������� ��
	 */
	public Date endDate;
	
	/**
	 * �� ������ �
	 */
	public Date periodBeginDate;
	
	/**
	 * �� ������ ��
	 */
	public Date periodEndDate;
	
	/**
	 * ������������� ��������� ������� ������ 1-�� ������
	 */
	public int costsAnl1Id;
	
	/**
	 * ������������� ��������� ������� ������ 2-�� ������
	 */
	public int costsAnl2Id;
	
	/**
	 * ������������� ��������� ������� ������ 3-�� ������
	 */
	public int costsAnl3Id;
	
	/**
	 * ������������� ��������� ������� ������ 4-�� ������
	 */
	public int costsAnl4Id;
	
	/**
	 * ������������� ��������� ������� ������ 5-�� ������
	 */
	public int costsAnl5Id;
}
