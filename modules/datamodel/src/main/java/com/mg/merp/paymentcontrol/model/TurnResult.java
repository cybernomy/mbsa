/*
 * TurnResult.java
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
package com.mg.merp.paymentcontrol.model;

import java.math.BigDecimal;

/**
 * Модель данных строки оборотки плана
 * 
 * @author Artem V. Sharapov
 * @version $Id: TurnResult.java,v 1.1 2007/05/14 05:12:10 sharapov Exp $
 */
public class TurnResult {
	
	private BigDecimal beginSaldo;
	private BigDecimal income;
	private BigDecimal expense;
	private BigDecimal endSaldo;
	
	public TurnResult() {
	}

	public TurnResult(BigDecimal beginSaldo, BigDecimal income, BigDecimal expense, BigDecimal endSaldo) {
		this.beginSaldo = beginSaldo;
		this.income = income;
		this.expense = expense;
		this.endSaldo = endSaldo;
	}

	public BigDecimal getBeginSaldo() {
		return beginSaldo;
	}

	public void setBeginSaldo(BigDecimal beginSaldo) {
		this.beginSaldo = beginSaldo;
	}

	public BigDecimal getEndSaldo() {
		return endSaldo;
	}

	public void setEndSaldo(BigDecimal endSaldo) {
		this.endSaldo = endSaldo;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

}
