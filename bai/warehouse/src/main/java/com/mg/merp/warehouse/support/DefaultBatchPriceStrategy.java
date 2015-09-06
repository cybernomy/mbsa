/* DefaultBatchPriceStrategy.java
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
package com.mg.merp.warehouse.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.warehouse.BatchPriceStrategy;

import java.math.BigDecimal;

/**
 * Стратегия рассчёта цены партии "по-умолчанию"
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: DefaultBatchPriceStrategy.java,v 1.3 2009/01/04 12:33:56 safonov Exp $
 */
public class DefaultBatchPriceStrategy implements BatchPriceStrategy {

  public BigDecimal doCalculate(DocSpec docSpec) {
    // не знаю почему, но во второй версии "Цена" из спецификации
    // закоменчена
    BigDecimal quan = docSpec.getQuantity() == null ? BigDecimal.ONE : docSpec.getQuantity();
    BigDecimal sum = docSpec.getSumma() == null ? BigDecimal.ZERO : docSpec.getSumma();
    return MathUtils.divide(sum, quan, new RoundContext(6));
  }

}
