/*
 * Constants.java
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
package com.mg.framework.api.math;


/**
 * �������������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Constants.java,v 1.3 2009/01/23 13:42:37 safonov Exp $
 */
public interface Constants {
	/**
	 * �������� ���������� ��� ������� ���������
	 */
	final static int QUANTITY_SCALE = 3;

	/**
	 * ���������� �������� ���������� ��� ������� ���������
	 */
	final static int QUANTITY_SCALE_EXT = 6;

	/**
	 * �������� ���������� ��� ������� ���������
	 */
	final static RoundContext QUANTITY_ROUND_CONTEXT = new RoundContext(QUANTITY_SCALE);
	
	/**
	 * �������� ���������� ��� ������� ��������� � ���������� ���������
	 */
	final static RoundContext QUANTITY_ROUND_CONTEXT_EXT = new RoundContext(QUANTITY_SCALE_EXT);

	/**
	 * ����������� �������� ���������� ��� ������� �������� ���
	 */
	final static int GENERAL_MONETARY_AMOUNT_SCALE = 4;

	/**
	 * ����������� �������� ���������� ��� ������� �������� ���
	 */
	final static RoundContext GENERAL_MONETARY_AMOUNT_ROUND_CONTEXT = new RoundContext(GENERAL_MONETARY_AMOUNT_SCALE);
	
}
