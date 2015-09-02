/*
 * BusinessProccessManagerServiceLocal.java
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
package com.mg.merp.bpm;

import java.io.InputStream;
import java.util.List;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.mg.framework.api.BusinessObjectService;

/**
 * ������-��������� "�������� ������-���������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: BusinessProccessManagerServiceLocal.java,v 1.1 2008/03/11 08:20:32 sharapov Exp $
 */
public interface BusinessProccessManagerServiceLocal extends BusinessObjectService {
	
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/bpm/BusinessProccessManager"; //$NON-NLS-1$
	
	/**
	 * ��� ������ "��������� ������ �����"
	 */
	static final String LOAD_TASKS_METHOD = "loadTasks"; //$NON-NLS-1$
	
	/**
	 * ��� ������ "��������� ������ ������-���������"
	 */
	static final String LOAD_PROCESSES_METHOD = "loadProcesses"; //$NON-NLS-1$
	
	/**
	 * ��� ������ "���������� ������-�������"
	 */
	static final String DEPLOY_PROCESS_METHOD = "deployProcess"; //$NON-NLS-1$
	
	/**
	 * ��������� ������ �����
	 * @return ������ �����
	 */
	List<TaskInstance> loadTasks();
		
	/**
	 * ��������� ������
	 * @param taskId - ������������� ������
	 */
	void startTask(long taskId);
	
	/**
	 * ��������� ������
	 * @param taskId - ������������� ������
	 */
	void endTask(long taskId);
		
	/**
	 * ��������� ������ ������-���������
	 * @return ������ ������-���������
	 */
	List<ProcessDefinition> loadProcesses();
	
	/**
	 * ���������� ������-�������
	 * @param inputStream - �����
	 */
	void deployProcess(InputStream inputStream);
	
	/**
	 * ������� ������-�������
	 * @param processId - ������������� ������-��������
	 */
	void deleteProcess(long processId);
	
	/**
	 * ������� ��������� ������-��������
	 * @param processId - ������������� ������-��������
	 */
	void createProcessInstance(long processId);
		
}
