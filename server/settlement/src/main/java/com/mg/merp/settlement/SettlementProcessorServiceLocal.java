/*
 * SettlementProcessorServiceLocal.java
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
package com.mg.merp.settlement;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ������ ���������� ������ "������� � ����������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: SettlementProcessorServiceLocal.java,v 1.2 2007/03/19 15:05:29 sharapov Exp $
 */
public interface SettlementProcessorServiceLocal extends BusinessObjectService {

	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/settlement/SettlementProcessor"; //$NON-NLS-1$

	/**
	 * ���� �� "��������� � ���� �������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void setToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * ����� ����� �� "��������� � ���� �������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void rollBackSetToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * ���� �� "���������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void processInSettlement(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * ����� ����� �� "���������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void rollBackProcessInSettlement(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * ���� �� "����� � ����� �������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void unsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

	/**
	 * ����� ����� �� "����� � ����� �������� � ��������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void rollBackUnsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams);

}
