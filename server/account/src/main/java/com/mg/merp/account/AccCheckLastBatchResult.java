/*
 * AccCheckLastBatchResult.java
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
 * Результат выполнения функции {@link #accCheckLastBatch()} сервиса {@link #ProcessorServiceLocal}.
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccCheckLastBatchResult.java,v 1.1 2008/03/25 14:49:51 alikaev Exp $
 */
public class AccCheckLastBatchResult implements Serializable {
	
	private BigDecimal realSummaNat;
	
	private BigDecimal realSummaCur;
	
	/**
	 * Конструктор
	 * @param realSummaNat
	 * 				- сумма в НДЕ
	 * @param realSummaCur
	 * 				- сумма в валюте
	 */
	public AccCheckLastBatchResult(BigDecimal realSummaNat, BigDecimal realSummaCur) {
		this.realSummaNat = realSummaNat;
		this.realSummaCur = realSummaCur;
	}

	/**
	 * Сумма в НДЕ
	 * @return realSummaNat
	 */
	public BigDecimal getRealSummaNat() {
		return realSummaNat;
	}

	/**
	 * Сумма в валюте
	 * @return realSummaCur
	 */
	public BigDecimal getRealSummaCur() {
		return realSummaCur;
	}
	
}
