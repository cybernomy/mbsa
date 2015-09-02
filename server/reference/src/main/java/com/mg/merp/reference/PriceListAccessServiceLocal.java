/*
 * PriceListAccessServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import java.util.List;

import com.mg.merp.reference.model.PriceListHeadRights;

/**
 * ������-��������� "����� �� ��������� �����-������"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListAccessServiceLocal.java,v 1.2 2008/05/13 06:57:27 alikaev Exp $
 */
public interface PriceListAccessServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PriceListHeadRights, Integer> {

	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/reference/PriceListAccess"; //$NON-NLS-1$

	/**
	 * ��������� ������ ���� ������������ ��� �����-�����
	 * 
	 * @param priceListId
	 * 				- ������������� �����-�����
	 * @return
	 * 				- ������ ���� ������������ ��� �����-�����
	 */
   List<PriceListAccessResult> loadPriceListPermissions(Integer priceListId);

	/**
	 * ���������� ����� ������� ��� �����-�����
	 * 
	 * @param permission 
	 * 				- ����� �������
	 * @param priceListId 
	 * 				- ������������� �����-�����
	 */
	void grantPermission(PriceListAccessResult permission, Integer priceListId);
	
	/**
	 * �������� ����� ������� ��� ����-�����
	 * 
	 * @param permission 
	 * 				- ����� �������
	 */
	void revokePermission(PriceListAccessResult permission);

}
