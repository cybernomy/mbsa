/*
 * DocFlowPluginFactory.java
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

/**
 * ������� ������������ ������� ������ ���������������� ��� ���������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactory.java,v 1.2 2006/10/21 10:49:00 safonov Exp $
 */
public interface DocFlowPluginFactory {
	
	/**
	 * �������� ������������� ����� ��, ������ ��������� � ��������������� � ��������� ������� �� DP_STAGE_ACTION
	 * 
	 * @return	�������������
	 */
	int getIdentifier();
	
	/**
	 * ������������ �������
	 * 
	 * @return	������������
	 */
	String getName();
	
	/**
	 * �������� ������������� ������
	 * 
	 * @return	������������ ������
	 */
	DocFlowPlugin createPlugin();
}
