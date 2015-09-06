/*
 * OrderSpecSupServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.OrderSpec;

/**
 * Бизнес-компонент "Спецификация зазаза поставщика"
 *
 * @author leonova
 * @version $Id: OrderSpecSupServiceLocal.java,v 1.3 2007/07/27 11:59:29 safonov Exp $
 */
public interface OrderSpecSupServiceLocal
    extends com.mg.merp.document.GoodsDocumentSpecification<OrderSpec, Integer> {
  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/warehouse/OrderSpecSup";
}
