/*
 * InventoryActHeadServiceLocal.java
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

import java.util.Date;

import com.mg.framework.api.orm.Criteria;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.model.InventoryActCommission;
import com.mg.merp.warehouse.model.InventoryActHead;
import com.mg.merp.warehouse.model.InventoryActSpec;
import com.mg.merp.warehouse.model.InventoryActSpecDifferencesResult;
import com.mg.merp.warehouse.support.InventoryParametrs;

/**
 * ������ ������-���������� "��� ��������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InventoryActHeadServiceLocal.java,v 1.8 2007/11/19 13:33:20 alikaev Exp $
 */
public interface InventoryActHeadServiceLocal extends com.mg.merp.document.GoodsDocument<InventoryActHead, Integer, DocumentPattern, InventoryActSpecServiceLocal> {

	/**
	 * ��� ����� ������������������� ����
	 */
	final static short FOLDER_PART = 34;

	/**
	 * ������ ������������������� ����
	 */
	final static short DOCSECTION = 18;

	/**
	 * ��������� �������������� ������
	 * @param invActHead - ��� ��������������
	 * @param endDate - ���� ��������������
	 * @param stock - �����
	 * @param mol - ���
	 * @param beginCode - ������ ��������� ����� ������� ��������
	 * @param endCode - ����� ��������� ����� ������� ��������
	 * @param stockKind - ������� ������������ ����� �� �������:
	 * 					0 - ���� ������ ��� �� ����� ���� �������;
	 * 					1 - ���� ������ ��� �� ���� ����� �������;
	 * @param isIncludeEmpty - ������� ��������� ������� � ������� ��������
	 */

	void executeStockInventory(InventoryActHead invActHead, Date endDate, OrgUnit stock, Contractor mol, String beginCode, String endCode, short stockKind, boolean isIncludeEmpty);

	/**
	 * ��������� �������������� ������
	 * <p>������ ������������ <code>criteria</code>:
	 * <pre>
	 * 		Criteria criteria = OrmTemplate.createCriteria(StockBatch.class)
	 *				.createAlias("StockCard", "sc", JoinType.INNER_JOIN)
	 *				.createAlias("sc.Catalog", "cat", JoinType.INNER_JOIN)
	 *				.add(Restrictions.eq("sc.Stock", document.getSrcStock()));
	 * </pre>
	 * @param invActHead	- ��� ��������������
	 * @param param 		- ��������� ��������������
	 * @param criteria		- �������� ������ ������: ����������� � �������� ������� ����� ��� ������ ��� 
	 */
	void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params, Criteria criteria);

	/**
	 * ��������� �������������� ������
	 * @param invActHead	- ��� ��������������
	 * @param param 		- ��������� ��������������
	 */
	void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params);

	/**
	 * ���������� ����� ������������ ����������
	 * @param inventoryActSpec - ������� ������������ ���� ��������������
	 * @return ���������
	 */
	InventoryActSpecDifferencesResult computeDifferenceByQuantity(InventoryActSpec inventoryActSpec);

	/**
	 * ���������� ���������� ������������ �����
	 * @param inventoryActSpec - ������� ������������ ���� ��������������
	 * @return ���������
	 */
	InventoryActSpecDifferencesResult computeDifferenceBySum(InventoryActSpec inventoryActSpec);

	/**
	 * ���������� ����� ��������/��������� ���� ��������������
	 * @param inventoryActHead - ��� ��������������
	 */
	void computeShortageAndExsessSum(InventoryActHead inventoryActHead);

	/**
	 * ������� ����� ��������
	 * 
	 * @param commLink	�����
	 */
	void excludeInvCommision(InventoryActCommission commLink);

	/**
	 * �������� ����� ��������
	 * 
	 * @param invActHead	��� ��������������
	 * @param empl			���������
	 * @return	�����
	 */
	InventoryActCommission includeInvCommision(InventoryActHead invActHead, Contractor empl);

}
