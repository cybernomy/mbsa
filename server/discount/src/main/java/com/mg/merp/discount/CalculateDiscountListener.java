/**
 * CalculateDiscountListener.java.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.discount;

import com.mg.merp.discount.support.DiscountResult;

import java.io.Serializable;

/**
 * Слушатель расчета значения скидки/наценки
 *
 * @author Oleg V. Safonov
 * @version $Id: CalculateDiscountListener.java,v 1.2 2007/10/30 13:55:56 sharapov Exp $
 */
public interface CalculateDiscountListener extends Serializable {

  /**
   * расчет выполнен
   *
   * @param value результат расчета скидки/наценки
   */
  void completed(DiscountResult value);

  /**
   * расчет прерван
   */
  void aborted();

}
