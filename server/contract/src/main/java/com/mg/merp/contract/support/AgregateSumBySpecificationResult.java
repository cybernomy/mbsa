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
 * Результат рассчета суммы пункта контракта
 * 
 * @author Artem V. Sharapov
 * @version $Id: AgregateSumBySpecificationResult.java,v 1.1 2008/03/11 09:49:41 sharapov Exp $
 */
public class AgregateSumBySpecificationResult {

	private Integer contractSpecCount;
	private BigDecimal agregateSum;

	
	/**
	 * Создать результат рассчета суммы пункта контракта
	 * @param contractSpecCount - количество позиций спецификации пункта
	 * @param agregateSum - агрегированная сумма спецификации пункта
	 */
	public AgregateSumBySpecificationResult(Integer contractSpecCount, BigDecimal agregateSum) {
		this.contractSpecCount = contractSpecCount;
		this.agregateSum = agregateSum;
	}

	/**
	 * Получить количество позиций спецификации пункта
	 * @return количество позиций спецификации пункта
	 */
	public Integer getContractSpecCount() {
		return this.contractSpecCount;
	}

	/**
	 * Получить агрегированную сумму спецификации пункта
	 * @return агрегированная сумма спецификации пункта
	 */
	public BigDecimal getAgregateSum() {
		return this.agregateSum;
	}

}
