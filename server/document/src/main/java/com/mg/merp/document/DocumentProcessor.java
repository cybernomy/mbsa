/* DocumentProcessor.java
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
package com.mg.merp.document;


import com.mg.merp.docflow.DocFlowPluginInvokeParams;

/**
 * ������-��������� "��������� ����������"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: DocumentProcessor.java,v 1.4 2009/02/04 09:33:09 safonov Exp $ 
 */
public interface DocumentProcessor {

	/**
	 * ���� "�������� ��������� �� ��������� ��������"
	 * 
	 * @param params	��������� �����
	 * @param listener	��������� �������� ��������� �� ������ ������ ��
	 */
	void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

	/**
	 * ���� "�������� ��������� �� ��������� ��������"
	 * 
	 * @param params	��������� �����
	 * @param listener	��������� �������� ��������� �� ������ ������ ��
	 * @param createCallback	������ ��������� ������ �������� ���������
	 */
	void processCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener, CreateDocumentBasisOfCallback createCallback);

	/**
	 * ����� ����� "�������� ��������� �� ��������� ��������"
	 * 
	 * @param params
	 *            ���������
	 */
	void rollbackCreateDocumentBasisOf(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

	/**
	 * ���� "�������� ��������� �� �������������"
	 * @param params
	 * 			��������� �����
	 */
	void processCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);
	

	/**
	 * ����� ����� "�������� ��������� �� �������������"
	 * 
	 * @param params
	 *            ���������
	 */
	void rollbackCreateDocOnComponents(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);
}
