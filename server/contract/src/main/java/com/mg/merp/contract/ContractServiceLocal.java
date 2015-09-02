/*
 * ContractServiceLocal.java
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
package com.mg.merp.contract;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.merp.contract.model.Contract;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;

/**
 * ������ ������-���������� "���������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractServiceLocal.java,v 1.6 2007/03/29 09:23:39 safonov Exp $
 */
public interface ContractServiceLocal extends com.mg.merp.document.Document<Contract, Integer, DocumentPattern> {
	
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/contract/Contract"; //$NON-NLS-1$
	
	/**
	 * ��� ����� ��� ����������
	 */
	final static short FOLDER_PART = 35;

	/**
	 * ������ ����������
	 */
	final static short DOCSECTION = 19;

	/**
	 * ����� �������� �� �������� ����������
	 * @param contractType - ��� ���������
	 * @param contractDate - ���� ���������
	 * @param contractNumber - ����� ���������
	 * @return �������� (NULL - ���� �� ������) 
	 */
	DocHead findByParams(DocType contractType, Date contractDate, String contractNumber);
	
	/**
	 * �������� �������
	 * @param shippedPaymentSum - ����� �������� �����������
	 * @param receivedGoodSum - ����� ��� � ����� �� �����������
	 * @param receivedPaymentSum - ����� �������� �� ����������� 
	 * @param shippedGoodSum - ����� ��� � ����� �� �����������
	 * @return �������
	 */
	BigDecimal calculateRestSum(BigDecimal shippedPaymentSum, BigDecimal receivedGoodSum, BigDecimal receivedPaymentSum, BigDecimal shippedGoodSum);
	
	/**
	 * �������� ����� �� ��������� (�� ����� �������)
	 * @param contract - ��������
	 * @return ����� �� ���������
	 */
	BigDecimal[] calculateTotalContractSum(DocHead contract);
	
	/**
	 * �������� ����� �� ����� (�� ����� �������)
	 * @param contract - ��������
	 * @return ����� �� �����
	 */
	BigDecimal[] calculateTotalPlanSum(DocHead contract);
	
	/**
	 * �������� ����� �� ����� (�� ����� �������)
	 * @param contract - ��������
	 * @return ����� �� �����
	 */
	BigDecimal[] calculateTotalFactSum(DocHead contract);
	
}
