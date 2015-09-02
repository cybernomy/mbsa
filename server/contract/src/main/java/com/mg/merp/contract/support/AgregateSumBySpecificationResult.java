/*
 * AgregateSumBySpecificationResult.java
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
package com.mg.merp.contract.support;

import java.math.BigDecimal;

/**
 * ��������� �������� ����� ������ ���������
 * 
 * @author Artem V. Sharapov
 * @version $Id: AgregateSumBySpecificationResult.java,v 1.1 2008/03/11 09:49:41 sharapov Exp $
 */
public class AgregateSumBySpecificationResult {

	private Integer contractSpecCount;
	private BigDecimal agregateSum;

	
	/**
	 * ������� ��������� �������� ����� ������ ���������
	 * @param contractSpecCount - ���������� ������� ������������ ������
	 * @param agregateSum - �������������� ����� ������������ ������
	 */
	public AgregateSumBySpecificationResult(Integer contractSpecCount, BigDecimal agregateSum) {
		this.contractSpecCount = contractSpecCount;
		this.agregateSum = agregateSum;
	}

	/**
	 * �������� ���������� ������� ������������ ������
	 * @return ���������� ������� ������������ ������
	 */
	public Integer getContractSpecCount() {
		return this.contractSpecCount;
	}

	/**
	 * �������� �������������� ����� ������������ ������
	 * @return �������������� ����� ������������ ������
	 */
	public BigDecimal getAgregateSum() {
		return this.agregateSum;
	}

}
