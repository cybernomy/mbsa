/**
 * DiscountProcessorServiceLocal.java.java
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

import java.util.List;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;

/**
 * ������-��������� "��������� ������� ������/�������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DiscountProcessorServiceLocal.java,v 1.3 2009/01/22 06:47:35 sharapov Exp $
 */
public interface DiscountProcessorServiceLocal extends BusinessObjectService {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/discount/DiscountProcessor";

	/**
	 * ������ ������/������� ��� ������������
	 * 
	 * @param discountGroup	������ ������/�������
	 * @param docSpec	������������ ���������
	 * @param listener	���������
	 */
	void calculateDiscountValue(Folder discountGroup, DocSpec docSpec, CalculateDiscountListener listener);
	
	/**
	 * ��������� ������/������� �� ��������
	 * 
	 * @param docHead	��������
	 */
	void applyDiscount(DocHead docHead);
	
	/**
	 * ��������� ������/������� �� �������� ��� ������� ������������
	 * @param docHead - ��������
	 * @param specs - ������ ������� ������������
	 */
	void applyDiscount(DocHead docHead, List<DocSpec> specs);
	
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
