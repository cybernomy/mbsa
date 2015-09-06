/*
 * PriceListAccessServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.PriceListHeadRights;

import java.util.List;

/**
 * Бизнес-компонент "Права на заголовки прайс-листов"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListAccessServiceLocal.java,v 1.2 2008/05/13 06:57:27 alikaev Exp $
 */
public interface PriceListAccessServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PriceListHeadRights, Integer> {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/reference/PriceListAccess"; //$NON-NLS-1$

  /**
   * Загрузить список прав пользователя для прайс-листа
   *
   * @param priceListId - идентификатор прайс-листа
   * @return - список прав пользователя для прайс-листа
   */
  List<PriceListAccessResult> loadPriceListPermissions(Integer priceListId);

  /**
   * Установить право доступа для прайс-листа
   *
   * @param permission  - право доступа
   * @param priceListId - идентификатор прайс-листа
   */
  void grantPermission(PriceListAccessResult permission, Integer priceListId);

  /**
   * Отменить право доступа для прас-листа
   *
   * @param permission - право доступа
   */
  void revokePermission(PriceListAccessResult permission);

}
