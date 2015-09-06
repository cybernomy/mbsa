/*
 * LiabilityServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.paymentcontrol.model.Liability;

import java.math.BigDecimal;

/**
 * Сервис бизнес-компонента "Реестр обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityServiceLocal.java,v 1.5 2007/06/04 05:06:19 sharapov Exp $
 */
public interface LiabilityServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Liability, Integer> {

  /**
   * локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/paymentcontrol/Liability"; //$NON-NLS-1$

  /**
   * тип папки для реестра обязательств
   */
  final static short FOLDER_PART = 13401;

  /**
   * Создание обязательства по образцу
   *
   * @param pattern - образец
   * @param folder  - папка-приемник
   * @return обязательство
   */
  Liability createByPattern(Liability pattern, Folder folder);

  /**
   * Получить исполненную сумму
   *
   * @param liabilityId - идентификатор обязательства
   * @param versionId   - идентификатор версии планирования
   * @return сумма
   */
  BigDecimal getExecutedSum(Integer liabilityId, Integer versionId);

  /**
   * Получить неисполненный остаток обязательства
   *
   * @param liabilityId - иденификатор обязательства
   * @param versionId   - иденификатор версии планирования
   * @return неисполненный остаток
   */
  BigDecimal getRemnSum(Integer liabilityId, Integer versionId);

  /**
   * Получить корневую папку Реестра обязательств
   *
   * @return корневая папка Реестра обязательств
   */
  Folder getRootFolder();

  /**
   * Найти документ (для просмотра)
   *
   * @param liability - обязательство
   * @return документ
   */
  DocHead findDoc(Liability liability);

  /**
   * Найти документ-основание (для просмотра)
   *
   * @param liability - обязательство
   * @return документ-основание
   */
  DocHead findBaseDoc(Liability liability);

  /**
   * Найти контракт (для просмотра)
   *
   * @param liability - обязательство
   * @return контракт
   */
  DocHead findContract(Liability liability);

}
