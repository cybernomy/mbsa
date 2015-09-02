/* WarehouseProcessor.java
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

import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ��������� "��������� ���������� ��������"
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessor.java,v 1.4 2008/08/27 09:39:57 sharapov Exp $ 
 */
public interface WarehouseProcessor {

	/**
	 * ���� "��������� �� ������"
	 * 
	 * @param params	��������� �����
	 * @param doInteractive	��������� ������������
	 * @param doCalculateCost	����������� ����
	 * @param listener	���������
	 */
	void processWarehouseTransaction(DocFlowPluginInvokeParams params, boolean doInteractive
			, boolean doCalculateCost, WarehouseTransactionListener listener);

	/**
	 * ����� ����� "��������� �� ������"
	 * 
	 * @param params	���������
	 */
	void rollbackWarehouseTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ���� "��������� ������������ �������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void processWarehousePlanTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "��������� ������������ �������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackWarehousePlanTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ���� "������ � ����� �������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void processWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "������ � ����� �������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackWarehousePlanWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ���� "�������������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void processWarehouseReservTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "�������������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackWarehouseReservTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ���� "������ � ������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void processWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "������ � ������� �� ������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackWarehouseReservWithdrawalTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * ���� "���������� ���� � ��������� �� ��������� ������ ������"
	 * 
	 * @param params	��������� �����
	 */
	void processCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "���������� ���� � ��������� �� ��������� ������ ������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackCalcPricesByWarehouseDataTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * ���� "������� ��������"
	 * 
	 * @param params	��������� �����
	 */
	void processAssembleProductTransaction(DocFlowPluginInvokeParams params, boolean assembleProductKind);

	/**
	 * ����� ����� "������� ��������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackAssembleProductTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * ���� "��������� ��������"
	 * 
	 * @param params	��������� �����
	 */
	void processDisAssembleProductTransaction(DocFlowPluginInvokeParams params);

	/**
	 * ����� ����� "��������� ��������"
	 * 
	 * @param params	��������� �����
	 */
	void rollbackDisAssembleProductTransaction(DocFlowPluginInvokeParams params);
	
	/**
	 * ���� "���������� ����� ��� �� ��������� ������"
	 * @param params - ��������� �����
	 * @param warehouseProcessListener - ��������� ���������� ����������
	 */
	void proccessCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params, WarehouseProcessListener warehouseProcessListener);
	
	/**
	 * ����� ����� "���������� ����� ��� �� ��������� ������"
	 * @param params - ��������� �����
	 */
	void rollbackCustomsDeclarationByStockBach(DocFlowPluginInvokeParams params);
	
}
