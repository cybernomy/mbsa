/*
 * InventoryServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.Inventory;
import com.mg.merp.account.model.Period;

import java.util.Date;
import java.util.List;

/**
 * Бизнес-компонент "Данные по видам учета"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InventoryServiceLocal.java,v 1.2 2008/04/28 10:09:51 alikaev Exp $
 */
public interface InventoryServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Inventory, Integer> {

  /**
   * Начисление амортизации
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  Integer calcAmortization(List<Inventory> inventories, Short aMonth, Integer batch);

  /**
   * Расчет амортизации линейным методом
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  void calcAmortizationLinear(Inventory inventory, Short aMonth, Integer batch);

  /**
   * Расчет амортизации пропорционально остаточной стоимости
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  void calcAmortizationDeprVal(Inventory inventory, Short aMonth, Integer batch);

  /**
   * Расчет амортизации пропорционально периоду эксплуатации
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  void calcAmortizationPeriod(Inventory inventory, Short aMonth, Integer batch);

  /**
   * Расчет амортизации по продукции
   *
   * @param inventory - данные по видам учета
   * @param aMonth    - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param batch     - служебное поле
   */
  void calcAmortizationProduction(Inventory inventory, Short aMonth, Integer batch);

  /**
   * Переоценка/дооценка
   *
   * @param inventories        - данные по видам учета
   * @param accRevaluateParams - параметры для проведения переоценки
   */
  void revaluate(List<Inventory> inventories, AccRevaluateParams accRevaluateParams);

  /**
   * Отменить последнее действие
   *
   * @param inventories - данные по видам учета
   */
  void rollback(List<Inventory> inventories);

  /**
   * Перемещение
   *
   * @param inventories - инвентарные карточки
   * @param params      - параметры для проведения операции перемещения инвентарных карточек
   */
  void moveInventory(List<Inventory> inventories, AccInventoryMoveParams params);

  /**
   * Списание
   *
   * @param inventories - инвентарные карточки
   * @param params      - параметры для проведения операции списания инвентарных карточек
   */
  void retire(List<Inventory> inventories, AccInventoryRetireParams params);

  /**
   * Консервация
   *
   * @param inventories - инвентарные карточки
   * @param freezeDate  - дата по которую осуществляется консервация инвентарных карточек
   */
  void freeze(List<Inventory> inventories, Date freezeDate);

  /**
   * Формирование остатков
   *
   * @param inventories - инвентарные карточки
   * @param period      - учетный период
   */
  void makeRemains(List<Inventory> inventories, Period period);

}