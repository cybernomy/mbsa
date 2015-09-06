/*
 * CurrencyServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

import java.math.BigDecimal;

/**
 * Бизнес-компонент "Валюта"
 *
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: CurrencyServiceLocal.java,v 1.8 2008/04/25 13:48:20 safonov Exp $
 */
public interface CurrencyServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Currency, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/reference/Currency";

  /**
   * конвертация валют
   *
   * @param currencyTo         валюта в которую конвертируем
   * @param currencyFrom       валюта из которой конвертируем
   * @param rateAuthority      источник курса валюты
   * @param rateType           тип курса валюты
   * @param effectiveDate      дата конвертации, если <code>null</code>, то будет использована
   *                           текущая дата
   * @param currencyFromAmount сумма для конвертации
   * @return конвертированная сумма
   * @throws CurrencyRateNotFoundException если не найден ни прямой ни обратный курс валют
   */
  BigDecimal conversion(Currency currencyTo, Currency currencyFrom,
                        CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException;

  /**
   * расширенная конвертация валют, возвращает дополнительные параметры по которым была произведена
   * конвертация
   *
   * @param currencyTo         валюта в которую конвертируем
   * @param currencyFrom       валюта из которой конвертируем
   * @param rateAuthority      источник курса валюты
   * @param rateType           тип курса валюты
   * @param effectiveDate      дата конвертации, если <code>null</code>, то будет использована
   *                           текущая дата
   * @param currencyFromAmount сумма для конвертации
   * @return конвертированная сумма и параметры конвертации
   * @throws CurrencyRateNotFoundException если не найден ни прямой ни обратный курс валют
   */
  CurrencyConversionResult conversionEx(Currency currencyTo, Currency currencyFrom,
                                        CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate, BigDecimal currencyFromAmount) throws CurrencyRateNotFoundException;

  /**
   * поиск валюты по коду
   *
   * @param code код валюты
   * @return валюта или <code>null</code> если не найдена
   */
  Currency findByCode(String code);

}
