/*
 * JobServiceLocal.java
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
package com.mg.merp.manufacture;

import java.util.Date;
import java.util.EnumSet;

import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobStatus;

/**
 * ������-��������� "���"
 * 
 * @author Oleg V. Safonov
 * @version $Id: JobServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface JobServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<Job, Integer>
{

	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/manufacture/Job";
	
	/**
	 * ��� ����� ��� �����-������� �� ������������
	 */
	final static short FOLDER_PART = 12501;

	/**
	 * ����������� ���� � ���
	 * 
	 * @param jobId	������������� ���
	 */
	void copyBOM(int jobId);

	/**
	 * ����������� ���� � ���
	 * 
	 * @param job	���
	 */
	void copyBOM(Job job);

	/**
	 * ��������� ���
	 * 
	 * @param jobId	������������� ���
	 */
	void run(int jobId);

	/**
	 * ���������� ���
	 * 
	 * @param jobId	������������� ���
	 */
	void stop(int jobId);

	/**
	 * ��������� ���
	 * 
	 * @param jobId	������������� ���
	 */
	void complete(int jobId);

	/**
	 * ��������� ���� ������� ����������� �������������
	 * 
	 * @param job	���
	 * @param date	���� �������
	 */
	void updateRollupDateTime(Job job, Date date);

	/**
	 * �������� ������� ���
	 * 
	 * @param job		���
	 * @param jobStatus	��������� ����������� ��������
	 * @throws InvalidJobStatusException ���� ������ ��� �� ������������� ����������� ��������
	 */
	void checkStatus(Job job, EnumSet<JobStatus> jobStatus) throws InvalidJobStatusException;
	
}
