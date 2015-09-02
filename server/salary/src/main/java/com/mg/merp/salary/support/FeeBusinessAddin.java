/*
 * FeeBusinessAddin.java
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
package com.mg.merp.salary.support;

/**
 * Базовый класс бизнес-расширения расчета начислений/удержаний ЗП.
 * 
 * <p>Класс расширения должен реализовывать следующий метод: <code>protected void doPerform() throws Exception</code>.
 * <p>Метод возвращает результат выполнения расчета начислений/удержаний ЗП.
 * 
 * <p>Пример данного метода:
 * <pre>
 * protected void doPerform() throws Exception {
 *     	BigDecimal base = (BigDecimal) getParam("СТАВКА_ОКЛАДА");
 *		BigDecimal numberOfRates = (BigDecimal) getParam("КОЛ_СТАВОК");
 *		BigDecimal percent = (BigDecimal) getParam("ПРОЦЕНТ");
 *			
 *		CostsAnlResult costsAnls = new CostsAnlResult();
 *		if(isCostsAnlEmpty())
 *			costsAnls = getCostsAnlFromTariff("ОКЛАД_СТАВКА");
 *				
 *		complete(new FeeBAiResult(
 *				base.multiply(numberOfRates).multiply(percent).divide(new BigDecimal(100)),
 *				null,
 *				null,
 *				null,
 *				null,
 *				costsAnls.getCostsAnl1(),
 *				costsAnls.getCostsAnl2(),
 *				costsAnls.getCostsAnl3(),
 *				costsAnls.getCostsAnl4(),
 *				costsAnls.getCostsAnl5()));
 * }
 * </pre>
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeBusinessAddin.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public abstract class FeeBusinessAddin extends SalaryBusinessAddin<FeeBAiResult> {

}
