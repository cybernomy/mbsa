/**
 * PriceListBusinessAddin.java.java
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
package com.mg.merp.reference.support;

import java.math.BigDecimal;
import java.util.Map;

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.reference.model.PriceListSpec;

/**
 * Базовый класс бизнес-расширения расчета цены прайс-листа. Класс бизнес-расширения должен
 * реализовывать следующий метод <code>protected void doPerform() throws Exception</code>.
 * Метод возвращает расчитанную цену прайс-листа.
 * 
 * <p>Пример данного метода:
 * <pre>
 * protected void doPerform() throws Exception {
 *     //умножить базовую цену на 2
 *     complete(getPriceListSpec().getPrice().multiply(new BigDecimal(2.0)));
 * }
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: PriceListBusinessAddin.java,v 1.1 2007/09/04 12:37:32 safonov Exp $
 */
public abstract class PriceListBusinessAddin extends AbstractBusinessAddin<BigDecimal> {
	/**
	 * имя параметра хранящего спецификацию прайс-листа
	 */
	public final static String PRICE_LIST_SPEC = "priceListSpec";
	
	private PriceListSpec priceListSpec;

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
	 */
	@Override
	protected void extractParams(Map<String, ? extends Object> params) {
		priceListSpec = (PriceListSpec) params.get(PRICE_LIST_SPEC);
	}

	/**
	 * получение позиции спецификации прайс-листа для которой пересчитываются цены
	 * 
	 * @return	позиция спецификации прайс-листа
	 */
	final protected PriceListSpec getPriceListSpec() {
		return priceListSpec;
	}
	
}
