/*
 * TaxRecord.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.support;

import java.util.Date;

/**
 * Запись о налоге
 * 
 * @see SalaryHelper#getTaxByIncome(String, Date, int, double)
 * @see SalaryHelper#getTaxByNumber(String, Date, int, int)
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class TaxRecord {
	private final int rateNumber;
	private final double minIncome;
	private final double maxIncome;
	private final double percent;
	private final double constValue;
	private final double privilegeRatio;
	
	public TaxRecord(int rateNumber, double minIncome, double maxIncome, double percent, double constValue, double privilegeRatio) {
		this.rateNumber = rateNumber;
		this.minIncome = minIncome;
		this.maxIncome = maxIncome;
		this.percent = percent;
		this.constValue = constValue;
		this.privilegeRatio = privilegeRatio;
	}

	/**
	 * 
	 * @return фиксированная сумма
	 */
	public double getConstValue() {
		return constValue;
	}

	/**
	 * 
	 * @return максимальный доход, до которого действует данная ставка
	 */
	public double getMaxIncome() {
		return maxIncome;
	}

	/**
	 * 
	 * @return минимальный доход, начиная с которого действует данная ставка
	 */
	public double getMinIncome() {
		return minIncome;
	}

	/**
	 * 
	 * @return процент ставки
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * 
	 * @return коэффициент для льгот
	 */
	public double getPrivilegeRatio() {
		return privilegeRatio;
	}

	/**
	 * 
	 * @return номер ставки
	 */
	public int getRateNumber() {
		return rateNumber;
	}
}
