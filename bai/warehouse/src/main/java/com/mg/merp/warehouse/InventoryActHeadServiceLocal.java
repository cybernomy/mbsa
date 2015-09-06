/*
 * InventoryActHeadServiceLocal.java
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

import com.mg.framework.api.orm.Criteria;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.model.InventoryActCommission;
import com.mg.merp.warehouse.model.InventoryActHead;
import com.mg.merp.warehouse.model.InventoryActSpec;
import com.mg.merp.warehouse.model.InventoryActSpecDifferencesResult;
import com.mg.merp.warehouse.support.InventoryParametrs;

import java.util.Date;

/**
 * Сервис бизнес-компонента "Акт инвентаризации"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InventoryActHeadServiceLocal.java,v 1.8 2007/11/19 13:33:20 alikaev Exp $
 */
public interface InventoryActHeadServiceLocal extends com.mg.merp.document.GoodsDocument<InventoryActHead, Integer, DocumentPattern, InventoryActSpecServiceLocal> {

  /**
   * Тип папки инвентаризационного акта
   */
  final static short FOLDER_PART = 34;

  /**
   * Раздел инвентаризационного акта
   */
  final static short DOCSECTION = 18;

  /**
   * Выполнить инвентаризацию склада
   *
   * @param invActHead     - акт инвентаризации
   * @param endDate        - дата инвентаризации
   * @param stock          - склад
   * @param mol            - МОЛ
   * @param beginCode      - начало диапазона кодов позиций каталога
   * @param endCode        - конец диапазона кодов позиций каталога
   * @param stockKind      - правило формирования строк по партиям: 0 - одна строка ТМЦ по одной
   *                       цене прихода; 1 - одна строка ТМЦ по всем ценам прихода;
   * @param isIncludeEmpty - признак включения позиций с нулевым остатком
   */

  void executeStockInventory(InventoryActHead invActHead, Date endDate, OrgUnit stock, Contractor mol, String beginCode, String endCode, short stockKind, boolean isIncludeEmpty);

  /**
   * Выполнить инвентаризацию склада <p>Пример формирования <code>criteria</code>:
   * <pre>
   * 		Criteria criteria = OrmTemplate.createCriteria(StockBatch.class)
   * 				.createAlias("StockCard", "sc", JoinType.INNER_JOIN)
   * 				.createAlias("sc.Catalog", "cat", JoinType.INNER_JOIN)
   * 				.add(Restrictions.eq("sc.Stock", document.getSrcStock()));
   * </pre>
   *
   * @param invActHead - акт инвентаризации
   * @param param      - параметры инвентаризации
   * @param criteria   - критерий отбора партии: обязательно в критерии указать склад для отбора
   *                   КСУ
   */
  void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params, Criteria criteria);

  /**
   * Выполнить инвентаризацию склада
   *
   * @param invActHead - акт инвентаризации
   * @param param      - параметры инвентаризации
   */
  void executeStockInventory(InventoryActHead invActHead, InventoryParametrs params);

  /**
   * Рассчитать сумму относительно количества
   *
   * @param inventoryActSpec - позиция спецификации акта инвентаризации
   * @return результат
   */
  InventoryActSpecDifferencesResult computeDifferenceByQuantity(InventoryActSpec inventoryActSpec);

  /**
   * Рассчитать количество относительно суммы
   *
   * @param inventoryActSpec - позиция спецификации акта инвентаризации
   * @return результат
   */
  InventoryActSpecDifferencesResult computeDifferenceBySum(InventoryActSpec inventoryActSpec);

  /**
   * Рассчитать суммы излишков/недостачи акта инвентаризации
   *
   * @param inventoryActHead - акт инвентаризации
   */
  void computeShortageAndExsessSum(InventoryActHead inventoryActHead);

  /**
   * удалить члена комиссии
   *
   * @param commLink связь
   */
  void excludeInvCommision(InventoryActCommission commLink);

  /**
   * добавить члена комиссии
   *
   * @param invActHead акт инвентаризации
   * @param empl       сотрудник
   * @return связь
   */
  InventoryActCommission includeInvCommision(InventoryActHead invActHead, Contractor empl);

}
