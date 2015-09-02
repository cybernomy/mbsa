/**
 * WarehouseProcessDocumentLineData.java
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
package com.mg.merp.warehouse;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * ������ �� ������������ ��� ��������� �� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessDocumentLineData.java,v 1.1 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseProcessDocumentLineData {

	/**
	 * �������� ������ ��
	 * 
	 * @return
	 */
	DocumentSpecItem getDocumentSpecItem();
	
	/**
	 * �������� ������������
	 * 
	 * @return
	 */
	DocSpec getDocumentSpec();
	
	/**
	 * �������� ��� ���������
	 * 
	 * @return
	 */
	Employees getSrcMol();
	
	/**
	 * �������� ����� ��������
	 * 
	 * @return
	 */
	Warehouse getSrcStock();
	
	/**
	 * �������� ��� ���������
	 * 
	 * @return
	 */
	Employees getDstMol();
	
	/**
	 * �������� ����� ��������
	 * 
	 * @return
	 */
	Warehouse getDstStock();
	
	/**
	 * �������� ������� ��������
	 * 
	 * @return
	 */
	Catalog getCatalog();
	
	/**
	 * �������� 1� ��
	 * 
	 * @return
	 */
	Measure getMeasure1();
	
	/**
	 * �������� 2� ��
	 * 
	 * @return
	 */
	Measure getMeasure2();
	
	/**
	 * �������� 1� ���������� � ���������
	 * 
	 * @return
	 */
	BigDecimal getQuantity1();
	
	/**
	 * �������� 2� ���������� � ���������
	 * 
	 * @return
	 */
	BigDecimal getQuantity2();
	
	/**
	 * �������� ���� ��� �� ���������� � ���� �������
	 * 
	 * @return
	 */
	BigDecimal getPrice();
	
	/**
	 * �������� ������ ����
	 * 
	 * @return
	 */
	BigDecimal getPrice1();

	/**
	 * �������� ����� ��� �� ���������� � ���� �������
	 * 
	 * @return
	 */
	BigDecimal getSum();
	
	/**
	 * �������� ������ �����
	 * 
	 * @return
	 */
	BigDecimal getSum1();

	/**
	 * �������� ���� ����������
	 * 
	 * @return
	 */
	Date getProcessDate();
	
}
