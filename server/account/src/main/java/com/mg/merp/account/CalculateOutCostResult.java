/*
 * CalculateOutCostResult.java
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
package com.mg.merp.account;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.AccBatchHistory;

/**
 * ��������� ���������� ������� "������ ���� �������� �� ���. �����"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: CalculateOutCostResult.java,v 1.1 2008/03/25 14:49:51 alikaev Exp $
 */
public class CalculateOutCostResult implements Serializable {
	
	private BigDecimal costNat;

	private BigDecimal costCur;
	
	private BigDecimal summaNat;
	
	private BigDecimal summaCur;
	
	private BigDecimal realQuantity;
	
	private AccBatch accBatch;
	
	private AccBatchHistory accBatchHistory;

	/**
	 * �����������
	 * 
	 * @param costNat
	 * 				- ����
	 * @param costCur
	 * 				- ���� � ������
	 * @param summaNat
	 * 				- �����
	 * @param summaCur
	 * 				- ����� � ������
	 * @param realQuantity
	 * 				- ����������
	 * @param accBatch
	 * 				- ������
	 * @param accBatchHistory
	 * 				- ������� ������
	 */
	public CalculateOutCostResult(BigDecimal costNat, BigDecimal costCur, BigDecimal summaNat, BigDecimal summaCur, BigDecimal realQuantity,
			AccBatch accBatch, AccBatchHistory accBatchHistory) {
		this.costNat = costNat;
		this.costCur = costCur;
		this.summaNat = summaNat;
		this.summaCur = summaCur;
		this.realQuantity = realQuantity;
		this.accBatch = accBatch;
		this.accBatchHistory = accBatchHistory;
	}

	/**
	 * ����
	 * @return costNat
	 */
	public BigDecimal getCostNat() {
		return costNat;
	}

	/**
	 * ���� � ������
	 * @return costCur
	 */
	public BigDecimal getCostCur() {
		return costCur;
	}

	/**
	 * �����
	 * @return summaNat
	 */
	public BigDecimal getSummaNat() {
		return summaNat;
	}

	/**
	 * ����� � ������
	 * @return summaCur
	 */
	public BigDecimal getSummaCur() {
		return summaCur;
	}

	/**
	 * ����������
	 * @return realQuantity
	 */
	public BigDecimal getRealQuantity() {
		return realQuantity;
	}

	/**
	 * ������
	 * @return accBatch
	 */
	public AccBatch getAccBatch() {
		return accBatch;
	}

	/**
	 * ������� ������
	 * @return accBatchHistory
	 */
	public AccBatchHistory getAccBatchHistory() {
		return accBatchHistory;
	}
	
}
