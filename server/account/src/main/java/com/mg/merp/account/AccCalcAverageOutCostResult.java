/*
 * AccCalcAverageOutCostResult.java
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

/**
 * Класс для хранения результата процедуры {@link #accCalcAverageOutCost()}
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccCalcAverageOutCostResult.java,v 1.1 2008/03/25 14:49:51 alikaev Exp $
 */
public class AccCalcAverageOutCostResult implements Serializable {

	private BigDecimal costNat;
	
	private BigDecimal costCur;
	
	private BigDecimal summaNat;
	
	private BigDecimal summaCur;
	
	private BigDecimal realQuan;

	public AccCalcAverageOutCostResult(BigDecimal costNat, BigDecimal costCur, BigDecimal summaNat, BigDecimal summaCur, BigDecimal realQuan) {
		this.costNat = costNat;
		this.costCur = costCur;
		this.summaNat = summaNat;
		this.summaCur = summaCur;
		this.realQuan = realQuan;
	}

	/**
	 * Цена в НДЕ
	 * @return costNat
	 */
	public BigDecimal getCostNat() {
		return costNat;
	}

	/**
	 * Цена в валюте
	 * @return costCur
	 */
	public BigDecimal getCostCur() {
		return costCur;
	}

	/**
	 * Сумма в НДЕ
	 * @return summaNat
	 */
	public BigDecimal getSummaNat() {
		return summaNat;
	}

	/**
	 * Сумма в валюте
	 * @return summaCur
	 */
	public BigDecimal getSummaCur() {
		return summaCur;
	}

	/**
	 * Количество
	 * @return realQuan
	 */
	public BigDecimal getRealQuan() {
		return realQuan;
	}
	
}
