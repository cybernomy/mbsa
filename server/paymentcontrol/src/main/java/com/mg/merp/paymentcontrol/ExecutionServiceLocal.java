/*
 * ExecutionServiceLocal.java
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TransferParams;

/**
 * ������ ������-���������� "���������� ������������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ExecutionServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface ExecutionServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Execution, Integer> {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Execution"; //$NON-NLS-1$
	
	/**
	 * ��� ����� ��� ������� �������
	 */
	final static short FOLDER_TYPE = 13400;

	/**
	 * ��������� �������������
	 * @param liability - �������������
	 * @param pmcResource - �������� �������
	 * @param planDate - ���� ����������
	 * @param sumCur - ����� ����������
	 */
	void executeLiability(Liability liability, PmcResource pmcResource, Date planDate, BigDecimal sumCur);
		
	/**
	 * ��������� �������������
	 * @param resourceId - ������������� �������� ������� 
	 * @param resourceFolderId - ������������� ������ ������� �������
	 * @param liability - �������������
	 * @param versionId - ������������� ������ ������������
	 * @param execDate - ���� ����������
	 * @param sumCur - ����� ����������
	 */
	void executeLiability(Integer resourceId, Integer resourceFolderId, Liability liability, Integer versionId, Date execDate, BigDecimal sumCur);

	/**
	 * ����������� ��������
	 * @param transferParams - ��������� ����������� �������
	 * @param versionId - ������������� ������ ������������
	 */
	void transferResourses(TransferParams transferParams, Integer versionId);
	
	/**
	 * ������������ ���������
	 * @param executionIds - ������ ��������������� ����������
	 * @param date - ����, �� ������� ����������� ���������
	 * @return ����� � ��������� ������������
	 */
	String createDocuments(Serializable[] executionIds, Date date);

	/**
	 * ���������/����� �����������
	 * @param executionIds - ������ ��������������� ����������
	 * @param isApproved - ������� �����������
	 */
	void setApproved(Serializable[] executionIds, boolean isApproved);
	
	/**
	 * �������� ��������� ��������
	 * @param executionId - ������������� ����������
	 */
	void showCreatedDocument(Integer executionId);
	
	/**
	 * ������� ��������� ��������
	 * @param executionIds - ������ ��������������� ����������
	 */
	void deleteCreatedDocument(Serializable[] executionIds);
	
	/**
	 * �������� ����������� ������������� �������� ���������/�������� ��� ��������� "���������� �������������"
	 * @param entity - ������-�������� "���������� �������������"
	 */
	void checkForOperationPossibility(Execution entity);
	
	/**
	 * ��������� ������ ������ ������������
	 * @param versionId - ������������� ������ ������������ 
	 * @param execDate - ����
	 */
	void checkVersionStatus(Integer versionId, Date execDate);

}
