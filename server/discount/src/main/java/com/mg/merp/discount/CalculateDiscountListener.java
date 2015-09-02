/**
 * CalculateDiscountListener.java.java
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
package com.mg.merp.discount;

import java.io.Serializable;

import com.mg.merp.discount.support.DiscountResult;

/**
 * ��������� ������� �������� ������/�������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CalculateDiscountListener.java,v 1.2 2007/10/30 13:55:56 sharapov Exp $
 */
public interface CalculateDiscountListener extends Serializable {

	/**
	 * ������ ��������
	 * 
	 * @param value	��������� ������� ������/�������
	 */
	void completed(DiscountResult value);
	
	/**
	 * ������ �������
	 */
	void aborted();
	
}
