/*
 * Processor.java
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

import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.RemnVal;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.CreateDocumentDocFlowListener;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Бизнес - компонент "Процессор хозяйственной операции"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: Processor.java,v 1.3 2008/05/08 09:06:56 alikaev Exp $
 */
public interface Processor {

  /**
   * Отработка этапа документооборота "Создать ХО"
   *
   * @param params   - параметры документооборота
   * @param listener - слушатель на событие открыть созданную ХО
   */
  void processCreateEconomicOper(DocFlowPluginInvokeParams params, CreateDocumentDocFlowListener listener);

  /**
   * Откат этапа документооборота "Создать ХО"
   *
   * @param params - параметры документооборота
   */
  void rollbackCreateEconomicOper(DocFlowPluginInvokeParams params);

  /**
   * Расчет цены списания по бух.учету
   *
   * @param operDate   - дата для расчета цены списания
   * @param accBatch   - бугалтерская партия
   * @param acc        - счет кредит
   * @param anl1       - аналитика 1-го уровня счета кредита
   * @param anl2       - аналитика 2-го уровня счета кредита
   * @param anl3       - аналитика 3-го уровня счета кредита
   * @param anl4       - аналитика 4-го уровня счета кредита
   * @param anl5       - аналитика 5-го уровня счета кредита
   * @param catalog    - товар
   * @param contractor - контрагент
   * @param quantity   - количество
   */
  CalculateOutCostResult calculateOutCost(Date operDate, AccBatch accBatch, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
                                          Catalog catalog, Contractor contractor, BigDecimal quantity);

  /**
   * Расчет сумм списания
   *
   * @param operDate - дата для расчета цены списания
   * @param accBatch - бугалтерская партия
   * @param quantity - количество
   * @param summaNat - сумма в НДЕ
   * @param summaCur - сумма в валюте
   */
  AccCheckLastBatchResult accCheckLastBatch(Date operDate, AccBatch accBatch, BigDecimal quantity, BigDecimal summaNat, BigDecimal summaCur);

  /**
   * Рассчитывает цену списания для средних цен
   *
   * @param operDate   - дата для расчета цены списания
   * @param acc        - счет кредит
   * @param anl1       - аналитика 1-го уровня счета кредита
   * @param anl2       - аналитика 2-го уровня счета кредита
   * @param anl3       - аналитика 3-го уровня счета кредита
   * @param anl4       - аналитика 4-го уровня счета кредита
   * @param anl5       - аналитика 5-го уровня счета кредита
   * @param catalog    - товар
   * @param contractor - контрагент
   * @param quantity   - количество
   */
  AccCalcAverageOutCostResult accCalcAverageOutCost(Date operDate, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
                                                    Catalog catalog, Contractor contractor, BigDecimal quantity);

  /**
   * Пересчет цен списания
   *
   * @param remnVal - оборотная ведомость по ТМЦ
   */
  void evaluateOutCost(RemnVal remnVal);

  /**
   * Пересчет цен списания
   *
   * @param remnValId - идентификатор оборотной ведомости по ТМЦ
   */
  void evaluateOutCost(Integer remnValId);

  /**
   * Процедура рассчитывает количество ТМЦ на заданную дату по оборотке по ТМЦ
   *
   * @param operDate   - дата для расчета цены списания
   * @param acc        - счет кредит
   * @param anl1       - аналитика 1-го уровня счета кредита
   * @param anl2       - аналитика 2-го уровня счета кредита
   * @param anl3       - аналитика 3-го уровня счета кредита
   * @param anl4       - аналитика 4-го уровня счета кредита
   * @param anl5       - аналитика 5-го уровня счета кредита
   * @param catalog    - товар
   * @param contractor - контрагент
   * @return - количество ТМЦ на заданную дату по оборотке по ТМЦ
   */
  BigDecimal calculateQuantityEnd(Date operDate, AccBatch accBatch, AccPlan acc, AnlPlan anl1, AnlPlan anl2, AnlPlan anl3, AnlPlan anl4, AnlPlan anl5,
                                  Catalog catalog, Contractor contractor);

}
