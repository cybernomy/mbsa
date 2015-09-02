/*
 * WarehouseServiceLocal.java
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

import java.io.Serializable;
import java.util.Date;

import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * ������-��������� "������"
 * 
 * @author leonova
 * @version $Id: WarehouseServiceLocal.java,v 1.6 2007/11/29 08:37:13 alikaev Exp $
 */
public interface WarehouseServiceLocal extends
		com.mg.framework.api.DataBusinessObjectService<Warehouse, Integer> {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/Warehouse";

	/**
	 * ��������� ������ � ������� WH_WAREHOUSE, ��������������� �������������
	 * 
	 * @param orgUnit
	 *            �������������
	 * @return ��������� �����
	 */
	Warehouse addWarehouse(OrgUnit orgUnit);

	/**
	 * ������� ������ � ������� WH_WAREHOUSE, ��������������� ������������� �
	 * ��������������� id
	 * 
	 * @param id
	 *            ������������� ������
	 */
	void eraseWarehouse(int id);
	
	/**
	 * �������� ��� �� ��������� ��� ������
	 * @param warehouse - �����
	 * @return ���(���������) �� ��������� ��� ������, ��� <code>null</code> ���� �� ������
	 */
	 Employees getWarehouseDefaultMOL(Warehouse warehouse);
	 
	 /**
	  * ������� �����
	  * @param warehouseIds - ����� �������
	  */
	 void openWarehouse(Serializable[] warehouseIds);
	 
	 /**
	  * ������� �����
	  * @param warehouseIds - ����� �������
	  * @param closedDateTill - ������� ����� �� �����
	  */
	 void closeWarehouse(Serializable[] warehouseIds, Date closedDateTill);

}
