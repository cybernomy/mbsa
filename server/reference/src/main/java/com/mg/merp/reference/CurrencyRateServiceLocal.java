/*
 * CurrencyRateServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRate;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

import java.math.BigDecimal;

/**
 * Бизнес-компонент "Курсы валют"
 *
 * @author leonova
 * @version $Id: CurrencyRateServiceLocal.java,v 1.4 2007/03/27 12:59:36 safonov Exp $
 */
public interface CurrencyRateServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<CurrencyRate, Integer> {

  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/reference/CurrencyRate"; //$NON-NLS-1$

  /**
   * точность курса валют
   */
  static final int DEFAULT_RATE_SCALE = 6;

  /**
   * получить прямой курс валют
   *
   * @param currencyTo    валюта для которой берется курс
   * @param currencyFrom  валюта по отношению к которой берется курс
   * @param rateAuthority источник курса
   * @param rateType      тип курса
   * @param effectiveDate дата курса, если <code>null</code>, то будет использована текущая дата
   * @return курс валюты
   * @throws CurrencyRateNotFoundException если курс не найден
   */
  BigDecimal getCurrencyRate(Currency currencyTo, Currency currencyFrom,
                             CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate) throws CurrencyRateNotFoundException;

  /**
   * получить обратный курс валют
   *
   * @param currencyTo    валюта для которой берется курс
   * @param currencyFrom  валюта по отношению к которой берется курс
   * @param rateAuthority источник курса
   * @param rateType      тип курса
   * @param effectiveDate дата курса, если <code>null</code>, то будет использована текущая дата
   * @return курс валюты
   * @throws CurrencyRateNotFoundException если курс не найден
   */
  BigDecimal getIndirectCurrencyRate(Currency currencyTo, Currency currencyFrom,
                                     CurrencyRateAuthority rateAuthority, CurrencyRateType rateType, java.util.Date effectiveDate) throws CurrencyRateNotFoundException;

}
