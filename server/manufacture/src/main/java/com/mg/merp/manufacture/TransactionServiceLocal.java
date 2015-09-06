/*
 * TransactionServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.Transaction;

import java.math.BigDecimal;

/**
 * Бизнес-компонент "Производственные транзакции"
 *
 * @author Oleg V. Safonov
 * @version $Id: TransactionServiceLocal.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
public interface TransactionServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Transaction, Integer> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/Transaction";

  /**
   * загрузить количество списанное по ресурсу
   *
   * @param resourceId идентификатор ресурса
   * @return списанное количество
   */
  BigDecimal getQuantityByResource(int resourceId);
}
