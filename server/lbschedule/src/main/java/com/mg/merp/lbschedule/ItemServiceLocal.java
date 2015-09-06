/*
 * ItemServiceLocal.java
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
package com.mg.merp.lbschedule;

import com.mg.merp.lbschedule.model.Item;

/**
 * Сервис бизнес-компонента "Пункты графика исполнения обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemServiceLocal.java,v 1.2 2007/04/17 12:48:40 sharapov Exp $
 */
public interface ItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Item, Integer> {

  /**
   * Расчет даты начала пункта
   *
   * @param item - пункт графика
   */
  void computeResultDate(Item item);

  /**
   * Расчет суммы пункта
   *
   * @param item - пункт графика
   */
  void computeResultSum(Item item);

}
