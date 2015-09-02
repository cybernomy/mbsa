/*
 * DocFlowManager.java
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
package com.mg.merp.docflow;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Local;

import com.mg.framework.api.BusinessObjectService;
import com.mg.merp.document.model.DocHead;

/**
 * ������ �������� ����������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowManager.java,v 1.6 2008/07/31 14:21:03 safonov Exp $
 */
@Local
public interface DocFlowManager extends BusinessObjectService {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/docflow/DocFlowManager";

	/**
	 * ����������������� ID ����� �������� ���������
	 */
	final int DOC_FLOW_CREATE_DOC = 1;
	
	/**
	 * ����������������� ID ����� �������� ��������� �� ������
	 */
	final int DOC_FLOW_BASED_ON = 2;

	/**
	 * ����������������� ID ����� �������� ��������� � ������� BAi (Business Add-in)
	 */
	final int CREATE_DOC_BY_BAI = 50;
	
	/**
	 * ����������������� ID ����� ���������� ��, ����� ����������� ������� ����� �� �����
	 * ��������� �����������
	 */
	final int DOC_FLOW_END = 60;
	
	/**
	 * ���������� ��������� �������� ��
	 */
	final int DOCFLOW_OWNER = 0x10;
	
	/**
	 * ��������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @throws AlreadyCompletedException	���� �� ��������
	 * @throws DocumentNotFound				���� �������� �� ������
	 */
	void execute(Serializable documentId, final DocFlowListener listener)
			throws AlreadyCompletedException, DocumentNotFound;

	/**
	 * ��������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param paramsStrategy	��������� ��������� ���������� ��
	 * @throws AlreadyCompletedException	���� �� ��������
	 * @throws DocumentNotFound	���� �������� �� ������
	 */
	void execute(Serializable documentId, final DocFlowListener listener, final DocFlowParamsStrategy paramsStrategy)
			throws AlreadyCompletedException, DocumentNotFound;
	
	/**
	 * ��������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param requester		��������� ����������
	 * @param silent		������� ������������� ����������������� ����������, ���� <code>true</code> �� �� ������������ UI
	 * @throws AlreadyCompletedException	���� �� ��������
	 * @throws DocumentNotFound				���� �������� �� ������
	 */
	void execute(Serializable documentId, final DocFlowListener listener, int requester, boolean silent)
			throws AlreadyCompletedException, DocumentNotFound;

	/**
	 * ��������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param stageCode		��� ����� �� ��� ����������
	 * @throws AlreadyCompletedException	���� �� ��������
	 * @throws DocumentNotFound	���� �������� �� ������
	 * @throws InaccesibleStateException	���� ��������� ��� �� �������� ��� ����������
	 */
	void execute(Serializable documentId, final DocFlowListener listener, String stageCode)
			throws AlreadyCompletedException, DocumentNotFound, InaccesibleStateException;
	
	/**
	 * ��������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param stageCode		��� ����� �� ��� ����������
	 * @param processDate	���� ��������� ��
	 * @param requester		��������� ����������
	 * @param silent		������� ������������� ����������������� ����������, ���� <code>true</code> �� �� ������������ UI
	 * @throws AlreadyCompletedException	���� �� ��������
	 * @throws DocumentNotFound	���� �������� �� ������
	 * @throws InaccesibleStateException	���� ��������� ��� �� �������� ��� ����������
	 */
	void execute(Serializable documentId, final DocFlowListener listener, String stageCode, Date processDate, int requester, boolean silent)
			throws AlreadyCompletedException, DocumentNotFound, InaccesibleStateException;

	/**
	 * �������� ���������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 */
	void rollback(Serializable documentId, final DocFlowListener listener);
	
	/**
	 * �������� ���������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param paramsStrategy	��������� ��������� ���������� ��
	 */
	void rollback(Serializable documentId, final DocFlowListener listener, final DocFlowParamsStrategy paramsStrategy);
	
	/**
	 * �������� ���������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param requester		��������� ����������
	 * @param silent		������� ������������� ����������������� ����������, ���� <code>true</code> �� �� ������������ UI
	 */
	void rollback(Serializable documentId, final DocFlowListener listener, int requester, boolean silent);
	
	/**
	 * 
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param stageCode		��� ����� �� ��� ����������
	 */
	void rollback(Serializable documentId, final DocFlowListener listener, String stageCode);
	
	/**
	 * �������� ���������� ��
	 * 
	 * @param documentId	������������� ���������
	 * @param listener		��������� ��
	 * @param stageCode		��� ����� �� ��� ����������
	 * @param requester		��������� ����������
	 * @param silent		������� ������������� ����������������� ����������, ���� <code>true</code> �� �� ������������ UI
	 */
	void rollback(Serializable documentId, final DocFlowListener listener, String stageCode, int requester, boolean silent);
	
	/**
	 * ����������� �������� ���������
	 * 
	 * @param docHead	������ ��������
	 */
	void createDocument(DocHead docHead);
	
	/**
	 * ����������� ��������� ���������
	 * 
	 * @param docHead	������ ��������
	 */
	void modifyDocument(DocHead docHead);

	/**
	 * �������� ����������� ��������� ���������
	 * 
	 * @param docHead	������ ��������
	 */
	void checkStatus(DocHead docHead);

}
