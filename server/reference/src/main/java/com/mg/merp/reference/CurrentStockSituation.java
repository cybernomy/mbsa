/*
 * CurrentStockSituation.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;

import java.io.Serializable;
import java.util.List;


/**
 * Сервис рассчёта количества на складах
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituation.java,v 1.4 2008/09/09 13:33:54 sharapov Exp $
 */
public interface CurrentStockSituation {
  String SERVICE_NAME = "merp:warehouse=CurrentStockSituationService";

  /**
   * Рассчёт количества позиций каталога <code>catalog</code> на складе <code>warehouse</code> с МОЛ
   * <code>mol</code>
   *
   * @param warehouse склад
   * @param mol       МОЛ
   * @param catalog   позиция каталога
   * @return количество на складе или <code>null</code> если на складе нет доступных количеств
   */
  StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
                                    Catalog catalog);

  /**
   * Рассчёт количества позиций каталога <code>catalog</code> на складе <code>warehouse</code> с МОЛ
   * <code>mol</code>
   *
   * @param warehouse     склад
   * @param mol           МОЛ
   * @param catalog       позиция каталога
   * @param onlyAvailable искать только доступные для текущего пользователя
   * @return количество на складе или <code>null</code> если на складе нет доступных количеств
   */
  StockSituationValues getSituation(OrgUnit warehouse, Contractor mol,
                                    Catalog catalog, boolean onlyAvailable);

  /**
   * Рассчёт количества позиций каталога <code>catalog</code> на складе <code>warehouse</code>
   *
   * @param warehouse склад
   * @param catalog   позиция каталога
   * @return количество на складе или <code>null</code> если на складе нет доступных количеств
   */
  StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog);

  /**
   * Рассчёт количества позиций каталога <code>catalog</code> на складе <code>warehouse</code>
   *
   * @param warehouse     склад
   * @param catalog       позиция каталога
   * @param onlyAvailable искать только доступные для текущего пользователя
   * @return количество на складе или <code>null</code> если на складе нет доступных количеств
   */
  StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog, boolean onlyAvailable);

  /**
   * Рассчёт количества на всех складах
   *
   * @return количество на складах
   */
  List<StockSituationValues> getSituation(Catalog catalog);

  /**
   * Рассчёт агрегированного количества позиций каталога на всех складах
   *
   * @param catalogId - идентификатор позиции каталога
   * @return количества на всех складах
   */
  StockSituationValues getAgregateSituation(Integer catalogId);

  /**
   * Рассчёт агрегированного количества позиций каталога на всех складах
   *
   * @param catalog - позиция каталога
   * @return количества на всех складах
   */
  StockSituationValues getAgregateSituation(Catalog catalog);

  /**
   * Вызов формы, отображающей доступное количество на складах для позиции каталога
   * <code>catalog</code>
   *
   * @param catalog позиция каталога
   */
  void showSituationForm(Catalog catalog);

  /**
   * Вызов форм, отображающих доступное количество на складах для позиций каталога
   *
   * @param catalogIds список идентификаторов каталога
   */
  void showSituationForm(Serializable[] catalogIds);

}
