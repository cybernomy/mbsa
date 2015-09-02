/*
 * WareCardServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.warehouse.model.StockCard;

/**
 * ������-��������� "�������� ���������� �����"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: WareCardServiceLocal.java,v 1.6 2008/04/18 15:15:53 safonov Exp $
 */
public interface WareCardServiceLocal extends
		com.mg.framework.api.DataBusinessObjectService<StockCard, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/WareCard";

	/**
	 * ����� ���
	 * 
	 * @param warehouse
	 *            �����
	 * @param mol
	 *            ���
	 * @param catalog
	 *            ������� ��������
	 * @param onlyAvailable
	 * 			������ ������ ��������� ��� �������� ������������
	 * @return ��� ��� <code>null</code> ���� �� �������
	 * 
	 */
	StockCard findStockCard(Contractor warehouse, Contractor mol,
		Catalog catalog, boolean onlyAvailable);

	/**
	 * ����� ��� ��� ����� ���
	 * 
	 * @param warehouse	�����
	 * @param catalog	������� ��������
	 * @param onlyAvailable	������ ������ ��������� ��� �������� ������������
	 * @return	��� ��� <code>null</code> ���� �� �������
	 */
	StockCard findStockCard(Contractor warehouse, Catalog catalog, boolean onlyAvailable);

	/**
	 * �������� ���, �� ������� ��� ������
	 * 
	 * @param 
	 * 			sctockCards ������ ���
	 */
	void deleteStockCards(StockCard ... sctockCards);
}
