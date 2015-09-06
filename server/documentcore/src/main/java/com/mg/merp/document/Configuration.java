/*
 * Configuration.java
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
package com.mg.merp.document;

import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * Конфигурация документов
 *
 * @author Oleg V. Safonov
 * @version $Id: Configuration.java,v 1.1 2006/12/12 14:32:43 safonov Exp $
 */
public interface Configuration {

  /**
   * базовая валюта, используется по умолчанию в качестве валюты документа при создании
   *
   * @return базовая валюта
   */
  Currency getBaseCurrency();

  /**
   * локальная валюта (национальная денежная валюта), используется для создания валютных документов
   * с основной валютой отличной от локальной
   *
   * @return локальная валюта
   */
  Currency getLocalCurrency();

  /**
   * точность расчетов денежных величин документов
   *
   * @return точность (количество знаков после запятой)
   */
  int getCurrencyScale();

  /**
   * базовый тип курса валюты, используется по умолчанию при создании документа
   *
   * @return тип курса валюты
   */
  CurrencyRateType getCurrencyRateType();

  /**
   * базовый источник курса валюты, используется по умолчанию при создании документа
   *
   * @return источник курса валюты
   */
  CurrencyRateAuthority getCurrencyRateAuthority();

}
