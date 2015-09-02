/*
 * PhaseServiceLocal.java
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

import com.mg.merp.contract.model.Phase;
import com.mg.merp.contract.model.PlanAndFactSumsByKindResult;

/**
 * ������ ������-���������� "����� ���������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseServiceLocal.java,v 1.4 2007/07/16 10:15:01 sharapov Exp $
 */
public interface PhaseServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Phase, Integer> {

	/**
	 * ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/contract/Phase"; //$NON-NLS-1$

	/**
	 * �������� ����� ����� ��������� �� ����� �������
	 * @param contractPhase - ���� ���������
	 * @return ����� ����� ��������� �� ����� �������
	 */
	BigDecimal[] calculateTotalPhaseSumByKind(Phase contractPhase);

	/**
	 * �������� �����
	 * @param shippedPayment - ����� �������� �����������
	 * @param receivedPayment - ����� �������� �� �����������
	 * @return �����
	 */
	BigDecimal calculateSum(BigDecimal shippedPayment, BigDecimal receivedPayment);
	
	/**
	 * ������ ����� ����� ���������
	 * @param phase - ���� ���������
	 */
	void adjust(Phase phase);
	
	/**
	 * �������� �������������� �� ����� ������� �������� ���������� � ����������� ����
	 * @param contractPhase - ���� ���������
	 * @return �������������� �� ����� ������� �������� ���������� � ����������� ����
	 */
	PlanAndFactSumsByKindResult getTotalPlanAndFactSumsByKind(Phase contractPhase);
		
	/**
	 * ������������ ���� ���������
	 * @param contractPhase - ���� ��������� 
	 */
	void madeAvoid(Phase contractPhase);

}
