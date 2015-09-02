/*
 * InvoiceHeadServiceLocal.java
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
package com.mg.merp.retail;

import java.util.List;

import com.mg.merp.discount.ApplyDiscountListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.retail.model.RtlInvoiceHead;

/**
 * ������-��������� "������������ ��������� �� ������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InvoiceHeadServiceLocal.java,v 1.6 2009/01/28 12:57:28 sharapov Exp $
 */
public interface InvoiceHeadServiceLocal extends com.mg.merp.document.GoodsDocument<RtlInvoiceHead, Integer, InvoiceHeadModelServiceLocal, InvoiceSpecServiceLocal> {

	/**
	 * ��� ����� ��� ���������� �� ������
	 */
	final static short FOLDER_PART = 13200;

	/**
	 * ������ ���������� �� ������
	 */
	final static short DOCSECTION = 12500;

	/**
	 * ��������� ������/�������
	 * @param docHead - ��������
	 */
	void applyDiscount(DocHead docHead);
	
	/**
	 * ��������� ������/������� �� ��������
	 * @param docHead - ��������
	 * @param �pplyDiscountListener - c�������� ���������� ������/�������
	 */
	void applyDiscount(DocHead docHead, ApplyDiscountListener �pplyDiscountListener);
	
	/**
	 * ��������� ������/������� �� �������� ��� ������� ������������
	 * @param docHead - ��������
	 * @param specs - ������ ������� ������������
	 * @param �pplyDiscountListener - c�������� ���������� ������/�������
	 */
	void applyDiscount(DocHead docHead, List<DocSpec> specs, ApplyDiscountListener �pplyDiscountListener);
	
}
