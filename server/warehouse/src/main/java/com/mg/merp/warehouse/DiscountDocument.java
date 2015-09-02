/**
 * DiscountDocument.java.java
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
package com.mg.merp.warehouse;

import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.document.model.DocHead;

/**
 * �������� �� �������/��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DiscountDocument.java,v 1.2 2009/01/22 06:52:07 sharapov Exp $
 */
public interface DiscountDocument {

	/**
	 * ��������� ������/�������
	 */
	void applyDiscount(DocHead docHead);
	
	/**
	 * ��������� ������/�������
	 * @param docHead - ��������
	 * @param �pplyDiscountListener - ��������� ���������� ������/�������
	 */
	void applyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener);

}
