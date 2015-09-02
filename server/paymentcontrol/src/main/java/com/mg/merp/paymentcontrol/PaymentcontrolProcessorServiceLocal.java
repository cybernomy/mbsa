/*
 * PaymentcontrolProcessorServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ������ ���������� ������ "��������� ���������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentcontrolProcessorServiceLocal.java,v 1.2 2007/06/01 07:14:11 sharapov Exp $
 */
public interface PaymentcontrolProcessorServiceLocal extends BusinessObjectService {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/PaymentcontrolProcessor"; //$NON-NLS-1$
	
	/**
	 * ���� �� "������� ������ ������� ������������"
	 * @param docFlowParams - ��������� ����������������
	 * @param processorListener - ��������� ����������
	 */
	void createLiability(DocFlowPluginInvokeParams docFlowParams, PaymentControlProcessorListener processorListener)  throws Exception;
	
	/**
	 * ����� ����� �� "������� ������ ������� ������������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void rollBackCreateLiability(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * ���� �� "������������� ���������� �������������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void confirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * ����� ����� �� "������������� ���������� �������������"
	 * @param docFlowParams - ��������� ����������������
	 */
	void rollBackConfirmExecutionByDocument(DocFlowPluginInvokeParams docFlowParams);
	
}
