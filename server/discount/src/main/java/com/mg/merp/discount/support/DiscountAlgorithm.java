/*
 * DiscountAlgorithm.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.merp.discount.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс BAi расчета скидок/наценок. Класс алгоритма должен реализовывать следующий метод
 * <code>protected Double doPerform() throws Exception</code> который возвращает вещественное число
 * - абсолютное значение скидки/наценки. Это значение будет прибавлено к цене спецификации для
 * получения итоговой цены.
 *
 * <p>Например, чтобы дать 20% скидку на цену, нужно создать следующий алгоритм:
 * <pre>
 * protected Double doPerform() throws Exception {
 *     complete(getSpecificationParam("Price") * (-0.2));
 * }
 * </pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: DiscountAlgorithm.java,v 1.4 2007/09/07 12:02:18 safonov Exp $
 * @deprecated используйте {@link DiscountBusinessAddin}
 */
@Deprecated
public abstract class DiscountAlgorithm extends AbstractBusinessAddin<Double> {

  /**
   * Возвращает значение, заданное пользователем в поле "Скидка/наценка на документ"
   *
   * @return значение скидки/наценки
   */
  final public double getDiscountOnDoc() throws ApplicationException {
    //return nativeGetDiscountOnDoc(handle);
    //TODO
    return 0;
  }

}
