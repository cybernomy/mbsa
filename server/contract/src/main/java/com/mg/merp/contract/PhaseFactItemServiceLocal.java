/*
 * PhaseFactItemServiceLocal.java
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.FactItemData;
import com.mg.merp.contract.model.ManualDistributionData;
import com.mg.merp.contract.model.PhaseFactItem;
import com.mg.merp.document.model.DocHead;

/**
 * Сервис бизнес-компонента "Фактический пункт контракта"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PhaseFactItemServiceLocal.java,v 1.4 2008/03/11 08:54:50 sharapov Exp $
 */
public interface PhaseFactItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PhaseFactItem, Integer> {

  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/contract/PhaseFactItem"; //$NON-NLS-1$

  /**
   * Рассчитать аттрибуты фактического пункта контракта
   *
   * @param phaseFactItem - фактический пункт контракта
   */
  void adjust(PhaseFactItem phaseFactItem);

  /**
   * Распределить фактичекую сумму автоматически
   *
   * @param phaseFactItem - фактический пункт контракта
   */
  void autoDistributionFactSum(PhaseFactItem phaseFactItem);

  /**
   * Распределить фактичекую сумму вручную
   *
   * @param data       - структура данных для распределения фактичекой суммы
   * @param factItemId - идентификатор фактического пункта контракта
   */
  void manualDistributionFactSum(ManualDistributionData[] data, Integer factItemId);

  /**
   * Аннулировать распределение фактической суммы
   *
   * @param factItemId - идентификатор фактического пункта контракта
   */
  void avoidDistributionFactSum(Integer factItemId);

  /**
   * Создать фактический пункт контракта
   *
   * @param factItemData - данные для создания
   * @return фактический пункт контракта
   */
  PhaseFactItem createContractFactItem(FactItemData factItemData);

  /**
   * Откат создания фактического пункта контракта
   *
   * @param docHead            - заголовок документа содержащего ссылку на контракт
   * @param contractFactItemId - идентификатор фактического пункта контракта
   * @param data               - дополнительный признак
   */
  void rollBackCreateContractFactItem(DocHead docHead, Integer contractFactItemId, Integer data);

}
