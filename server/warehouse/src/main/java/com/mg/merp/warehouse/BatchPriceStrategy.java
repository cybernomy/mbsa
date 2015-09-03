/* BatchPriceStrategy.java
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
package com.mg.merp.warehouse;

import java.math.BigDecimal;

import com.mg.merp.document.model.DocSpec;

/**
 * Интерфейс стратегии рассчёта цены складской партии
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: BatchPriceStrategy.java,v 1.1 2007/02/22 09:44:57 poroxnenko Exp $
 */
public interface BatchPriceStrategy {

	/**
	 * Метод рассчитывает цену складской партии
	 * 
	 * @param docSpec
	 *            спецификации документа, на основании которого производится
	 *            рассчёт
	 * @return
	 */
	BigDecimal doCalculate(DocSpec docSpec);
}
