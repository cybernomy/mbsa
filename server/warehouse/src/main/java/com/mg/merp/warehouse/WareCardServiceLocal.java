/*
 * WareCardServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.warehouse.model.StockCard;

/**
 * Бизнес-компонент "Карточка складского учета"
 *
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: WareCardServiceLocal.java,v 1.6 2008/04/18 15:15:53 safonov Exp $
 */
public interface WareCardServiceLocal extends
    com.mg.framework.api.DataBusinessObjectService<StockCard, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/warehouse/WareCard";

  /**
   * Поиск КСУ
   *
   * @param warehouse     склад
   * @param mol           МОЛ
   * @param catalog       позиция каталога
   * @param onlyAvailable искать только доступные для текущего пользователя
   * @return КСУ или <code>null</code> если не найдена
   */
  StockCard findStockCard(Contractor warehouse, Contractor mol,
                          Catalog catalog, boolean onlyAvailable);

  /**
   * Поиск КСУ без учета МОЛ
   *
   * @param warehouse     склад
   * @param catalog       позиция каталога
   * @param onlyAvailable искать только доступные для текущего пользователя
   * @return КСУ или <code>null</code> если не найдена
   */
  StockCard findStockCard(Contractor warehouse, Catalog catalog, boolean onlyAvailable);

  /**
   * Удаление КСУ, на которые нет ссылок
   *
   * @param sctockCards список КСУ
   */
  void deleteStockCards(StockCard... sctockCards);
}
