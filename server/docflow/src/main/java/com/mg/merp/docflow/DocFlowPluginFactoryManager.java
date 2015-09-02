/*
 * DocFlowPluginFactoryManager.java
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
package com.mg.merp.docflow;

/**
 * �������� ������ ���������� ������ ��
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryManager.java,v 1.2 2007/01/29 13:55:40 safonov Exp $
 */
public interface DocFlowPluginFactoryManager {
	
	/**
	 * ����������� �������
	 * 
	 * @param pluginFactory	�������
	 */
	void registerPluginFactory(DocFlowPluginFactory pluginFactory);
	
	/**
	 * �������� �������
	 * 
	 * @param pluginFactory	�������
	 */
	void unregisterPluginFactory(DocFlowPluginFactory pluginFactory);
	
	/**
	 * ����� ������� �� ��������������
	 * 
	 * @param identifier	�������������
	 * @return	�������
	 * @throws PluginNotImplementedException	���� ������� �� �������
	 */
	DocFlowPluginFactory findPluginFactory(int identifier) throws PluginNotImplementedException;
}
