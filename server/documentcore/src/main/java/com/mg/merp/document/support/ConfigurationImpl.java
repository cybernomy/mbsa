/*
 * ConfigurationImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.document.support;

import com.mg.merp.document.Configuration;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * Базовая реализация конфигурации документов
 *
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationImpl.java,v 1.1 2006/12/12 14:33:09 safonov Exp $
 */
public class ConfigurationImpl implements Configuration {
  private Currency baseCurrency;
  private Currency localCurrency;
  private int currencyScale;
  private CurrencyRateAuthority currencyRateAuthority;
  private CurrencyRateType currencyRateType;

  public ConfigurationImpl(Currency baseCurrency, Currency localCurrency, int currencyScale, CurrencyRateAuthority currencyRateAuthority, CurrencyRateType currencyRateType) {
    super();
    this.baseCurrency = baseCurrency;
    this.localCurrency = localCurrency;
    this.currencyScale = currencyScale;
    this.currencyRateAuthority = currencyRateAuthority;
    this.currencyRateType = currencyRateType;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.Configuration#getBaseCurrency()
   */
  public Currency getBaseCurrency() {
    return baseCurrency;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.Configuration#getCurrencyRateAuthority()
   */
  public CurrencyRateAuthority getCurrencyRateAuthority() {
    return currencyRateAuthority;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.Configuration#getCurrencyRateType()
   */
  public CurrencyRateType getCurrencyRateType() {
    return currencyRateType;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.Configuration#getCurrencyScale()
   */
  public int getCurrencyScale() {
    return currencyScale;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.Configuration#getLocalCurrency()
   */
  public Currency getLocalCurrency() {
    return localCurrency;
  }

}
