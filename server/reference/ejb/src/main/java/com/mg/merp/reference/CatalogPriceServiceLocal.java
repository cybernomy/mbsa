/*
 * CatalogPriceServiceLocal.java
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

import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.Currency;

import java.util.Date;

/**
 * Бизнес-компонент "Нормативная цена"
 *
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: CatalogPriceServiceLocal.java,v 1.2 2007/07/27 12:08:16 safonov Exp $
 */
public interface CatalogPriceServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CatalogPrice, Integer> {
  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/reference/CatalogPrice";

  /**
   * поиск актуальной нормативной цены
   *
   * @param actualityDate дата актуальности
   * @param catalog       позиция каталога
   * @param currency      валюта
   * @return нормативная цена
   */
  CatalogPrice findActual(Date actualityDate, Catalog catalog, Currency currency);

}
