/*
 * ItemSpecTaxServiceLocal.java
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
package com.mg.merp.lbschedule;

import java.math.BigDecimal;

import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecTax;
import com.mg.merp.lbschedule.model.TaxResult;

/**
 * ������ ������-���������� "������ ������� ������������ ������ ������� ���������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecTaxServiceLocal.java,v 1.2 2007/04/17 12:48:40 sharapov Exp $
 */
public interface ItemSpecTaxServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ItemSpecTax, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/ItemSpecTax"; //$NON-NLS-1$

	/**
	 * ����������� ������ ������� ������������ ������ �������
	 * @param itemSpec - ������� ������������
	 * @param factor - ������
	 * @return ��������� ���������
	 */
	TaxResult recomputeTaxes(ItemSpec itemSpec, BigDecimal factor);
}
