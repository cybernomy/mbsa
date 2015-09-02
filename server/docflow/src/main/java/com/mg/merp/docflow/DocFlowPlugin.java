/*
 * DocFlowPlugin.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow;

import com.mg.merp.docprocess.model.DocHeadState;


/**
 * ������������ ������ ������ ���������������� ��� ���������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPlugin.java,v 1.2 2006/10/21 10:48:22 safonov Exp $
 */
public interface DocFlowPlugin {
	
	/**
	 * ���������������� ��������� �� ������� ������������� ������
	 * 
	 * @param listener	���������
	 */
	void registerListener(DocFlowPluginListener listener);
	
	/**
	 * ������� ��������� �� ������� ������������� ������
	 * 
	 * @param listener	���������
	 */
	void unregisterListener(DocFlowPluginListener listener);
	
	/**
	 * ��������� ���� ��
	 * 
	 * @param params		��������� ��
	 * @throws Exception	��� ����� ��
	 */
	void execute(DocFlowPluginInvokeParams params) throws Exception;
	
	/**
	 * ��������� ����� ���� ��
	 * 
	 * @param params		��������� ��
	 * @throws Exception	��� ����� ��
	 */
	void rollback(DocFlowPluginInvokeParams params) throws Exception;
	
	/**
	 * �������� ��������� ������������� � ���������� ���������� ����� ��
	 * 
	 * @param docHeadState		��������� ����� ��
	 * @return	���������	�������������
	 */
	String getDocActionResultTextRepresentation(DocHeadState docHeadState);
	
	/**
	 * �������� � ���������������� ���������� ��������� ������������ ����� ��.
	 * <strong>����� ������� ������ �������� ������ ���� ������� ������������
	 * �������� �������������.</strong>
	 * 
	 * @param docHeadState		��������� ����� ��
	 */
	void showDocActionResult(DocHeadState docHeadState);
	
}
