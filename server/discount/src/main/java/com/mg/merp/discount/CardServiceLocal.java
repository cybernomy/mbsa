/*
 * CardServiceLocal.java
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
package com.mg.merp.discount;

import com.mg.merp.discount.model.Card;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Бизнес-компонент "Дисконтные карты"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardServiceLocal.java,v 1.4 2007/10/05 07:21:13 sharapov Exp $
 */
public interface CardServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Card, Integer> {

  /**
   * Имя сервиса
   */
  final static String SERVICE_NAME = "merp/discount/Card"; //$NON-NLS-1$

  /**
   * тип папки для дисконтных карт
   */
  final static short FOLDER_PART = 13100;

  /**
   * Получить скидку из истории дисконтной карты на дату
   *
   * @param disCard    - дисконтная карта
   * @param actualDate - дата актульности
   * @return скидка из истории дисконтной карты на дату
   */
  BigDecimal getDiscountFromHistory(Card disCard, Date actualDate);

  com.mg.framework.api.AttributeMap getCardByOwner(int ownerId);

  int[] getCardsByContractor(int contractorId);

}
