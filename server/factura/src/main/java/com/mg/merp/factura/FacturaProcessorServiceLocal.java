/*
 * FacturaProcessorServiceLocal.java.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.factura;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ��������� ������-������
 * 
 * @author Artem V. Sharapov
 * @version $Id: FacturaProcessorServiceLocal.java,v 1.2 2009/03/16 14:30:34 sharapov Exp $
 */
public interface FacturaProcessorServiceLocal extends BusinessObjectService {
	
	static final String SERVICE_NAME = "merp/factura/FacturaProcessor";
		
	/**
	 * ��������� ��� "����������� ����-�������"
	 * @param params - ��������� ���
	 */
	void registerFactura(DocFlowPluginInvokeParams params);
	
	/**
	 * �������� ��� "����������� ����-�������"
	 * @param params - ��������� ���
	 */
	void rollBackRegisterFactura(DocFlowPluginInvokeParams params);
	
	
	/**
	 * ��������� ��� "����������� ���� �������������"
	 * @param params - ��������� ���
	 */
	void registerStockDate(DocFlowPluginInvokeParams params);
	
	/**
	 * �������� ��� "����������� ���� �������������"
	 * @param params - ��������� ���
	 */
	void rollBackRegisterStockDate(DocFlowPluginInvokeParams params);
	
	
	/**
	 * ��������� ��� "����������� ���� ������"
	 * @param params - ��������� ���
	 */
	void registerPayDate(DocFlowPluginInvokeParams params);
	
	/**
	 * �������� ��� "����������� ���� ������"
	 * @param params - ��������� ���
	 */
	void rollBackRegisterPayDate(DocFlowPluginInvokeParams params);
	
	
	/**
	 * ��������� ��� "����������� � ����� �������"
	 * @param params - ��������� ���
	 */
	void registerInBuyBook(DocFlowPluginInvokeParams params);
	
	/**
	 * �������� ��� "����������� � ����� �������"
	 * @param params - ��������� ���
	 */
	void rollBackRegisterInBuyBook(DocFlowPluginInvokeParams params);
	
	
	/**
	 * ��������� ��� "����������� � ����� ������"
	 * @param params - ��������� ���
	 */
	void registerInSaleBook(DocFlowPluginInvokeParams params);
	
	/**
	 * �������� ��� "����������� � ����� ������"
	 * @param params - ��������� ���
	 */
	void rollBackRegisterInSaleBook(DocFlowPluginInvokeParams params);
	
}
