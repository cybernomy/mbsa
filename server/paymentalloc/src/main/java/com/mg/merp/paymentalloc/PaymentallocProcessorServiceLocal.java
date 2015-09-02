/*
 * PaymentallocProcessorServiceLocal.java
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
package com.mg.merp.paymentalloc;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ������ ���������� ������ "������ ��������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentallocProcessorServiceLocal.java,v 1.2 2007/05/31 14:08:57 sharapov Exp $
 */
public interface PaymentallocProcessorServiceLocal extends BusinessObjectService {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentalloc/PaymentallocProcessor"; //$NON-NLS-1$
	
	/**
	 * ���� �� "������� ������ � ������� ��������"
	 * @param docFlowParams - ��������� ��
	 * @param processorListener - ��������� ����������
	 */
	void createPayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;
	
	/**
	 * ����� ����� �� "������� ������ � ������� ��������"
	 * @param docFlowParams - ��������� ��
	 */
	void rollBackCreatePayment(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * ���� �� "��������� � ������� ��������"
	 * @param docFlowParams - ��������� ��
	 * @param processorListener - ��������� ����������
	 */
	void allocatePayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;
	
	/**
	 * ����� ����� �� "��������� � ������� ��������"
	 * @param docFlowParams - ��������� ��
	 */
	void rollBackAllocatePayment(DocFlowPluginInvokeParams docFlowParams);
	
	/**
	 * ���� �� "��������� � ������� �������� �������������"
	 * @param docFlowParams - ��������� ��
	 * @param processorListener - ��������� ����������
	 */
	void allocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception;
	
	/**
	 * ����� ����� �� "��������� � ������� �������� �������������"
	 * @param docFlowParams - ��������� ��
	 */
	void rollBackAllocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams);
	
}
