/*
 * Processor.java
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
package com.mg.merp.account;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

/**
 * ������ - ��������� "��������� ������������� ��������"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: Processor.java,v 1.3 2008/05/08 09:06:56 alikaev Exp $
 */
public interface Processor {
	
	/**
	 * ��������� ����� ���������������� "������� ��"
	 * 
	 * @param params
	 * 				- ��������� ����������������
	 * @param listener
	 * 				- ��������� �� ������� ������� ��������� ��
	 */
	void processCreateEconomicOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);
	
	/**
	 * ����� ����� ���������������� "������� ��"
	 * @param params
	 * 				- ��������� ����������������
	 */
	void rollbackCreateEconomicOper(DocFlowPluginInvokeParams params);

	/**
	 * ������ ���� �������� �� ���.�����
	 * 
	 * @param operDate
	 * 				- ���� ��� ������� ���� ��������
	 * @param accBatch
	 * 				- ������������ ������ 
	 * @param acc
	 * 				- ���� ������
	 * @param anl1
	 * 				- ��������� 1-�� ������ ����� �������
	 * @param anl2
	 * 				- ��������� 2-�� ������ ����� �������
	 * @param anl3
	 * 				- ��������� 3-�� ������ ����� �������
	 * @param anl4
	 * 				- ��������� 4-�� ������ ����� �������
	 * @param anl5
	 * 				- ��������� 5-�� ������ ����� �������
	 * @param catalog
	 * 				- �����
	 * @param contractor
	 * 				- ����������
	 * @param quantity
	 * 				- ����������
	 * @return
	 */
	CalculateOutCostResult calculateOutCost(Date operDate, AccBatch accBatch, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
			Catalog catalog, Contractor contractor, BigDecimal quantity);

	/**
	 * ������ ���� ��������
	 * 
	 * @param operDate
	 * 				- ���� ��� ������� ���� ��������
	 * @param accBatch
	 * 				- ������������ ������ 
	 * @param quantity
	 * 				- ����������
	 * @param summaNat
	 * 				- ����� � ���
	 * @param summaCur
	 * 				- ����� � ������
	 * @return
	 */
	AccCheckLastBatchResult accCheckLastBatch(Date operDate, AccBatch accBatch, BigDecimal quantity, BigDecimal summaNat, BigDecimal summaCur);
	
	/**
	 * ������������ ���� �������� ��� ������� ���
	 * 
	 * @param operDate
	 * 				- ���� ��� ������� ���� ��������
	 * @param acc
	 * 				- ���� ������
	 * @param anl1
	 * 				- ��������� 1-�� ������ ����� �������
	 * @param anl2
	 * 				- ��������� 2-�� ������ ����� �������
	 * @param anl3
	 * 				- ��������� 3-�� ������ ����� �������
	 * @param anl4
	 * 				- ��������� 4-�� ������ ����� �������
	 * @param anl5
	 * 				- ��������� 5-�� ������ ����� �������
	 * @param catalog
	 * 				- �����
	 * @param contractor
	 * 				- ����������
	 * @param quantity
	 * 				- ����������
	 * @return
	 */
	AccCalcAverageOutCostResult accCalcAverageOutCost(Date operDate, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
			Catalog catalog, Contractor contractor, BigDecimal quantity);
	
	/**
	 * �������� ��� ��������
	 * 
	 * @param remnVal
	 * 			- ��������� ��������� �� ���
	 */
	void evaluateOutCost(RemnVal remnVal);
	
	/**
	 * �������� ��� ��������
	 * 
	 * @param remnValId
	 * 			- ������������� ��������� ��������� �� ���
	 */
	void evaluateOutCost(Integer remnValId);

	/**
	 * ��������� ������������ ���������� ��� �� �������� ���� �� �������� �� ���
	 * 
	 * @param operDate
	 * 				- ���� ��� ������� ���� ��������
	 * @param acc
	 * 				- ���� ������
	 * @param anl1
	 * 				- ��������� 1-�� ������ ����� �������
	 * @param anl2
	 * 				- ��������� 2-�� ������ ����� �������
	 * @param anl3
	 * 				- ��������� 3-�� ������ ����� �������
	 * @param anl4
	 * 				- ��������� 4-�� ������ ����� �������
	 * @param anl5
	 * 				- ��������� 5-�� ������ ����� �������
	 * @param catalog
	 * 				- �����
	 * @param contractor
	 * 				- ����������
	 * @return
	 * 				- ���������� ��� �� �������� ���� �� �������� �� ���
	 */
	BigDecimal calculateQuantityEnd(Date operDate, AccBatch accBatch, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
			Catalog catalog, Contractor contractor);
	
}
