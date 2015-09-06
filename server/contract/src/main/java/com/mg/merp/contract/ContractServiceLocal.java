/*
 * ContractServiceLocal.java
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.Contract;
import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Сервис бизнес-компонента "Контракты"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContractServiceLocal.java,v 1.6 2007/03/29 09:23:39 safonov Exp $
 */
public interface ContractServiceLocal extends com.mg.merp.document.Document<Contract, Integer, DocumentPattern> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/contract/Contract"; //$NON-NLS-1$

  /**
   * тип папки для контрактов
   */
  final static short FOLDER_PART = 35;

  /**
   * раздел контрактов
   */
  final static short DOCSECTION = 19;

  /**
   * Найти контракт по заданным параметрам
   *
   * @param contractType   - тип контракта
   * @param contractDate   - дата контракта
   * @param contractNumber - номер контракта
   * @return контракт (NULL - если не найден)
   */
  DocHead findByParams(DocType contractType, Date contractDate, String contractNumber);

  /**
   * Получить остаток
   *
   * @param shippedPaymentSum  - сумма платежей контрагенту
   * @param receivedGoodSum    - сумма ТМЦ и услуг от контрагента
   * @param receivedPaymentSum - сумма платежей от контрагента
   * @param shippedGoodSum     - сумма ТМЦ и услуг от контрагенту
   * @return остаток
   */
  BigDecimal calculateRestSum(BigDecimal shippedPaymentSum, BigDecimal receivedGoodSum, BigDecimal receivedPaymentSum, BigDecimal shippedGoodSum);

  /**
   * Получить сумму по контракту (по видам пунктов)
   *
   * @param contract - контракт
   * @return сумма по контракту
   */
  BigDecimal[] calculateTotalContractSum(DocHead contract);

  /**
   * Получить сумму по плану (по видам пунктов)
   *
   * @param contract - контракт
   * @return сумма по плану
   */
  BigDecimal[] calculateTotalPlanSum(DocHead contract);

  /**
   * Получить сумму по факту (по видам пунктов)
   *
   * @param contract - контракт
   * @return сумма по факту
   */
  BigDecimal[] calculateTotalFactSum(DocHead contract);

}
